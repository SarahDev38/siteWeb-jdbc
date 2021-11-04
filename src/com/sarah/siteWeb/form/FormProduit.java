package com.sarah.siteWeb.form;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableProduitDAO;
import com.sarah.siteWeb.entities.Produit;
import com.sarah.siteWeb.entities.Reference;

public class FormProduit {
	private TableProduitDAO produitDao;
	private static final String CHAMP_ID = "idProduit";
	private static final String CHAMP_NOM = "nomProduit";
	private static final String CHAMP_TAILLE = "taille";
	private static final String CHAMP_PRIX_PUBLIC = "prixPublic";
	private static final String CHAMP_PRIX_ACHAT = "prixAchat";
	private static final String CHAMP_QTE_DISPO = "qteDispo";
	private static final String CHAMP_QTE_MIN = "qteMin";
	private static final String CHAMP_BOUTON_SUPPR = "boutonSupprimer";
	private static final String CHAMP_BOUTON_MODIFIER = "boutonModifier";

	public FormProduit(TableProduitDAO produitDao) {
		this.produitDao = produitDao;
	}

	public Produit creerProduit(HttpServletRequest request, Reference reference)
			throws IOException, ServletException {
		if (reference == null) {
			return null;
		}
		String nom = getValeurChamp(request, CHAMP_NOM);
		String taille = getValeurChamp(request, CHAMP_TAILLE);
		Double prixPublic = Double.parseDouble(getValeurChamp(request, CHAMP_PRIX_PUBLIC));
		Double prixAchat = Double.parseDouble(getValeurChamp(request, CHAMP_PRIX_ACHAT));
		int qteDispo = Integer.parseInt(getValeurChamp(request, CHAMP_QTE_DISPO));
		int qteMin = Integer.parseInt(getValeurChamp(request, CHAMP_QTE_MIN));
		Produit produit = new Produit(reference, nom, taille, prixPublic, prixAchat, qteDispo, qteMin);
		try {
			produit = produitDao.creer(produit);
		} catch (DaoException e) {
		}
		return produit;
	}

	public Produit modifierProduit(HttpServletRequest request, Reference reference)
			throws IOException, ServletException {
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		String nom = getValeurChamp(request, CHAMP_NOM);
		String taille = getValeurChamp(request, CHAMP_TAILLE);
		Double prixPublic = Double.parseDouble(getValeurChamp(request, CHAMP_PRIX_PUBLIC));
		Double prixAchat = Double.parseDouble(getValeurChamp(request, CHAMP_PRIX_ACHAT));
		int qteDispo = Integer.parseInt(getValeurChamp(request, CHAMP_QTE_DISPO));
		int qteMin = Integer.parseInt(getValeurChamp(request, CHAMP_QTE_MIN));
		Produit produit = new Produit(reference, nom, taille, prixPublic, prixAchat, qteDispo, qteMin);
		produit.setId(id);

		try {
			produitDao.modifier(produit);
		} catch (DaoException ignore) {
		}
		return produit;
	}

	public Produit modifierProduitInventaire(HttpServletRequest request) throws IOException, ServletException {
		Long id = Long.parseLong(request.getParameter(CHAMP_BOUTON_MODIFIER));
		String taille = getValeurChampInventaire(request, CHAMP_TAILLE, request.getParameter(CHAMP_BOUTON_MODIFIER));
		Double prixPublic = Double.parseDouble(getValeurChampInventaire(request, CHAMP_PRIX_PUBLIC, request.getParameter(CHAMP_BOUTON_MODIFIER)));
		Double prixAchat = Double.parseDouble(getValeurChampInventaire(request, CHAMP_PRIX_ACHAT, request.getParameter(CHAMP_BOUTON_MODIFIER)));
		int qteDispo = Integer.parseInt(getValeurChampInventaire(request, CHAMP_QTE_DISPO, request.getParameter(CHAMP_BOUTON_MODIFIER)));
		int qteMin = Integer.parseInt(getValeurChampInventaire(request, CHAMP_QTE_MIN, request.getParameter(CHAMP_BOUTON_MODIFIER)));

		Produit produit = produitDao.trouverId(id);
		produit.setTaille(taille);
		produit.setPrix_public(prixPublic);
		produit.setPrix_achat(prixAchat);
		produit.setQuantite_disponible(qteDispo);
		produit.setQuantite_min_stock(qteMin);
		try {
			produitDao.modifier(produit);
		} catch (DaoException ignore) {
		}
		return produit;
	}

	public void deleteProduitById(HttpServletRequest request) {
		try {
			produitDao.supprimer(produitDao.trouverId(Long.parseLong(request.getParameter(CHAMP_BOUTON_SUPPR))));
		} catch (NumberFormatException | DaoException e) {
		}
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	private static String getValeurChampInventaire(HttpServletRequest request, String nomChamp, String ChampId) {
		String valeur = request.getParameter(ChampId + " " + nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	public void supprimerProduit(HttpServletRequest request) throws IOException, ServletException {
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		try {
			produitDao.supprimer(produitDao.trouverId(id));
		} catch (DaoException ignore) {
		}
	}
}
