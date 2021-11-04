jQuery(document).ready(function(){
	var idClient = $('#id').val();
	var $divAffichageImg = $('#affichageImage');
	var imageClient = $('#ImageClient').val();

	var $boutonModifierImg = $('#modifierImage');
	var $boutonAjouterImg = $('#ajouterImage');
	var $boutonModifierClient = $('#modifierClient');
	var $boutonPasserCommande = $('#passerCommande');
	var $boutonRetourListe = $('#retourListe');

	var urlModifierClient = "modifierClient?client_id=";
	var urlNouvelleCommande = "nouvelleCommande?client_id=";
	var urlListeClients = "listeClients";

	if ( imageClient != 'null' && imageClient != '') {
		$divAffichageImg.show();$boutonModifierImg.show(); $boutonAjouterImg.hide();
	} else {
		$divAffichageImg.hide();$boutonAjouterImg.show(); $boutonModifierImg.hide();
	}

	$boutonAjouterImg.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierClient.concat( idClient ) ) );
	});

	$boutonModifierImg.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierClient.concat( idClient ) ) );
	});

	$boutonModifierClient.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierClient.concat( idClient ) ) );
	});

	$boutonPasserCommande.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlNouvelleCommande.concat( idClient ) ) );
	});

	$boutonRetourListe.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlListeClients ) );
	});

	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}
});