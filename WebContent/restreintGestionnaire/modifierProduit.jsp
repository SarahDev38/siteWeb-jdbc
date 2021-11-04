<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/modifierProduit.js" />' ></script>
<title>formulaire commande</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style ="max-width:1000px;">
<form id = "formulaireProduit" name = "formulaireProduit" method="post" action="<c:url value="modifierProduit" />" enctype="multipart/form-data">

	<fieldset style ="width:850px;">
		<legend>Type de produit</legend>
	
		<c:import url="/inc/formulaireRubrique.jsp" />
		
		<div id="divReference">
			<c:import url="/inc/formulaireReference.jsp" />
		</div>
	</fieldset>
	
	<fieldset style ="width:850px;">
		<legend><B>Produit</B></legend>
		
		<div id="divProduit">
			<c:import url="/inc/formulaireProduit.jsp" /><br />
			<button class="bouton" type="submit" name="boutonValider" id="boutonValider"  value="">Valider</button>
			<button class="bouton" id="reset" value="">Réinitialiser</button>
			<button class="bouton" id="annuler" value="">Annuler</button><br/><br/>
		</div>
		
	</fieldset><br />
	
</form><br/>
</div>
</body>
</html>