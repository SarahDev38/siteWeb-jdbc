package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Commande;

@Stateless
public class TableCommandeDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Commande u WHERE u.id=:id";
	private static final String JPQL_SELECT_PAR_ID_CLIENT = "SELECT u FROM Commande u WHERE u.client.id=:client_id";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Commande u";
	private static final String PARAM_ID = "id";
	private static final String PARAM_CLIENT_ID = "client_id";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Commande creer(Commande commande) throws DaoException {
		try {
			em.persist(commande);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return commande;
	}

	public void modifier(Commande commande) throws DaoException {
		try {
			em.merge(commande);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void supprimer(Commande commande) throws DaoException {
		try {
			em.remove(commande);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Commande trouverId(Long id) throws DaoException {
		Commande commande = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			commande = (Commande) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return commande;
	}

	public List<Commande> listerCommandes() throws DaoException{
		List<Commande> commandes = null;
		TypedQuery<Commande> requete = em.createQuery(JPQL_SELECT_ALL, Commande.class);
		try {
			commandes = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return commandes;
	}
	
	public List<Commande> listerCommandesClient(Long client_id) throws DaoException{
		List<Commande> commandes = null;
		TypedQuery<Commande> requete = em.createQuery(JPQL_SELECT_PAR_ID_CLIENT, Commande.class);
		requete.setParameter(PARAM_CLIENT_ID, client_id);
		try {
			commandes = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return commandes;
	}

}