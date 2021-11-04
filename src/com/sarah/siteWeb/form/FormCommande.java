package com.sarah.siteWeb.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableCommandeDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.entities.Commande;
import com.sarah.siteWeb.entities.Gestionnaire;
import com.sarah.siteWeb.entities.ModeDeLivraison;
import com.sarah.siteWeb.entities.ModeDePaiement;
import com.sarah.siteWeb.entities.StatutDeLivraison;
import com.sarah.siteWeb.entities.StatutDePaiement;

public class FormCommande {
	private TableCommandeDAO commandeDao;
	private static final String CHAMP_ID_COMMANDE = "idCommande";
	private static final String CHAMP_DATE_CREATION = "dateCommande";
	private static final String CHAMP_MONTANT = "montantCommande";
	private static final String CHAMP_MODE_PAIEMENT = "modePaiementCommande";
	private static final String CHAMP_STATUT_PAIEMENT = "statutPaiementCommande";
	private static final String CHAMP_MODE_LIVRAISON = "modeLivraisonCommande";
	private static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";
	
	private String resultat;

	public static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";

	public FormCommande(TableCommandeDAO commandeDao) {
		this.commandeDao = commandeDao;
	}

	public Commande getCommandeFromForm(HttpServletRequest request, Commande commande) {
		try {
			commande.setMontant(Double.parseDouble(getValeurChamp(request, CHAMP_MONTANT)));
		} catch (NullPointerException ignore) {
		}
		commande.setModePaiement(ModeDePaiement.getModeDePaiementByCode(getValeurChamp(request, CHAMP_MODE_PAIEMENT)));
		commande.setStatutPaiement(
				StatutDePaiement.getStatutDePaiementByCode(getValeurChamp(request, CHAMP_STATUT_PAIEMENT)));
		commande.setModeLivraison(
				ModeDeLivraison.getModeDeLivraisonByCode(getValeurChamp(request, CHAMP_MODE_LIVRAISON)));
		commande.setStatutLivraison(
				StatutDeLivraison.getStatutDeLivraisonByCode(getValeurChamp(request, CHAMP_STATUT_LIVRAISON)));
		return commande;
	}
 
	public Commande creerCommande(HttpServletRequest request, Client client) {
		Commande commande = new Commande();
		commande = getCommandeFromForm(request, commande);
		HttpSession session = request.getSession();
		Gestionnaire gestionnaire = (Gestionnaire) session.getAttribute(ATT_SESSION_GESTIONNAIRE);
		commande.setGestionnaire(gestionnaire);
		commande.setClient(client);
		commande.setDateCreation(new DateTime(System.currentTimeMillis()));
		commande.setDateModification(commande.getDateCreation());

		try {
			commandeDao.creer(commande);
			resultat = "Commande enregistrée avec succès.";
		} catch (DaoException e) {
			resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return commande;
	}

	public Commande getCommandeAModifierFromForm (HttpServletRequest request) {
		Commande commande = new Commande();
		commande = getCommandeFromForm(request, commande);
		try{
			commande.setDateCreation(DateTime.parse(getValeurChamp(request, CHAMP_DATE_CREATION)));
		} catch (Exception ignore) {
		}		
		return commande;
	}
	public Commande modifierCommande(HttpServletRequest request, Client client) {
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID_COMMANDE));
		Commande commande = commandeDao.trouverId(id);
		commande = getCommandeFromForm(request, commande);
		commande.setDateModification(new DateTime(System.currentTimeMillis()));
		commande.setClient(client);
		try {
			commandeDao.modifier(commande);
			resultat = "Commande modifiée avec succès.";
		} catch (DaoException e) {
			resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return commande;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
