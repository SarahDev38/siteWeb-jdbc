package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Gamme;

@Stateless
public class TableGammeDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Gamme u WHERE u.id=:id";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Gamme u";
	private static final String PARAM_ID = "id";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Gamme creer(Gamme gamme) throws DaoException {
		try {
			em.persist(gamme);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return gamme;
	}

	public Gamme trouverId(Long id) throws DaoException {
		Gamme gamme = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			gamme = (Gamme) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return gamme;
	}

	public List<Gamme> listerGammes() throws DaoException{
		List<Gamme> gammes = null;
		TypedQuery<Gamme> requete = em.createQuery(JPQL_SELECT_ALL, Gamme.class);
		try {
			gammes = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return gammes;
	}
}
