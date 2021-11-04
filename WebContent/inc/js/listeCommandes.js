$(document).ready(function() {
	$('#pasdecommandeclient').hide();	
	$('#pasdecommandegestionnaire').hide(); 
	$('#pasdecommandeclientgestionnaire').hide();
	var clientIdValue = ''; var gestionnaireIdValue = '';
	
	$('#choixIdClient').on('change',function(){
		clientIdValue = $(this).val();
		$(this).prop("selected", true);
		$('#idClient').val(clientIdValue);
		afficherTable();
	});
	
	$('#choixPseudo').on('change',function(){
		gestionnaireIdValue = $(this).val();
		$(this).prop("selected", true);
		var cur_selection = $('#choixPseudo option:selected').text().trim();
		$('#pseudo').val(cur_selection);
		afficherTable();
	});
	
	function afficherTable(){
		$('#pasdecommandeclient').hide();	
		$('#pasdecommandegestionnaire').hide(); 
		$('#pasdecommandeclientgestionnaire').hide();
		var nomClient = "class^='".concat(clientIdValue).concat("'");
		var nomUser = "class$='".concat(gestionnaireIdValue).concat("'");
		var nomClientUser = "class='".concat(clientIdValue).concat(" - ").concat(gestionnaireIdValue).concat("'");
		if (clientIdValue == '' && gestionnaireIdValue == '') {
			$( "tr" ).css("display","table-row");
		} else if (clientIdValue == '') {
			if ($( "tr[" + nomUser +"] " ).val() == undefined ) {
				$( "#myTable tr" ).css("display","none");
				$('#pasdecommandegestionnaire').show();

			} else {
				$( "#myTable tr" ).css("display","none");
				$( "tr[class='entete'] " ).css("display","table-row");
				$( "tr[" + nomUser +"] " ).css("display","table-row");
			}
		} else if (gestionnaireIdValue == '') {
			if ($( "tr[" + nomClient +"] " ).val() == undefined ) {
				$( "#myTable tr" ).css("display","none");
				$('#pasdecommandeclient').show();

			} else {
				$( "#myTable tr" ).css("display","none");
				$( "tr[class='entete'] " ).css("display","table-row");
				$( "tr[" + nomClient +"] " ).css("display","table-row");
			}
		} else {
			if ($( "tr[" + nomClientUser +"] " ).val() == undefined ) {
				$( "#myTable tr" ).css("display","none");
				$('#pasdecommandeclientgestionnaire').show();
			} else {
				$( "#myTable tr" ).css("display","none");
				$( "tr[class='entete'] " ).css("display","table-row");
				$( "tr[" + nomClientUser +"] " ).css("display","table-row");
			}
		}
	}
});