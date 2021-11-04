package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.TableGammeDAO;
import com.sarah.siteWeb.dao.TableProduitDAO;
import com.sarah.siteWeb.dao.TableReferenceDAO;
import com.sarah.siteWeb.entities.Produit;
import com.sarah.siteWeb.entities.Reference;
import com.sarah.siteWeb.entities.Rubrique;

@WebServlet("/rechercheProduits")
public class RechercheMot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_LISTE_PRODUITS = "listeProduits";
	private static final String ATT_LISTE_REFERENCES = "listeReferences";
	private static final String ATT_LISTE_RUBRIQUES = "listeRubriques";
	private static final String ATT_LISTE_GAMMES = "listeGammes";
	private static final String CHAMP_BOUTON_AFFICHER = "boutonAfficher";
	private static final String cheminStockage = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/produits/";
	private static final String PARAM_RECHERCHE = "recherche";
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
		List<Produit> produits = getProduitsFromParameter(request);

		request.setAttribute(ATT_LISTE_PRODUITS, produits);
		request.setAttribute(ATT_LISTE_REFERENCES, setCheminsImages(referenceDao.listerReferences()));
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
			request.setAttribute(ATT_LISTE_REFERENCES, setCheminsImages(referenceDao.listerReferences()));
			request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
			request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
			this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(request, response);
		}
	}

	private List<Reference> setCheminsImages(List<Reference> references) {
		Iterator<Reference> iterator = references.iterator();
		while (iterator.hasNext()) {
			Reference reference = iterator.next();
			reference.setImage(cheminStockage.concat(reference.getImage().substring(2)));
		}
		return references;
	}

	private List<Produit> getProduitsFromParameter(HttpServletRequest request) {
		List<Produit> produits = null;
		String recherche = request.getParameter(PARAM_RECHERCHE);
		if (recherche.contains("+")) {
			String[] mots = recherche.split("+");
			Character last = mots[0].charAt(mots[0].length() -1);
			if (!last.equals('*') ) {
				recherche = mots[0].concat("*");
			} else {
				recherche = mots[0];
			}
			for (int i = 1; i < mots.length; i++) {
				last = mots[i].charAt(mots[i].length() -1);
				if (!last.equals('*') ) {
					recherche += " " + mots[i] + "*";
				} else {
					recherche += " " + mots[i];
				}
			}
		} else {
			recherche +="*";
		}
		produits = produitDao.listerProduitsRecherche(recherche);
		return produits;
	}

}
