jQuery(document).ready(function(){
	var $boutonAfficher = $("button[name='boutonAfficher']")

	$boutonAfficher.click(function(e){
		e.preventDefault();
		$(location).attr( 'href', changeURL( "informationsGestionnaire?gestionnaire_id=".concat( $(this).val().trim() ) ) );
	});
	
	function changeURL(page){
		let currentUrl = location.href;
		let index = currentUrl.lastIndexOf("/");
		chemin = currentUrl.slice(0,index+1);
		chemin = chemin + page ;
		return chemin;
	}
});