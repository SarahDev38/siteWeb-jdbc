<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href='<c:url value="/inc/style.css" />' />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/creerGestionnaire.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" /><br/>
<div class="content" style="max-width:700px; max-height:400px;">
	<form method="post" action="<c:url value="nouveauGestionnaire" />" >

		<fieldset>
			<legend>Inscription nouveau gestionnaire </legend>
			<p>Vous pouvez inscrire un nouveau gestionnaire avec ce formulaire : </p>
			<div >
				<c:import url="/inc/formulaireNouveauGestionnaire.jsp" />
			</div><br/>
		</fieldset><br/><br/>

		<input class="bouton" type="submit" id="boutonValider" value="Valider" />
		<button class="bouton" type="submit" name="boutonConnection">Valider et <br/> se connecter</button>
		<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

	</form>
</div>
</body>
</html>