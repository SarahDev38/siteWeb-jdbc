<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="URLaccueil" value="Accueil" />
<c:url var="URLconnectionGestionnaire" value="connectionGestionnaire" />
<c:url var="URLdeconnectionGestionnaire" value="deconnectionGestionnaire" />
<c:url var="URLnouveauGestionnaire" value="nouveauGestionnaire" />
<c:url var="URLmodificationGestionnaire" value="modifierGestionnaire" />
<c:url var="URLprofilGestionnaire" value="informationsGestionnaire" />

<c:url var="URLconnectionClientWeb" value="connectionClientWeb" />
<c:url var="URLdeconnectionClientWeb" value="deconnectionClientWeb" />
<c:url var="URLinscriptionClientWeb" value="nouveauClientWeb" />
<c:url var="URLmodificationClientWeb" value="modifierClientWeb" />
<c:url var="URLpanierClientWeb" value="panierClientWeb" />

<c:url var="URLcreerProduit" value="nouveauProduit" />
<c:url var="URLlisteProduits" value="listeProduits" />
<c:url var="URLmodifierProduit" value="modifierProduit" />
<c:url var="URLmodifierInventaire" value="modifierInventaire" />

<c:url var="URLcreerClient" value="nouveauClient" />
<c:url var="URLprofilClient" value="informationsClient" />
<c:url var="URLmodifierClient" value="modifierClient" />
<c:url var="URLlisteClients" value="listeClients" />
<c:url var="URLcreerCommande" value="nouvelleCommande" />
<c:url var="URLlisteCommandes" value="listeCommandes" />
<c:url var="URLlisteGestionnaires" value="listeGestionnaires" />
<c:url var="URLnousContacter" value="nousContacter" />


<div id="bandeauBlanc">
	<table style="top:0;height:100px;width:100%">
	<tr>

		<td rowspan=2 style="width: 150px;word-wrap: break-word;"><button style="background-color: white;border: none;max-width:200px;cursor:pointer;" onclick="location.href = '${URLaccueil}'" type="button" ><img src="inc/icones/logoSite.png" alt="rechercher" width=100% style="min-width:110px;max-width:200px;"></button></td>

		<td style="min-width: 170px;word-wrap: break-word;">
			<table style="background-color:white; text-align:right;height:40px;width:100%"><tr>
				<td style="border-bottom:1px solid black;"><input type="text" id="inputRecherche" style="height:1.5em;text-align:right;border:none;color:gray;font-size:16px;font-style: italic;width:100%" value="je recherche..."></td>
				<td><button id="boutonRecherche" style='background-color: white; border: none;' type="button" ><img src="inc/icones/rechercher.png" alt="rechercher" height=22px width=22px></button></td></tr>
			</table>
		</td>

		<td>
			<div class="divHeader">
				<div>
					<button style="background-color: white;border: none;" type="button" class="boutonConnection">
						<img src="inc/icones/connect.png" alt="se connecter" height=40px width=40px>
					</button>
				</div>
				<div class="divDropDown"> 
					<div class="divFleche"></div>
					<div class="divConnection" style="padding-top:0em;"><span style="font-size: 80%;">espace client</span>
					<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>
						<div style=" width : 90%; margin: 0 auto;background-color: white;">
							<p style="color: black; font-size: 60%;text-align:left;background-color: white;" ${sessionScope.sessionClient == null ? '':'hidden="hidden"'}><i>Déjà enregistré ?</i></p>
							<button type="button" class="boutonChoix" onclick="location.href = '${URLconnectionClientWeb}'" ${sessionScope.sessionClient == null ? '':'hidden="hidden"'} style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>Se connecter</button>
							<button type="button" class="boutonChoix" onclick="location.href = '${URLdeconnectionClientWeb}'" ${sessionScope.sessionClient != null ? '':'hidden="hidden"'}style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>Se déconnecter</button>
						</div>
						<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>
						<div style=" width : 90%; margin: 0 auto;background-color: white;">
							<p style="color: black; font-size: 60%;text-align:left;background-color: white;"><i>Nouveau client ?</i></p>
								<button type="button" class="boutonChoix" onclick="location.href = '${URLinscriptionClientWeb}'" style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>S'inscrire</button>
						</div>
						<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>
						<div style=" width : 90%; margin: 0 auto;background-color: white;padding-top:0.5em; padding-bottom:0.5em;">
							<p>   </p>
							<p style="background-color: white;text-align:left;"><a href="<c:out value='${URLmodificationClientWeb}'/>" >voir mon profil</a></p>
							<p></p>
							<p style="background-color: white;text-align:left;"><a href="<c:out value='${URLpanierClientWeb}'/>" >voir mon panier</a></p>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div>
					<button style='background-color: white; border: none;' type="button" class="boutonConnection">
						<img src="inc/icones/connectGestionnaire.png" alt="se connecter" height=40px width=40px>
					</button>
				</div>
				<div class="divDropDown">
					<div class="divFleche"></div>
					<div class="divConnection" style="padding-top:0em;"><span style="font-size: 80%;">espace gestionnaire</span>
					<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>
						<div style=" width : 90%; margin: 0 auto;background-color: white;">
							<p style="color: black; font-size: 60%;text-align:left;background-color: white;" ${sessionScope.sessionGestionnaire == null ? '':'hidden="hidden"'}><i>Déjà enregistré ?</i></p>
							<button type="button" class="boutonChoix" onclick="location.href = '${URLconnectionGestionnaire}'" ${sessionScope.sessionGestionnaire == null ? '':'hidden="hidden"'} style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>Se connecter</button>
							<button type="button" class="boutonChoix" onclick="location.href = '${URLdeconnectionGestionnaire}'" ${sessionScope.sessionGestionnaire != null ? '':'hidden="hidden"'}style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>Se déconnecter</button>
						</div>


						<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>
						<div style=" width : 90%; margin: 0 auto;background-color: white;">
							<p style="color: black; font-size: 60%;text-align:left;background-color: white;"><i>Nouveau gestionnaire ?</i></p>
								<button type="button" class="boutonChoix" onclick="location.href = '${URLnouveauGestionnaire}'" style='background-color: #F96552; line-height: 22px;color: white;width : 10em;height:2.5em;font-size:20px; border:none'>S'inscrire</button>
						</div>



						<p></p><div style=" width : 90%; margin: 0 auto;;border-top:1px solid black;"></div><p></p>

						<div style=" width : 90%; margin: 0 auto;background-color: white;padding-top:0.5em; padding-bottom:0.5em;">
							<p >   </p>
							<p style="background-color: white;text-align:left;"><a href="<c:out value='${URLprofilGestionnaire}'/>" >voir mon profil</a></p>
							<p></p>
							<p style="background-color: white;text-align:left;"><a href="<c:out value='${URLlisteCommandes}'/>" >commandes en attente</a></p>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</td>

		<td  style="max-width:150px;" colspan=2><div id="connection">
			<c:if test="${sessionScope.sessionGestionnaire != null}">
				<input type="text" style="color: #03ac13;text-align:right;font-style: italic;width:100%;pointer-events: none;border:none;right:0;" value="Gestionnaire connecté(e) : "/>
				<input type="text" style="color: #03ac13;text-align:right;font-style: italic;width:100%;pointer-events: none;border:none;right:0;" value="${sessionScope.sessionGestionnaire.pseudo}"/>
			</c:if> <br/>
			<c:if test="${sessionScope.sessionClient != null}">
				<input type="text" style="color: #03ac13;text-align:right;font-style: italic;width:100%;pointer-events: none;border:none;right:0;" value="Client connecté(e) :"/>
				<input type="text" style="color: #03ac13;text-align:right;font-style: italic;width:100%;pointer-events: none;border:none;right:0;" value="${sessionScope.sessionClient.prenom} ${sessionScope.sessionClient.nom}"/>
