package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.TableGammeDAO;
import com.sarah.siteWeb.dao.TableProduitDAO;
import com.sarah.siteWeb.dao.TableReferenceDAO;
import com.sarah.siteWeb.entities.Gamme;
import com.sarah.siteWeb.entities.Produit;
import com.sarah.siteWeb.entities.Reference;
import com.sarah.siteWeb.entities.Rubrique;
import com.sarah.siteWeb.form.FormGamme;
import com.sarah.siteWeb.form.FormProduit;
import com.sarah.siteWeb.form.FormReference;

@WebServlet(urlPatterns = "/nouveauProduit", initParams = {
		@WebInitParam(name = "cheminStockage", value = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/produits/"),
		@WebInitParam(name = "fileTypes", value = "doc;xls;zip") })
@MultipartConfig(location = "C:/Temp/Download", // dossier temp pour copie
		fileSizeThreshold = 1024 * 1024, // >1MB => passage par dossier temp
		maxFileSize = 1024 * 1024 * 5, // 5MB autorisé par fichier
		maxRequestSize = 1024 * 1024 * 5 * 5 // 25MB autorisés pour l'ensemble
)
public class CreationProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_FORM_TYPE = "formType";
	private static final String ATT_PRODUIT = "produit";
	private static final String ATT_REFERENCE = "reference";
	private static final String ATT_RUBRIQUE = "rubrique";
	private static final String ATT_GAMME = "gamme";
	private static final String ATT_LISTE_REFERENCES = "listeReferences";
	private static final String ATT_LISTE_RUBRIQUES = "listeRubriques";
	private static final String ATT_LISTE_GAMMES = "listeGammes";
	private static final String CHEMIN_STOCKAGE_IMAGE = "cheminStockage";
	private static final String VUE_FORM = "/restreintGestionnaire/creerProduit.jsp";
	private static final String VUE_INFOS = "/infosProduit.jsp";
	@EJB
	private TableGammeDAO gammeDao;
	@EJB
	private TableReferenceDAO referenceDao;
	@EJB
	private TableProduitDAO produitDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATT_LISTE_REFERENCES, referenceDao.listerReferences());
		request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
		request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
		this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter(CHEMIN_STOCKAGE_IMAGE);
		FormGamme formGamme = new FormGamme(gammeDao);
		FormReference formReference = new FormReference(referenceDao);
		FormProduit formProduit = new FormProduit(produitDao);

		Gamme gamme = formGamme.creerGamme(request);
		Reference reference = formReference.creerReference(request, chemin, gamme);
		Produit produit = formProduit.creerProduit(request, reference);
		Rubrique rubrique = reference.getRubrique();
		request.setAttribute(ATT_REFERENCE, reference);
		request.setAttribute(ATT_PRODUIT, produit);
		request.setAttribute(ATT_RUBRIQUE, rubrique);
		request.setAttribute(ATT_GAMME, gamme);
		try {
			if (formReference.getErreurs().isEmpty() && produit != null) {
				this.getServletContext().getRequestDispatcher(VUE_INFOS).forward(request, response);
			} else {
				request.setAttribute(ATT_FORM_TYPE, formReference);
				request.setAttribute(ATT_LISTE_REFERENCES, referenceDao.listerReferences());
				request.setAttribute(ATT_LISTE_RUBRIQUES, new ArrayList<Rubrique>(Arrays.asList(Rubrique.values())));
				request.setAttribute(ATT_LISTE_GAMMES, gammeDao.listerGammes());
				this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
			}
		} catch (Exception e) {
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}
