package com.sarah.siteWeb.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sarah.siteWeb.dao.DaoException;
import com.sarah.siteWeb.dao.TableClientDAO;
import com.sarah.siteWeb.dao.TableCommandeDAO;
import com.sarah.siteWeb.entities.Client;
import com.sarah.siteWeb.entities.Commande;

@WebServlet("/informationsClient")

public class AfficheClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_CLIENT = "client";
	private static final String ATT_LISTE_COMMANDES_CLIENT = "listeCommandesClient";
	private static final String PARAM_CLIENT_ID = "client_id";
	private static final String CHAMP_BOUTON_PRECEDENT = "boutonPrecedent";
	private static final String CHAMP_BOUTON_SUIVANT = "boutonSuivant";
	private static final String CHAMP_BOUTON_SUPPR_COMMANDE = "boutonSupprimerCommande";
	private static final String URL_REDIRECTION_AFFICHER_CLIENTS = "http://localhost:8088/siteWeb/listeClients";
	private static final String VUE = "/restreintGestionnaire/infosClient.jsp";
	@EJB
	private TableClientDAO clientDao;
	@EJB
	private TableCommandeDAO commandeDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Client client = getClientFromParameter(request);
		if (client != null) {
			request.setAttribute(ATT_CLIENT, client);
			request.setAttribute(ATT_LISTE_COMMANDES_CLIENT, getListeCommandes(client));
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			response.sendRedirect(URL_REDIRECTION_AFFICHER_CLIENTS);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter(CHAMP_BOUTON_SUPPR_COMMANDE) != null) {
			Client client = supprimerCommande(request);
			request.setAttribute(ATT_CLIENT, client);
			request.setAttribute(ATT_LISTE_COMMANDES_CLIENT, getListeCommandes(client));
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		if (request.getParameter(CHAMP_BOUTON_SUIVANT) != null) {
			Client suivant = getNextClient(request);
			request.setAttribute(ATT_CLIENT, suivant);
			request.setAttribute(ATT_LISTE_COMMANDES_CLIENT, getListeCommandes(suivant));
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
		if (request.getParameter(CHAMP_BOUTON_PRECEDENT) != null) {
			Client precedent = getPreviousClient(request);
			request.setAttribute(ATT_CLIENT, precedent);
			request.setAttribute(ATT_LISTE_COMMANDES_CLIENT, getListeCommandes(precedent));
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}

	private List<Commande> getListeCommandes(Client client) {
		List<Commande> commandes = null;
		if (client != null) {
			try {
				commandes = commandeDao.listerCommandesClient(client.getId());
			} catch (DaoException | NullPointerException e) {
			}
		}
		return commandes;
	}

	private Client getClientFromParameter(HttpServletRequest request) {
		Client client = null;
		Long idClient = (long) 0;
		try {
			idClient = Long.parseLong(request.getParameter(PARAM_CLIENT_ID));
		} catch (NumberFormatException e) {
		}
		if (!idClient.equals((long) 0)) {
			try {
				client = clientDao.trouverId(idClient);
			} catch (DaoException e) {
			}
		}
		return client;
	}

	private Client getNextClient(HttpServletRequest request) {
		Client next = null;
		try {
			List<Client> clients = clientDao.listerClients();
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_SUIVANT)).longValue();
			Iterator<Client> iterator = clients.iterator();
			while (iterator.hasNext()) {
				Client cl = iterator.next();
				if (cl.getId().equals(id)) {
					if (iterator.hasNext()) {
						next = iterator.next();
						break;
					} else {
						next = clients.get(0);
					}
				}
			}
		} catch (DaoException e) {
		}
		return next;
	}

	private Client getPreviousClient(HttpServletRequest request) {
		Client previous = null;
		try {
			List<Client> clients = clientDao.listerClients();
			Long id = Long.valueOf(request.getParameter(CHAMP_BOUTON_PRECEDENT)).longValue();
			ListIterator<Client> iterator = clients.listIterator();
			while (iterator.hasNext()) {
				iterator.next();
			}
			while (iterator.hasPrevious()) {
				Client cl = iterator.previous();
				if (cl.getId().equals(id)) {
					if (iterator.hasPrevious()) {
						previous = iterator.previous();
						break;
					} else {
						previous = clients.get(clients.size() - 1);
					}
				}
			}
		} catch (DaoException e) {
		}
		return previous;
	}

	public Client supprimerCommande(HttpServletRequest request) {
		Client client = null;
		Long id_commande = Long.valueOf(request.getParameter(CHAMP_BOUTON_SUPPR_COMMANDE));
		try {
			Commande commande = commandeDao.trouverId(id_commande);
			client = commande.getClient();
			commandeDao.supprimer(commande);
		} catch (DaoException e) {
		}
		return client;
	}
}
