package com.sarah.siteWeb.entities;

public enum StatutDePaiement {
    PAYE( "paye", "payé" ), ATTENTE( "attente", "en attente du paiement" ), REMBOURSE( "rembourse",
            "remboursé" ), ANNULE( "annule", "annulé" );

    private String code = "";
    private String nom  = "";

    StatutDePaiement( String code, String nom ) {
        this.code = code;
        this.nom = nom;
    }

    public static StatutDePaiement getStatutDePaiementByCode( String str ) {
        for ( StatutDePaiement mode : StatutDePaiement.values() ) {
            if ( mode.code.equals( str ) ) {
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

    public void setCode( String code ) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

}
