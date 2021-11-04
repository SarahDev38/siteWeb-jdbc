package com.sarah.siteWeb.servlets;

import java.io.File;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.dao.TableGestionnaireDAO;
import com.sarah.siteWeb.entities.Client;

@WebServlet("/listeClients")
public class ListeClients extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String cheminStockage = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/clients/";
	private static final String ATT_LISTE_CLIENTS = "listeClients";
	private static final String ATT_LISTE_USERS = "listeUtilisateurs";
	private static final String CHAMP_BOUTON_SUPPR = "boutonSupprimer";
	private static final String CHAMP_BOUTON_AFFICHER = "boutonAfficher";
	private static final String VUE_LISTE = "/restreintGestionnaire/listeClients.jsp";
	private static final String URL_REDIRECTION_CLIENT = "http://localhost:8088/siteWeb/informationsClient?client_id=";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;
	@EJB
	private TableClientDAO clientDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		request.setAttribute(ATT_LISTE_USERS, gestionnaireDao.listerGestionnaires());
		this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		request.setAttribute(ATT_LISTE_USERS, gestionnaireDao.listerGestionnaires());
		if (request.getParameter(CHAMP_BOUTON_SUPPR) != null) {
			deleteClientById(request);
		}
		if (request.getParameter(CHAMP_BOUTON_AFFICHER) != null) {
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_AFFICHER)).longValue();
			response.sendRedirect(URL_REDIRECTION_CLIENT + id);
		} else {
			request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
			this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
		}
	}

	private void deleteClientById(HttpServletRequest request) {
		try {
			Client client = clientDao
					.trouverId(Long.valueOf(request.getParameter(CHAMP_BOUTON_SUPPR)).longValue());
			try {
				File fichierAEffacer = new File(cheminStockage + client.getImage().substring(2));
				fichierAEffacer.delete();
			} catch (Exception e) {
			}
			clientDao.supprimer(clientDao.trouverId(Long.parseLong(request.getParameter(CHAMP_BOUTON_SUPPR))));
		} catch (NumberFormatException | DaoException e) {
		}
	}
}
