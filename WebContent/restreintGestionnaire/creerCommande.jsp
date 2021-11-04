<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<link rel="stylesheet" media="screen" type="text/css" href="<c:url value="/inc/style.css" />" />
<script type="text/javascript" src="<c:url value="/inc/jquery-3.5.1.min.js"/>" charset="utf-8"></script>
<script type="text/javascript" src='<c:url value="/inc/js/creerCommande.js" />' ></script>
<title>site web</title>
</head>
<body>
<c:import url="/inc/menu.jsp" />
<div class="content" style ="max-width:1000px;">
<form id = "formulaireCommande" name = "formulaireCommande" method="post" action=" <c:url value="nouvelleCommande" /> ">
<fieldset style ="width:850px;">
	<legend>Choix du client</legend>
		<input type="hidden" name ="id" id="id" value ="${Oldclient.id }" />
		<table >	
			<tr>
				<td width=27%><label for="radioNouveau">Nouveau client ? <span class="requis">*</span></label> </td>
				<td width=25%  style="text-align:center; font-size:14px;">
					<input type="radio" name="radioNouveau" id="radioNouveauClient" value="oui" required/><label for="oui">oui</label>
					&emsp;<input type="radio" name="radioNouveau" id="radioClientEnregistre" value="non"/><label for="non"><i>non :</i></label>
				</td>
				<td>
					<c:if test="${! empty listeClients }">
						<select name="choixClient" id="choixClient" size="1">
							<option value ="">Choisissez un client...</option>
							<c:forEach items="${listeClients}" var="cl">
								<option value ="${cl.id}" ${cl.id == client.id ? 'selected' : ''}>${cl.nom} ${cl.prenom}</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
			</tr>
		</table><br/>

	<c:import url="/inc/formulaireClientPourCommande.jsp" />
	<table>
		<tr><td width =50%></td>
			<td><button class="bouton" id="AnnulerModifClient" >Annuler les <br/>modifications</button>
			<button type="submit" class="bouton" id="PasserCommande"name="PasserCommande"  >Passer la<br/>commande</button>
			</td>
		</tr>
	</table>

</fieldset>

<fieldset style ="width:850px;">
	<legend><B>Nouvelle commande</B></legend>
	<div id="Commande" style="display: ${(!empty form && empty form.erreurs)? 'inline-block;':'none;' }">
		<c:import url="/inc/formulaireCommande.jsp" /><br />
		
		<button class="bouton" type="submit" name="boutonValider" id="boutonValider"  value="">Valider la commande</button>
		<button class="bouton" id="resetCommande" value="">Réinitialiser la commande</button><br/><br/>
		
	</div>
</fieldset><br />

<c:if test="${! empty commande }">
	<p class="${(empty formCommande.erreurs && empty form.erreurs) ? 'succes' : 'erreur'}"> ${empty form.erreurs? formCommande.resultat : ''}</p>
</c:if> <br />

</form>

</div>
</body>
</html>