jQuery(document).ready(function(){
	var $champ =$('.champ');
	var $boutonValider = $('#boutonValider');
	var $boutonReset = $('#reset');
	var $divDoublon = $('#divDoublon');
	var doublon = $('#doublon');
	var $radioChoixDoublon = $('input[name=choixDoublon]:radio');
	var email = $('#email');
	var $resultat = $('#resultat');
	
	var $divDoublon = $('#divDoublon');
	let checkKeys = ['nom', 'prenom', 'email', 'tel', 'adresse', 'codePostal', 'ville'];
	let checkValues = [true, true, true, true, true, true, true];

	$champ.each(function(){
		check_longueur_champ_onNewPage( $(this) );
	});
	check_boutonValider();

	$champ.keyup(function(){
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonValider();
	});

	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
	});

	$radioChoixDoublon.click(function(){
		if ( $(this).val() == "redirect" ) {
			$(location).attr( 'href', changeURL( doublon.val() ) );
		} else {
			email.val('');
			$resultat.text('');
			$divDoublon.hide();
			email.closest('td').next().find('span').text( '' );
			email.closest('td').next().find('img').hide();
			email.css( {border : 'solid 1px gray', color : 'black'} );
			setValueFromKey('email', false);
		}
	});

	$boutonReset.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( '' ) );
	});

	function check_longueur_champ_onKeyUp( ceChamp ){
		if( check_champ( ceChamp ) ) {
			ceChamp.css( {border : 'solid 1px green', color : 'black'} );
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
				ceChamp.css( {border : 'solid 1px green', color : 'black'} );
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
				ceChamp.css( {border : 'solid 1px green', color : 'black'} );
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
		for (var i=0; i<7; i++) {
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

	function changeURL(idDoublon){
		let currentUrl = location.href;
		let index = currentUrl.indexOf('nouveauClient');
		chemin = currentUrl.slice(0,index);
		if (idDoublon ==''){
			chemin = chemin + 'nouveauClient' ;
			return chemin;
		} else {
			chemin = chemin + 'modifierClient?client_id=' + idDoublon ;
			return chemin;
		}
	}

	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
});