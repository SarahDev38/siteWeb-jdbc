package com.sarah.siteWeb.entities;

public enum ModeDeLivraison {
    CHRONOPOST( "chronopost", "chronopost" ), RELAY( "relay", "mondial relay" ), MAGASIN( "magasin",
            "retrait en magasin" );

    private String code = "";
    private String nom  = "";

    ModeDeLivraison( String code, String nom ) {
        this.code = code;
        this.nom = nom;
    }

    public static ModeDeLivraison getModeDeLivraisonByCode( String str ) {
        for ( ModeDeLivraison mode : ModeDeLivraison.values() ) {
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
