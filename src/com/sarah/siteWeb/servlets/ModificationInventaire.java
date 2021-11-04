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
import com.sarah.siteWeb.form.FormProduit;

@WebServlet("/modifierInventaire")
public class ModificationInventaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LISTE_PRODUITS = "listeProduits";
	private static final String ATT_LISTE_REFERENCES = "listeReferences";
	private static final String ATT_LISTE_RUBRIQUES = "listeRubrisues";
	private static final String ATT_LISTE_GAMMES = "listeGammes";
	private static final String CHAMP_BOUTON_SUPPR = "boutonSupprimer";
	private static final String CHAMP_BOUTON_MODIFIER = "boutonModifier";
	private static final String VUE_FORM = "/restreintGestionnaire/modifierInventaire.jsp";
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
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormProduit formProduit = new FormProduit(produitDao);
		if (request.getParameter(CHAMP_BOUTON_SUPPR) != null) {
			formProduit.deleteProduitById(request);
		}
		if (request.getParameter(CHAMP_BOUTON_MODIFIER) != null) {
			System.out.println(request.getParameter(CHAMP_BOUTON_MODIFIER));
			formProduit.modifierProduitInventaire(request);
			request.setAttribute(ATT_LISTE_PRODUITS, produitDao.listerProduits());
			request.setAttribute(ATT_LISTE_REFERENCES, referenceDao.listerReferences());
			request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
			request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}
