<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/modifierClient.js" />' ></script>
<title>site web</title>
</head>
<body>

<c:import url="/inc/menu.jsp" />

<div class="content" style="height:680px; width:950px; overflow:auto;font-family: verdana">
	<form method="post" action="<c:url value="modifierClient" />" enctype="multipart/form-data"><br/>
		<fieldset style="width:930px; height:600px; left:-20px;">
			<legend>modifier les informations client</legend>
			<label style="color:#0099ff;">id : </label><input type="text" readonly id="id" name="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${Oldclient.id }" />
			
			<c:import url="/inc/formulaireModifClient.jsp" />
			
		</fieldset>
		<p></p>
		<button class="bouton" type="submit" id="boutonValider">Enregistrer les modifications</button>
		<button class="bouton" id="AnnulerModif">Annuler les modifications</button>
		<button class="bouton" id="retourInfos">Annuler...<br />et retour </button>
	</form>
</div>
</body>
</html>