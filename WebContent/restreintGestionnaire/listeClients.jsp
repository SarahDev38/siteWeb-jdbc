<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/listeClients.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<!-- <div class="content" style ="width:85%;height:550px"> -->
<div class="content">
	
	<c:if test="${empty listeClients}">
		<p class="erreur">Aucun client enregistré.</p>
	</c:if>
	
	<c:if test="${!empty listeClients}">
		<c:if test="${! empty listeGestionnaires }">
			<label for="pseudo" style="margin-left: 7em">liste par gestionnaire : </label>
				<select name="choixPseudo" id="choixPseudo" size="1">
					<option value ="" selected>liste complète</option>
					<c:forEach items="${listeGestionnaires}" var="g">
						<option value ="${g.id}" >${g.pseudo}</option>
					</c:forEach>
				</select>
		</c:if>
		<input type="hidden" name="pseudo" id="pseudo" value="<c:out value=""/>" />
		<br/><br/>

	<fieldset style ="max-width:1200px;">
	<legend>Liste des clients enregistrés</legend>
	<form method="post" action="<c:url value="listeClients" />">

	<div id ="listeVide">
		<br /><p class="erreur">Aucun client enregistré par ce gestionnaire.</p>
	</div>
		
	<div >
		<table class="liste"  id = "myTable">
			<tr class="entete">
				<th ><b><c:out value="id" /></b></th>
				<th ><b><c:out value="gestionnaire" /></b></th>
				<th ><b><c:out value="Nom" /></b></th>
				<th ><b><c:out value="Prénom" /></b></th>
				<th colspan=2><b><c:out value="Adresse" /></b></th>
				<th ><b><c:out value="Téléphone" /></b></th>
				<th ><b><c:out value="Email" /></b></th>
				<th ><b><c:out value="Action..." /></b></th>
			</tr>
			<c:forEach items="${listeClients}" var="cl">
				<tr class="${cl.gestionnaire.id}" >
					<th ><c:out value="${cl.id}" /></th>
					<th ><c:out value="${cl.gestionnaire.id}" /><br/><c:out value="${cl.gestionnaire.pseudo}" /></th>
					<td ><c:out value="${cl.nom}" /></td>
					<td ><c:out value="${cl.prenom}" /></td>
					<td ><c:out value="${cl.adresse}" /></td>
					<td ><c:out value="${cl.codePostal}"/><br/><c:out value="${cl.ville}" /></td>
					<td ><c:out value="${cl.tel}" /></td>
					<td ><c:out value="${cl.email}" /></td>
					<td ><table><tr>
						<td style="border:none"><button class="boutonAction" type="button" name="boutonPhoto" id ="${cl.nom}  ${cl.prenom}" value="${cl.image}" ><img src="inc/icones/photo.png" alt="afficher" height=25px width=25px></button></td>
						<td style="border:none"><button class="boutonAction" type="submit" name="boutonAfficher" id="boutonAfficher" value="${cl.id}" ><img src="inc/icones/user.png" alt="afficher" height=25px width=25px></button></td>
						<td style="border:none"><button class="boutonAction" type="submit" name="boutonSupprimer" id="boutonSupprimer" value="${cl.id}" ><img src="inc/icones/non.png" alt="supprimer" height=25px width=25px></button></td>
						</tr></table>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id = "divPhoto" style="position:fixed; left:35%; top:100px; background : white; border: 1px solid black;text-align:center; height:600px; width:600px;" hidden='true'>
			<input type="text" readonly id = "description" style="width:60%;text-align : center;font-size : 18px;font-weight: bold;font-family: papyrus;border:none;" value=""/><br/>
			<img id="photo" src="" alt="" style="border: 1px solid black;"><p></p>
			<a id = "linkPhoto" href="">Voir</a>
		</div>
		<div id = "divPasPhoto" style="width:500px; height: 150px;position:fixed;left:500px; top:250px;background : silver; border: 1px solid black;" hidden='true'>
 			<p style="text-align : center;font-size : 32px;font-weight: bold;font-family: papyrus;" > <br/> pas d'image à afficher </p>
 		</div>
	</div>
	</form>	
	</fieldset><br/>
</c:if><br/><br/><br/><br/><br/>
</div>
</body>
</html>