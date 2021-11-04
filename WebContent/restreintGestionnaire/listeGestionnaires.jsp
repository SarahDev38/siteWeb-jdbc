<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/listeGestionnaires.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style ="max-width:900px;">
	<c:if test="${empty listeGestionnaires}">
		<p class="erreur">Aucun gestionnaire enregistré.</p>
	</c:if>
	
	<c:if test="${!empty listeGestionnaires}">
	<fieldset style ="width:90%;">
	<legend>Liste des gestionnaires enregistrés</legend>
	<form method="post" action="<c:url value="listeGestionnaires" />"><br/>
		<div >
		<table class="liste" >
			<tr >
				<th width=5%><b><c:out value="id" /></b></th>
				<th width=15%><b><c:out value="pseudo" /></b></th>
				<th width=17%><b><c:out value="Nom" /></b></th>
				<th width=17%><b><c:out value="Prénom" /></b></th>
				<th ><b><c:out value="email" /></b></th>
				<th width=10%><b><c:out value="Action" /></b></th>
			</tr>
			<c:forEach items="${listeGestionnaires}" var="g">
				<tr >
					<th ><c:out value="${g.id}" /></th>
					<th ><c:out value="${g.pseudo}" /></th>
					<td ><c:out value="${g.nom}" /></td>
					<td ><c:out value="${g.prenom}" /></td>
					<td ><c:out value="${g.email}" /></td>
					<td >
						<button class="boutonAction" type="submit" name="boutonSupprimer" value="${g.id}"><img src="inc/icones/non.png" alt="supprimer" height=25px width=25px></button>
						<button class="boutonAction" name="boutonAfficher" value="${g.id}"><img src="inc/icones/user.png" alt="afficher" height=25px width=25px></button>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</form>
	</fieldset><br/><br/><br/><br/><br/>
	</c:if>	
</div>
</body>
</html>