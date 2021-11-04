$(document).ready(function() {
	var $divProduit = $('.divProduit');
	var urlinfosProduit = "modifierProduit?produit_id=";

	$divProduit.click(function(e) {
		e.preventDefault();
		$(location).attr( 'href', changeURL( $(this).children().first().val() ) );
	});
	
	function changeURL(id){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1) + urlinfosProduit + id;
		return chemin;
	}

});
