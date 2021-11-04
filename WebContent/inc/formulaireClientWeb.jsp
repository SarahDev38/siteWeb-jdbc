<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table style="font-size: 12px; ">

<tr>
	<td width=20% style="text-align:right"><label for="dateCommande">Date : <span class="requis">*</span></label> </td>
	<td width=20% ><input type="text" readonly name="dateInscription" style="text-align:center; border:none;" value="<c:out value="${date}"/>" size="20" maxlength="20"/></td>
	<td></td>
</tr> 
<tr>
	<td style="text-align:right; "><label for="nom">Nom : <span class="requis">*</span></label> </td>
	<td ><input type="text" name="nom" required id="nom" class="champ" value="<c:out value="${client.nom}"/>" size="40" maxlength="30" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right"><label for="prenom">Prénom : <span class="requis">*</span></label> </td>
	<td><input type="text" name="prenom" required id="prenom" class="champ" value="<c:out value="${client.prenom}"/>" size="40" maxlength="30" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right; vertical-align : top"><label for="email">Adresse email : <span class="requis">*</span></label> </td>
	<td><input type="text" name="email" required id="email" class="champ" value="<c:out value="${client.email}"/>" size="40" maxlength="50" />
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;">${form.erreurs['email']}</span>
  	</td>
</tr>
<tr>
	<td style="text-align:right"><label for="tel">Téléphone : <span class="requis">*</span></label></td>
	<td><input type="text" name="tel" required id="tel" class="champ" value="<c:out value="${client.tel}"/>" size="40" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right"><label for="adresse">Adresse : <span class="requis">*</span></label> </td>
	<td><input type="text" name="adresse" required id="adresse" class="champ" value="<c:out value="${client.adresse}"/>"size="40" maxlength="40" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right"><label for="codePostal">Code postal : <span class="requis">*</span></label> </td>
	<td><input type="text" name="codePostal" required id="codePostal" class="champ" value="<c:out value="${client.codePostal}"/>" size="40" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr> 
	<td style="text-align:right"><label for="Ville">Ville : <span class="requis">*</span></label> </td>
	<td><input type="text" name="ville" required id="ville" class="champ" value="<c:out value="${client.ville}"/>" size="40" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td><label for="motdepasse">Mot de passe <span class="requis">*</span></label></td>
	<td><input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" /></td>
	<td>
		<img id="imgNoCheckMDP" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span id="erreurMDP" style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr class="confirm">
	<td style="text-align : right;"><label for="confirmation" ><i>Confirmation : </i><span class="requis">*</span></label></td>
	<td><input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" /></td>
	<td>
		<img id="imgCheckConfirmation" src="inc/icones/check.png" style="height:22px;width:22px;" hidden=hidden>
		<img id="imgNoCheckConfirmation" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span id="erreurConfirmation" style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
</table>