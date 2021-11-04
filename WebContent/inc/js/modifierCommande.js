jQuery(document).ready(function(){
	var $id = $('#id');
	var id = $('#id').val();
	var $textId = $('#textId');
	var idCommande = $('#idCommande').val();
	var oldId = $('#oldId').val();
	var $champ =$('.champ');
	var $old=$('.old');
	var email = $('#email');
	var montant = $('#montantCommande');
	var $resultat = $('#resultat');
	var $champCommande = $('.champCommande');

	var $radioNouveau = $('input[name=radioNouveau]:radio');
	var $radioClientNouveau = $("#radioClientNouveau");
	var $radioClientAutre = $("#radioClientAutre");
	var $radioClientAncien = $("#radioClientAncien");
	var $selectChoixClient = $('#choixClient');
	var $radioModePaiement = $('input[name=modePaiementCommande]:radio');
	var $radioStatutPaiement = $('input[name=statutPaiementCommande]:radio');
	var $radioModeLivraison = $('input[name=modeLivraisonCommande]:radio');
	var $radioStatutLivraison = $('input[name=statutLivraisonCommande]:radio');
	var $modePaiement = $('#modePaiementCommande');
	var $statutPaiement = $('#statutPaiementCommande');
	var $modeLivraison = $('#modeLivraisonCommande');
	var $statutLivraison = $('#statutLivraisonCommande');

	var $boutonValider = $('#boutonValider');
	var $boutonAnnulerModif = $('#boutonAnnuler');
	
	var urlModifierCommande = "modifierCommande?commande_id=";

	let checkKeys = ['nom', 'prenom', 'email', 'tel', 'adresse', 'codePostal', 'ville'];
	let checkValues = [true, true, true, true, true, true, true];
	
	$old.hide();

	$radioClientAncien.prop("checked", false );
	$radioClientNouveau.prop("checked", false );
	$radioClientAutre.prop("checked", false );
	if (id == '') {
		$radioClientNouveau.prop("checked", true );
	} else if (id == oldId) {
		$radioClientAncien.prop("checked", true );
	} else {
		$radioClientAutre.prop("checked", true );
	}

	$boutonAnnulerModif.prop('disabled', true);
	$champ.each(function(){
		check_longueur_champ_onNewPage( $(this) );
		if ($(this).val().trim() != $(this).nextAll('input').first().val().trim() ){
			$boutonAnnulerModif.prop('disabled', false);
			if (id == oldId) {
				$(this).nextAll('input').first().show();
			} else {
				$(this).nextAll('input').first().hide();
			}
		} 
	});

	check_boutonValider();

	$champ.keyup(function(){
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonValider();
		if ($(this).val().trim() != $(this).nextAll('input').first().val().trim() && $id.val() == oldId) {
			$(this).nextAll('input').first().show();
		} else {
			$(this).nextAll('input').first().hide();
		} 
		$resultat.text('');
		$boutonAnnulerModif.prop('disabled', false);
	});

	$champCommande.keyup(function(){
		check_boutonValider();
		$boutonAnnulerModif.prop('disabled', false);
	});

	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
	});

	$radioNouveau.click(function(){
		if ( $(this).val() == "nouveau" ) {
			$id.val('');
			$textId.val('nouveau');
			$champ.each(function(){
				$(this).val('');
				$(this).css( {border : 'solid 1px gray', color : 'black'} );
				$(this).nextAll('input').first().hide();
				$(this).closest('td').next().find('img').hide();
				$(this).closest('td').next().find('span').text('');
			});
			$boutonAnnulerModif.prop('disabled', true);
		} else if ( $(this).val() == "autre" ) {
			$selectChoixClient.css( {"backgroundColor": "#F9CEBF", "color": "red"} );
		} else  {
			$(location).attr( 'href', changeURL( idCommande, null ) );
		}
	});
	$selectChoixClient.on('click',function(){
		$selectChoixClient.css( {"backgroundColor": "white", "color": "black"} );
	});
	$selectChoixClient.on('change',function(){
		$(location).attr( 'href', changeURL( idCommande, ( $(this).val() == oldId ) ? null : $(this).val() ) );
	});

	$radioModePaiement.click(function(){
		$modePaiement.val( $(this).val() );
		check_boutonValiderCommande();
	});
	$radioStatutPaiement.click(function(){
		$statutPaiement.val( $(this).val() );
		check_boutonValiderCommande();
	});
	$radioModeLivraison.click(function(){
		$modeLivraison.val( $(this).val() );
		check_boutonValiderCommande();
	});
	$radioStatutLivraison.click(function(){
		$statutLivraison.val( $(this).val() );
		check_boutonValiderCommande();
	});

	montant.keyup(function(){
		$(this).css( {border : 'solid 1px gray', color : 'black'} );
		$(this).closest('td').next().find('img').hide();
		$(this).closest('td').next().find('span').text('');
		$boutonAnnulerModif.prop('disabled', false);
	});

	montant.change(function(){
		var regex = /^[+]?(\d*\.?\d*)$/;
		var valid = regex.test( montant.val() );
		if( !valid ) {
			$(this).closest('td').next().find('span').text( ' : le montant ('+ $(this).val() + ') doit être numérique !' );
			$(this).val('');
			$(this).css( {border : 'solid 2px red', color : 'red'} );
			$(this).closest('td').next().find('img').show();
			$(this).closest('td').next().find('span').attr('class', 'erreur');
		} else {
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$(this).closest('td').next().find('img').hide();
			$(this).closest('td').next().find('span').text('');
			if($(this).val().indexOf('.')!=-1){         
				if($(this).val().split(".")[1].length > 2){                
					$(this).val( parseFloat( $(this).val() ).toFixed(2) );
				}  
			}
			if($(this).val().indexOf('.')==-1){
				$(this).val( parseFloat( $(this).val() ).toFixed(2) );  
			}     
		}
	});

	$boutonAnnulerModif.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( idCommande, '' ) );
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
				if (id != '' && ceChamp.val() == ceChamp.nextAll('input').first().val() ) {
					ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
				} else {
					ceChamp.css( {border : 'solid 1px green', color : 'green'} );
				}
				setValueFromKey(name, true);
			}
			ceChamp.closest('td').next().find('img').hide();
			ceChamp.closest('td').next().find('span').text('');
		}
	}

	function check_longueur_champ_onNewPage ( ceChamp ){
		var name = ceChamp.attr('name');
			if ( name == 'email' && email.closest('td').next().find('span').text() != '' ) {
			ceChamp.css( {border : 'solid 1px red', color : 'red'} );
			setValueFromKey(name, false);
			ceChamp.closest('td').next().find('img').show();
		} else {
			ceChamp.css( {border : 'solid 1px gray', color : 'black'} );
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
	function check_codePostal(codeVal){
		var regex = /^[0-9\s]{2,5}$/;
        return regex.test(codeVal);
	}	
	function check_email(emailVal){
		var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(emailVal);
	}
	
	function check_boutonValider(){
		$boutonValider.prop('disabled', false);
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
			$champCommande.each(function(){
				if ($(this).val().trim() == '') {
					$boutonValider.prop('disabled', true);
				}
			});
		} else {
			$boutonValider.prop('disabled', true);
		}
	}

	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
	
	function changeURL(commande_id, client_id){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1) + urlModifierCommande + commande_id;
		chemin = chemin.concat( ( client_id == null )? '' : '&client_id='.concat(client_id) ) ;
		return chemin;
	}
});