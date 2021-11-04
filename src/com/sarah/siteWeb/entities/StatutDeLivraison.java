package com.sarah.siteWeb.entities;

public enum StatutDeLivraison {
	TRAITEMENT("traitement", "traitement de la commande en cours"), TRANSIT("transit", "en transit"),
	LIVRAISON("livraison", "en cours de livraison"), LIVRE("livre", "livré");

	private String code = "";
	private String nom = "";

	StatutDeLivraison(String code, String nom) {
		this.code = code;
		this.nom = nom;
	}

	StatutDeLivraison(String code) {
		this.code = code;
		this.nom = StatutDeLivraison.getStatutDeLivraisonByCode(code).getNom();
	}

	public static StatutDeLivraison getStatutDeLivraisonByCode(String str) {
		for (StatutDeLivraison mode : StatutDeLivraison.values()) {
			if (mode.code.equals(str)) {
				return mode;
			}
		}
		return null;
	}

	public String toString() {
		return this.nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
