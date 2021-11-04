package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableGestionnaireDAO;

@WebServlet("/listeGestionnaires")
public class ListeGestionnaires extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LISTE_USERS = "listeGestionnaires";
	private static final String CHAMP_BOUTON_SUPPR = "boutonSupprimer";
	private static final String VUE = "/restreintGestionnaire/listeGestionnaires.jsp";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_USERS, gestionnaireDao.listerGestionnaires());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			gestionnaireDao.supprimer(gestionnaireDao.trouverId(Long.valueOf(request.getParameter(CHAMP_BOUTON_SUPPR)).longValue()));
		} catch (NumberFormatException | DaoException e) {
		}
		request.setAttribute(ATT_LISTE_USERS, gestionnaireDao.listerGestionnaires());
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
