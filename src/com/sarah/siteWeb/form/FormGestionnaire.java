package com.sarah.siteWeb.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.joda.time.DateTime;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableGestionnaireDAO;
import com.sarah.siteWeb.entities.Gestionnaire;

public class FormGestionnaire {
	private TableGestionnaireDAO gestionnaireDao;
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_MDP = "motdepasse";
	private Map<String, String> erreurs = new HashMap<String, String>();
	private String resultat;
	private static final String ALGO_CHIFFREMENT = "SHA-256";

	public FormGestionnaire(TableGestionnaireDAO gestionnaireDao) {
		this.gestionnaireDao = gestionnaireDao;
	}

	public Gestionnaire creerGestionnaire(HttpServletRequest request) {
		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String pseudo = getValeurChamp(request, CHAMP_PSEUDO);
		String email = getValeurChamp(request, CHAMP_EMAIL).replace(" ", "");
		String motdepasse = getValeurChamp(request, CHAMP_MDP);
		DateTime date = new DateTime(System.currentTimeMillis());

		Gestionnaire gestionnaire = new Gestionnaire();
		gestionnaire.setPseudo(pseudo);
		gestionnaire.setNom(nom);
		gestionnaire.setPrenom(prenom);
		gestionnaire.setEmail(email);
		gestionnaire.setDateInscription(date);

		validerPseudo(pseudo);
		validerEmail(email);
		validerMdp(motdepasse);
		gestionnaire.setMotdepasse(chiffrerMDP(motdepasse));

		try {
			if (erreurs.isEmpty()) {
				gestionnaireDao.creer(gestionnaire);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		} catch (DaoException e) {
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return gestionnaire;
	}

	public Gestionnaire modifierGestionnaire(HttpServletRequest request) {
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		Gestionnaire gestionnaire = gestionnaireDao.trouverId(id);
		String motdepasse = getValeurChamp(request, CHAMP_MDP);

		if (!motdepasse.trim().equals("")) {
			validerMdp(motdepasse);
			gestionnaire.setMotdepasse(chiffrerMDP(motdepasse));
		}

		gestionnaire.setPseudo(getValeurChamp(request, CHAMP_PSEUDO));
		gestionnaire.setNom(getValeurChamp(request, CHAMP_NOM));
		gestionnaire.setPrenom(getValeurChamp(request, CHAMP_PRENOM));
		gestionnaire.setEmail(getValeurChamp(request, CHAMP_EMAIL).replace(" ", ""));
		validerModifEmail(id, gestionnaire.getEmail());

		try {
			if (erreurs.isEmpty()) {
				gestionnaireDao.modifier(gestionnaire);
				resultat = "Succès de l'inscription.";
			} else {
				resultat = "Échec de l'inscription.";
			}
		} catch (DaoException e) {
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return gestionnaire; 
	}

	public Gestionnaire connecterGestionnaire(HttpServletRequest request) {
		String pseudo = getValeurChamp(request, CHAMP_PSEUDO);
		String motdepasse = getValeurChamp(request, CHAMP_MDP);
		Gestionnaire gestionnaire = gestionnaireDao.trouverPseudo(pseudo);

		validerMdp(motdepasse);

//		System.out.println(gestionnaire.getMotdepasse());
//		System.out.println(chiffrerMDP(motdepasse));
//		&& gestionnaire.getMotdepasse().equals(chiffrerMDP(motdepasse)) ) {

		if ( gestionnaire != null ) {
			resultat = "Succès de la connection.";
		} else {
			resultat = "Échec de la connection."; 
		}
		return gestionnaire;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	private void validerPseudo(String pseudo) {
		try {
			validationPseudo(pseudo);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PSEUDO, e.getMessage());
		} catch (Exception ignore) {
		}
	}

	private void validationPseudo(String pseudo) throws FormValidationException {
		if (gestionnaireDao.trouverPseudo(pseudo) != null) {
			throw new FormValidationException("ce pseudo est déjà utilisé !");
		}
	}

//	private void validationPseudo(String pseudo) throws FormValidationException {
//		if (pseudo != null && pseudo.trim().length() < 3) {
//			throw new FormValidationException("Le pseudo doit contenir au moins 3 caractères.");
//		}
//	}

	private void validerEmail(String email) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		} catch (Exception ignore) {
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		if (gestionnaireDao.trouverEmail(email) != null) {
			throw new FormValidationException("ce mail est déjà utilisé !");
		}
	}

	private void validerModifEmail(Long id, String email) {
		try {
			validationModifEmail(id, email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
	}

//	private void validationEmail(String email) throws Exception {
//		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
//			throw new Exception("Merci de saisir une adresse mail valide.");
//		}
//	}

	private void validerMdp(String motDePasse) {
		try {
			validationMdp(motDePasse);
		} catch (FormValidationException e1) {
		}
	}
	private void validationMdp(String motDePasse) throws FormValidationException {
		if (motDePasse != null) {
			if (motDePasse.trim().length() < 3) {
				throw new FormValidationException("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException("Merci de saisir et confirmer votre mot de passe.");
		}
	}
	private String chiffrerMDP(String motDePasse) {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);
		return motDePasseChiffre;
	}

	private void validationModifEmail(Long id, String email) throws FormValidationException {
		Gestionnaire gestionnaireEmail = gestionnaireDao.trouverEmail(email);
		if (gestionnaireEmail != null) {
			if (!gestionnaireEmail.getId().equals(id)) {
				throw new FormValidationException("ce mail est déjà utilisé !");
			}
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	public static String getChampEmail() {
		return CHAMP_EMAIL;
	}

	public static String getChampMdp() {
		return CHAMP_MDP;
	}

	public static String getChampNom() {
		return CHAMP_NOM;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public static String getChampPrenom() {
		return CHAMP_PRENOM;
	}

	public static String getChampPseudo() {
		return CHAMP_PSEUDO;
	}

	public static String getChampId() {
		return CHAMP_ID;
	}
}
