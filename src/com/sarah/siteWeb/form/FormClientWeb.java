package com.sarah.siteWeb.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.joda.time.DateTime;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientWebDAO;
import com.sarah.siteWeb.entities.ClientWeb;

public class FormClientWeb {
	private TableClientWebDAO clientWebDao;
	// creation client
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_TEL = "tel";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_ADRESSE = "adresse";
	private static final String CHAMP_CODE_POSTAL = "codePostal";
	private static final String CHAMP_VILLE = "ville";
	private static final String CHAMP_MDP = "motdepasse";

	// validations
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String resultat;
	private ClientWeb doublon = null;
	private static final String ALGO_CHIFFREMENT = "SHA-256";

	public FormClientWeb(TableClientWebDAO clientWebDao) {
		this.clientWebDao = clientWebDao;
	}

	private ClientWeb getClientWebFromForm(HttpServletRequest request) {
		ClientWeb client = new ClientWeb();
		client.setNom(getValeurChamp(request, CHAMP_NOM));
		client.setPrenom(getValeurChamp(request, CHAMP_PRENOM));
		client.setTel(getValeurChamp(request, CHAMP_TEL));
		client.setEmail(getValeurChamp(request, CHAMP_EMAIL).replace(" ", ""));
		client.setAdresse(getValeurChamp(request, CHAMP_ADRESSE));
		client.setCodePostal(getValeurChamp(request, CHAMP_CODE_POSTAL));
		client.setVille(getValeurChamp(request, CHAMP_VILLE));

		return client;
	}

	public ClientWeb creerClientWeb(HttpServletRequest request) {
		ClientWeb client = getClientWebFromForm(request);
		String motDePasse = getValeurChamp(request, CHAMP_MDP);
		DateTime date = new DateTime( System.currentTimeMillis() );
		client.setDateInscription(date);

		validerEmail(client.getEmail());
		validerMDP(motDePasse, client);

		try {
			if (erreurs.isEmpty()) {
				clientWebDao.creer(client);
				resultat = "Client créé avec succès.";
			} else {
				resultat = "Échec de l'enregistrement des données client.";
			}
		} catch (DaoException e) {
			resultat = "Échec : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return client;
	}

	public ClientWeb checkClientWeb_Commande(HttpServletRequest request) {
		ClientWeb client = getClientWebFromForm(request);
		DateTime date = new DateTime( System.currentTimeMillis() );
		client.setDateInscription(date);
		String motDePasse = getValeurChamp(request, CHAMP_MDP);

		validerMDP(motDePasse, client);

		if (getValeurChamp(request, CHAMP_ID) == null) {
			validerEmail(client.getEmail());
		} else {
			Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
			client.setId(id);
			validerModifEmail(id, client.getEmail());
		}

		if (erreurs.isEmpty()) {
			resultat = "Les données clients sont correctes.";
		} else {
			resultat = "Érreurs dans les données client.";
		}
		return client;
	}

	public ClientWeb creerClientWeb_Commande(HttpServletRequest request) {
		ClientWeb client = new ClientWeb();
		if (getValeurChamp(request, CHAMP_ID) == null) {
			client = creerClientWeb(request);
		} else {
			client = modifierClientWeb(request, null);
		}
		return client;
	}

	public ClientWeb modifierClientWeb(HttpServletRequest request, String chemin) {
		ClientWeb client = getClientWebFromForm(request);
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		String motDePasse = getValeurChamp(request, CHAMP_MDP);

		client.setId(id);
		client.setDateInscription(clientWebDao.trouverId(id).getDateInscription());

		validerModifEmail(id, client.getEmail());
		validerMDP(motDePasse, client);

		try {
			if (erreurs.isEmpty()) {
				clientWebDao.modifier(client);
				resultat = "Client modifié avec succès.";
			} else {
				resultat = "Échec de la modification.";
			}
		} catch (DaoException e) {
			resultat = "Échec de la modification : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return client;
	}

	public ClientWeb connecterClientWeb(HttpServletRequest request) {
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_MDP);
		ClientWeb client = new ClientWeb();
		client.setEmail(email);
		client.setMotdepasse(motDePasse);

		Boolean check = false;
		resultat = "Échec de la connection.";
		try {
			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
			passwordEncryptor.setPlainDigest(false);
			ClientWeb clientEmail = clientWebDao.trouverEmail(email);
			if (clientEmail == null) {
				setErreur(CHAMP_EMAIL, "aucun client n'est enregistré avec ce mot de passe !");
			}
			String passwordEnregistre = clientEmail.getMotdepasse();
			check = passwordEncryptor.checkPassword(motDePasse, passwordEnregistre);
			if (check) {
				client = clientEmail;
				resultat = "Succès de la connection.";
			} else {
				setErreur(CHAMP_MDP, "le mot de passe ne correspond pas!");
			}
		} catch (DaoException e) {
		}
		return client;
	}

	private void validerMDP(String motDePasse, ClientWeb client) {
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
		passwordEncryptor.setPlainDigest(false);
		String motDePasseChiffre = passwordEncryptor.encryptPassword(motDePasse);

		client.setMotdepasse(motDePasseChiffre);
	}

	private void validerEmail(String email) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		doublon = clientWebDao.trouverEmail(email);
		if (doublon != null) {
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

	private void validationModifEmail(Long id, String email) throws FormValidationException {
		doublon = clientWebDao.trouverEmail(email);
		if (doublon != null && !doublon.getId().equals(id)) {
			throw new FormValidationException("ce mail est déjà utilisé !");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public ClientWeb getDoublon() {
		return doublon;
	}
}
