<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table style="font-size: 12px; ">

<tr>
	<td style="vertical-align:top; text-align:center;" rowspan=7 >
		<div id ="modifierImage">
			<table style="width:100%;"><tr>
				<td style="text-align:right;"><button class="bouton" type="button" name="supprimer" id="supprimer"> supprimer l'image</button></td>
				<td style="text-align:left;"><div id ="modifImage"><button class="bouton" type="button" name="modifier" id="modifier"> changer d'image</button></div></td></tr>
			</table><br/>
		</div>
		<div id ="ajouterImage">
			<button class="bouton" type="button" name="ajouter" id="ajouter"> ajouter une image</button><br/>
		</div>
		<div id ="cheminImage">
			<p>Image : <input type="file" name="Image" id="Image" style="font-size:12px;" value="" size="20" maxlength="20" />
			<i>5 Mo max</i></p><br/>
		</div>
		<div id ="affichageImage">
			<table style="width:100%;"><tr>
				<td style="text-align:left;"><button type="button" name="boutonDroite" id="boutonDroite" ><img src="inc/icones/rotationD.png" alt="rotation droite" height=40px width=40px></button></td>
				<td style="text-align:right;"><button type="button" name="boutonGauche" id="boutonGauche" ><img src="inc/icones/rotationG.png" alt="rotation gauche" height=40px width=40px></button></td></tr>
			</table>
			<img id="image" src="${empty client.image ? '' :'DisplayImageClient?image='}${client.image}" alt="image" style="max-height:280px; max-width:280px;border: 1px solid black;"><br/><br/><br/><br/><br/>
		</div>
			<input type="hidden" id="ImageDepart" name="ImageDepart" value="${Oldclient.image}" />
			<input type="hidden" id="AngleImage" name="AngleImage" value="0" />
			<input type="hidden" id="pasdimage" name="pasdimage" value="${(client.image == 'null' || empty client.image) ? 'true' : 'false'}" />
			<input type="hidden" id="imgmodifiee" name="imgmodifiee" value="false" /> 
	</td>

	<td style="text-align:right; vertical-align : middle;" ><label for="nom">Nom :</label></td>
	<td style="vertical-align : middle;" ><input type="text" name="nom" required id="nom" class="modif" value="<c:out value="${ client.nom }"/>" size="30" maxlength="30" />
		<br> <input type="text" class="old" id="nomOld" readonly value="${Oldclient.nom}" size="30"/></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right; vertical-align : middle;" ><label for="prenom">Prénom :</label></td>
	<td style="vertical-align : middle;" ><input type="text" name="prenom" required id="prenom" class="modif" value="<c:out value="${ client.prenom }"/>" size="30" maxlength="30" />
		<br> <input type="text" class="old" id="prenomOld" readonly value="${Oldclient.prenom}" size="30"/></td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right; vertical-align : middle;"><label for="email">Adresse email :</label> </td>
	<td style="vertical-align : middle;" ><input type="text" name="email" required id="email" class="modif" value="<c:out value="${ client.email }"/>" size="30" maxlength="50" />
		<br><input type="text" class="old" id="mailOld" readonly value="${Oldclient.email}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span class="erreur" style="line-height:15px;text-align:left; vertical-align: top;">${form.erreurs['email']}</span>
  	</td>
</tr>
<tr>
	<td style="text-align:right; vertical-align : middle;"><label for="tel">Téléphone :</label></td>
	<td style="vertical-align : middle;" ><input type="text" name="tel" required id="tel" class="modif" value="<c:out value="${ client.tel }"/>" size="30" maxlength="20" />
		<br><input type="text" class="old" id="telOld" readonly value="${Oldclient.tel}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr> 
<tr>
	<td style="text-align:right; vertical-align : middle;"><label for="adresse">Adresse :</label> </td>
	<td style="vertical-align : middle;" ><input type="text" name="adresse" required id="adresse" class="modif" value="<c:out value="${ client.adresse }"/>"size="30" maxlength="40" />
		<br> <input type="text" class="old" id="adresseOld" readonly value="${Oldclient.adresse}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr>
	<td style="text-align:right; vertical-align : middle;"><label for="codePostal">Code postal :</label> </td>
	<td style="vertical-align : middle;" ><input type="text" name="codePostal" required id="codePostal" class="modif" value="<c:out value="${ client.codePostal }"/>" size="30" maxlength="20" />
		<br><input type="text" class="old" id="codePostalOld" readonly value="${Oldclient.codePostal}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
<tr> 
	<td style="text-align:right; vertical-align : middle;"><label for="Ville">Ville :</label> </td>
	<td><input type="text" name="ville" required id="ville" class="modif" value="<c:out value="${client.ville}"/>" size="30" maxlength="20" />
		<br><input type="text" class="old" id="villeOld" readonly value="${Oldclient.ville}" size="30"/>
	</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
</tr>
</table>
