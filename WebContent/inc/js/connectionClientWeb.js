$(document).ready(function() {
	var $url = $('#urlDemande');
	var $boutonSeConnecter = $('#boutonSeConnecter');
	var $email = $('#email');
	var $mdp = $('#motdepasse');
	
	$boutonSeConnecter.prop('disabled', true);
	
	if ($url.val().trim() != ''){ 
		$url.val(location.href);
	} 
	
	$mdp.keyup(function(){
		if ($email.val() !=''){
			$boutonSeConnecter.prop('disabled', false);
		}
	});

});
