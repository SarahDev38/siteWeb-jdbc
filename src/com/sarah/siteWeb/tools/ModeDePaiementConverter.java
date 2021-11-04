package com.sarah.siteWeb.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sarah.siteWeb.entities.ModeDePaiement;

@Converter(autoApply = true)
public class ModeDePaiementConverter implements AttributeConverter<ModeDePaiement, String> {

	@Override
	public String convertToDatabaseColumn(ModeDePaiement modeDePaiement) {
		return (modeDePaiement == null) ? null : new String(((ModeDePaiement) modeDePaiement).getCode());
	}

	@Override
	public ModeDePaiement convertToEntityAttribute(String code) {
		return (code == null) ? null : ModeDePaiement.getModeDePaiementByCode((String) code);
	}
}
