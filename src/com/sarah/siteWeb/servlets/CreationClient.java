package com.sarah.siteWeb.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.form.FormClient;

@WebServlet("/nouveauClient")
public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM = "form";
	private static final String ATT_CLIENT = "client";
	private static final String VUE_FORM = "/restreintGestionnaire/creerClient.jsp";
	private static final String VUE_INFOS = "/restreintGestionnaire/infosClient.jsp";
	@EJB
	private TableClientDAO clientDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormClient form = new FormClient(clientDao);
		Client client = form.creerClient(request);
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_CLIENT, client);
		try {
			if (form.getErreurs().isEmpty()) {
				this.getServletContext().getRequestDispatcher(VUE_INFOS).forward(request, response);
			} else {
				this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			}
		} catch (Exception e) {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}
