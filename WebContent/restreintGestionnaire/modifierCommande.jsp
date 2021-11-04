<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/modifierCommande.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style="font-family: verdana;width:900px;height:700px;">
<form method="post" action="<c:url value="modifierCommande" />">
<fieldset style ="width:850px;font-size: 12px;">
<legend>Modification de la commande</legend>
<div>

<fieldset style ="width:800px;font-size: 12px;">
<table >
<tr >
	<td><label style="font-size: 24px;font-family: papyrus;"><b>client : </b></label></td>
	<td><input type="text" readonly id="textId" style="border:none; width : 5em; text-align:right; font-size: 18px; font-style:italic; color:#0099ff;pointer-events: none;" value ="id : " /></td>
	<td><input type="text" readonly id="id" name="id" style="border:none; text-align:left; font-size: 18px; font-style:italic; color:#0099ff;pointer-events: none;" value ="${client.id }" /></td>
</tr>
<tr>
	<td></td><td></td>
	<td><input type="text" class="old" readonly id="oldId" name="oldId" style="border:none; font-size: 18px; color:#0099ff;pointer-events: none;" value ="${Oldclient.id }" /></td>
</tr>
</table>

<br/>
		<label for="radioNouveau" style="font-size: 16px;">Changer de client ?</label>
			<table style="font-size: 14px;">	
			<tr><td width =20%> <input type="radio" name="radioNouveau" id="radioClientAncien" value="ancien" ${client.id == Oldclient.id ? 'checked' : ''} /><label for="ancien">non</label></td>
				<td width =48%> <input type="radio" name="radioNouveau" id="radioClientNouveau" value="nouveau" ${client.id == '' ? 'checked' : ''}/><label for="oui">créer un nouveau client</label></td>
				<td>			<input type="radio" name="radioNouveau" id="radioClientAutre" value="autre" ${(client.id != '' && client.id != Oldclient.id)? 'checked' : ''}/><label for="choisir"><i>choisir un autre client:</i></label>
				</td>
			</tr>
			<tr>
			<td></td><td></td>
				<td>
					<select name="choixClient" id="choixClient" size="1">
						<option value =""></option>
						<c:forEach items="${listeClients}" var="cl">
							<option ${(cl.id == Oldclient.id) ? 'style="font-weight : bold;backgroundColor: #F9CEBF;color: red"' : ''} value ="${cl.id}" ${cl.id == client.id ? 'selected' : ''}>${cl.nom} ${cl.prenom}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</table><br/>

	<c:import url="/inc/formulaireClientPourCommande.jsp" />

</fieldset><p></p>

<fieldset style ="width:800px;font-size: 12px;">
<table>
<tr>
	<td><label style="font-size: 24px;font-family: papyrus;"><b>commande : </b></label></td>
	<td><label style="padding-left: 50px;font-size: 18px;color:#0099ff;">id : </label></td>
	<td><input type="text" readonly id="idCommande" name="idCommande" style="border:none; font-size: 18px; color:#0099ff;pointer-events: none;" value ="${Oldcommande.id }" /></td>
</tr>
</table>

	<c:import url="/inc/formulaireModifCommande.jsp" /><br />

</fieldset><br/>
</div>
<p></p>

<table>
<tr><td width =30%></td>
	<td>
		<button class="bouton" id="boutonAnnuler">Annuler les<br/>modifications</button>
		&emsp;<button class="bouton" id="boutonRetourInfos">Annuler...<br />et retour </button>
		&emsp;<button class="bouton" type="submit" id="boutonValider">Enregistrer les<br/>modifications</button>
	</td>
</tr>
</table>

</fieldset>
<p></p>
</form>
</div>
</body>
</html>