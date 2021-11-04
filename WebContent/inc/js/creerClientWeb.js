jQuery(document).ready(function(){
	var $champ =$('.champ').not('#confirmation').not('.bouton');
	var $allChamp = $(':input').not('.bouton');
	var $boutonValider = $('#boutonValider');
	var $boutonReset = $('#reset');
	var email = $('#email');
	var $motdepasse = $('#motdepasse');
	var $confirm = $('.confirm');
	var $confirmation = $('#confirmation');
	var $erreurMDP = $('#erreurMDP');
	var $erreurConfirmation = $('#erreurConfirmation');
	var $imgNoCheckMDP = $('#imgNoCheckMDP');
	var $imgNoCheckConfirmation = $('#imgNoCheckConfirmation');
	var $imgCheckConfirmation = $('#imgCheckConfirmation');

	let checkKeys = ['nom', 'prenom', 'email', 'tel', 'adresse', 'codePostal', 'ville', 'mdp'];
	let checkValues = [true, true, true, true, true, true, true, false];

	$confirm.hide();

	$champ.each(function(){
		check_longueur_champ_onNewPage( $(this) );
	});
	check_boutonValider();

	$allChamp.keyup(function(){
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonValider();
	});

	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
	});

	$motdepasse.keyup(function(){
		$confirmation.val('');
		if( check_champ( $(this) ) ) {
			$confirmation.css( {border : 'solid 1px gray', color : 'black'} );
			$erreurConfirmation.text('');
			$imgNoCheckConfirmation.hide();
			$imgCheckConfirmation.hide();
			$confirm.show();
		} else {
			$confirm.hide();
		}
	});

	$motdepasse.change(function(){
		setValueFromKey('mdp', false);
		if( $(this).val().trim() != '' && !check_champ( $(this) ) ) {
			$(this).css( {border : 'solid 2px red', color : 'red'} );
			$imgNoCheckMDP.show();
			$erreurMDP.attr('class', 'erreur');
			$erreurMDP.text( set_TextError( champ) );
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
		if( $(this).val().trim() == $motdepasse.val().trim() ) {
			$(this).css( {border : 'solid 1px green', color : 'black'} );
			$motdepasse.css( {border : 'solid 1px green', color : 'black'} );
			$erreurConfirmation.text('');
			$imgCheckConfirmation.show();
			setValueFromKey('mdp', true);
		} else {
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$motdepasse.css( {border : 'solid 1px gray', color : 'black'} );
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

	$boutonReset.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL('' ) );
	});

	function check_longueur_champ_onKeyUp( ceChamp ){
		if( check_champ( ceChamp ) ) {
			ceChamp.css( {border : 'solid 1px green', color : 'green'} );
			setValueFromKey(ceChamp.attr('name'), true);
		} else {
			ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
			setValueFromKey(ceChamp.attr('name'), false);
		}
		ceChamp.closest('td').next().find('img').hide();
		ceChamp.closest('td').next().find('span').text('');
	}

	function check_longueur_champ_onChange( ceChamp ){
		var name = ceChamp.attr('name');
		if( ceChamp.val().trim() != '' && !check_champ( ceChamp ) ) {
			ceChamp.css( {border : 'solid 2px red', color : 'red'} );
			ceChamp.closest('td').next().find('img').show();
			ceChamp.closest('td').next().find('span').attr('class', 'erreur');
			ceChamp.closest('td').next().find('span').text( set_TextError( ceChamp) );
			setValueFromKey(name, false);
		} else {
			if ( ceChamp.val().trim() == '') {
				ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
			} else {
				ceChamp.css( {border : 'solid 1px green', color : 'green'} );
				setValueFromKey(name, true);
			}
			ceChamp.closest('td').next().find('img').hide();
			ceChamp.closest('td').next().find('span').text('');
		}
	}

	function check_longueur_champ_onNewPage ( ceChamp ){
		var name = ceChamp.attr('name');
		if( ceChamp.val().trim() != '' && !check_champ( ceChamp ) ) {
			ceChamp.css( {border : 'solid 2px red', color : 'red'} );
			ceChamp.closest('td').next().find('img').show();
			ceChamp.closest('td').next().find('span').attr('class', 'erreur');
			ceChamp.closest('td').next().find('span').text( set_TextError( ceChamp) );
			setValueFromKey(name, false);
		} else if ( name == 'email' && email.closest('td').next().find('span').text() != '' ) {
			ceChamp.css( {border : 'solid 1px red', color : 'red'} );
			setValueFromKey(name, false);
			ceChamp.closest('td').next().find('img').show();
		} else {
			if ( ceChamp.val().trim() == '') {
				ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
			} else {
				ceChamp.css( {border : 'solid 1px green', color : 'green'} );
				setValueFromKey(name, true);
			}
			ceChamp.closest('td').next().find('img').hide();
			ceChamp.closest('td').next().find('span').text('');
		}
	}

	function check_champ( ceChamp){
		var name = ceChamp.attr('name');
		var reponse=true;
		switch(name) {
		 case 'nom':
			reponse = check_longueurchamp( ceChamp.val(), 2 );
			break;
		 case 'prenom':
			reponse = check_longueurchamp( ceChamp.val(), 2 );
		  	break;
		 case 'email':
			reponse = check_email( ceChamp.val() );
			break;
		 case 'tel':
				reponse = check_phoneNumber( ceChamp.val() );
			break;
		 case 'adresse':
				reponse = check_longueurchamp( ceChamp.val(), 5 );
			break;
		 case 'codePostal':
			reponse = check_codePostal( ceChamp.val() );
			break;
		 case 'ville':
			reponse = check_longueurchamp( ceChamp.val(), 3 );
			break;
		}    
        return reponse;
	}

	function set_TextError( ceChamp){
		var name = ceChamp.attr('name');
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
		for (var i=0; i<8; i++) {
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

	function changeURL(id){
		let currentUrl = location.href;
		let index = currentUrl.indexOf('nouveauClientWeb');
		chemin = currentUrl.slice(0,index);
		if (id ==''){
			chemin = chemin + 'nouveauClientWeb' ;
			return chemin;
		} else {
			chemin = chemin + 'modifierClientWeb?client_id=' + id ;
			return chemin;
		}
	}

	function getValueFromKey(key) {
		var index = checkKeys.indexOf(key);
		return checkValues[index];
	}

	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
});