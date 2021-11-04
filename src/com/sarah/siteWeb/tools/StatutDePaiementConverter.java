package com.sarah.siteWeb.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sarah.siteWeb.entities.StatutDePaiement;

@Converter(autoApply = true)
public class StatutDePaiementConverter implements AttributeConverter<StatutDePaiement, String> {

	@Override
	public String convertToDatabaseColumn(StatutDePaiement statutDePaiement) {
		return (statutDePaiement == null) ? null : new String(((StatutDePaiement) statutDePaiement).getCode());
	}

	@Override
	public StatutDePaiement convertToEntityAttribute(String code) {
		return (code == null) ? null : StatutDePaiement.getStatutDePaiementByCode((String) code);
	}

}
