jQuery(document).ready(function(){
	var id = $('#id').val();
	var $champ =$('.champ');
	var $old=$('.old');
	var email = $('#email');
	var montant = $('#montantCommande');
	var $resultat = $('#resultat');
	var $champCommande = $('.champCommande');
	var $divCommande = $('#Commande');

	var $radioNouveau = $('input[name=radioNouveau]:radio');
	var $radioNouveauClient = $("#radioNouveauClient");
	var $radioClientEnregistre = $("#radioClientEnregistre");
	var $selectChoixClient = $('#choixClient');
	var $radioCommande = $('.radioCommande');
	var $radioModePaiement = $('input[name=modePaiementCommande]:radio');
	var $radioStatutPaiement = $('input[name=statutPaiementCommande]:radio');
	var $radioModeLivraison = $('input[name=modeLivraisonCommande]:radio');
	var $radioStatutLivraison = $('input[name=statutLivraisonCommande]:radio');
	var $modePaiement = $('#modePaiementCommande');
	var $statutPaiement = $('#statutPaiementCommande');
	var $modeLivraison = $('#modeLivraisonCommande');
	var $statutLivraison = $('#statutLivraisonCommande');

	var $boutonAnnulerModif = $('#AnnulerModifClient');
	var $boutonPasserCommande = $('#PasserCommande');
	var $boutonValider = $('#boutonValider');
	var $boutonResetCommande = $('#resetCommande');
	var urlCommandeNouveauClient = "nouvelleCommande";
	var urlCommandeClient = "nouvelleCommande?client_id=";

	let checkKeys = ['nom', 'prenom', 'email', 'tel', 'adresse', 'codePostal', 'ville'];
	let checkValues = [true, true, true, true, true, true, true];
	
	$old.hide();
	if (id == '') {
		$radioNouveauClient.prop("checked", true );
		$radioClientEnregistre.prop("checked", false );
		$boutonPasserCommande.prop('disabled', true);
	} else {
		$radioNouveauClient.prop("checked", false );
		$radioClientEnregistre.prop("checked", true );
		$boutonPasserCommande.prop('disabled', false);
	}

	$boutonAnnulerModif.prop('disabled', true);
	$champ.each(function(){
		check_longueur_champ_onNewPage( $(this) );
		if (id != '') {
			if ($(this).val().trim() != $(this).nextAll('input').first().val().trim() ){
				$(this).nextAll('input').first().show();
				$boutonAnnulerModif.prop('disabled', false);
			} else {
				$(this).nextAll('input').first().hide();
			}
		} 
	});

	check_boutonPasserCommande();
	check_boutonValiderCommande();

	$champ.keyup(function(){
		check_longueur_champ_onKeyUp( $(this) );
		check_boutonPasserCommande();
		if (id != '') {
			if ($(this).val() != $(this).nextAll('input').first().val()){
				$(this).nextAll('input').first().show();

			} else {
				$(this).css( {color : 'black'} );
				$(this).nextAll('input').first().hide();
			}
			
		}
		$boutonAnnulerModif.prop('disabled', false);
		$resultat.text('');
		$divCommande.css( { display: 'none'} );
	});
	
	$champCommande.keyup(function(){
		check_boutonValiderCommande();
	});


	$champ.change(function(){
		check_longueur_champ_onChange( $(this) );
		check_boutonPasserCommande();
	});

	$radioNouveau.click(function(){
		if ( $(this).val() == "oui" ) {
			$(location).attr( 'href', changeURL( '' ) );
		} else {
			$radioNouveauClient.prop("checked", ( id == "" ) ? true : false );
			$radioClientEnregistre.prop("checked", ( id != "" ) ? true : false );
			$selectChoixClient.css( {"backgroundColor": "#F9CEBF", "color": "red"} );
		}
	});
	$selectChoixClient.on('click',function(){
		$selectChoixClient.css( {"backgroundColor": "white", "color": "black"} );
	});
	$selectChoixClient.on('change',function(){
		var clientIdValue = $(this).val();
		$(location).attr( 'href', changeURL( clientIdValue ) );
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
		$champ.each(function(){
			if (id =='') {
				$(this).val('');
			} else {
				$(this).val( $(this).nextAll('input').first().val() );
			}
			$(this).css( {border : 'solid 1px gray', color : 'black'} );
			$(this).nextAll('input').first().hide();
			$(this).closest('td').next().find('img').hide();
			$(this).closest('td').next().find('span').text('');
		});
	});

	$boutonResetCommande.click(function(e){
		e.preventDefault();
		$champCommande.each(function(){
			$(this).val('');
		});
		$radioCommande.each(function(){
			$(this).prop("checked", false);
		});
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
		alert(name);
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
					ceChamp.css( {border : 'solid 1px green', color : 'black'} );
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
	
	function check_boutonPasserCommande(){
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
			$boutonPasserCommande.prop('disabled', false);
		} else {
			$boutonPasserCommande.prop('disabled', true);
		}
	}

	function check_boutonValiderCommande(){
		$boutonValider.prop('disabled', false);
		$champCommande.each(function(){
			if ($(this).val().trim() == '') {
				$boutonValider.prop('disabled', true);
			}
		});
	}
	
	function setValueFromKey(key, newValue) {
		var index = checkKeys.indexOf(key);
		checkValues[index] = newValue;
	}
	
	function changeURL(client_id){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin.concat( ( client_id == '' )? urlCommandeNouveauClient : urlCommandeClient.concat(client_id) ) ;
		return chemin;
	}
});