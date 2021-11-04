package com.sarah.siteWeb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class PanierLigne implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "GESTIONNAIRE_ID")
	private Gestionnaire gestionnaire;

	@ManyToOne
	@JoinColumn(name = "PRODUIT_ID")
	private Produit produit;

	private int quantite = 1;
	@OneToOne
	@JoinColumn(name = "COMMANDE_ID")
	private Commande commande = null;

	private Double montantUnitaire;

	public PanierLigne() {
	}

	public PanierLigne(Client client, Gestionnaire gestionnaire, Commande commande, Produit produit, int quantite,
			Double montantUnitaire) {
		this.gestionnaire = gestionnaire;
		this.client = client;
		this.quantite = quantite;
		this.produit = produit;
		this.commande = commande;
		this.montantUnitaire = montantUnitaire;
	}

	public void panierToString() {
		System.out.println("********** Description du panier id = " + this.getId() + " **********");
		System.out.println("                    client id  : " + this.getClient().getId());
		System.out.println("              gestionnaire id  : " + this.getGestionnaire().getId());
		System.out.println("                     quantite  : " + this.getQuantite());
		System.out.println("                   produit id  : " + this.getProduit().getId());
		System.out.println("     commandé ? (n° commande)  : " + this.getCommande().getId());
		System.out.println("****************************************************");
	}

	public Double getMontantUnitaire() {
		return montantUnitaire;
	}

	public void setMontantUnitaire(Double montantUnitaire) {
		this.montantUnitaire = montantUnitaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
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

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

}
