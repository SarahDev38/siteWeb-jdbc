package com.sarah.siteWeb.form;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.joda.time.DateTime;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.entities.Gestionnaire;

public class FormClient {
	private TableClientDAO clientDao;
	// creation client
	private static final String CHAMP_ID = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_TEL = "tel";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_ADRESSE = "adresse";
	private static final String CHAMP_CODE_POSTAL = "codePostal";
	private static final String CHAMP_VILLE = "ville";
	// recuperation fichier image
	private static final String CHAMP_FICHIER_IMAGE = "Image";
	private static final String CHAMP_IMAGE_DEPART = "ImageDepart";
	private static final String CHAMP_ANGLE_ROTATION = "AngleImage";
	private static final String CHAMP_SUPPR_IMAGE = "pasdimage";
	private static final String CHAMP_MODIF_IMAGE = "imgmodifiee";
	public static final int TAILLE_TAMPON = 10240; // 10ko
	// validations
	private Map<String, String> erreurs = new HashMap<String, String>();
	public String resultat;
	private Client doublon = null;

	public static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";

	public FormClient(TableClientDAO clientDao) {
		this.clientDao = clientDao;
	}

	private Client getClientFromForm(HttpServletRequest request) {
		Client client = new Client();
		client.setNom(getValeurChamp(request, CHAMP_NOM));
		client.setPrenom(getValeurChamp(request, CHAMP_PRENOM));
		client.setTel(getValeurChamp(request, CHAMP_TEL));
		client.setEmail(getValeurChamp(request, CHAMP_EMAIL).replace(" ", ""));
		client.setAdresse(getValeurChamp(request, CHAMP_ADRESSE));
		client.setCodePostal(getValeurChamp(request, CHAMP_CODE_POSTAL));
		client.setVille(getValeurChamp(request, CHAMP_VILLE));

		return client;
	}

