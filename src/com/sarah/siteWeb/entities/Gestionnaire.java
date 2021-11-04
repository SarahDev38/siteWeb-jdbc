package com.sarah.siteWeb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import com.sarah.siteWeb.tools.JodaDateTimeConverter;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints= { @UniqueConstraint( columnNames= {"pseudo"} ) })
public class Gestionnaire implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
//	@Column(length=56)
	private String motdepasse;

	@Column(name = "DATE_INSCRIPTION", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime dateInscription;

	public Gestionnaire() {
	}

	public Gestionnaire(String nom, String prenom, String pseudo, String email, String motdepasse) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setPseudo(pseudo);
		this.setEmail(email);
		this.setMotdepasse(motdepasse);
	}

	public void gestionnaireToString() {
		System.out.println("********** Description du gestionnaire id = " + this.getId() + " **********");
		System.out.println("           pseudo : " + this.getPseudo());
		System.out.println("              nom : " + this.getNom());
		System.out.println("           prénom : " + this.getPrenom());
		System.out.println("            email : " + this.getEmail());
		System.out.println(" date inscription : " + this.getDateInscription());
		System.out.println("****************************************************");
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	public String getPrenom() {
		prenom = prenom.toLowerCase();
		prenom = InitialestoUpperCase(prenom, "\\s");
		prenom = InitialestoUpperCase(prenom, "-");
		return prenom;
	}

	String InitialestoUpperCase(String mot, String regex) {
		String words[] = mot.split(regex);
		String separation = regex.equals("\\s") ? " " : "-";
		String m = "";
		for (String w : words) {
			String first = w.substring(0, 1);
			String afterfirst = w.substring(1);
			m += first.toUpperCase() + afterfirst + separation;
		}
		m = m.charAt(m.length() - 1) == '-' ? m.substring(0, m.length() - 1) : m;
		return m.trim();
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setMotdepasse(String motDePasse) {
		this.motdepasse = motDePasse;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public DateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(DateTime dateInscription) {
		this.dateInscription = dateInscription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
