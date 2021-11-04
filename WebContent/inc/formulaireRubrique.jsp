<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" name="rubrique" id="rubrique" value="${rubrique.nom}" />
<input type="hidden" name="idGamme"  id="idGamme" value="${gamme.id}" />

<label for="rubrique">Rubrique : <span class="requis">*</span></label>
<table style="text-align:center;">
	<tr><td></td>
		<td width=150px><img src="inc/icones/maison.png" alt="maison" height=60px width=60px></td>
		<td width=150px><img src="inc/icones/mode.png" alt="mode" height=60px width=60px></td>
		<td width=150px><img src="inc/icones/bijoux.png" alt="bijoux" height=60px width=80px ></td>
	</tr>
	<tr><td></td>
		<td width=150px><input type="radio" name="rubrique" class="radioRubrique" value="maison" ${rubrique.nom == 'maison' ? 'checked' : ''} />maison</td>
		<td width=150px><input type="radio" name="rubrique" class="radioRubrique" value="linge" ${rubrique.nom == 'linge' ? 'checked' : ''} />linge</td>
		<td width=150px><input type="radio" name="rubrique" class="radioRubrique" value="bijoux" ${rubrique.nom == 'bijoux' ? 'checked' : ''} />bijoux</td>
	</tr>
</table><br/>
<table style="font-size: 12px;"><tr>
	<td>
	<div id ="divGammeMaison" style="padding-left:5em;" ${gamme.rubrique.nom == 'maison' ? '' : 'hidden="hidden"'}>
		<label for="Gamme" style="width:20px;">Gamme :</label>
		<select name="choixGamme" class="choixGamme"  size="1" style="width:15em;">
			<option value =""></option>
			<c:forEach items="${listeGammes}" var="g">
				<c:if test="${g.rubrique.nom == 'maison'}">
					<option value ="${g.id}" ${g.id == gamme.id ? 'selected' : ''}>${g.nom}</option>
				</c:if>
			</c:forEach>
		</select>
	</div>
	<div id ="divGammeLinge" style="padding-left:5em;" ${gamme.rubrique.nom == 'linge' ? '' : 'hidden="hidden"'}>
		<label for="Gamme">Gamme : </label>
		<select name="choixGamme" class="choixGamme"  size="1" style="width:15em;">
			<option value =""></option>
			<c:forEach items="${listeGammes}" var="g">
				<c:if test="${g.rubrique.nom == 'linge'}">
					<option value ="${g.id}" ${g.id == gamme.id ? 'selected' : ''}>${g.nom}</option>
				</c:if>
			</c:forEach>
		</select>
	</div>
	<div id ="divGammeBijoux" style="padding-left:5em;" ${gamme.rubrique.nom == 'bijoux' ? '' : 'hidden="hidden"'}>
		<label for="Gamme">Gamme : </label>
		<select name="choixGamme" class="choixGamme" size="1" style="width:15em;">
			<option value =""></option>
			<c:forEach items="${listeGammes}" var="g">
				<c:if test="${g.rubrique.nom == 'bijoux'}">
					<option value ="${g.id}" ${g.id == gamme.id ? 'selected' : ''}>${g.nom}</option>
				</c:if>
			</c:forEach>
		</select>
	</div>
	</td>
	<td><div id="divInputGamme" >
		<input type="text" class="inputAutre" name="nomGamme" id="nomGamme" 
			${empty gamme.nom ? 'style = "margin-left:1.2em;width:20em;border:none;border-bottom:solid 1px gray;text-align:center;color:gray;font-style:italic"' 
			:  'style = "margin-left:1.2em;width:20em;border:none;border-bottom:solid 1px gray;text-align:center;color:black;font-style:none;"'} 
			value="${empty gamme.nom ? 'autre...' : gamme.nom}" ${empty rubrique ? 'hidden="hidden"' : ''} /><br/>
		</div>
	</td>
</tr>
</table>
<br/>