<%-- 				<p style="color: #03ac13;text-align:right;"><i>Client connecté(e) : <br/><c:out value="${sessionScope.sessionClient.prenom} ${sessionScope.sessionClient.nom}" /></i></p> --%>
			</c:if> 
			
			<c:if test="${sessionScope.sessionGestionnaire == null && sessionScope.sessionClient == null}">
				<input type="text" style="color: red;text-align:right;font-style: italic;width:100%;pointer-events: none;border:none;right:0;" value="vous n'êtes pas connecté(e)"/>
<!-- 				<p style="color: red;text-align:right;"><i>vous n'êtes pas connecté(e)</i></p> -->
			</c:if></div>
		</td>
	</tr>


	<tr style="height:100px;vertical-align:top;"> <td colspan=5>
		<table class="divHeaderGestionnaire" ${(sessionScope.sessionGestionnaire != null  && sessionScope.sessionClient == null)? '':'hidden="hidden"'} style="width:100%"> <tr> 
	
		<td>
			<div class="divHeader">
				<div ><button type="button"  class="boutonMenu">Stocks</button></div>
				<div class="divDropDownMenu">
				<table  style="width:100%">
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu" onclick="location.href = '${URLlisteProduits}'">inventaire</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLmodifierInventaire}'">mise à jour<br/>inventaire</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">commandes produits<br/>à renouveler</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">historique<br/>des ventes</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Produits</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu" onclick="location.href = '${URLcreerProduit}'">nouveau<br/>produit</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu">mise à jour<br/>produit</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu" onclick="location.href = '${URLlisteProduits}'">liste des<br/>produits</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu">commande<br/>fournisseurs</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button" class="boutonSousMenu">historique<br/>des ventes</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Clients</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLcreerClient}'">nouveau<br/>client</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLlisteClients}'">liste des<br/>clients</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLmodifierClient}'">mise à jour</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">mailing</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Commandes</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLcreerCommande}'">nouvelle<br/>commande</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLlisteCommandes}'">liste des<br/>commandes</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">mise à jour</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>
		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Gestionnaires</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLinscriptionGestionnaire}'">nouveau<br/>gestionnaire</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLmodificationGestionnaire}'">modifier infos<br/>gestionnaire</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu" onclick="location.href = '${URLlisteGestionnaires}'">liste des<br/>gestionnaires</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>
	</tr></table>
	
	
	<table class="divHeaderClientWeb" ${sessionScope.sessionClient != null ? '':'hidden="hidden"'} style="width:100%"> <tr> 
		<td>
			<div class="divHeader">
				<div ><button type="button"  class="boutonMenu">Maison</button></div>
				<div class="divDropDownMenu">
				<table style="width:100%">
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">savon</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">déco</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Mode</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">jupe</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">shorts</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">sandales</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">vestes</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td>
			<div class="divHeader">
				<div >
					<button type="button"  class="boutonMenu">Bijoux</button>
				</div>
				<div class="divDropDownMenu">
				<table>
					<tr>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">bracelets</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">colliers</button></td>
						<td><div class="carre"></div></td>
						<td><button type="button"  class="boutonSousMenu">bagues</button></td>
						<td><div class="carre"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</td>

		<td >
			<div class="divHeader">
				<div >
					<button type="button" class="boutonMenu" onclick="location.href = '${URLnousContacter}'">Nous contacter</button>
				</div>
				<div class="divDropDownMenu">
				</div>
			</div>
		</td>
		<td></td>
		</tr></table>
		
		</td>
	</tr>
	</table>
