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

import com.sarah.siteWeb.dao.TableClientWebDAO;
import com.sarah.siteWeb.entities.ClientWeb;
import com.sarah.siteWeb.form.FormClientWeb;

@WebServlet("/nouveauClientWeb")
public class CreationClientWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM = "form";
	private static final String ATT_CLIENT = "client";
	private static final String ATT_DATE = "date";
	private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss";
	private static final String VUE_FORM = "/creerClientWeb.jsp";
	private static final String VUE_INFOS = "/restreintClientWeb/infosClientWeb.jsp";
	@EJB
	private TableClientWebDAO clientWebDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DateTime date = new DateTime();
		String formattedDate = date.toString(DateTimeFormat.forPattern(FORMAT_DATE));
		request.setAttribute(ATT_DATE, formattedDate);
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormClientWeb form = new FormClientWeb(clientWebDao);
		ClientWeb client = form.creerClientWeb(request);
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
