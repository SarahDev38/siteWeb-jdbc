package com.sarah.siteWeb.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sarah.siteWeb.entities.StatutDeLivraison;

@Converter(autoApply = true)
public class StatutDeLivraisonConverter implements AttributeConverter<StatutDeLivraison, String> {

	@Override
	public String convertToDatabaseColumn(StatutDeLivraison statutLivraison) {
		return (statutLivraison == null) ? null : new String(((StatutDeLivraison) statutLivraison).getCode());
	}

	@Override
	public StatutDeLivraison convertToEntityAttribute(String code) {
		return (code == null) ? null : StatutDeLivraison.getStatutDeLivraisonByCode((String) code);
	}

}
