package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Client;;

@Stateless
public class TableClientDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Client u WHERE u.id=:id";
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Client u WHERE u.email=:email";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Client u ORDER BY u.nom";
	private static final String PARAM_ID = "id";
	private static final String PARAM_EMAIL = "email";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Client creer(Client client) throws DaoException {
		try {
			em.persist(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}

	public void modifier(Client client) throws DaoException {
		try {
			em.merge(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void supprimer(Client client) throws DaoException {
		try {
			em.remove(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Client trouverId(Long id) throws DaoException {
		Client client = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			client = (Client) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}

	public Client trouverEmail(String email) throws DaoException {
		Client client = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);
		try {
			client = (Client) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}
	
	public List<Client> listerClients() throws DaoException{
		List<Client> clients = null;
		TypedQuery<Client> requete = em.createQuery(JPQL_SELECT_ALL, Client.class);
		try {
			clients = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return clients;
	}
}
