<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/modifierGestionnaire.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style="max-width:800px;max-height:400px;font-family: verdana">
<fieldset style="min-height:350px;">
<legend>Modifier un gestionnaire : </legend>
	
	<c:if test="${empty listeGestionnaires}">
		<p class="erreur">Aucun gestionnaire enregistré.</p>
	</c:if>
	
	<form method="post" action="<c:url value="modifierGestionnaire" />"><br/>
		<c:if test="${ ! empty gestionnaire }">
			<label style="color:#0099ff;">id : </label><input type="text" readonly id="id" name="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${gestionnaire.id }" /><br/>
			<label style="color:#0099ff;">pseudo : </label>
			<select name="choixPseudo" id="choixPseudo" size="1" style="position:fixed;color:#0099ff;border : solid silver 1px;">
				<option value ="" selected>Choisir un utilisateur : </option>
				<c:forEach items="${listeGestionnaires}" var="g">
					<option value ="${g.id}" ${g.pseudo == gestionnaire.pseudo ? 'selected' : ''}>${g.pseudo}</option>
				</c:forEach>
			</select>
			<input type="hidden" readonly id="pseudo" name="pseudo" style="border:none; color:#0099ff;pointer-events: none;" value ="${gestionnaire.pseudo }" /><br/><br/>
			
			<table class="inscriptiontable" style="width:850px;">
				<tr>
					<td style="width:18%;text-align:right"><label for="nom">nom  : </label></td>
					<td style="width:27%"><input type="text" id="nom" name="nom" class="modif" value="<c:out value="${gestionnaire.nom}"/>" size="20" maxlength="30" /></td>
					<td> <img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
						<span style="line-height:15px;text-align:left; vertical-align: top;font-size : small;"></span></td>
				</tr>
				<tr>
					<td style="text-align:right"><label for="prenom">prénom : </label></td>
					<td ><input type="text" id="prenom" name="prenom" class="modif" value="<c:out value="${gestionnaire.prenom}"/>" size="20" maxlength="30" /></td>
					<td> <img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
						<span style="line-height:15px;text-align:left; vertical-align: top;font-size : small;"></span></td>
				</tr> 
				<tr>
					<td style="text-align:right"><label for="email">email :  </label></td>
					<td><input type="text" id="email" name="email" class="modif" value="<c:out value="${gestionnaire.email}"/>" size="20" maxlength="60" /></td>
					<td> <img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
						<span style="line-height:15px;text-align:left; vertical-align: top;font-size : small;">${form.erreurs['email']}</span></td>
				</tr>
				<tr>
					<td style="text-align:right"><label for="motdepasse">mot de passe : </label></td>
					<td><input type="password" id="mdpAffiche" value="" size="20" maxlength="30" />
						<input type="hidden" id="motdepasse" name="motdepasse" value="<c:out value="${gestionnaire.motdepasse}"/>" /></td>
					<td><img id="imgNoCheckMDP" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
						<span id="erreurMDP" style="line-height:15px;text-align:left; vertical-align: top;font-size : small;"></span></td>
				</tr>
				<tr class="confirm" >
					<td style="text-align:right"><label for="confirmation">Confirmation :</label></td>
					<td><input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="30" /></td>
					<td><img id="imgCheckConfirmation" src="inc/icones/check.png" style="height:22px;width:22px;" hidden=hidden>
						<img id="imgNoCheckConfirmation" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
						<span id="erreurConfirmation" style="line-height:15px;text-align:left; vertical-align: top;font-size : small;"></span></td>
				</tr>
			</table><br/>
	
				<button class="bouton" type="submit" id="boutonValider" >Enregistrer les modifications</button>
				<button class="bouton" id="AnnulerModif">Annuler les modifications</button>
				<button class="bouton" id="retourInfos">Annuler...<br/> et retour</button>
			</c:if>
		</form>
		</fieldset>
	</div>
</body>
</html>