<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>site web</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/connectionClientWeb.js" />' ></script>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div id="connectionMessage" ${( (empty sessionScope.sessionClient) && (!empty urlDemande) ) ? '' : 'hidden' } >
	<div class="content" style="max-width:700px; max-height:10px;">
		<p class="erreur"><b>Il faut être connecté pour accéder à cette page ...</b></p>
	</div>
</div>

<div class="content" style="max-width:700px; max-height:400px;">
	<form method="post" action="<c:url value="connectionClientWeb" />">
		<fieldset>
			<legend>Connection Client</legend>
			<input type="hidden" name="urlDemande" id="urlDemande" value="<c:out value="${urlDemande}"/>" />
			<p>Vous pouvez vous connecter avec ce formulaire :</p><br /><br />

			<table>
			<tr >
				<td style="width:160px; " align="right"><label for="email" >email : <span class="requis">*</span></label></td> 
				<td >
					<input type="text" name="email" id="email" value="<c:out value="${client.email}"/>" size="30" maxlength="50"/>
				</td>
				<td><span class="erreur">${form.erreurs['email']}</span></td>
			</tr>
			<tr>
				<td style="width:170px;" align="right"><label for="motdepasse">Mot de passe : <span class="requis">*</span></label></td>
				<td ><input type="password" name="motdepasse" required id="motdepasse" value="<c:out value=""/>" size="30" maxlength="30" /></td>
				<td><span class="erreur">${form.erreurs['motdepasse']}</span></td>
			</tr>
			</table><br/>
			
		<c:if test="${! empty form }">
			<p class="${empty form.erreurs ? 'succes' : 'erreur'}"> ${form.resultat}</p>
		</c:if> <br />
			
			<div style="padding-left:9em;">
				<label for="memoire" style="font-size:small;left:2px; "><i>Se souvenir de moi</i></label>
				<input type="checkbox" id="memoire" name="memoire" checked/><br/><br/>
			</div>
		</fieldset><br/><br/><br/>
		<button class="bouton" id="boutonSeConnecter" type="submit" >Se connecter</button><br/><br/>


		<c:if test="${!empty sessionScope.sessionClient}">
			<p class="succes"><i>Vous êtes déjà connecté(e) : ${sessionScope.sessionClient.prenom} ${sessionScope.sessionClient.nom}.</i></p>
		</c:if>
	</form>
</div>
</body>
</html>