	public Client creerClient(HttpServletRequest request) {
		Client client = getClientFromForm(request);
		Gestionnaire gestionnaire = (Gestionnaire) request.getSession().getAttribute(ATT_SESSION_GESTIONNAIRE);
		client.setGestionnaire(gestionnaire);
		client.setDateCreation(new DateTime(System.currentTimeMillis()));

		validerEmail(client.getEmail());

		try {
			if (erreurs.isEmpty()) {
				clientDao.creer(client);
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

	public Client checkClient_Commande(HttpServletRequest request) {
		Client client = getClientFromForm(request);

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
			resultat = "Erreurs dans les données client.";
		}

		return client;
	}

	public Client creerClient_Commande(HttpServletRequest request) {
		Client client = new Client();
		if (getValeurChamp(request, CHAMP_ID) == null) {
			client = creerClient(request);
		} else {
			client = modifierClient(request, null);
		}
		return client;
	}

	public Client modifierClient(HttpServletRequest request, String chemin) {
		Client client = getClientFromForm(request);
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		Client old = clientDao.trouverId(id);
		client.setId(id);
		client.setGestionnaire(old.getGestionnaire());
		validerModifEmail(id, client.getEmail());

		if (chemin != null) {
			enregistrerImage(request, client, chemin);
		} else {
			client.setImage(old.getImage());
		}
		try {
			if (erreurs.isEmpty()) {
				clientDao.modifier(client);
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

	public void enregistrerImage(HttpServletRequest request, Client client, String chemin) {
		String imageClient = "";
		int angleImage = 0;
		try {
			angleImage = Integer.parseInt(getValeurChamp(request, CHAMP_ANGLE_ROTATION));
		} catch (NumberFormatException ignore) {
		}

		String ImageClientDepart = getValeurChamp(request, CHAMP_IMAGE_DEPART);
		String yaPasDimage = getValeurChamp(request, CHAMP_SUPPR_IMAGE);
		String onaChangeLimage = getValeurChamp(request, CHAMP_MODIF_IMAGE);
		if (yaPasDimage != null && yaPasDimage.equals("true")) {
			// pas ou plus d image
			try {
				if (!ImageClientDepart.equals("null") || ImageClientDepart != null) {
					File fichierAEffacer = new File(chemin + ImageClientDepart);
					fichierAEffacer.delete();
				}
			} catch (Exception e) {
			}
		} else {
			if (onaChangeLimage != null && onaChangeLimage.equals("false")) {
				// meme image angle eventuellement different
				imageClient = ImageClientDepart;
			} else {
				// copie_le_nom_modifié_du_fichier_image_dans_Client.image
				String nomFichier = null;
				InputStream contenuFichier = null;
				try {
					Part part = request.getPart(CHAMP_FICHIER_IMAGE);
					nomFichier = getNomFichier(part);
					if (nomFichier != null && !nomFichier.isEmpty()) {
						nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
								.substring(nomFichier.lastIndexOf('\\') + 1);
						contenuFichier = part.getInputStream();
					}
				} catch (IllegalStateException e) {
					e.printStackTrace();
					setErreur(CHAMP_FICHIER_IMAGE, "Les données envoyées sont trop volumineuses.");
				} catch (IOException e) {
					e.printStackTrace();
					setErreur(CHAMP_FICHIER_IMAGE, "Erreur de configuration du serveur.");
				} catch (ServletException e) {
					e.printStackTrace();
					setErreur(CHAMP_FICHIER_IMAGE,
							"Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
				}

				// try {
				// validationImage( nomFichier, contenuFichier );
				// System.out.println( "enregistre nom fichier image presente 5"
				// );
				// } catch ( FormValidationException e ) {
				// setErreur( CHAMP_FICHIER_IMAGE, e.getMessage() );
				// System.out.println( " !!!!!!!!!!!!! erreur dans validation
				// image !!!!!!!!!!" );
				// }

				if (erreurs.isEmpty()) {
					imageClient = String.valueOf(client.getId()).concat("_").concat(nomFichier);
					try {
						copierFichier(contenuFichier, imageClient, chemin);
						if (angleImage != 0) {
							rotationFichier(nomFichier, chemin, angleImage);
						}
					} catch (IOException e) {
						setErreur(CHAMP_FICHIER_IMAGE, "Erreur lors de l'écriture du fichier sur le disque.");
					}
				} else {
					resultat = "Échec de l'inscription : l'envoi du fichier n'a pu être effectué.";
				}
				try {
					if (!ImageClientDepart.equals("null") || ImageClientDepart != null) {
						File fichierAEffacer = new File(chemin + ImageClientDepart);
						fichierAEffacer.delete();
					}
				} catch (Exception e) {
				}
			}
		}
		client.setImage(imageClient);
	}

	public void copierFichier(InputStream contenu, String nomFichier, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
				entree.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void rotationFichier(String nomFichier, String chemin, int angle) throws IOException {
		BufferedImage image = null;
		File imageDepart = new File(chemin + nomFichier);
		switch (angle) {
		case 1:
			angle = 90;
			break;
		case 2:
			angle = 180;
			break;
		case 3:
			angle = 270;
			break;
		}
		try {
			image = ImageIO.read(imageDepart);
			final double rads = Math.toRadians(angle);
			final double sin = Math.abs(Math.sin(rads));
			final double cos = Math.abs(Math.cos(rads));
			final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
			final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
			final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
			final AffineTransform at = new AffineTransform();
			at.translate(w / 2, h / 2);
			at.rotate(rads, 0, 0);
			at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
			final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
			rotateOp.filter(image, rotatedImage);
			imageDepart.delete();
			ImageIO.write(rotatedImage, "JPG", new File(chemin + nomFichier));
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String getNomFichier(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

//    private void validationImage( String nomImage, InputStream contenu ) throws FormValidationException {
//        MimeUtil.registerMimeDetector( "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" );
//        Collection<?> mimeTypes = MimeUtil.getMimeTypes( contenu );
//        if ( nomImage != null && !nomImage.equals( "" ) ) {
//            if ( !mimeTypes.toString().startsWith( "image" ) ) {
//                throw new FormValidationException( "Le fichier envoyé n'est reconnu comme une image" );
//            }
//        }
//        // if ( nomImage == null || nomImage.equals( "" ) || contenu == null ) {
//        // throw new Exception( "Merci de sélectionner un fichier à envoyer." );
//        // }
//    }

	private void validerEmail(String email) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		doublon = clientDao.trouverEmail(email);
		if (doublon != null) {
			throw new FormValidationException(
					"ce mail est déjà utilisé par : " + doublon.getPrenom() + " " + doublon.getNom());
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
		doublon = clientDao.trouverEmail(email);
		if (doublon != null && !doublon.getId().equals(id)) {
			throw new FormValidationException(
					email + " est déjà utilisé par : " + doublon.getPrenom() + " " + doublon.getNom());
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

	public Client getDoublon() {
		return doublon;
	}
}
