package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableGammeDAO;
import com.sarah.siteWeb.dao.TableProduitDAO;
import com.sarah.siteWeb.dao.TableReferenceDAO;
import com.sarah.siteWeb.entities.Produit;
import com.sarah.siteWeb.entities.Reference;
import com.sarah.siteWeb.entities.Rubrique;

@WebServlet("/informationsProduit")

public class AfficheProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_PRODUIT = "produit";
	private static final String ATT_REFERENCE = "reference";
	private static final String ATT_RUBRIQUE = "rubrique";
	private static final String ATT_GAMME = "gamme";
	private static final String PARAM_PRODUIT_ID = "produit_id";
	private static final String CHAMP_BOUTON_PRECEDENT = "boutonPrecedent";
	private static final String CHAMP_BOUTON_SUIVANT = "boutonSuivant";
	private static final String URL_REDIRECTION_AFFICHER_PRODUITS = "http://localhost:8088/siteWeb/listeProduits";
	private static final String VUE = "/infosProduit.jsp";
	@EJB
	private TableGammeDAO gammeDao;
	@EJB
	private TableReferenceDAO referenceDao;
	@EJB
	private TableProduitDAO produitDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Produit produit = getProduitFromParameter(request);
		if (produit != null) {
			Reference reference = produit.getReference();
			Rubrique rubrique = reference.getRubrique();
			request.setAttribute(ATT_GAMME, reference.getGamme());
			request.setAttribute(ATT_PRODUIT, produit);
			request.setAttribute(ATT_REFERENCE, reference);
			request.setAttribute(ATT_RUBRIQUE, rubrique);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_PRODUITS);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(CHAMP_BOUTON_SUIVANT) != null) {
			Produit suivant = getNextProduit(request);
			request.setAttribute(ATT_PRODUIT, suivant);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		if (request.getParameter(CHAMP_BOUTON_PRECEDENT) != null) {
			Produit precedent = getPreviousProduit(request);
			request.setAttribute(ATT_PRODUIT, precedent);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	private Produit getProduitFromParameter(HttpServletRequest request) {
		Produit produit = null;
		Long idProduit = (long) 0;
		try {
			idProduit = Long.parseLong(request.getParameter(PARAM_PRODUIT_ID));
		} catch (NumberFormatException e) {
		}
		if (!idProduit.equals((long) 0)) {
			try {
				produit = produitDao.trouverId(idProduit);
			} catch (DaoException e) {
			}
		}
		return produit;
	}

	private Produit getNextProduit(HttpServletRequest request) {
		Produit next = null;
		try {
			List<Produit> produits = produitDao.listerProduits();
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_SUIVANT)).longValue();
			Iterator<Produit> iterator = produits.iterator();
			while (iterator.hasNext()) {
				Produit cl = iterator.next();
				if (cl.getId().equals(id)) {
					if (iterator.hasNext()) {
						next = iterator.next();
						break;
					} else {
						next = produits.get(0);
					}
				}
			}
		} catch (DaoException e) {
		}
		return next;
	}

	private Produit getPreviousProduit(HttpServletRequest request) {
		Produit previous = null;
		try {
			List<Produit> produits = produitDao.listerProduits();
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_PRECEDENT)).longValue();
			ListIterator<Produit> iterator = produits.listIterator();
			while (iterator.hasNext()) {
				iterator.next();
			}
			while (iterator.hasPrevious()) {
				Produit cl = iterator.previous();
				if (cl.getId().equals(id)) {
					if (iterator.hasPrevious()) {
						previous = iterator.previous();
						break;
					} else {
						previous = produits.get(produits.size() - 1);
					}
				}
			}
		} catch (DaoException e) {
		}
		return previous;
	}
}
