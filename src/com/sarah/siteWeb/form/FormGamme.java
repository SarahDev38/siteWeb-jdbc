package com.sarah.siteWeb.form;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableGammeDAO;
import com.sarah.siteWeb.entities.Gamme;
import com.sarah.siteWeb.entities.Rubrique;

public class FormGamme {
	private TableGammeDAO gammeDao;
	private static final String CHAMP_NOM = "nomGamme";
	private static final String CHAMP_RUBRIQUE = "rubrique";
	private static final String CHAMP_ID_GAMME = "idGamme";

	public FormGamme(TableGammeDAO gammeDao) {
		this.gammeDao = gammeDao;
	}

	public Gamme creerGamme(HttpServletRequest request)
			throws IOException, ServletException {
		Gamme gamme = null;
		String nom = getValeurChamp(request, CHAMP_NOM);
		Long gamme_id = null;
		try {
			gamme_id = Long.parseLong(getValeurChamp(request, CHAMP_ID_GAMME));
		} catch (NumberFormatException e) {
		}
		if (gamme_id != null) {
			gamme = gammeDao.trouverId(gamme_id);
		} else if (!nom.equals("autre...")) {
			try {
				gamme = new Gamme(nom, Rubrique.getRubriqueByNom(getValeurChamp(request, CHAMP_RUBRIQUE)));
				gamme = gammeDao.creer(gamme);
			} catch (NumberFormatException | DaoException e) {
			}
		}
		return gamme;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}
}
