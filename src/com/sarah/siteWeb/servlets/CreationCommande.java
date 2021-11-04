package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.dao.TableCommandeDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.entities.Commande;
import com.sarah.siteWeb.form.FormClient;
import com.sarah.siteWeb.form.FormCommande;

@WebServlet("/nouvelleCommande")
public class CreationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM_CLIENT = "form";
	private static final String ATT_CLIENT = "client";
	private static final String ATT_OLD_CLIENT = "Oldclient";
	private static final String ATT_LISTE_CLIENTS = "listeClients";
	private static final String ATT_COMMANDE = "commande";
	private static final String ATT_DATE = "date";
	private static final String PARAM_CLIENT_ID_FORM = "id";
	private static final String PARAM_CLIENT_ID = "client_id";
	private static final String CHAMP_BOUTON_VALID_CLIENT = "PasserCommande";
	private static final String CHAMP_BOUTON_VALID_COMMANDE = "boutonValider";
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
	private static final String URL_REDIRECTION_INFOS = "http://localhost:8088/siteWeb/informationsCommande?commande_id=";
	private static final String VUE_FORM = "/restreintGestionnaire/creerCommande.jsp";
	@EJB
	private TableClientDAO clientDao;
	@EJB
	private TableCommandeDAO commandeDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client client = getClientFromParameter(request);
		if (client != null) {
			request.setAttribute(ATT_OLD_CLIENT, client);
			request.setAttribute(ATT_CLIENT, client);
		}
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormClient formClient = new FormClient(clientDao);
		Client client = formClient.checkClient_Commande(request);
		FormCommande formCommande = new FormCommande(commandeDao);
		Commande commande = formCommande.getCommandeFromForm(request, new Commande());
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		request.setAttribute(ATT_OLD_CLIENT, getClientById(request));
		request.setAttribute(ATT_FORM_CLIENT, formClient);
		request.setAttribute(ATT_CLIENT, client);
		request.setAttribute(ATT_COMMANDE, commande);

		if (request.getParameter(CHAMP_BOUTON_VALID_CLIENT) != null) {
			DateTime date = new DateTime();
			String formattedDate = date.toString(DateTimeFormat.forPattern(FORMAT_DATE));
			request.setAttribute(ATT_DATE, formattedDate);
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
		if (request.getParameter(CHAMP_BOUTON_VALID_COMMANDE) != null) {
			if (formClient.getErreurs().isEmpty()) {
				client = formClient.creerClient_Commande(request);
				commande = formCommande.creerCommande(request, client);
				response.sendRedirect(URL_REDIRECTION_INFOS + commande.getId());
			} else {
				this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			}
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

	private Client getClientById(HttpServletRequest request) {
		Client client = null;
		try {
			client = clientDao.trouverId(Long.parseLong(request.getParameter(PARAM_CLIENT_ID_FORM)));
		} catch (NumberFormatException | DaoException e1) {
		}
		return client;
	}

}
