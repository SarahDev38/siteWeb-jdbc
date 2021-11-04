<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/modifierInventaire.js" />' ></script>
<title>site Web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content">
	
	<c:if test="${empty listeProduits}">
		<p class="erreur">Aucun produit enregistré.</p>
	</c:if>
	<form method="post" action="<c:url value="modifierInventaire" />">
	
	<c:forEach items="${listeRubriques}" var="rubrik">
	<fieldset style ="max-width:1200px;">
		<legend>${rubrik.nom}</legend>

		
		<table class="liste">
			<tr >
				<th ><c:out value="id article" /></th>
				<th ><c:out value="id référence" /></th>
				<th ><c:out value="nom référence" /></th>
				<th ><c:out value="taille" /></th>
				<th ><c:out value="prix public (euros)" /></th>
				<th ><c:out value="prix d'achat (euros)" /></th>
				<th ><c:out value="quantité disponible en stock" /></th>
				<th ><c:out value="quantité minimale du stock" /></th>
				<th ><c:out value="Action..." /></th>
			</tr>

			<c:forEach items="${listeGammes}" var="gam">
			<c:forEach items="${listeProduits}" var="produit">
			<c:forEach items="${listeReferences}" var="ref">
			<c:if test="${produit.reference.id == ref.id && ref.rubrique.nom == rubrik.nom && ref.gamme.id == gam.id}">
			<input type="hidden" name="idProduit" value="${produit.id}"/>
			<tr >
				<th ><c:out value="${produit.id}" /></th>
				<th ><c:out value="${ref.id}" /></th>
				<td ><c:out value="${ref.nom}" /></td>
				<td ><input type="text" name="${produit.id } taille" class="champ" value="<c:out value="${produit.taille}"/>"size="6" maxlength="10" style= "border:none;text-align:right;font-size:14px;"/></td>
				<td ><input type="text" name="${produit.id } prixPublic" class="champ" value="<c:out value="${produit.prix_public}"/>"size="6" maxlength="10" style= "border:none;text-align:right;font-size:14px;"/>&euro;</td>
				<td ><input type="text" name="${produit.id } prixAchat" class="champ" value="<c:out value="${produit.prix_achat}"/>"size="6" maxlength="10" style= "border:none;text-align:right;font-size:14px;"/>&euro;</td>
				<td ><input type="text" name="${produit.id } qteDispo" class="champ" value="<c:out value="${produit.quantite_disponible}"/>"size="4" maxlength="8" style= "border:none;text-align:right;font-size:14px;"/></td>
				<td ><input type="text" name="${produit.id } qteMin" class="champ" value="<c:out value="${produit.quantite_min_stock}"/>"size="4" maxlength="8" style= "border:none;text-align:right;font-size:14px;"/></td>
				<td class="boutonsAction"><table><tr>
					<td style="border:none"><button class="boutonAction" type="submit" name="boutonModifier" id="boutonModifier" value="${produit.id}" ><img src="inc/icones/validate.png" alt="modifier" height=25px width=25px></button></td>
					<td style="border:none"><button class="boutonAction" type="submit" name="boutonSupprimer" id="boutonSupprimer" value="${produit.id}" ><img src="inc/icones/non.png" alt="supprimer" height=25px width=25px></button></td>
					</tr></table>
				</td>
			</tr>
			</c:if>
			</c:forEach>
			</c:forEach>
			</c:forEach>
		</table>
	</fieldset>
	</c:forEach>
</form>
<br/><br/>

</div>
</body>
</html>