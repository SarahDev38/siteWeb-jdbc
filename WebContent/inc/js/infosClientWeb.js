jQuery(document).ready(function(){
	var idClient = $('#id').val();
	var $boutonRetourListe = $('#retourListe');

	var urlModifierClient = "modifierClientWeb?client_id=";
	var urlListeClientsWeb = "listeClientsWeb";


	$boutonModifierClient.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierClient.concat( idClient ) ) );
	});

	$boutonRetourListe.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlListeClientsWeb ) );
	});

	
	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}
});