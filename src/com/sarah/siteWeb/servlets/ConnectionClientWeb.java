package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientWebDAO;
import com.sarah.siteWeb.entities.ClientWeb;
import com.sarah.siteWeb.form.FormClientWeb;

@WebServlet("/connectionClientWeb")
public class ConnectionClientWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM = "form";
	private static final String ATT_SESSION_CLIENT = "sessionClient";
	private static final String ATT_CLIENT = "client";
	private static final String ATT_INTERVALLE_CONNECTIONS = "intervalleConnections";
	private static final String ATT_URLDEMANDE = "urlDemande";
	private static final String COOKIE_DERNIERE_CONNECTION = "derniereConnection";
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
	private static final String CHAMP_MEMOIRE = "memoire";
	private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365; // 1an
	private static final String VUE_INFOS_CONNECTION = "/restreintClientWeb/infosConnectionClientWeb.jsp";
	private static final String VUE = "/connectionClientWeb.jsp";
	@EJB
	private TableClientWebDAO clientWebDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormClientWeb form = new FormClientWeb(clientWebDao);
		ClientWeb client = form.connecterClientWeb(request);
		HttpSession session = request.getSession();
		String intervalleConnections = getIntervalleConnections(request);
		request.setAttribute(ATT_INTERVALLE_CONNECTIONS, intervalleConnections);

		if (form.getErreurs().isEmpty()) {
			try {
				setCookieDerniereConnection(request, response);
				session.setAttribute(ATT_SESSION_CLIENT, client);
				if (request.getParameter(ATT_URLDEMANDE).equals("")) {
					this.getServletContext().getRequestDispatcher(VUE_INFOS_CONNECTION).forward(request, response);
				} else {
					response.sendRedirect(((String) request.getParameter(ATT_URLDEMANDE)).trim());
				}
			} catch (Exception e) {
			}
		} else {
			try {
				request.setAttribute(ATT_CLIENT, client);
				request.setAttribute(ATT_FORM, form);
				session.setAttribute(ATT_SESSION_CLIENT, null);
			} catch (DaoException e) {
			}
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	private String getIntervalleConnections(HttpServletRequest request) {
		String intervalleConnections = null;
		String derniereConnection = getCookieValue(request, COOKIE_DERNIERE_CONNECTION);
		if (derniereConnection != null) {
			DateTime dtCourante = new DateTime();
			DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
			DateTime dtDerniereConnection = formatter.parseDateTime(derniereConnection);
			Period periode = new Period(dtDerniereConnection, dtCourante);
			PeriodFormatter periodFormatter = new PeriodFormatterBuilder().appendYears().appendSuffix(" an ", " ans ")
					.appendMonths().appendSuffix(" mois ").appendDays().appendSuffix(" jour ", " jours ").appendHours()
					.appendSuffix(" heure ", " heures ").appendMinutes().appendSuffix(" minute ", " minutes ")
					.appendSeparator("et ").appendSeconds().appendSuffix(" seconde", " secondes").toFormatter();
			intervalleConnections = periodFormatter.print(periode);
		}
		return intervalleConnections;
	}

	private static String getCookieValue(HttpServletRequest request, String nom) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && nom.equals(cookie.getName())) {
					try {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						System.out.println("erreur dans la lecture du cookie");
					}
				}
			}
		}
		return null;
	}

	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
		Cookie cookie;
		try {
			cookie = new Cookie(nom, URLEncoder.encode(valeur, "UTF-8"));
			cookie.setMaxAge(maxAge);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			System.out.println("erreur dans l'encodage du cookie");
		}
	}

	private void setCookieDerniereConnection(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter(CHAMP_MEMOIRE) != null) {
			DateTime dt = new DateTime();
			DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
			String dateDerniereConnection = formatter.print(dt);
			setCookie(response, COOKIE_DERNIERE_CONNECTION, dateDerniereConnection, COOKIE_MAX_AGE);
		} else {
			setCookie(response, COOKIE_DERNIERE_CONNECTION, "", 0);
		}
	}
}