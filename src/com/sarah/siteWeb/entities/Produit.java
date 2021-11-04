package com.sarah.siteWeb.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "REFERENCE_ID")
	private Reference reference;

	private String nom;
	private String taille;
	private Double prix_public;
	private Double prix_achat;
	private int quantite_disponible;
	private int quantite_min_stock;

	public Produit(Reference reference, String nom, String taille, Double prix_public, Double prix_achat,
			int quantite_disponible, int quantite_min_stock) {
		this.reference = reference;
		this.nom = nom;
		this.taille = taille;
		this.prix_public = prix_public;
		this.prix_achat = prix_achat;
		this.quantite_disponible = quantite_disponible;
		this.quantite_min_stock = quantite_min_stock;
	}

	public void produitToString() {
		System.out.println("********** Description du produit id = " + this.getId() + " **********");
		System.out.println("        produitType id : " + this.getReference().getId());
		System.out.println("                   nom : " + this.getNom());
		System.out.println("                taille : " + this.getTaille());
		System.out.println("           prix_public : " + this.getPrix_public());
		System.out.println("            prix_achat : " + this.getPrix_achat());
		System.out.println("   quantite_disponible : " + this.getQuantite_disponible());
		System.out.println("    quantite_min_stock : " + this.getQuantite_min_stock());
		System.out.println("****************************************************");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public Double getPrix_public() {
		return prix_public;
	}

	public void setPrix_public(Double prix_public) {
		this.prix_public = prix_public;
	}

	public Double getPrix_achat() {
		return prix_achat;
	}

	public void setPrix_achat(Double prix_achat) {
		this.prix_achat = prix_achat;
	}

	public int getQuantite_disponible() {
		return quantite_disponible;
	}

	public void setQuantite_disponible(int quantite_disponible) {
		this.quantite_disponible = quantite_disponible;
	}

	public int getQuantite_min_stock() {
		return quantite_min_stock;
	}

	public void setQuantite_min_stock(int quantite_min_stock) {
		this.quantite_min_stock = quantite_min_stock;
	}

	public Produit() {
	}
}
