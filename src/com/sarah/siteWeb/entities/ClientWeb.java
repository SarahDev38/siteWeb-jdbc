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
@Table(uniqueConstraints= { @UniqueConstraint( columnNames= {"email"} ) })
public class ClientWeb implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String tel;
	private String email;
	private String adresse;
	private String codePostal;
	private String ville;
	
	@Column(name = "DATE_INSCRIPTION", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = JodaDateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime dateInscription;

	private String motdepasse;

	public ClientWeb() {
	}

	public ClientWeb(String nom, String prenom, String tel, String email, String adresse, String codePostal,
			String ville, DateTime dateInscription, String motdepasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.dateInscription = dateInscription;
		this.motdepasse = motdepasse;
	}

	public void clientWebToString() {
		System.out.println("********** Description du client id = " + this.getId() + " **********");
		System.out.println("                nom : " + this.getNom());
		System.out.println("             prénom : " + this.getPrenom());
		System.out.println("                tel : " + this.getTel());
		System.out.println("               mail : " + this.getEmail());
		System.out.println("            adresse : " + this.getAdresse());
		System.out.println("        code postal : " + this.getCodePostal());
		System.out.println("              ville : " + this.getVille());
		System.out.println("  date_inscription  : " + this.getDateInscription());
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
		try {
			this.nom = nom.toUpperCase();
		} catch (NullPointerException e) {
			this.nom = "";
		}
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		try {
			prenom = prenom.toLowerCase();
			prenom = InitialestoUpperCase(prenom, "\\s");
			prenom = InitialestoUpperCase(prenom, "-");
		} catch (Exception e) {
		}
		this.prenom = prenom;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		try {
			ville = ville.toLowerCase();
			ville = InitialestoUpperCase(ville, "\\s");
			ville = InitialestoUpperCase(ville, "-");
			ville = InitialestoUpperCase(ville, "'");
		} catch (Exception e) {
		}
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public DateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(DateTime dateInscription) {
		this.dateInscription = dateInscription;
	}
}
