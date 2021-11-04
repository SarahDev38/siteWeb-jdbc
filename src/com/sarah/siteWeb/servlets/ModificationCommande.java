package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.dao.TableCommandeDAO;
import com.sarah.siteWeb.dao.TableGestionnaireDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.entities.Commande;
import com.sarah.siteWeb.form.FormClient;
import com.sarah.siteWeb.form.FormCommande;

@WebServlet("/modifierCommande")
public class ModificationCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM_CLIENT = "form";
	private static final String ATT_CLIENT = "client";
	private static final String ATT_OLD_CLIENT = "Oldclient";
	private static final String ATT_OLD_COMMANDE = "Oldcommande";
	private static final String ATT_COMMANDE = "commande";
	private static final String ATT_LISTE_COMMANDES = "listeCommandes";
	private static final String ATT_LISTE_CLIENTS = "listeClients";
	private static final String ATT_LISTE_USERS = "listeGestionnaires";
	private static final String PARAM_CLIENT_ID = "client_id";
	private static final String PARAM_COMMANDE_ID = "commande_id";
	private static final String VUE_FORM = "/restreintGestionnaire/modifierCommande.jsp";
	private static final String VUE_LISTE = "/restreintGestionnaire/listeCommandes.jsp";
	private static final String URL_REDIRECTION_INFOS = "http://localhost:8088/siteWeb/informationsCommande?commande_id=";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;
	@EJB
	private TableClientDAO clientDao;
	@EJB
	private TableCommandeDAO commandeDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Commande commande = getCommandeFromParameter(request);
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		if (commande != null) {
			Client original = commande.getClient();
			Client newClient = getClientFromParameter(request);
			request.setAttribute(ATT_CLIENT, (newClient != null) ? newClient : original);
			request.setAttribute(ATT_OLD_CLIENT, original);
			request.setAttribute(ATT_COMMANDE, commande);
			request.setAttribute(ATT_OLD_COMMANDE, commande);
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		} else {
			request.setAttribute(ATT_LISTE_COMMANDES, commandeDao.listerCommandes());
			request.setAttribute(ATT_LISTE_USERS, gestionnaireDao.listerGestionnaires());
			this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormClient formClient = new FormClient(clientDao);
		Client client = formClient.checkClient_Commande(request);
		FormCommande formCommande = new FormCommande(commandeDao);

		if (formClient.getErreurs().isEmpty()) {
			client = formClient.creerClient_Commande(request);
			Commande commande = formCommande.modifierCommande(request, client);
			response.sendRedirect(URL_REDIRECTION_INFOS + commande.getId());
		} else {
			Commande commande = formCommande.getCommandeAModifierFromForm(request);
			request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
			request.setAttribute(ATT_OLD_CLIENT, commande.getClient() );
			request.setAttribute(ATT_FORM_CLIENT, formClient);
			request.setAttribute(ATT_CLIENT, client);
			request.setAttribute(ATT_COMMANDE, commande);
			request.setAttribute(ATT_OLD_COMMANDE, commandeDao.trouverId(commande.getId()));
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}

	}

	private Commande getCommandeFromParameter(HttpServletRequest request) {
		Commande commande = null;
		Long idCommande = (long) 0;
		try {
			idCommande = Long.parseLong(request.getParameter(PARAM_COMMANDE_ID));
		} catch (NumberFormatException e) {
		}
		if (!idCommande.equals((long) 0)) {
			try {
				commande = commandeDao.trouverId(idCommande);
			} catch (DaoException e) {
			}
		}
		return commande;
	}

	private Client getClientFromParameter(HttpServletRequest request) {
		Client client = null;
		Long idClient = (long) 0;
		try {
			idClient = Long.parseLong(request.getParameter(PARAM_CLIENT_ID));
		} catch (NumberFormatException | DaoException e) {
		}
		if (!idClient.equals((long) 0)) {
			client = clientDao.trouverId(idClient);
		}
		return client;
	}
}
