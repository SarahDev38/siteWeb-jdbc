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
import com.sarah.siteWeb.entities.Gestionnaire;
import com.sarah.siteWeb.form.FormGestionnaire;

@WebServlet("/modifierGestionnaire")
public class ModificationGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM = "form";
	private static final String ATT_GESTIONNAIRE = "gestionnaire";
	private static final String ATT_LISTE_GESTIONNAIRES = "listeGestionnaires";
	private static final String PARAM_GESTIONNAIRE_ID = "gestionnaire_id";
	private static final String URL_REDIRECTION_AFFICHER_USERS = "http://localhost:8088/siteWeb/listeGestionnaires";
	private static final String URL_REDIRECTION_INFOS = "http://localhost:8088/siteWeb/informationsGestionnaire?gestionnaire_id=";
	private static final String VUE_FORM = "/restreintGestionnaire/modifierGestionnaire.jsp";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gestionnaire gestionnaire = getGestionnaireFromParameter(request);
		if (gestionnaire != null) {
			request.setAttribute(ATT_GESTIONNAIRE, gestionnaire);
			request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_USERS);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormGestionnaire form = new FormGestionnaire(gestionnaireDao);
		Gestionnaire gestionnaire = form.modifierGestionnaire(request);
		if (form.getErreurs().isEmpty()) {
			response.sendRedirect(URL_REDIRECTION_INFOS + gestionnaire.getId());
		} else {
			request.setAttribute(ATT_GESTIONNAIRE, gestionnaire);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}

	private Gestionnaire getGestionnaireFromParameter(HttpServletRequest request) {
		Gestionnaire gestionnaire = null;
		Long idGestionnaire = (long) 0;
		try {
			idGestionnaire = Long.parseLong(request.getParameter(PARAM_GESTIONNAIRE_ID));
		} catch (NumberFormatException e) {
		}
		if (!idGestionnaire.equals((long) 0)) {
			try {
				gestionnaire = gestionnaireDao.trouverId(idGestionnaire);
			} catch (DaoException e) {
			}
		}
		return gestionnaire;
	}

}
