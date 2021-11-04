package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.TableGestionnaireDAO;
import com.sarah.siteWeb.entities.Gestionnaire;
import com.sarah.siteWeb.form.FormGestionnaire;

@WebServlet(urlPatterns = { "/nouveauGestionnaire" })
public class CreationGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_GESTIONNAIRE = "gestionnaire";
	private static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";
	private static final String CHAMP_BOUTON_CONNECTION = "boutonConnection";
	private static final String ATT_FORM = "form";
	private static final String VUE_INFOS = "/restreintGestionnaire/infosGestionnaire.jsp";
	private static final String VUE_FORM = "/creerGestionnaire.jsp";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormGestionnaire form = new FormGestionnaire(gestionnaireDao);
		Gestionnaire gestionnaire = form.creerGestionnaire(request);
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_GESTIONNAIRE, gestionnaire);
		if (request.getParameter(CHAMP_BOUTON_CONNECTION) != null && form.getErreurs().isEmpty()) {
			request.getSession().setAttribute(ATT_SESSION_GESTIONNAIRE, gestionnaire);
		}
		this.getServletContext().getRequestDispatcher(form.getErreurs().isEmpty() ? VUE_INFOS : VUE_FORM)
				.forward(request, response);
	}
}
