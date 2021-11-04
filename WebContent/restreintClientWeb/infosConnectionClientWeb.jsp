<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/inc/style.css" />" />
<title>site web</title></head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style="max-width:700px; max-height:50px;font-size: 16px;">
	<br/><span class="succes"><c:out value="Client connecté avec succès !" /></span><br/><br/>
	<c:if test="${ !empty sessionScope.sessionClient && !empty requestScope.intervalleConnections}">
		<p class="info">(Vous ne vous êtes pas connecté(e) depuis ce navigateur depuis ${requestScope.intervalleConnections})</p>
	</c:if>
</div>
<br />

<div class="content" style="max-width:700px; max-height:230px">
<fieldset>
	<legend>Client connecté</legend>

	<p>id : <c:out value="${sessionScope.sessionClient.id }" /></p><br/>
	<p>Nom : <c:out value="${sessionScope.sessionClient.nom }" /></p>
	<p>Prénom : <c:out value="${sessionScope.sessionClient.prenom }" /></p>
	<p>Adresse e-mail : <c:out value="${sessionScope.sessionClient.email }" /></p>
</fieldset>
</div>

</body>
</html>