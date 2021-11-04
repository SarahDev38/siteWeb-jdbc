jQuery(document).ready(function() {
	var $champ =$('.champ').not('#confirmation');
	var $allChamp = $('.champ');
	var $motdepasse = $('#motdepasse');
	var $confirm = $('.confirm');
	var $confirmation = $('#confirmation');
	var $erreurMDP = $('#erreurMDP');
	var $erreurConfirmation = $('#erreurConfirmation');
	var $imgNoCheckMDP = $('#imgNoCheckMDP');
	var $imgNoCheckConfirmation = $('#imgNoCheckConfirmation');
	var $imgCheckConfirmation = $('#imgCheckConfirmation');
	var $boutonValider = $('#boutonValider');
	let checkKeys = ['pseudo', 'nom', 'prenom', 'email', 'mdp'];
	let checkValues = [true, true, true, true, false];
	
	$confirm.hide();
	$boutonValider.prop('disabled', true);
	
	$champ.each(function(){
		check_longueur_champ_onChange( $(this) );
	});

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

	function check_longueur_champ_onKeyUp( champ ){
		if( check_champ( champ ) ) {
			champ.css( {border : 'solid 1px green', color : 'black'} );
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

	function check_champ( champ ){
		var name = champ.attr('name');
		var reponse=true;
		switch(name) {
		 case 'pseudo':
			reponse = check_longueurchamp( champ.val(), 3 );
			break;
		 case 'nom':
			reponse = check_longueurchamp( champ.val(), 2 );
			break;
		 case 'prenom':
			reponse = check_longueurchamp( champ.val(), 2 );
		  	break;
		 case 'email':
			reponse = check_email( champ.val() );
			break;
		 case 'mdp':
			reponse = check_longueurchamp( champ.val(), 5 );
			break;
		}    
        return reponse;
	}

	function set_TextError( champ){
		var name = champ.attr('name');
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
				reponse = name + ' trop court : 5 caractères minimum !';
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
		$allChamp.each(function(){
			if ($(this).val().trim() == '') {
				formRempli='false';
			}
		});
		var checkAll = true;
		for (var i=0; i<5; i++) {
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