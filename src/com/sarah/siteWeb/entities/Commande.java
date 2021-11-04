package com.sarah.siteWeb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import com.sarah.siteWeb.tools.JodaDateTimeConverter;

@SuppressWarnings("serial")
@Entity
public class Commande implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "GESTIONNAIRE_ID")
	private Gestionnaire gestionnaire;

	@Column(name = "DATE_CREATION", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime dateCreation;

	@Column(name = "DATE_MODIFICATION", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime dateModification = null;

	private Double montant;
	private ModeDePaiement modePaiement;
	private StatutDePaiement statutPaiement;
	private ModeDeLivraison modeLivraison;
	private StatutDeLivraison statutLivraison;

	public Commande() {
	}

	public Commande(DateTime dateCreation, DateTime dateModification, Double montant, ModeDePaiement modePaiement,
			StatutDePaiement statutPaiement, ModeDeLivraison modeLivraison, StatutDeLivraison statutLivraison,
			Client client, Gestionnaire gestionnaire) throws BeanException {
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.setMontant(montant);
		this.modePaiement = modePaiement;
		this.statutPaiement = statutPaiement;
		this.modeLivraison = modeLivraison;
		this.statutLivraison = statutLivraison;
		this.client = client;
		this.gestionnaire = gestionnaire;
	}

	public Commande(DateTime dateCreation, DateTime dateModification, Double montant, String modePaiement,
			String statutPaiement, String modeLivraison, String statutLivraison, Client client,
			Gestionnaire gestionnaire) throws BeanException {
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
		this.setMontant(montant);
		this.modePaiement = ModeDePaiement.getModeDePaiementByCode(modePaiement);
		this.statutPaiement = StatutDePaiement.getStatutDePaiementByCode(statutPaiement);
		this.modeLivraison = ModeDeLivraison.getModeDeLivraisonByCode(modeLivraison);
		this.statutLivraison = StatutDeLivraison.getStatutDeLivraisonByCode(statutLivraison);
		this.client = client;
		this.gestionnaire = gestionnaire;
	}

	public void commandeToString() {
		System.out.println("********** Description de la commande id = " + this.getId() + " **********");
		System.out.println("            date creation : " + this.getDateCreation());
		try {
			System.out.println("        date modification : " + this.getDateModification());
		} catch (Exception e) {
		}
		System.out.println("                  montant : " + this.getMontant());
		System.out.println("                id client : " + this.getClient().getId());
		System.out.println("          id gestionnaire : " + this.getGestionnaire().getId());
		System.out.println("         mode de paiement : " + this.getModePaiement().getCode());
		System.out.println("       statut du paiement : " + this.getStatutPaiement().getCode());
		System.out.println("        mode de livraison : " + this.getModeLivraison().getCode());
		System.out.println("   statut de la livraison : " + this.getStatutLivraison().getCode());
		System.out.println("*********************************************************");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public ModeDePaiement getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(ModeDePaiement modePaiement) {
		this.modePaiement = modePaiement;
	}

	public void setModePaiement(String code) {
		this.modePaiement = ModeDePaiement.getModeDePaiementByCode(code);
	}

	public StatutDePaiement getStatutPaiement() {
		return statutPaiement;
	}

	public void setStatutPaiement(StatutDePaiement statutPaiement) {
		this.statutPaiement = statutPaiement;
	}

	public void setStatutPaiement(String code) {
		this.statutPaiement = StatutDePaiement.getStatutDePaiementByCode(code);
	}

	public ModeDeLivraison getModeLivraison() {
		return modeLivraison;
	}

	public void setModeLivraison(ModeDeLivraison modeLivraison) {
		this.modeLivraison = modeLivraison;
	}

	public void setModeLivraison(String code) {
		this.modeLivraison = ModeDeLivraison.getModeDeLivraisonByCode(code);
	}

	public StatutDeLivraison getStatutLivraison() {
		return statutLivraison;
	}

	public void setStatutLivraison(StatutDeLivraison statutLivraison) {
		this.statutLivraison = statutLivraison;
	}

	public void setStatutLivraison(String code) {
		this.statutLivraison = StatutDeLivraison.getStatutDeLivraisonByCode(code);
	}

	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public DateTime getDateModification() {
		return dateModification;
	}

	public void setDateModification(DateTime dateModification) {
		this.dateModification = dateModification;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
}
