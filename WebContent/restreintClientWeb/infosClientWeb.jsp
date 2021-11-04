<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/infosClientWeb.js" />' ></script>
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
			<label style="color:#0099ff;">id : </label><input type="text" readonly name ="id" id="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${client.id }" />
		<table>
			<tr><td><p style="font-size: larger;">Nom : <c:out value="${client.nom }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Prénom : <c:out value="${client.prenom }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Téléphone : <br/><c:out value="${client.tel }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Adresse e-mail : <br/><c:out value="${client.email }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Adresse : <br/><c:out value="${client.adresse }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Code Postal : <br/><c:out value="${client.codePostal }" /></p></td></tr>
			<tr><td><p style="font-size: larger;">Ville : <br/><c:out value="${client.ville }" /></p></td></tr>
		</table>
		</fieldset>
	</c:if><br/><br/>
	
	<form method="post" action="<c:url value="informationsClientWeb" />">
		<div style="position:fixed;left:6.5%; top:45%;"><button name="boutonPrecedent" type="submit" value="${client.id }" style="background:white;"><img src="inc/icones/precedent.png" alt="precedent" height=100px width=100px></button>
			</div>
		<div style="position:fixed;left:85%; top:45%;"><button name="boutonSuivant" type="submit" value="${client.id }" style="background:white;"><img src="inc/icones/suivant.png" alt="suivant" height=100px width=100px></button>
			</div>
	</form>
</div>
</body>
</html>