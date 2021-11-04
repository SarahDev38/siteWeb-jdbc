<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" name="idProduit"  id="idProduit" value="${produit.id}" />
<table style="font-size: 12px; ">

<tr>
	<td style="text-align:right;"><label style="text-align:right;">nom :</label> </td>
	<td><input type="text" name="nomProduit" class="champ" style="text-align:left;"value="${produit.nom}" size="40" maxlength="40" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right;"><label >taille :</label> </td>
	<td><input type="text" name="taille" style="border-color:gray;padding-right:0.3em;text-align:right;" value="${produit.taille}" size="20" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right;"><label>prix public :</label> </td>
	<td><input type="text" name="prixPublic" class="champ currency" style="text-align:right;padding-right:0.3em;"value="${produit.prix_public}" size="20" maxlength="20" />&euro;</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right;"><label >prix d'achat :</label> </td>
	<td><input type="text" name="prixAchat" class="champ currency" style="text-align:right;padding-right:0.3em;"value="${produit.prix_achat}" size="20" maxlength="20" />&euro;</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right;"><label >quantité disponible :</label> </td>
	<td><input type="text" name="qteDispo" class="champ" style="text-align:right;padding-right:0.3em;"value="${produit.quantite_disponible}" size="20" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right;"><label >quantité minimale :</label> </td>
	<td><input type="text" name="qteMin" class="champ" style="text-align:right;padding-right:0.3em;"value="${produit.quantite_min_stock}" size="20" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
</table>