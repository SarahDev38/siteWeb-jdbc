jQuery(document).ready(function(){
	var $boutonModifier = $('#modifier');
	var urlModifierproduit = "modifierProduit?produit_id=";

	$boutonModifier.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( urlModifierproduit.concat( $(this).val() ) ) );
	});

	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}

});
