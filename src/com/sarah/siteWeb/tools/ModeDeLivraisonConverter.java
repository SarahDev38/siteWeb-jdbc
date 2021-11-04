package com.sarah.siteWeb.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sarah.siteWeb.entities.ModeDeLivraison;

@Converter(autoApply = true)
public class ModeDeLivraisonConverter implements AttributeConverter<ModeDeLivraison, String> {

	@Override
	public String convertToDatabaseColumn(ModeDeLivraison modeLivraison) {
		return (modeLivraison == null) ? null : new String(((ModeDeLivraison) modeLivraison).getCode());
	}

	@Override
	public ModeDeLivraison convertToEntityAttribute(String code) {
		return (code == null) ? null : ModeDeLivraison.getModeDeLivraisonByCode((String) code);
	}
}
