jQuery(document).ready(function(){
	var $boutonModifier = $('#boutonModifier');
	var $boutonListe = $('#boutonListe');
	var idUser = $('#id').val();

	$boutonModifier.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( "modifierGestionnaire?gestionnaire_id=".concat( idUser ) ) );
	});
	
	$boutonListe.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( "listeGestionnaires" ) );
	});
	
	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}
});