</div>

<script type="text/javascript"
	src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
jQuery(document).ready(function() {
var $inputRecherche = $('#inputRecherche');
var $boutonConnection = $('.boutonConnection');
var $boutonRecherche = $('#boutonRecherche');
var $divConnection = $('#divConnection');
var $boutonMenu = $('.boutonMenu');
var $divDropDown = $('.divDropDown');

var $divDropDownMenu = $('.divDropDownMenu');
var $boutonChoix = $('.boutonChoix');

var urlrechercheProduit = "rechercheProduits?recherche=";

$boutonConnection.mouseenter(function() {
	$divDropDown.hide();
	$divDropDownMenu.hide();
	$(this).css( {cursor:'pointer'} );
	$(this).parent().next().show();
});

$boutonConnection.mouseleave(function() {
	if ( !$(this).parent().parent().is(':hover') ) {
		$divDropDown.hide();
		$divDropDownMenu.hide();
	}
});


$boutonMenu.mouseenter(function() {
	$divDropDown.hide();
	$divDropDownMenu.hide();
	$(this).css( {cursor:'pointer'} );
	$(this).parent().next().show();
});

$divDropDown.hover(
	function(){ return; }, 
	function(){ $(this).hide();
});

$divDropDownMenu.hover(
		function(){ return; }, 
		function(){ $(this).hide();
	});

$inputRecherche.hover(
	function(){ 
		if ($inputRecherche.val().trim() == 'je recherche...'){
			$inputRecherche.val(' ');}
			$inputRecherche.focus();
		}, 
	function(){ 
		if ($inputRecherche.val().trim() == ''){
			$inputRecherche.val('je recherche...');}
	});
$inputRecherche.keydown(function(){
	if ( $(this).val().trim() == 'je recherche...'){
		$(this).val('');
	}
});

$inputRecherche.keypress(function (e) {
	  if (e.which == 13) {
		$boutonRecherche.click();
		return false;
	  }
	});
$boutonRecherche.click(function() {
	if ( $inputRecherche.val() != '' &&  $inputRecherche.val() != 'je recherche...' ){
		$inputRecherche.val( replaceSpec($inputRecherche.val().trim() ).replace(/["~!@#$%^&*\(\)_+=`{}\[\]\|\\:;'<>,.\/?"\- \t\r\n]+/g, '') ) ;
		var string = $inputRecherche.val().trim().split(" ");
		var recherche =string[0];
		for (let i = 1; i < string.length; i++) {
			recherche = recherche + '+' + string[i].trim();
		}
		$(location).attr( 'href', changeURL( urlrechercheProduit, recherche ) );
	}
});

var rules = {
		a:"àáâãäå",
		A:"ÀÁÂ",
		e:"èéêë",
		E:"ÈÉÊË",
		i:"ìíîï",
		I:"ÌÍÎÏ",
		o:"òóôõöø",
		O:"ÒÓÔÕÖØ",
		u:"ùúûü",
		U:"ÙÚÛÜ",
		y:"ÿ",
		c: "ç",
		C:"Ç",
		n:"ñ",
		N:"Ñ"
		}; 

function  getJSONKey(key){
	for (acc in rules){
		if (rules[acc].indexOf(key)>-1){return acc}
	}
}

function replaceSpec(Texte){
	regstring=""
	for (acc in rules){
		regstring+=rules[acc]
	}
	reg=new RegExp("["+regstring+"]","g" );
	return Texte.replace(reg,function(t){ return getJSONKey(t) });
	}

function changeURL(url, id){
	let currentUrl = location.href;
	let index = currentUrl.lastIndexOf("/");
	chemin = currentUrl.slice(0,index+1) + url + id;
	return chemin;
}

});
</script>
<style>
#bandeauBlanc {
	position: fixed;
	display:block;
	top:0;
	z-index: 10;
	height:180px;
 	width: 100%;
 	min-width:800px;
