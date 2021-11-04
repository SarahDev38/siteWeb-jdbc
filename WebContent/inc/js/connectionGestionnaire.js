$(document).ready(function() {
	var $url = $('#urlDemande');
	var $boutonSeConnecter = $('#boutonSeConnecter');
	var $mdp = $('#motdepasse');
	var $pseudoChoisi = $('#choixPseudo');
	var $pseudo = $('#pseudo');
	
	$boutonSeConnecter.prop('disabled', true);
	
	if ($url.val().trim() != ''){ 
		$url.val(location.href);
	} 
	
	$pseudoChoisi.on('change',function(){
		$(this).prop("selected", true);
		$pseudo.val( $('#choixPseudo option:selected').text().trim() );
		if ($pseudoChoisi.val() !='') {
			$mdp.val('');
			$mdp.focus();
		}
	});
	
	$mdp.keyup(function(){
		if ($pseudoChoisi.val() !=''){
			$boutonSeConnecter.prop('disabled', false);
		}
	});

});
