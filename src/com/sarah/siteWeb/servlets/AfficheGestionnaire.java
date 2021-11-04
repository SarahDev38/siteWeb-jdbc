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

@WebServlet("/informationsGestionnaire")
public class AfficheGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_GESTIONNAIRE = "gestionnaire";
	private static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";
	private static final String PARAM_GESTIONNAIRE_ID = "gestionnaire_id";
	private static final String URL_REDIRECTION_AFFICHER_GESTIONNAIRES = "http://localhost:8088/siteWeb/listeGestionnaires";
	private static final String VUE = "/restreintGestionnaire/infosGestionnaire.jsp";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gestionnaire gestionnaire = getGestionnaireFromParameter(request);
		if (gestionnaire != null) {
			System.out.println("do get infos gestionnaire id en parametre url");
			request.setAttribute(ATT_GESTIONNAIRE, gestionnaire);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else if (request.getSession().getAttribute(ATT_SESSION_GESTIONNAIRE) != null) {
			System.out.println("do get infos gestionnaire id en parametre session");
			request.setAttribute(ATT_GESTIONNAIRE, request.getSession().getAttribute(ATT_SESSION_GESTIONNAIRE));
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			System.out.println("do get infos gestionnaire -> redirect liste");
			response.sendRedirect(URL_REDIRECTION_AFFICHER_GESTIONNAIRES);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	}

	private Gestionnaire getGestionnaireFromParameter(HttpServletRequest request) {
		Gestionnaire gestionnaire = null;
		Long idUser = (long) 0;
		try {
			idUser = Long.parseLong(request.getParameter(PARAM_GESTIONNAIRE_ID));
		} catch (NumberFormatException e) {
		}
		if (!idUser.equals((long) 0)) {
			try {
				gestionnaire = gestionnaireDao.trouverId(idUser);
			} catch (DaoException e) {
			}
		}
		return gestionnaire;
	}
}
