<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" name ="idReference" id="idReference" value ="${reference.id }" />

<table style="font-size: 12px;">
<tr>
	<td ><label for="radioNouveau" style="width:20px;">Produit de référence :</label> </td>
	<td>
		<c:forEach items="${listeRubriques}" var="rubrik">

			<div class="selectReference _${rubrik.nom}" ${(!empty produit && !empty gamme) ? 'hidden="hidden"' : '' }>
				<select name="choixType" class="choixType" size="1" style="width:15em;">
					<option value =""></option>
					<c:forEach items="${listeReferences}" var="ref">
						<c:if test="${ref.rubrique.nom == rubrik.nom && empty ref.gamme.id}">
							<option value ="${ref.id}" ${ref.id == reference.id ? 'selected' : ''}>${ref.nom}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		
			<c:forEach items="${listeGammes}" var="gam">
				<c:if test="${gam.rubrique.nom == rubrik.nom }">
				<div class="selectReference ${rubrik.nom} ${rubrik.nom}_${gam.id}" ${(!empty produit && gamme.id != gam.id) ? 'hidden="hidden"' : '' }>
				<select name="choixRef" class="choixRef" size="1" style="width:15em;">
					<option value =""></option>
						<c:forEach items="${listeReferences}" var="ref">
							<c:if test="${ref.rubrique.nom == rubrik.nom && ref.gamme.id == gam.id}">
								<option value ="${ref.id}" ${ref.id == reference.id ? 'selected' : ''}>${ref.nom}</option>
							</c:if>
						</c:forEach>
				</select>
				</div>
				</c:if>
			</c:forEach>
		</c:forEach>
	</td>
	<td><input type="text" class="inputAutre" name="nomReference" required id="nomReference" 
			${empty reference.nom ? 'style = "margin-left:1.2em;width:20em;border:none;border-bottom:solid 1px gray;text-align:center;color:gray;font-style:italic"' 
			:  'style = "margin-left:1.2em;width:20em;border:none;border-bottom:solid 1px gray;text-align:center;color:black;font-style:none;"'} 
			value="${empty reference.nom ? 'autre...' : reference.nom}" 
			${empty rubrique ? 'hidden="hidden"' : '' }/><br/>
	</td>
</tr>
</table><br/>
<div id="description">
<table style="font-size: 12px;">
<tr>
	<td><label >description :</label> </td>
	<td><input type="text" name="description" id="description" style="border:none;border-bottom:solid 1px gray;text-align:left;"value="${reference.description}" size="70" maxlength="70" /></td>
</tr>
</table>
<br/>

<table>
<tr>
<td><div id ="ajouterImage" ${empty reference.image ? '' :'hidden="hidden"'}>
		<button class="bouton" type="button" name="ajouter" id="ajouter"> ajouter une image</button><br/><br/>
	</div>
	<div id ="modifierImage">
		<button class="bouton" type="button" name="supprimer" id="supprimer"> supprimer l'image</button><br/><br/>
		<button class="bouton" type="button" name="modifier" id="modifier"> changer d'image</button>
	</div>
	<br/><br/>
	<div id ="cheminImage" hidden='hidden'>
		<p>Image : <input type="file" name="Image" id="Image" style="font-size:12px;" value="" size="20" maxlength="20" /><br/>
		<i>5 Mo max</i></p><br/>
	</div>
</td>
<td><div id ="affichageImage" style="height:310px;" hidden='hidden'>
	<table style="width:100%;vertical-align:top;"><tr>
		<td style="text-align:left;vertical-align:top;"><button type="button" name="boutonDroite" id="boutonDroite" ><img src="inc/icones/rotationD.png" alt="rotation droite" height=40px width=40px></button></td>
		<td style="vertical-align:top;"><img id="image" src="${empty reference.image ? '' :'DisplayImage?image='}${reference.image}" alt="${reference.image}" style="max-height:280px; max-width:280px;border: 1px solid black;"></td>
		<td style="text-align:right;vertical-align:top;"><button type="button" name="boutonGauche" id="boutonGauche" ><img src="inc/icones/rotationG.png" alt="rotation gauche" height=40px width=40px></button></td></tr>
	</table>
	</div>
</td>
</tr>
</table>
<input type="hidden" id="ImageDepart" name="ImageDepart" value="${reference.image }" />
<input type="hidden" id="AngleImage" name="AngleImage" value="0" />
<input type="hidden" id="pasdimage" name="pasdimage" value="${(reference.image == 'null' || empty reference.image) ? 'true' : 'false'}" />
<input type="hidden" id="imgmodifiee" name="imgmodifiee" value="false" /> 
</div>