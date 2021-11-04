<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table style="font-size: 12px; ">
	
<tr>
	<td style="text-align:right; "><label for="nom">Nom <span class="requis">*</span></label> </td>
	<td ><input type="text" name="nom" required id="nom" class="champ" value="<c:out value="${client.nom}"/>" size="40" maxlength="30" />
		<br> <input type="text" class="old" readonly value="${Oldclient.nom}" size="30"/></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right"><label for="prenom">Prénom <span class="requis">*</span></label> </td>
	<td><input type="text" name="prenom" required id="prenom" class="champ" value="<c:out value="${client.prenom}"/>" size="40" maxlength="30" />
		<br> <input type="text" class="old" readonly value="${Oldclient.prenom}" size="30"/></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right; vertical-align : top"><label for="email">Adresse email <span class="requis">*</span></label> </td>
	<td><input type="text" name="email" required id="email" class="champ" value="<c:out value="${(empty form.erreurs['email'])? client.email : ''}"/>" size="40" maxlength="50" />
		<br><input type="text" class="old" readonly value="${Oldclient.email}" size="30"/></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;">${form.erreurs['email']}</span>
  	</td>
</tr>
<tr>
	<td style="text-align:right"><label for="tel">Téléphone <span class="requis">*</span></label></td>
	<td><input type="text" name="tel" required id="tel" class="champ" value="<c:out value="${client.tel}"/>" size="40" maxlength="20" />
		<br><input type="text" class="old" readonly value="${Oldclient.tel}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right"><label for="adresse">Adresse <span class="requis">*</span></label> </td>
	<td><input type="text" name="adresse" required id="adresse" class="champ" value="<c:out value="${client.adresse}"/>"size="40" maxlength="40" />
		<br> <input type="text" class="old" readonly value="${Oldclient.adresse}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right"><label for="codePostal">Code postal <span class="requis">*</span></label> </td>
	<td><input type="text" name="codePostal" required id="codePostal" class="champ" value="<c:out value="${client.codePostal}"/>" size="40" maxlength="20" />
		<br><input type="text" class="old" readonly value="${Oldclient.codePostal}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr> 
	<td style="text-align:right"><label for="Ville">Ville <span class="requis">*</span></label> </td>
	<td><input type="text" name="ville" required id="ville" class="champ" value="<c:out value="${client.ville}"/>" size="40" maxlength="20" />
		<br><input type="text" class="old" readonly value="${Oldclient.ville}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
</table>
<input type="hidden" id="doublon" value="${ form.doublon.id }" >