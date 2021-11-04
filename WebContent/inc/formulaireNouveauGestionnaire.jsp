<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table style="font-size: 12px;" class="inscriptiontable">
<tr>
	<td style="width:20%;text-align:right;"><label for="pseudo">Pseudo <span class="requis">*</span></label></td>
	<td style="width:35%;"><input type="text" name="pseudo" class="champ" value="<c:out value="${gestionnaire.pseudo}"/>" size="20" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;">${form.erreurs['pseudo']}</span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right;"><label for="nom">Nom <span class="requis">*</span></label></td>
	<td><input type="text" name="nom" class="champ" value="<c:out value="${gestionnaire.nom}"/>" size="20" maxlength="20" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right;"><label for="prenom">Prénom <span class="requis">*</span></label></td>
	<td ><input type="text" name="prenom" class="champ" value="<c:out value="${gestionnaire.prenom}"/>" size="20" maxlength="60" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right;"><label for="email">Email <span class="requis">*</span></label></td>
	<td><input type="text" name="email" class="champ" value="<c:out value="${gestionnaire.email}"/>" size="20" maxlength="60" /></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;">${form.erreurs['email']}</span>
  	</td>
</tr>
<tr>
	<td style="text-align:right;"><label for="motdepasse">Mot de passe <span class="requis">*</span></label></td>
	<td><input type="password" id="motdepasse" class="champ" name="mdp" value="" size="20" maxlength="20" /></td>
	<td>
		<img id="imgNoCheckMDP" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span id="erreurMDP" style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr class="confirm">
	<td style="text-align : right;"><label for="confirmation" ><i>Confirmation </i><span class="requis">*</span></label></td>
	<td><input type="password" id="confirmation" class="champ" name="confirmation" value="" size="20" maxlength="20" /></td>
	<td>
		<img id="imgCheckConfirmation" src="inc/icones/check.png" style="height:22px;width:22px;" hidden=hidden>
		<img id="imgNoCheckConfirmation" src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span id="erreurConfirmation" style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
</table>