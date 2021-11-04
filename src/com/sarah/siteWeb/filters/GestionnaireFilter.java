package com.sarah.siteWeb.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter( urlPatterns = "/restreintGestionnaire/*" )
public class GestionnaireFilter implements Filter {
	public static final String ACCES_CONNEXION = "/connection";
	public static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";
	public static final String ATT_URLDEMANDE = "urlDemande";

	public GestionnaireFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String chemin = req.getRequestURI().substring(req.getContextPath().length());
		if (chemin.startsWith("/inc")) {
			chain.doFilter(req, resp);
			return;
		}

		HttpSession session = req.getSession();

		if (session.getAttribute(ATT_SESSION_GESTIONNAIRE) == null) {
			String str = req.getRequestURL().toString();
			req.setAttribute(ATT_URLDEMANDE, str);
			req.getRequestDispatcher(ACCES_CONNEXION).forward(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
