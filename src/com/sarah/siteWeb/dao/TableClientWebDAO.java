package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.ClientWeb;

@Stateless
public class TableClientWebDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM ClientWeb u WHERE u.id=:id";
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM ClientWeb u WHERE u.email=:email";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM ClientWeb u";
	private static final String PARAM_ID = "id";
	private static final String PARAM_EMAIL = "email";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public ClientWeb creer(ClientWeb client) throws DaoException {
		try {
			em.persist(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}

	public void modifier(ClientWeb client) throws DaoException {
		try {
			em.merge(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void supprimer(ClientWeb client) throws DaoException {
		try {
			em.remove(client);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public ClientWeb trouverId(Long id) throws DaoException {
		ClientWeb client = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			client = (ClientWeb) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}

	public ClientWeb trouverEmail(String email) throws DaoException {
		ClientWeb client = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);
		try {
			client = (ClientWeb) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return client;
	}
	
	public List<ClientWeb> listerClients() throws DaoException{
		List<ClientWeb> clients = null;
		TypedQuery<ClientWeb> requete = em.createQuery(JPQL_SELECT_ALL, ClientWeb.class);
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