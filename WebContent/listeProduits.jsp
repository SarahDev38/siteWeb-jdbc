<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/listeProduits.js" />' ></script>
<title>site Web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content">
	
	<c:if test="${empty listeProduits}">
		<p class="erreur">Aucun produit enregistré.</p>
	</c:if>
	
	<c:forEach items="${listeRubriques}" var="rubrik">
	<fieldset style ="max-width:1200px;">
		<legend>${rubrik.nom}</legend>
		<c:forEach items="${listeGammes}" var="gam">
			<c:forEach items="${listeProduits}" var="produit">
			<c:forEach items="${listeReferences}" var="ref">
			<c:if test="${produit.reference.id == ref.id && ref.rubrique.nom == rubrik.nom && ref.gamme.id == gam.id}">
				<div class="divProduit" style="float:left;width : 380px;border:1px solid black;margin:3px;">
					<input type="hidden" value="${produit.id}">
					<table style="font-size: 12px; ">
						<tr><td colspan=2 style="text-align: center;"><label style="font-weight: bold;font-size: large;">${ref.nom}</label></td>
						</tr>
						<tr><td rowspan=3  width=160px style="text-align: center;">
								<img src="${empty ref.image ? '' :'DisplayImage?image='}${ref.image}" alt="${ref.image}" alt="${ref.image}" style="border: 1px solid black;max-width: 150px;max-height: 100px;"/>
							</td>
							<td><span style="hyphens: none;">${ref.description}</span></td>
						</tr> 
						<tr>
							<td><span >taille : ${produit.taille}</span></td>
						</tr> 
							
						<tr>
							<td><span >prix (<i>hors FP</i>) : ${produit.prix_public} &euro;</span></td>
						</tr> 
					</table>
					
				</div>
				
				</c:if>
			</c:forEach>
			</c:forEach>
		</c:forEach>
	</fieldset>
	</c:forEach><br/><br/>
</div>

</body>
</html>