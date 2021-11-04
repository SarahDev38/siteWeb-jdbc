package com.sarah.siteWeb.entities;

public enum ModeDePaiement {
    ESPECES( "especes", "paiement en espèces" ), CHEQUE( "cheque", "paiement en chèque" ), RESTO( "resto",
            "paiement en chèque restaurant" ), VISA( "visa", "paiement en carte visa" ), VIREMENT( "virement",
                    "paiement par virement bancaire" ), PAYPAL( "paypal", "paiement paypal" );

    private String code = "";
    private String nom  = "";

    ModeDePaiement( String code, String mode ) {
        this.code = code;
        this.nom = mode;
    }

    public static ModeDePaiement getModeDePaiementByCode( String str ) {
        for ( ModeDePaiement mode : ModeDePaiement.values() ) {
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
