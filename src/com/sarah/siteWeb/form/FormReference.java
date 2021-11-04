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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableReferenceDAO;
import com.sarah.siteWeb.entities.Gamme;
import com.sarah.siteWeb.entities.Gestionnaire;
import com.sarah.siteWeb.entities.Reference;
import com.sarah.siteWeb.entities.Rubrique;

public class FormReference {
	private TableReferenceDAO referenceDao;

	private static final String CHAMP_ID = "idReference";
	private static final String CHAMP_NOM = "nomReference";
	private static final String CHAMP_DESCRIPTION = "description";
	private static final String CHAMP_RUBRIQUE = "rubrique";

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

	public static final String ATT_SESSION_GESTIONNAIRE = "sessionGestionnaire";

	public FormReference(TableReferenceDAO referenceDao) {
		this.referenceDao = referenceDao;
	}

	public Reference creerReference(HttpServletRequest request, String chemin, Gamme gamme)
			throws IOException, ServletException {
		Reference reference = null;
		String nom = getValeurChamp(request, CHAMP_NOM);
		Long id = null;
		try {
			id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		} catch (NumberFormatException e) {
		}
		if (id != null) {
			reference = referenceDao.trouverId(id);
		} else if (!nom.equals("autre...")) {
			HttpSession session = request.getSession();
			Gestionnaire gestionnaire = (Gestionnaire) session.getAttribute(ATT_SESSION_GESTIONNAIRE);
			String description = getValeurChamp(request, CHAMP_DESCRIPTION);
			Rubrique rubrique = Rubrique.getRubriqueByNom(getValeurChamp(request, CHAMP_RUBRIQUE));
			try {
				reference = new Reference(nom, description, rubrique, gamme, gestionnaire);
				reference = referenceDao.creer(reference);
				enregistrerImage(request, reference, chemin);
				referenceDao.modifier(reference);
			} catch (NumberFormatException | DaoException e) {
			}
		}
		return reference;
	}

	public Reference modifierReference(HttpServletRequest request, String chemin, Rubrique rubrique, Gamme gamme)
			throws IOException, ServletException {
		Long id = Long.parseLong(getValeurChamp(request, CHAMP_ID));
		String nom = getValeurChamp(request, CHAMP_NOM);
		String description = getValeurChamp(request, CHAMP_DESCRIPTION);

		Reference reference = new Reference(nom, description, rubrique, gamme, null);
		reference.setId(id);
		reference.setImage("");

		if (chemin != null) {
			enregistrerImage(request, reference, chemin);
		} else {
			reference.setImage(referenceDao.trouverId(id).getImage());
		}
		try {
			referenceDao.modifier(reference);
		} catch (DaoException ignore) {
		}
		return reference;
	}

	public void enregistrerImage(HttpServletRequest request, Reference reference, String chemin) {
		String image = "";
		String ImageDepart = getValeurChamp(request, CHAMP_IMAGE_DEPART);
		String yaPasDimage = getValeurChamp(request, CHAMP_SUPPR_IMAGE);
		String onaChangeLimage = getValeurChamp(request, CHAMP_MODIF_IMAGE);
		int angleImage = 0;
		try {
			angleImage = Integer.parseInt(getValeurChamp(request, CHAMP_ANGLE_ROTATION));
		} catch (NumberFormatException ignore) {
		}

		if (yaPasDimage != null && yaPasDimage.equals("true")) {
			// pas ou plus d image
			try {
				if (!ImageDepart.equals("null") || ImageDepart != null) {
					File fichierAEffacer = new File(chemin + ImageDepart);
					fichierAEffacer.delete();
				}
			} catch (Exception e) {
			}
		} else {
			if (onaChangeLimage != null && onaChangeLimage.equals("false")) {
				image = ImageDepart;
			} else {
				try {
					if (!ImageDepart.equals("null") || ImageDepart != null) {
						File fichierAEffacer = new File(chemin + ImageDepart);
						fichierAEffacer.delete();
					}
				} catch (Exception e) {
				}

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
					nomFichier = String.valueOf(reference.getId()).concat("_").concat(nomFichier);
					image = nomFichier;
					try {
						copierFichier(contenuFichier, nomFichier, chemin);
						if (angleImage != 0) {
							rotationFichier(nomFichier, chemin, angleImage);
						}
					} catch (IOException e) {
						setErreur(CHAMP_FICHIER_IMAGE, "Erreur lors de l'écriture du fichier sur le disque.");
					}
				}

				if (!erreurs.isEmpty()) {
					resultat = "Échec de l'inscription : l'envoi du fichier n'a pu être effectué.";
				}
			}
		}
		reference.setImage(image);
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

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
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
}
