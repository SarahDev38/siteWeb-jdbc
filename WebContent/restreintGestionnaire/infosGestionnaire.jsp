<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/infosGestionnaire.js" />' ></script>
<title>informations gestionnaire</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />

<c:if test="${ !empty form }">
	<div class="content" style="max-width:700px;max-height:50px;font-size: 16px;">
		<br/><span class="succes"><c:out value="Gestionnaire créé avec succès !" /></span>
	</div>
</c:if>

<div class="content" style="max-width:700px;max-height:400px;">
<form method="post" action="<c:url value="informationsGestionnaire" />"><br/>

	<fieldset>
	<legend>informations gestionnaire</legend>

		<label style="color:#0099ff;">id : </label><input type="text" readonly id="id" name="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${gestionnaire.id }" /><br/>
		<p style="font-size: larger;">pseudo : <c:out value="${gestionnaire.pseudo }" /></p>
		<p style="font-size: larger;">Nom : <c:out value="${gestionnaire.nom }" /></p>
		<p style="font-size: larger;">Prénom : <c:out value="${gestionnaire.prenom }" /></p>
		<p style="font-size: larger;">Adresse e-mail : <c:out value="${gestionnaire.email }" /></p>
		<br/>
		<button class="bouton" value="${gestionnaire.id}" id="boutonModifier">Modifier</button>

	</fieldset><br/><br/><br/>

	<button class="bouton" id="boutonListe">Voir la liste des gestionnaires</button>

</form>
</div>
</body>
</html>