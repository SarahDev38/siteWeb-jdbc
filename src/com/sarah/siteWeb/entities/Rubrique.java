package com.sarah.siteWeb.entities;

public enum Rubrique {
	MAISON("maison"), LINGE("linge"), BIJOUX("bijoux");

	private String nom = "";

	Rubrique(String nom) {
		this.nom = nom;
	}

	public static Rubrique getRubriqueByNom(String nom) {
		for (Rubrique r : Rubrique.values()) {
			if (r.nom.equals(nom)) {
				return r;
			}
		}
		return null;
	}

	public String toString() {
		return this.nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}