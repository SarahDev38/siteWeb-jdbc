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
import com.sarah.siteWeb.dao.TableGestionnaireDAO;
import com.sarah.siteWeb.entities.Gestionnaire;
import com.sarah.siteWeb.form.FormGestionnaire;

@WebServlet("/connectionGestionnaire")
public class ConnectionGestionnaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_PSEUDO = "pseudo";
	private static final String ATT_LISTE_GESTIONNAIRES = "listeGestionnaires";
//	private static final String ATT_ERREUR_MDP = "erreurMDP";
	private static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";
	private static final String ATT_INTERVALLE_CONNECTIONS = "intervalleConnections";
	private static final String ATT_URLDEMANDE = "urlDemande";
	private static final String PARAM_USER_ID = "u_id";
	private static final String COOKIE_DERNIERE_CONNECTION = "derniereConnection";
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";
	private static final String CHAMP_MEMOIRE = "memoire";
	private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365; // 1an
	private static final String VUE_INFOS_CONNECTION = "/restreintGestionnaire/infosConnectionGestionnaire.jsp";
	private static final String VUE = "/connectionGestionnaire.jsp";
	@EJB
	private TableGestionnaireDAO gestionnaireDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gestionnaire gestionnaire = getGestionnaireFromParameter(request);
		request.setAttribute(ATT_PSEUDO, (gestionnaire == null) ? "" : gestionnaire.getPseudo());
		try {
			request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
		} catch (DaoException e) {
		}
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormGestionnaire form = new FormGestionnaire(gestionnaireDao);
		Gestionnaire gestionnaire = form.connecterGestionnaire(request);
		HttpSession session = request.getSession();
		String intervalleConnections = getIntervalleConnections(request);
		request.setAttribute(ATT_INTERVALLE_CONNECTIONS, intervalleConnections);

//		if (ValidationMDP(gestionnaire)) {
			try {
				setCookieDerniereConnection(request, response);
				session.setAttribute(ATT_SESSION_GESTIONNAIRE, gestionnaire);
				if (request.getParameter(ATT_URLDEMANDE).equals("")) {
					this.getServletContext().getRequestDispatcher(VUE_INFOS_CONNECTION).forward(request, response);
				} else {
					response.sendRedirect(((String) request.getParameter(ATT_URLDEMANDE)).trim());
				}
			} catch (Exception e) {
			}
//		} else {
//			String messageErreur = "Échec de la connection : mot de passe incorrect.";
//			try {
//				request.setAttribute(ATT_PSEUDO, gestionnaire.getPseudo());
//				request.setAttribute(ATT_LISTE_GESTIONNAIRES, gestionnaireDao.listerGestionnaires());
//				request.setAttribute(ATT_ERREUR_MDP, messageErreur);
//				session.setAttribute(ATT_SESSION_GESTIONNAIRE, null);
//			} catch (DaoException e) {
//			}
//			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
//		}
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

//	private Boolean ValidationMDP(Gestionnaire gestionnaire) {
//		Boolean check = false;
//		try {
//			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
//			passwordEncryptor.setAlgorithm("SHA-256");
//			passwordEncryptor.setPlainDigest(false);
//
//			String passwordEnregistre = gestionnaireDao.trouverPseudo(gestionnaire.getPseudo()).getMotdepasse();
//			check = passwordEncryptor.checkPassword(gestionnaire.getMotdepasse(), passwordEnregistre);
//		} catch (DaoException e) {
//		}
//		return check;
//	}

	private Gestionnaire getGestionnaireFromParameter(HttpServletRequest request) {
		Gestionnaire gestionnaire = null;
		Long idGestionnaire = (long) 0;
		try {
			idGestionnaire = Long.parseLong(request.getParameter(PARAM_USER_ID));
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