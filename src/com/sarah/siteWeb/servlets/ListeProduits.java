package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.TableGammeDAO;
import com.sarah.siteWeb.dao.TableProduitDAO;
import com.sarah.siteWeb.dao.TableReferenceDAO;
import com.sarah.siteWeb.entities.Rubrique;

@WebServlet("/listeProduits")
public class ListeProduits extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LISTE_PRODUITS = "listeProduits";
	private static final String ATT_LISTE_REFERENCES = "listeReferences";
	private static final String ATT_LISTE_RUBRIQUES = "listeRubriques";
	private static final String ATT_LISTE_GAMMES = "listeGammes";
	private static final String CHAMP_BOUTON_AFFICHER = "boutonAfficher";
	private static final String VUE_LISTE = "/listeProduits.jsp";
	private static final String URL_REDIRECTION_PRODUIT = "http://localhost:8088/siteWeb/informationsProduit?produit_id=";
	@EJB
	private TableGammeDAO gammeDao;
	@EJB
	private TableReferenceDAO referenceDao;
	@EJB
	private TableProduitDAO produitDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_PRODUITS, produitDao.listerProduits());
		request.setAttribute(ATT_LISTE_REFERENCES, referenceDao.listerReferences());
		request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
		request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
		this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_PRODUITS, produitDao.listerProduits());
		if (request.getParameter(CHAMP_BOUTON_AFFICHER) != null) {
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_AFFICHER)).longValue();
			response.sendRedirect(URL_REDIRECTION_PRODUIT + id);
		} else {
			request.setAttribute(ATT_LISTE_PRODUITS, produitDao.listerProduits());
			request.setAttribute(ATT_LISTE_REFERENCES, referenceDao.listerReferences());
			request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
			request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
			this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
		}
	}
}
