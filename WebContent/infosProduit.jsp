<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/infosProduit.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />

<div class="content" style ="max-height:620px; max-width:750px;">
<fieldset>
	<legend>informations produit</legend>
	<label style="color:#0099ff;">id : </label><input type="text" readonly name ="id" id="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${produit.id }" />
	<input type="text" readonly name ="id" id="id" style="border:none; color:#0099ff;pointer-events: none;" value ="${produit.nom }" />

<table style="font-size: 12px; ">
<tr>
	<td style="text-align:right;"><label style="text-align:right;">produit de référence :</label> </td>
	<td><label >${reference.nom}</label></td>
</tr> 
<tr>
	<td style="text-align:right;"><label style="text-align:right;">description :</label> </td>
	<td><label >${reference.description}</label></td>
</tr> 
<tr>
	<td style="text-align:right;"><label style="text-align:right;">rubrique :</label> </td>
	<td><label >${rubrique}</label></td>
</tr> 
	
<tr>
	<td style="text-align:right;"><label style="text-align:right;">gamme :</label> </td>
	<td><label >${gamme.nom}</label></td>
</tr> 
</table>

<table>
<tr><td rowspan=7  width=350px>
	<input type="hidden" id="Image" value="${reference.image}" />
	<div id="affichageImage">
		<img id="imageAffichee" src="${empty reference.image ? '' :'DisplayImage?image='}${reference.image}" alt="${reference.image}" width=300px style="border: 1px solid black;">
	</div>
	</td>
	<td style="text-align:right;"><label >taille :</label> </td>
	<td><label >${produit.taille}</label></td>
</tr>
<tr>
	<td style="text-align:right;"><label>prix public :</label> </td>
	<td><label >${produit.prix_public} &euro;</label></td>
</tr>
<tr>
	<td style="text-align:right;"><label >prix d'achat :</label> </td>
	<td><label >${produit.prix_achat} &euro;</label></td>
</tr> 
<tr>
	<td style="text-align:right;"><label >quantité disponible :</label> </td>
	<td><label >${produit.quantite_disponible}</label></td>
</tr>
<tr>
	<td style="text-align:right;"><label >quantité minimale :</label> </td>
	<td><label >${produit.quantite_min_stock}</label></td>
</tr>
<tr>
	<td></td>
	<td></td>
</tr>
<tr>
	<td style="text-align:left;"><input type="text" id="texteNomImage" value="${reference.image}" readonly style="color: #0099ff;font-size: smaller;font-style: italic;border:none"/></td>
	<td></td>
</tr>
</table>

</fieldset><br/><br/>

<button class="bouton" id="modifier" value="${produit.id}">mettre à jour</button>

</div>
</body>
</html>