jQuery(document).ready(function() {
	var rubrique = $('#rubrique');
	var $radioRubrique = $('input[name=rubrique]:radio');
	var idGamme = $('#idGamme');
	var $nomGamme = $('#nomGamme');
	
	var $divGammeMaison = $('#divGammeMaison');
	var $divGammeLinge = $('#divGammeLinge');
	var $divGammeBijoux = $('#divGammeBijoux');
	var $divInputGamme = $('#divInputGamme');
	var $inputAutre = $('.inputAutre');
	var $SelectGamme = $('.choixGamme');
	var idReference = $('#idReference');
	var $nomReference = $('#nomReference');
	var $divReference = $('#divReference');
	var $divProduit = $('#divProduit');
	var $SelectReference = $('.choixRef');
	var $description = $('#description');

	var $divModifierImg = $('#modifierImage');
	var $divAjouterImg = $('#ajouterImage');
	var $divCheminImg = $('#cheminImage');
	var $divImg = $('#affichageImage');
	var $boutonModifierImg = $('#modifier');
	var $boutonSupprimerImg = $('#supprimer');
	var $boutonAjouterImg = $('#ajouter');
	var $inputCheminImg = $('#Image');
	var $boutonGaucheImg = $('#boutonGauche');
	var $boutonDroiteImg = $('#boutonDroite');
	var $image = $('#image');
	var imgOrigine = $('#ImageDepart').val();
	var imgSupprimee = $('#pasdimage');
	var imgModifiee = $('#imgmodifiee');
	var imgAngle = $('#AngleImage');
	var angleRotation = 0;

	var cheminDossierStockage = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/produits/";

	var $champ = $('.champ');
	var $boutonValider = $('#boutonValider');

	check_boutonValider();
	checkOpenReference();
	checkOpenProduit()

	$radioRubrique.click(function() {
		if (rubrique.val() != $(this).val()) {
			rubrique.val($(this).val());
			idGamme.val('');
			idReference.val('');
			$nomReference.val('autre...');
			$nomGamme.val('autre...');
			showDivGamme($(this).val());
			checkOpenReference();
			$nomReference.show();
			$nomGamme.show();
			
		}
		afficheSelectReference(rubrique.val(), idGamme.val());
	});

	$SelectGamme.on('change', function() {
		idGamme.val($(this).val());
		$nomGamme.val("autre...");
		afficheSelectReference(rubrique.val(), idGamme.val());
	});

	$SelectReference.on('change', function() {
		idReference.val($(this).val());
		$description.hide();
		$nomReference.val("autre...");
		check_boutonValider();
		checkOpenProduit();
	});

	$divInputGamme.keyup(function() {
		$SelectGamme.find("option:selected").prop('selected', false);
		idGamme.val('');
		afficheSelectReference(rubrique.val(), idGamme.val());
	});

	$nomReference.keyup(function() {
		$SelectReference.find("option:selected").prop('selected', false);
		idReference.val('');
		$description.show();
		check_boutonValider();
		checkOpenProduit();
	});

	$inputAutre.hover(
		function() {
			if ($(this).val().trim() == 'autre...') {
				$(this).val('');
			}
		}, function() {
			if ($(this).val().trim() == '') {
				$(this).val('autre...');
			}
		});

	$inputAutre.keydown(function() {
		if ($(this).val().trim() == 'autre...') {
			$(this).val('');
		}
	});

	$champ.keyup(function() {
		check_longueur_champ_onKeyUp($(this));
		check_boutonValider();
	});

	$champ.change(function() {
		check_longueur_champ_onChange($(this));
	});

	// 	TRAITEMENT DES IMAGES
	if (imgOrigine != 'null' && imgOrigine != '') { //IMAGE A AFFICHER AU DEPART
		angleRotation = imgAngle.val();
		cheminImage = cheminDossierStockage.concat(imgOrigine.trim());
		$image.attr("src", cheminImage);
		(angleRotation == 0) ? $image.removeClass() : $image.attr("class", "rotate".concat(angleRotation));
		$divImg.show()
		$divModifierImg.show();
		$divCheminImg.hide();
		$divAjouterImg.hide();
	} else { // PAS D IMAGE AU DEPART
		$divImg.hide();
		$divModifierImg.hide();
		$divCheminImg.show();
		$divAjouterImg.show();
	}

	$boutonSupprimerImg.click(function() {
		$divModifierImg.hide();
		$divImg.hide();
		$divAjouterImg.show();
		$divCheminImg.hide();
		imgSupprimee.val("true");
		$image.attr("src", "");
		imgModifiee.val("true"); // pour image de depart a supprimer dans dossier stockage
	});

	$boutonAjouterImg.click(function() {
		$divCheminImg.show();
		$inputCheminImg.click();
	});

	$boutonModifierImg.click(function() {
		$divCheminImg.show();
		$inputCheminImg.click();
	});

	$inputCheminImg.change(function() {
		angleRotation = 0;
		imgAngle.val(0);
		var chemin = 'DisplayImage?image='+ $(this).val().trim();
//		alert(chemin);
//		$image.attr( "src", chemin );
		if (chemin.trim() != '') {
			imgSupprimee.val("false");
		}
		imgModifiee.val("true");
//		$divImg.show()
		$divModifierImg.show();
		$divCheminImg.show();
		$divAjouterImg.hide();
	});

//	$boutonGaucheImg.click(function() {
//		angleRotation = (angleRotation + 9) % 12; //9 -> 270°(horloge)
//		(angleRotation == 0) ? $image.removeClass() : $image.attr("class", "rotate".concat(angleRotation));
//		imgAngle.val(angleRotation);
//	});
//
//	$boutonDroiteImg.click(function() {
//		angleRotation = (angleRotation + 3) % 12; //3 -> 90°
//		(angleRotation == 0) ? $image.removeClass() : $image.attr("class", "rotate".concat(angleRotation));
//		imgAngle.val(angleRotation);
//	});

	function checkOpenReference() {
		if (rubrique.val() != '') {
			$divReference.show();
		} else {
			$divReference.hide();
		}
	}

	function showDivGamme(rubrikNom) {
		$divGammeMaison.hide();
		$divGammeLinge.hide();
		$divGammeBijoux.hide();
		$divInputGamme.show();
		switch (rubrikNom) {
			case 'maison':
				$divGammeMaison.show();
				break;
			case 'linge':
				$divGammeLinge.show();
				break;
			case 'bijoux':
				$divGammeBijoux.show();
				break;
		}
	}
	function afficheSelectReference(RubrikNom, idGamme) {
		$('.selectReference').hide();
		if (idGamme.trim() != '') {
			$('.' + RubrikNom + '.' + RubrikNom + '_' + idGamme).show();
		} else {
			$('._' + RubrikNom).show();
		}
	}
	function check_longueur_champ_onKeyUp(ceChamp) {
		ceChamp.closest('td').next().find('img').hide();
		ceChamp.closest('td').next().find('span').text('');
	}

	function check_longueur_champ_onChange(ceChamp) {
		var name = ceChamp.attr('name');
		if (ceChamp.val().trim() != '' && !check_champ(ceChamp)) {
			ceChamp.css({ border: 'solid 2px red', color: 'red' });
			ceChamp.closest('td').next().find('img').show();
			ceChamp.closest('td').next().find('span').attr('class', 'erreur');
			ceChamp.closest('td').next().find('span').text(set_TextError(ceChamp));
		} else {
			if (ceChamp.val().trim() == '') {
				ceChamp.css({ border: 'solid 1px gray', color: 'black' });
				if (name == 'nomProduit') {
					ceChamp.css({ border: 'none', 'border-bottom': 'solid 1px gray', color: 'black' });
				}
			} else {
				ceChamp.css({ border: 'solid 1px green', color: 'black' });
				if (name == 'prixPublic' || name == 'prixAchat') {
					if (ceChamp.val().indexOf('.') != -1) {
						if (ceChamp.val().split(".")[1].length > 2) {
							ceChamp.val(parseFloat(ceChamp.val()).toFixed(2));
						}
					}
					if (ceChamp.val().indexOf('.') == -1) {
						ceChamp.val(parseFloat(ceChamp.val()).toFixed(2));
					}
				}
			}
			ceChamp.closest('td').next().find('img').hide();
			ceChamp.closest('td').next().find('span').text('');
		}
	}

	function check_champ(ceChamp) {
		var name = ceChamp.attr('name');
		var reponse = true;
		switch (name) {
			case 'nomProduit':
				reponse = check_longueurchamp(ceChamp.val(), 3);
				break;
			case 'prixPublic':
				reponse = check_currency(ceChamp.val());
				break;
			case 'prixAchat':
				reponse = check_currency(ceChamp.val());
				break;
			case 'qteDispo':
				reponse = check_nombre(ceChamp.val());
				break;
			case 'qteMin':
				reponse = check_nombre(ceChamp.val());
				break;
		}
		return reponse;
	}

	function set_TextError(ceChamp) {
		var name = ceChamp.attr('name');
		var reponse;
		switch (name) {
			case 'nomProduit':
				reponse = name + ' trop court : 3 caractères minimum !';
				break;
			case 'prixPublic':
				reponse = 'format numérique';
				break;
			case 'prixAchat':
				reponse = 'format numérique';
				break;
			case 'qteDispo':
				reponse = 'format numérique';
				break;
			case 'qteMin':
				reponse = 'format numérique';
				break;
		}
		return reponse;
	}

	function check_longueurchamp(valeurChamp, longueur) {
		return (valeurChamp.length > (longueur - 1));
	}
	function check_nombre(nombre) {
		var regex = /^[0-9\s]{1,7}$/;
		return regex.test(nombre);
	}
	function check_currency(montant) {
		var regex = /^[+]?(\d*\.?\d*)$/;
		return regex.test(montant);
	}

	function checkOpenProduit() {
		$divProduit.hide();
		let reponse = true;
		if ($nomReference.val().trim() == 'autre...'
			|| $nomReference.val().trim() == '') {
			reponse = false;
		}
		if (reponse || idReference.val() != '') {
			$divProduit.show();
		}
	}

	function check_boutonValider() {
		$boutonValider.prop('disabled', false);
		if (idReference == '' && $nomReference == '') {
			$boutonValider.prop('disabled', true);
		}
		$champ.each(function() {
			if ($(this).val().trim() == '') {
				$boutonValider.prop('disabled', true);
			}
		});
	}
});