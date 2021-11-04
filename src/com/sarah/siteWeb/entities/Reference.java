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
public class Reference implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String description;
	private Rubrique rubrique;

	
	@ManyToOne
	@JoinColumn(name = "GAMME_ID")
	private Gamme gamme;

	@ManyToOne
	@JoinColumn(name = "GESTIONNAIRE_ID")
	private Gestionnaire gestionnaire;

	private String image = "";
 
	public Reference() {
	}

	public Reference(String nom, String description, Rubrique rubrique, Gamme gamme, Gestionnaire gestionnaire) {
		this.nom = nom;
		this.description = description;
		this.rubrique = rubrique;
		this.gamme = gamme;
		this.gestionnaire = gestionnaire;
	}

	public void referenceToString() {
		System.out.println("********** Description du produit id = " + this.getId() + " **********");
		System.out.println("              nom : " + this.getNom());
		System.out.println("      description : " + this.getDescription());
		System.out.println("         rubrique : " + this.getRubrique().getNom());
		System.out.println("            gamme : " + this.getGamme().getId());
		System.out.println("            image : " + this.getImage());
		System.out.println(" gestionnaire id  : " + this.getGestionnaire().getId());
		System.out.println("****************************************************");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rubrique getRubrique() {
		return rubrique;
	}

	public void setRubrique(Rubrique rubrique) {
		this.rubrique = rubrique;
	}

	public Gamme getGamme() {
		return gamme;
	}

	public void setGamme(Gamme gamme) {
		this.gamme = gamme;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
