jQuery(document).ready(function(){
	var idClient = $('#id').val();
	var $champ=$('.modif');
	var $old=$('.old');
	var $boutonValider = $('#boutonValider');
	var $boutonAnnulerModif = $('#AnnulerModif');
	var $boutonRetourInfos = $('#retourInfos');

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
	var angleRotation=0;

	var cheminDossierStockage = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/clients/" ;
	var urlModifierClient = "modifierClient?client_id=";
	var urlInfosClient = "informationsClient?client_id=";
	
	let checkKeys = ['nom', 'prenom', 'email', 'tel', 'adresse', 'codePostal', 'ville'];
	let checkValues = [true, true, true, true, true, true, true];

	$old.hide();

	$champ.each(function(){
		if ($(this).val().trim() != $(this).nextAll('input').first().val().trim() ){
			$(this).css( {color : 'darkOrange'} );
			$(this).nextAll('input').first().show();
		} else {
			$(this).nextAll('input').first().hide();
		}
	});

	$champ.keyup(function(){
		if ($(this).val() != $(this).nextAll('input').first().val()){
			$(this).css( {color : 'darkOrange'} );
			$(this).nextAll('input').first().show();
		} else {
			$(this).css( {color : 'black'} );
			$(this).nextAll('input').first().hide();
		}
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonValider();
	});

	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
	});
	

	$boutonAnnulerModif.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierClient.concat( idClient ) ) );
	});
	
	$boutonRetourInfos.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlInfosClient.concat( idClient ) ) );
	});


// 	TRAITEMENT DES IMAGES
	if (imgOrigine !='null' && imgOrigine !='') { //IMAGE A AFFICHER AU DEPART
		angleRotation = imgAngle.val();
		cheminImage = cheminDossierStockage.concat( imgOrigine.trim() );
		$image.attr( "src", cheminImage );
		(angleRotation == 0) ? $image.removeClass() : $image.attr( "class", "rotate".concat(angleRotation) );
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
		$image.attr( "src", "" );
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
		chemin = $(this).val().trim();
		$image.attr( "src", chemin );
		if( chemin.trim() != '') {
			imgSupprimee.val("false");
		}
		imgModifiee.val("true");
		$divImg.show()
		$divModifierImg.show();
		$divCheminImg.show();
		$divAjouterImg.hide();
	});

	$boutonGaucheImg.click(function() {
		angleRotation = (imgAngle.val() + 9) % 12; //9 -> 270°(horloge)
		(angleRotation == 0) ? $image.removeClass() : $image.attr( "class", "rotate".concat(angleRotation) );
		imgAngle.val(angleRotation);
	});

	$boutonDroiteImg.click(function() {
		angleRotation = (imgAngle.val() + 3) % 12; //3 -> 90°
		(angleRotation == 0) ? $image.removeClass() : $image.attr( "class", "rotate".concat(angleRotation) );
		imgAngle.val(angleRotation);
	});

// FONCTIONS
	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}
	function check_longueur_champ_onKeyUp( champ ){
		if( check_champ( champ ) ) {
			champ.css( {border : 'solid 1px green', color : 'green'} );
			setValueFromKey(champ.attr('name'), true);
		} else {
			champ.css( {border : 'solid 1px gray', color : 'black'} );
			setValueFromKey(champ.attr('name'), false);
		}
		champ.closest('td').next().find('img').hide();
		champ.closest('td').next().find('span').text('');
	}
	function check_longueur_champ_onChange( champ ){
		var name = champ.attr('name');
		if( champ.val().trim() != '' && !check_champ( champ ) ) {
			champ.css( {border : 'solid 2px red', color : 'red'} );
			champ.closest('td').next().find('img').show();
			champ.closest('td').next().find('span').attr('class', 'erreur');
			champ.closest('td').next().find('span').text( set_TextError( champ) );
			setValueFromKey(name, false);
		} else {
			if ( champ.val().trim() == '') {
				champ.css( {border : 'solid 1px gray', color : 'black'} );
			} else {
				champ.css( {border : 'solid 1px green', color : 'black'} );
				setValueFromKey(name, true);
			}
			champ.closest('td').next().find('img').hide();
			champ.closest('td').next().find('span').text('');
		}
	}
	function check_champ( champ){
		var name = champ.attr('name');
		var reponse=true;
		switch(name) {
		 case 'nom':
			reponse = check_longueurchamp( champ.val(), 2 );
			break;
		 case 'prenom':
			reponse = check_longueurchamp( champ.val(), 2 );
		  	break;
		 case 'email':
			reponse = check_email( champ.val() );
			break;
		 case 'tel':
				reponse = check_phoneNumber( champ.val() );
			break;
		 case 'adresse':
				reponse = check_longueurchamp( champ.val(), 5 );
			break;
		 case 'codePostal':
			reponse = check_codePostal( champ.val() );
			break;
		 case 'ville':
			reponse = check_longueurchamp( champ.val(), 3 );
			break;
		}    
		return reponse;
	}
	function set_TextError( champ){
		var name = champ.attr('name');
		var reponse;
		switch(name) {
		 case 'nom':
			reponse = name + ' trop court : 2 caractères minimum !';
			break;
		 case 'prenom':
				reponse = name + ' trop court : 2 caractères minimum !';
		  	break;
		 case 'email':
				reponse = 'adresse email invalide ! (x@x.xx)';
			break;
		 case 'tel':
				reponse = 'n° de téléphone invalide ! (10 chiffres minimum)';
			break;
		 case 'adresse':
				reponse = name + ' trop court : 5 caractères minimum !';
			break;
		 case 'codePostal':
			reponse = 'code Postal invalide ! (2 à 5 chiffres)';
			break;
		 case 'ville':
			reponse = name + ' trop court : 3 caractères minimum !';
			break;
		}    
        return reponse;
	}
	function check_longueurchamp( valeurChamp,longueur){
        return (valeurChamp.length > (longueur-1) );
	}
	function check_phoneNumber(telNumber){
		var regex = /^[+]?[0-9\./-\s]{10,20}$/;
        return regex.test(telNumber);
	}
	function check_codePostal(code){
		var regex = /^[0-9\s]{2,5}$/;
        return regex.test(code);
	}
	function check_email(email){
		var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
	}
	function check_boutonValider(){
		var formRempli='';
		$champ.each(function(){
			if ($(this).val().trim() == '') {
				formRempli='false';
			}
		});
		var checkAll = true;
		for (var i=0; i<7; i++) {
			if (checkValues[i] == false) {
				checkAll = false;
			}
		}
		if (formRempli != 'false' && checkAll == true){
			$boutonValider.prop('disabled', false);
			$boutonModifierImg.prop('disabled', false);
			$boutonSupprimerImg.prop('disabled', false);
			$boutonAjouterImg.prop('disabled', false);
		} else {
			$boutonValider.prop('disabled', true);
			$boutonModifierImg.prop('disabled', true);
			$boutonSupprimerImg.prop('disabled', true);
			$boutonAjouterImg.prop('disabled', true);
		}
	}
	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
});