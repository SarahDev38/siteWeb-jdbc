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

@WebServlet("/listeCommandes")
public class ListeCommandes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LISTE_COMMANDES = "listeCommandes";
	private static final String ATT_LISTE_CLIENTS = "listeClients";
	private static final String ATT_LISTE_GESTIONNAIRES = "listeGestionnaires";
	private static final String CHAMP_BOUTON_SUPPR = "boutonSupprimer";
	private static final String CHAMP_BOUTON_AFFICHER = "boutonAfficher";
	private static final String VUE_LISTE = "/restreintGestionnaire/listeCommandes.jsp";
	private static final String URL_REDIRECTION_COMMANDE = "http://localhost:8088/siteWeb/informationsCommande?commande_id=";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;
	@EJB
	private TableClientDAO clientDao;
	@EJB
	private TableCommandeDAO commandeDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		request.setAttribute(ATT_LISTE_COMMANDES, commandeDao.listerCommandes());
		request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
		this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
		request.setAttribute(ATT_LISTE_COMMANDES, commandeDao.listerCommandes());
		request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
		if (request.getParameter(CHAMP_BOUTON_SUPPR) != null) {
			deleteCommandeById(request);
		}
		if (request.getParameter(CHAMP_BOUTON_AFFICHER) != null) {
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_AFFICHER)).longValue();
			response.sendRedirect(URL_REDIRECTION_COMMANDE + id);
		} else {
			request.setAttribute(ATT_LISTE_CLIENTS, clientDao.listerClients());
			this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
		}
	}

	private void deleteCommandeById(HttpServletRequest request) {
		try {
			commandeDao.supprimer(commandeDao.trouverId(Long.parseLong(request.getParameter(CHAMP_BOUTON_SUPPR))));
		} catch (NumberFormatException | DaoException e) {
		}
	}
}
