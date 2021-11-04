<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<c:if test="${!empty form.erreurs}">
	<c:redirect url="/nouvelleCommande"  />
</c:if>

<div class="content">
	
	<c:if test="${!empty form && empty form.erreurs}">
			<span class="succes"><c:out value="Commande créée avec succès !" /></span>
	</c:if><br/><br/>

	<c:if test="${empty form.erreurs }">
	<fieldset>
	<legend>informations commande</legend>
		<p style="font-size: larger;"><b>Client : </b></p>
		<p style="color: #0099ff;">id : <c:out value="${client.id }" /></p>
		<p style="font-size: larger;">Nom : <c:out value="${client.nom }" /></p>
		<p style="font-size: larger;">Prénom : <c:out value="${client.prenom }" /></p>
		<p style="font-size: larger;">Téléphone : <c:out value="${client.tel }" /></p>
		<p style="font-size: larger;">Adresse e-mail : <c:out value="${client.email }" /></p>
		<p style="font-size: larger;">Adresse : <c:out value="${client.adresse }" /></p>
		<p style="font-size: larger;">Code Postal : <c:out value="${client.codePostal }" /></p>
		<p style="font-size: larger;">Ville : <c:out value="${client.ville }" /></p><br/>

		<p style="font-size: larger;"><b>Commande : </b></p>
		<p style="color: #0099ff;">id : <c:out value="${commande.id }" /></p>
		<p style="font-size: larger;"> créée le : <c:out value="${commande.dateCreation }" /></p>
		<c:if test="${commande.dateCreation != commande.dateModification}">
			<p style="font-size: larger;"> modifiée le  : <c:out value="${commande.dateModification }" /></p>
		</c:if>
		<p style="font-size: larger;">Montant : <c:out value="${commande.montant }" /></p>
		<p style="font-size: larger;">Mode de paiement : <c:out value="${commande.modePaiement.nom }" /></p>
		<p style="font-size: larger;">Statut du paiement : <c:out value="${commande.statutPaiement.nom }" /></p>
		<p style="font-size: larger;">Mode de livraison : <c:out value="${commande.modeLivraison.nom }" /></p>
		<p style="font-size: larger;">Statut de la livraison : <c:out value="${commande.statutLivraison.nom }" /></p>
		</fieldset>
	</c:if>

	<form method="post" action="<c:url value="informationsCommande" />"><br/>
		<button class="bouton" type="submit" value="${commande.id}" name="boutonModifierCommande">Modifier la commande</button>
		<c:if test="${empty form }">
			<button class="bouton" type="submit" name="boutonRetourListe">Retour à la liste des commandes</button>
		</c:if>
	</form><br/>
</div>
</body>
</html>