/* 	width:100vw; */
	right:0;
	background-color: white;
}

#logo{
	z-index: 9999;
	width: 40%;
	height:100%;
	left: 0;
}
#divRecherche {
	position: fixed;
	top: 0%;
	z-index: 9997;
	right: 60%;
	width: 20%;
}
#connection{
	padding-right:5em;
}

.divHeader {
	z-index: 9998;
/* 	width: 22em; */
	text-align:center;
	float:left;
}

.divDropDown {
	position: fixed;
	z-index: 9998;
	text-align: center;
	line-height: 3em;
	font-size: 180%;
	font-weight: bold;
	font-family: papyrus;
  	display:none; 
  	transform: translateX(-40%); 
}

.divDropDownMenu {
	position: fixed;
	z-index: 9998;
	text-align: left;
	line-height: 3em;
	font-size: 180%;
	font-weight: bold;
	font-family: papyrus;
  	display:none; 
  	border : none;
  	left:130px;
}
 
.divFleche {
	z-index: 9999;
	content: "";
	position: relative;
 	text-align:center;
	width: 0;
	height: 0;
	right:-50%;
	border-top: 13px solid transparent;
	border-right: 26px solid black;
	border-bottom: 13px solid transparent;
	transform: rotate(90deg) translateY(25%); 
}

.divConnection {
	background-color: white;
	border: 1px solid black;
	width:120%;
}


/****************************************************************************************************************/

.boutonMenu {
	background-color: white;
	border : none;
	font-size: 180%;
	font-weight: bold;
	font-family: papyrus;
}

.boutonSousMenu {
	background-color:rgba(0, 0, 0, 0);
	border : none;
	font-size: 70%;
	font-weight: bold;
	font-family: papyrus;
	margin-left : 0.1em;
	margin-right : 0.1em;
	padding-top:-0.5em;
	padding-bottom:0.5em;
	min-width: 7em;
	color:#505050;
	cursor:pointer;
	line-height:1.3em;
}

.carre {
    height: 12px;
    width: 12px;
    background: #505050;
    -ms-transform: rotate(45deg); /* Internet Explorer */
    -moz-transform: rotate(45deg); /* Firefox */
    -webkit-transform: rotate(45deg); /* Safari et Chrome */
    -o-transform: rotate(45deg); /* Opera */
}


/****************************************************************************************************************/

div#sideMenuHorizontal { /* bandeau du menu */
	width: 100vw;
	height: 1.8em;
	padding: 0.8em;
	background-color: white;
}

ul#menuHorizontal {
	margin: 0;
	padding: 0;
	text-align: center;
	z-index: 4;
}

ul#menuHorizontal a {
	display: block;
	width: 8.6em; /* attribuer la largeur aux éléments contenus dans li */
	height: 1.6em;
	padding: 0.2em 1em;
	color: black;
	background-color: white;
	font-family: papyrus;
	font-size: 18px;
	font-weight: bold;
	text-decoration: none;
}

ul#menuHorizontal li {
	position: relative;
	list-style: none;
	float: left;
	top: -1.0em;
	margin: 0.4em;
	padding: 0;
}

/* ul#menuHorizontal li:hover a { */
/* color:green; */
/* } */
ul#menuHorizontal li ul {
	position: absolute;
	margin: -0.2em;
	padding: 0;
	background-color: white;
	left: 0;
	display: none; /* cacher le sous-menu */
	z-index: 3;
}

ul#menuHorizontal li:hover ul { /* bloc sous menu qd hover menu */
	display: block; /* afficher le sous-menu par les navigaters modernes */
	top: 3.2em;
	border-width: 4px;
	border-style: solid;
	border-color: white #FC6E56 #FC6E56 #FF8C5C;
}

ul#menuHorizontal li ul li { /* chaque sous menu*/
	margin-bottom: 0.2em;
	height: 2.2em; /*distance entre menus*/
	line-height: 20px;
	padding-bottom: 0.3em;
}
</style>