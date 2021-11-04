<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connection</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/connectionGestionnaire.js" />' ></script>

</head>
<body>
<c:import url="/inc/menu.jsp" />
<div id="connectionMessage" ${( (empty sessionScope.sessionGestionnaire) && (!empty urlDemande) ) ? '' : 'hidden' } >
	<div class="content" style="max-width:700px; max-height:10px;">
		<p class="erreur"><b>Il faut être connecté pour accéder à cette page ...</b></p>
	</div>
</div>

<div id="deconnectionMessage" ${(empty sessionScope.sessionGestionnaire && empty urlDemande ) ? '' : 'hidden' } >
	<div class="content" style="max-width:700px; max-height:10px;">
		<p class="erreur"><i>Vous n'êtes pas connecté...</i></p>
	</div>
</div>
	
<div class="content" style="max-width:700px; max-height:400px;">
	<form method="post" action="<c:url value="connectionGestionnaire" />">
		<fieldset>
			<legend>Connection</legend>
			<input type="hidden" name="urlDemande" id="urlDemande" value="<c:out value="${urlDemande}"/>" />
			<p>Vous pouvez vous connecter avec ce formulaire :</p><br /><br />

			<table>
			<tr >
				<td style="width:160px; " align="right"><label for="pseudo" >pseudo : <span class="requis">*</span></label>
				</td> 
				<td style="padding: 0.7em;">
					<input type="hidden" name="pseudo" id="pseudo" value="<c:out value="${pseudo}"/>" />
					<c:if test="${! empty listeGestionnaires }">
						<div id="utilisateurEnregistré">
							<select name="choixPseudo" id="choixPseudo" size="1" style="width:16em" required>
									<option value ="">choix du pseudo</option>
									<c:forEach items="${listeGestionnaires}" var="g">
										<option value ="${g.pseudo}" ${(g.pseudo == pseudo) ? 'selected' : ''} >${g.pseudo}</option>
									</c:forEach>
							</select>
						</div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width:170px;" align="right"><label for="motdepasse">Mot de passe : <span class="requis">*</span></label>
				</td>
				<td ><input type="password" name="motdepasse" required id="motdepasse" value="<c:out value=""/>" size="20" maxlength="20" />
				</td>
			</tr>
			</table><br/>
			
			<div style="padding-left:9em;">
				<label for="memoire" style="font-size:small;left:2px; "><i>Se souvenir de moi</i></label>
				<input type="checkbox" id="memoire" name="memoire" checked/><br/><br/>
			</div>
		</fieldset><br/><br/><br/>
		<button class="bouton" id="boutonSeConnecter" type="submit" >Se connecter</button><br/><br/>

		<p class="erreur">${erreurMDP}</p>

		<c:if test="${!empty sessionScope.sessionGestionnaire}">
			<p class="succes"><i>Vous êtes déjà connecté(e) avec le pseudo : ${sessionScope.sessionGestionnaire.pseudo}.</i></p>
		</c:if>
	</form>
</div>
</body>
</html>