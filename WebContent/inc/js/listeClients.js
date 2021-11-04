$(document).ready(function() {
	var $boutonPhoto = $("button[name='boutonPhoto']");
	var $divPhoto = $('#divPhoto');
	var $divPasPhoto = $('#divPasPhoto');
	var $listeVide = $('#listeVide');
	var $descriptionPhoto = $("#description");
	var $photo = $("#photo")
	$listeVide.hide();
	$divPhoto.hide();
	
	$boutonPhoto.hover(function(){
		if ($(this).val() =='') {
			$(this).css ("background-color", "silver");
		}
	});

	$boutonPhoto.click(function(e){
		e.preventDefault();
		if ($(this).val() !='') {
			angleRotation = 0;
			var nomImage = $(this).val().trim();
			$divPhoto.show();
			$photo.removeAttr("src").attr( "src", "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/clients/".concat( nomImage ) );
			$photo.attr( "alt", nomImage );
			$photo.css ({"width": "auto" , "height" : "auto" }) ;

			let h0 = $photo[0].clientHeight;
			let w0 = $photo[0].clientWidth;
			let w1 = (h0 >= w0)? ( w0 * 500 / h0 ) : 500 ;
			let h1 = (h0 >= w0)? 500 : ( h0 * 500 / w0 ) ;
			$photo.css ({"width": w1.toString() , "height" : h1.toString() }) ;
			
			(angleRotation == '0') ? $photo.removeClass() : $photo.attr( "class", "rotate".concat(angleRotation) );
			$descriptionPhoto.val($(this).attr('id'));
 			$('#linkPhoto').prop("href", "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/clients/"+nomImage);
			$divPasPhoto.hide();
		} else {// pas d image
			$divPhoto.hide();
			$divPasPhoto.show();
		}
	});
	
	$(document).click(function(event){   
		if($(event.target).attr('class') == "boutonAction")
	          return;
		$divPasPhoto.hide();
		$divPhoto.hide();
	});

	$('#choixPseudo').on('change',function(){
		$listeVide.hide();
		var gestionnaireIdValue = $(this).val();
		$(this).prop("selected", true);
		var cur_selection = $('#choixPseudo option:selected').text().trim();
		$('#pseudo').val(cur_selection);
		
		var nom = "class='".concat(gestionnaireIdValue).concat("'");
//		var nom2 = "tr.".concat(gestionnaireIdValue);
		if (gestionnaireIdValue == '') {
			$( "tr" ).css("display","table-row");
		} else {
			if ($( "tr[" + nom +"] " ).val() == undefined ) {
//			if ($( nom2 ).val() == undefined ) {
				$( "tr" ).css("display","none");
				$listeVide.show();
			} else {
				$( "tr" ).css("display","none");
				$('.entete').css("display","table-row");
				$( "tr[" + nom +"] " ).css("display","table-row");
			}
		}
	});
});