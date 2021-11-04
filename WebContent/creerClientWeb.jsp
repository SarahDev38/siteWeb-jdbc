<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/creerClientWeb.js" />' ></script>

<title>nouveau client Web</title>
</head>
<body>

<c:import url="/inc/menu.jsp" />

<div class="content" style ="max-height:600px; max-width:1000px;">
	<form method="post" action="<c:url value="nouveauClientWeb" />" >
		<fieldset>
			<legend> Nouveau client Web</legend>
				<c:import url="/inc/formulaireClientWeb.jsp" /><br/>
		</fieldset><br /><br />
		
		<c:if test="${! empty client }">
			<p id = "resultat" class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</c:if><br /> 
	
		<input class="bouton" type="submit" name="boutonValider" id="boutonValider" value="Valider"  style="text-align: center;"/> 
		<button class="bouton" type="reset" id="reset" value="">Remettre à zéro</button><br/><br/>
	</form>
</div>
</body>
</html>