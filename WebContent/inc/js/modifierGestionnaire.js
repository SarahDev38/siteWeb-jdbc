jQuery(document).ready(function(){
	var idGestionnaire = $('#id').val();
	var $champ=$('.modif');
	var $inputMDP = $('#mdpAffiche');
	var $motdepasse = $('#motdepasse');
	var $confirm = $('.confirm');
	var $confirmation = $('#confirmation');
	var $erreurMDP = $('#erreurMDP');
	var $erreurConfirmation = $('#erreurConfirmation');
	var $imgNoCheckMDP = $('#imgNoCheckMDP');
	var $imgNoCheckConfirmation = $('#imgNoCheckConfirmation');
	var $imgCheckConfirmation = $('#imgCheckConfirmation');
	var $boutonValider = $('#boutonValider');
	var $boutonAnnulerModif = $('#AnnulerModif');
	var $boutonRetourInfos = $('#retourInfos');
	let checkKeys = [ 'nom', 'prenom', 'email', 'mdp'];
	let checkValues = [ true, true, true, true];

	$confirm.hide();
	$boutonValider.prop('disabled', true);

	$champ.keyup(function(){
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonValider();
	});

	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
	});

	$inputMDP.keyup(function(){
		$confirmation.val('');
		$confirmation.css("border","2px solid red");
		if( check_champ( $(this) ) ) {
			$confirmation.css( {border : 'solid 1px gray', color : 'black'} );
			$erreurConfirmation.text('');
			$imgNoCheckConfirmation.hide();
			$imgCheckConfirmation.hide();
			$confirm.show();
		} else {
			$confirm.hide();
		}
		$motdepasse.val( '' );
	});
	
	$inputMDP.change(function(){
		setValueFromKey('mdp', false);
		if( $(this).val().trim() != '' && !check_champ( $(this) ) ) {
			$(this).css( {border : 'solid 2px red', color : 'red'} );
			$imgNoCheckMDP.show();
			$erreurMDP.attr('class', 'erreur');
			$erreurMDP.text('mot de passe trop court : 3 caractères minimum !');
		} else {
			if ( $(this).val().trim() == '') {
				$(this).css( {border : 'solid 1px gray', color : 'black'} );
			} else {
				$(this).css( {border : 'solid 1px green', color : 'black'} );
			}
			$imgNoCheckMDP.hide();
			$erreurMDP.text('');
		}
	});

	$confirmation.keyup(function(){
		$imgNoCheckConfirmation.hide();
		$imgCheckConfirmation.hide();
		if( $(this).val().trim() == $inputMDP.val().trim() ) {
			$(this).css( {border : 'solid 1px green', color : 'black'} );
			$inputMDP.css( {border : 'solid 1px green', color : 'black'} );
			$erreurConfirmation.text('');
			$imgCheckConfirmation.show();
			$motdepasse.val( $(this).val() );
			setValueFromKey('mdp', true);
		} else {
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$inputMDP.css( {border : 'solid 1px gray', color : 'black'} );
			$imgCheckConfirmation.hide();
			setValueFromKey('mdp', false);
		}
		check_boutonValider();
	});

	$confirmation.change(function(){
		setValueFromKey('mdp', false);
		$imgNoCheckConfirmation.hide();
		$imgCheckConfirmation.hide();
		if( $(this).val().trim() != $motdepasse.val().trim() ) {
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$imgNoCheckConfirmation.show();
			$erreurConfirmation.attr('class', 'erreur');
			$erreurConfirmation.text('Confirmez à nouveau le mot de passe !')
			$(this).val('');
		} else if ( $(this).val().trim() == '') {
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$erreurConfirmation.text('');
		} else {
			$(this).css( {border : 'solid 1px green', color : 'black'} );
			$erreurConfirmation.text('');
			$imgCheckConfirmation.show();
			setValueFromKey('mdp', true);
		}
	});

	$('#choixPseudo').on('change',function(){
		var gestionnaireIdValue = $(this).val();
		$(location).attr( 'href', changeURL( "modifierGestionnaire?gestionnaire_id=".concat( gestionnaireIdValue ) ) );
	});
	
	$boutonAnnulerModif.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( "modifierGestionnaire?gestionnaire_id=".concat( idGestionnaire ) ) );
	});
	
	$boutonRetourInfos.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( "informationsGestionnaire?gestionnaire_id=".concat( idGestionnaire ) ) );
	});
	
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

	function check_longueur_champ_onChange( ceChamp ){
		var name = ceChamp.attr('name');
		if( ceChamp.val().trim() != '' && !check_champ( ceChamp ) ) {
			ceChamp.css( {border : 'solid 2px red', color : 'red'} );
			ceChamp.closest('td').next().find('img').show();
			ceChamp.closest('td').next().find('span').attr('class', 'erreur');
			ceChamp.closest('td').next().find('span').text( set_TextError( champ) );
			setValueFromKey(name, false);
		} else {
			if ( ceChamp.val().trim() == '') {
				ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
			} else {
				ceChamp.css( {border : 'solid 1px green', color : 'black'} );
				setValueFromKey(name, true);
			}
			champ.closest('td').next().find('img').hide();
			champ.closest('td').next().find('span').text('');
		}
	}

	function check_champ( ceChamp){
		var name = ceChamp.attr('name');
		var reponse=true;
		switch(name) {
		 case 'pseudo':
				reponse = check_longueurchamp( ceChamp.val(), 3 );
			break;
		 case 'nom':
			reponse = check_longueurchamp( ceChamp.val(), 2 );
			break;
		 case 'prenom':
			reponse = check_longueurchamp( ceChamp.val(), 2 );
		  	break;
		 case 'email':
			reponse = check_email( ceChamp.val() );
			break;
		 case 'mdp':
			reponse = check_longueurchamp( ceChamp.val(), 3 );
			break;
		}    
        return reponse;
	}

	function set_TextError( ceChamp){
		var name = ceChamp.attr('name');
		var reponse;
		switch(name) {
		 case 'pseudo':
			reponse = name + ' trop court : 3 caractères minimum !';
			break;
		 case 'nom':
				reponse = name + ' trop court : 2 caractères minimum !';
			break;
		 case 'prenom':
				reponse = name + ' trop court : 2 caractères minimum !';
		  	break;
		 case 'email':
				reponse = 'adresse email invalide ! (x@x.xx)';
			break;
		 case 'mdp':
				reponse = name + ' trop court : 3 caractères minimum !';
			break;
		}    
        return reponse;
	}

	function check_longueurchamp( valeurChamp,longueur){
        return (valeurChamp.length > (longueur-1) );
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
		if ($motdepasse.val().trim() == '') {
			formRempli='false';
		}
		var checkAll = true;
		for (var i=0; i<4; i++) {
			if (checkValues[i] == false) {
				checkAll = false;
			}
		}
		if (formRempli != 'false' && checkAll == true){
			$boutonValider.prop('disabled', false);
		} else {
			$boutonValider.prop('disabled', true);
		}
	}
	
	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
});