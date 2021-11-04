<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/listeCommandes.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" >

	<c:if test="${empty listeCommandes}">
		<p class="erreur">Aucune commande enregistrée.</p>
	</c:if>


	<c:if test="${!empty listeCommandes}">
		<table>
	<tr>
		<th>
		<c:if test="${! empty listeClients }">
			<label for="pseudo" style="margin-left: 7em;">liste par client : </label>
			<select name="choixIdClient" id="choixIdClient" size="1">
				<option value ="" selected>tous les clients</option>
				<c:forEach items="${listeClients}" var="cl">
					<option value ="${cl.id}" >${cl.nom} ${cl.prenom}</option>
				</c:forEach>
			</select>
		</c:if></th>
		<th>
			<c:if test="${! empty listeGestionnaires }">
			<label for="pseudo" style="margin-left: 7em;">liste par utilisateur : </label>
				<select name="choixPseudo" id="choixPseudo" size="1">
					<option value ="" selected>tous les gestionnaires</option>
					<c:forEach items="${listeGestionnaires}" var="g">
						<option value ="${g.id}" >${g.pseudo}</option>
					</c:forEach>
				</select>
			</c:if>
		</th>
		</tr>
	</table><br />
	<input type="hidden" name="pseudo" id="pseudo" value="<c:out value=""/>" />		
	<input type="hidden" name="idClient" id="idClient" value="<c:out value=""/>" />

	<fieldset style ="width:90%;">
	<legend>Liste des commandes enregistrées</legend>
	<form method="post" action="<c:url value="listeCommandes" />">

	<div id ="pasdecommandeclient" style="display:none;">
		<p class="erreur">Aucune commande enregistrée par ce client.</p>
	</div>
	<div id ="pasdecommandegestionnaire" style="display:none;" >
		<p class="erreur">Aucune commande enregistrée par ce gestionnaire.</p>
	</div>
	<div id ="pasdecommandeclientgestionnaire" style="display:none;" >
		<p class="erreur">Aucune commande enregistrée par ce client et ce gestionnaire.</p>
	</div>

		
	<div >
	<table class="liste" id = "myTable">
		<tr class="entete">
			<th ><b><c:out value="client" /></b></th>
			<th ><b><c:out value="gestionnaire" /></b></th>
			<th ><b><c:out value="date" /></b></th>
			<th ><b><c:out value="montant (euros)" /></b></th>
			<th ><b><c:out value="mode de paiement" /></b></th>
			<th ><b><c:out value="statut du paiement" /></b></th>
			<th ><b><c:out value="mode de livraison" /></b></th>
			<th ><b><c:out value="statut de la livraison" /></b></th>
			<th ><b><c:out value="Action..." /></b></th>
		</tr>
		<c:forEach items="${listeCommandes}" var="comm">
			<tr class="${comm.client.id} - ${comm.gestionnaire.id}" >
				<th ><c:out value="${comm.client.nom} ${comm.client.prenom}" /><br/><c:out value="${comm.client.id}" /></th>
				<th ><c:out value="${comm.gestionnaire.pseudo}"   /><br/><c:out value="${comm.gestionnaire.id == null ? '*' : comm.gestionnaire.id}" /></th>
				<td ><c:out value="${comm.dateCreation}" /> 
					<c:if test="${comm.dateCreation != comm.dateModification}">
					<br/>modifiée le  : <c:out value="${comm.dateModification }" /></c:if></td>
				<td ><c:out value="${comm.montant}" /></td>
				<td ><c:out value="${comm.modePaiement.code}" /></td>
				<td ><c:out value="${comm.statutPaiement.code}" /></td>
				<td ><c:out value="${comm.modeLivraison.code}" /></td>
				<td ><c:out value="${comm.statutLivraison.code}" /></td>
				<td >
					<button class="boutonAction" type="submit" name="boutonSupprimer" value="${comm.id}" ><img src="inc/icones/non.png" alt="supprimer" height=25px width=25px></button>
					<button class="boutonAction" type="submit" name="boutonAfficher" id="boutonAfficher" value="${comm.id}"><img src="inc/icones/user.png" alt="afficher" height=25px width=25px></button>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	</form>
	</fieldset><br/><br/><br/><br/><br/>
	
	</c:if><br/>
</div>
<script >

</script>
</body>
</html>