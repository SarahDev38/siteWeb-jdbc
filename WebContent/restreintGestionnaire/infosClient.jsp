<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/infosClient.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<c:if test="${!empty form.erreurs}">
	<c:redirect url="/nouveauClient"  />
</c:if>
<c:if test="${!empty form }">
	<div class="content" style ="font-size: 16px;height:20px; max-width:750px;">
		<span class="succes"><c:out value="${form.resultat}" /></span>
	</div>
</c:if>

<div class="content" style ="max-height:620px; max-width:750px;">
<c:if test="${empty form.erreurs }">
	<fieldset>
	<legend>informations client</legend>
		<input type="hidden" id="ImageClient" value="${client.image}" />
		<label style="color:#0099ff;">id : </label><input type="text" readonly name ="id" id="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${client.id }" />
	<table>
		<tr>
			<td><p style="font-size: larger;">Nom : <c:out value="${client.nom }" /></p></td>
			<td style="text-align : right;"
				><div id = "ajouterImage">
					<button class="bouton" type="button" name="ajouterImg"  id="ajouterImg" hidden='true'> ajouter une image</button><br/></div>
				<div id ="modifierImage">
					<button class="bouton" type="button" name="modifierImg" id="modifierImg" hidden='true'> modifier/ changer l'image</button><br/></div>
			</td>
		</tr>
		<tr><td><p style="font-size: larger;">Prénom : <c:out value="${client.prenom }" /></p></td>
			<td rowspan=5>
				<div id="affichageImage">
					<img id="image" src="${empty client.image ? '' :'DisplayImageClient?image='}${client.image}" alt="image" width=300px style="border: 1px solid black;"></div></td>
		</tr>
		<tr><td><p style="font-size: larger;">Téléphone : <br/><c:out value="${client.tel }" /></p></td></tr>
		<tr><td><p style="font-size: larger;">Adresse e-mail : <br/><c:out value="${client.email }" /></p></td></tr>
		<tr><td><p style="font-size: larger;">Adresse : <br/><c:out value="${client.adresse }" /></p></td></tr>
		<tr><td><p style="font-size: larger;">Code Postal : <br/><c:out value="${client.codePostal }" /></p></td></tr>
		<tr><td><p style="font-size: larger;">Ville : <br/><c:out value="${client.ville }" /></p></td>
			<td><input type="text" id="texteNomImage" value="" readonly style="color: #0099ff;font-size: smaller;font-style: italic;text-align:right;border:none"/></td>
		</tr>
	</table>
	</fieldset>
</c:if><br/><br/>

<form method="post" action="<c:url value="informationsClient" />">
<div style="position:fixed;left:6.5%; top:45%;"><button name="boutonPrecedent" type="submit" value="${client.id }" style="background:white;"><img src="inc/icones/precedent.png" alt="precedent" height=100px width=100px></button>
</div>
<div style="position:fixed;left:85%; top:45%;"><button name="boutonSuivant" type="submit" value="${client.id }" style="background:white;"><img src="inc/icones/suivant.png" alt="suivant" height=100px width=100px></button>
</div>
</form>

<c:if test="${empty form && empty listeCommandesClient}">
	<p class="erreur">Aucune commande enregistrée.</p>
</c:if>

<form method="post" action="<c:url value="informationsClient" />"><br/>
	<c:if test="${empty form && !empty listeCommandesClient}">
		<b>Liste des commandes enregistrées : </b><br /> <br />
		<table class="liste">
			<tr >
				<th width=14%><c:out value="date" /></th>
				<th width=14%><c:out value="montant (euros)" /></th>
				<th width=15%><c:out value="mode de paiement" /></th>
				<th width=14%><c:out value="statut du paiement" /></th>
				<th width=14%><c:out value="mode de livraison" /></th>
				<th width=15%><c:out value="statut de la livraison" /></th>
				<th width=10%><c:out value="Action" /></th>
			</tr>
			<c:forEach items="${listeCommandesClient}" var="comm">
				<tr >
					<td width=14%><c:out value="${comm.dateCreation}" /></td>
					<td width=14%><c:out value="${comm.montant}" /></td>
					<td width=15%><c:out value="${comm.modePaiement}" /></td>
					<td width=14%><c:out value="${comm.statutPaiement}" /></td>
					<td width=14%><c:out value="${comm.modeLivraison}" /></td>
					<td width=15%><c:out value="${comm.statutLivraison}" /></td>
					<td width=10%><button type="submit" name="boutonSupprimerCommande" value="${comm.id}" style="border:none"><img src="inc/icones/non.png" alt="supprimer" width=25px></button></td>
				</tr>
			</c:forEach>
		</table><br/>
	</c:if>

	<c:if test="${empty form.erreurs}">
		<button class="bouton" id="passerCommande" value="${client.id}">Passer une commande</button>
		<button class="bouton" id="modifierClient" value="${client.id}">Modifier les données client</button>
		<button class="bouton" id="retourListe" >Aller à la liste des clients</button>
	</c:if>

</form><br/><br/>
</div>
</body>
</html>