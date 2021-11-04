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
import com.sarah.siteWeb.entities.Commande;

@WebServlet("/informationsCommande")
public class AfficheCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_CLIENT = "client";
	private static final String ATT_COMMANDE = "commande";
	private static final String CHAMP_BOUTON_RETOUR_LISTE = "boutonRetourListe";
	private static final String CHAMP_BOUTON_MODIFIER_COMMANDE = "boutonModifierCommande";
	private static final String PARAM_COMMANDE_ID = "commande_id";
	private static final String VUE = "/restreintGestionnaire/infosCommande.jsp";
	private static final String URL_REDIRECTION_AFFICHER_COMMANDES = "http://localhost:8088/siteWeb/listeCommandes";
	private static final String URL_REDIRECTION_MODIFIER_COMMANDE = "http://localhost:8088/siteWeb/modifierCommande?commande_id=";
	@EJB
	private TableClientDAO clientDao;
	@EJB
	private TableCommandeDAO commandeDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Commande commande = getCommandeFromParameter(request);
		if (commande != null) {
			request.setAttribute(ATT_COMMANDE, commande);
			request.setAttribute(ATT_CLIENT, commande.getClient());
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_COMMANDES);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(CHAMP_BOUTON_RETOUR_LISTE) != null) {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_COMMANDES);
		}
		if (request.getParameter(CHAMP_BOUTON_MODIFIER_COMMANDE) != null) {
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_MODIFIER_COMMANDE)).longValue();
			response.sendRedirect(URL_REDIRECTION_MODIFIER_COMMANDE + id);
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
}
