package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.form.FormClient;

@WebServlet(urlPatterns = "/modifierClient", initParams = {
		@WebInitParam(name = "cheminStockage", value = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/clients/"),
		@WebInitParam(name = "fileTypes", value = "doc;xls;zip") })
@MultipartConfig(location = "C:/Temp/Download", // dossier temp pour copie
		fileSizeThreshold = 1024 * 1024, // >1MB => passage par dossier temp
		maxFileSize = 1024 * 1024 * 5, // 5MB autorisé par fichier
		maxRequestSize = 1024 * 1024 * 5 * 5 // 25MB autorisés pour l'ensemble
)

public class ModificationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM = "form";
	private static final String ATT_CLIENT = "client";
	private static final String ATT_OLD_CLIENT = "Oldclient";
	private static final String ATT_LISTE_CLIENTS = "listeClients";
	private static final String PARAM_CLIENT_ID = "client_id";
	private static final String PARAM_CLIENT_ID_FORM = "id";
	private static final String CHEMIN_STOCKAGE_IMAGE = "cheminStockage";
	private static final String URL_REDIRECTION_AFFICHER_CLIENTS = "http://localhost:8080/siteWeb/listeClients";
	private static final String URL_REDIRECTION_INFOS = "http://localhost:8088/siteWeb/informationsClient?client_id=";
	private static final String VUE_FORM = "/restreintGestionnaire/modifierClient.jsp";
	@EJB
	private TableClientDAO clientDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client client = getClientFromParameter(request);
		if (client != null) {
			request.setAttribute(ATT_OLD_CLIENT, client);
			request.setAttribute(ATT_CLIENT, client);
			request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_CLIENTS);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter(CHEMIN_STOCKAGE_IMAGE);
		FormClient form = new FormClient(clientDao);
		Client client = form.modifierClient(request, chemin);

		if (form.getErreurs().isEmpty()) {
			response.sendRedirect(URL_REDIRECTION_INFOS + client.getId());
		} else {
			try {
				Client clientOrigin = clientDao
						.trouverId(Long.parseLong(request.getParameter(PARAM_CLIENT_ID_FORM)));
				request.setAttribute(ATT_OLD_CLIENT, clientOrigin);
			} catch (NumberFormatException | DaoException e1) {
			}
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_CLIENT, client);
			request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	private Client getClientFromParameter(HttpServletRequest request) {
		Client client = null;
		Long idClient = (long) 0;
		try {
			idClient = Long.parseLong(request.getParameter(PARAM_CLIENT_ID));
		} catch (NumberFormatException e) {
		}
		if (!idClient.equals((long) 0)) {
			try {
				client = clientDao.trouverId(idClient);
			} catch (DaoException e) {
			}
		}
		return client;
	}
}