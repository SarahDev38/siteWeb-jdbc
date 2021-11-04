<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8" >
	<table style="font-size: 12px;">
	<tr>
	<td width=20% style="text-align:right"><label for="dateCommande">Date de création : <span class="requis">*</span></label> </td>
	<td width=20% ><input type="text" readonly name="dateCommande" id="dateCommande" style="text-align:center; border:none;" value="<c:out value="${empty commande.dateCreation ? date : commande.dateCreation}"/>" size="20" maxlength="20"/></td>
	<td align="left"><span class="erreur"></span></td>
	</tr> 
	<tr>
	<td style="text-align:right"><label for="montantCommande">Montant : <span class="requis">*</span></label> </td>
	<td ><input type="text" style="padding-right:0.3em;text-align:right;" class="champCommande" name="montantCommande" id="montantCommande" value="<c:out value="${commande.montant}"/>" size="16" maxlength="16" class="comm" />&euro;</td>
	<td>
		<img src="inc/icones/nocheck.png" style="height:16px;width:16px;" hidden=hidden>
		<span style="line-height:15px;text-align:left; vertical-align: top;"></span>
  	</td>
	</tr> 
	<tr>
	<td style="text-align:right"><label for="modePaiementCommande">Mode de paiement : <span class="requis">*</span></label> </td>
	<td colspan=2 style="vertical-align:bottom;">
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="especes" ${commande.modePaiement.code == 'especes' ? 'checked' : ''} /><img src="inc/icones/modePaiement/especes.gif" alt="espèces" height=50px width=50px>
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="cheque" ${commande.modePaiement.code == 'cheque' ? 'checked' : ''} /><img src="inc/icones/modePaiement/cheque.gif" alt="chèque" height=50px width=50px>
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="resto" ${commande.modePaiement.code == 'resto' ? 'checked' : ''} /><img src="inc/icones/modePaiement/ticketresto.gif" alt="ticket resto" height=60px width=80px>
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="visa" ${commande.modePaiement.code == 'visa' ? 'checked' : ''} /><img src="inc/icones/modePaiement/visa.gif" alt="visa" height=45px width=60px>
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="virement" ${commande.modePaiement.code == 'virement' ? 'checked' : ''} /><img src="inc/icones/modePaiement/virement.gif" alt="virement" height=50px width=70px>
		<input type="radio" name="modePaiementCommande" class="radioCommande" style="vertical-align:1em" value="paypal" ${commande.modePaiement.code == 'paypal' ? 'checked' : ''} /><img src="inc/icones/modePaiement/paypal.gif" alt="paypal" height=45px width=80px>
	<input type="hidden" class="champCommande" name="modePaiementCommande" id="modePaiementCommande" value="<c:out value="${commande.modePaiement.code}"/>" size="30" maxlength="20" /></td>
	</tr> 
	<tr style="height : 15px;"></tr> 
	<tr>
	<td style="text-align:right"><label for="statutPaiementCommande">Statut du paiement : </label> </td>
	<td colspan=2>
		<input type="radio" name="statutPaiementCommande" class="radioCommande" value="paye" ${commande.statutPaiement.code == 'paye' ? 'checked' : ''} /><label for="paye">payé</label>
		<input type="radio" name="statutPaiementCommande" class="radioCommande" value="attente" ${commande.statutPaiement.code == 'attente' ? 'checked' : ''} /><label for="attente">en attente du paiement </label>
		<input type="radio" name="statutPaiementCommande" class="radioCommande" value="rembourse" ${commande.statutPaiement.code == 'rembourse' ? 'checked' : ''} /><label for="rembourse">remboursé </label>
		<input type="radio" name="statutPaiementCommande" class="radioCommande" value="annule" ${commande.statutPaiement.code == 'annule' ? 'checked' : ''} /><label for="annule">annulé </label>
	<input type="hidden" class="champCommande" name="statutPaiementCommande" id="statutPaiementCommande" value="<c:out value="${commande.statutPaiement.code}"/>" size="30" maxlength="20" /></td>
	</tr> 
	<tr style="height : 15px;"></tr> 
	<tr>
		<td style="text-align:right"><label for="modeLivraisonCommande">Mode de livraison : <span class="requis">*</span></label> </td>
	<td colspan=2 >
		<input type="radio" name="modeLivraisonCommande" class="radioCommande" style="vertical-align:1em" value="magasin" ${commande.modeLivraison.code == 'magasin' ? 'checked' : ''} /><img src="inc/icones/modeLivraison/magasin.gif" alt="magasin" height=60px width=65px>
		<input type="radio" name="modeLivraisonCommande" class="radioCommande" style="vertical-align:1em" value="chronopost" ${commande.modeLivraison.code == 'chronopost' ? 'checked' : ''} /><img src="inc/icones/modeLivraison/chronopost.gif" alt="chronopost" height=55px width=60px>
		<input type="radio" name="modeLivraisonCommande" class="radioCommande" style="vertical-align:1em" value="relay" ${commande.modeLivraison.code == 'relay' ? 'checked' : ''} /><img src="inc/icones/modeLivraison/relay.gif" alt="mondial relay" height=55px width=60px>
	<input type="hidden" class="champCommande" name="modeLivraisonCommande" id="modeLivraisonCommande" value="<c:out value="${commande.modeLivraison.code}"/>" size="30" maxlength="20" /></td>
	</tr> 
	<tr style="height : 15px;"></tr> 
	<tr>
	<td style="text-align:right"><label for="statutLivraisonCommande">Statut de la livraison : </label> </td>
	<td colspan=2>
		<input type="radio" name="statutLivraisonCommande" class="radioCommande" value="traitement" ${commande.statutLivraison.code == 'traitement' ? 'checked' : ''} /><label for="traitement">en cours de traitement</label>
		<input type="radio" name="statutLivraisonCommande" class="radioCommande" value="transit" ${commande.statutLivraison.code == 'transit' ? 'checked' : ''} /><label for="transit">en transit</label>
		<input type="radio" name="statutLivraisonCommande" class="radioCommande" value="livraison" ${commande.statutLivraison.code == 'livraison' ? 'checked' : ''} /><label for="livraison">en cours de livraison</label>
		<input type="radio" name="statutLivraisonCommande" class="radioCommande" value="livre" ${commande.statutLivraison.code == 'livre' ? 'checked' : ''} /><label for="livre">livré </label>
	<input type="hidden" class="champCommande" name="statutLivraisonCommande" id="statutLivraisonCommande" value="<c:out value="${commande.statutLivraison.code}"/>" size="30" maxlength="20" /></td>
	</tr>
	</table>

