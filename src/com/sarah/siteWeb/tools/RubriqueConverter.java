package com.sarah.siteWeb.tools;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sarah.siteWeb.entities.Rubrique;

@Converter(autoApply = true)
public class RubriqueConverter implements AttributeConverter<Rubrique, String> {

	@Override
	public String convertToDatabaseColumn(Rubrique rubrique) {
		return (rubrique == null) ? null : new String(((Rubrique) rubrique).getNom());
	}

	@Override
	public Rubrique convertToEntityAttribute(String nom) {
		return (nom == null) ? null : Rubrique.getRubriqueByNom((String) nom);
	}

}
