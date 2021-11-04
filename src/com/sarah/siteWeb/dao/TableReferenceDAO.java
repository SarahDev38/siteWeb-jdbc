package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Reference;

@Stateless
public class TableReferenceDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Reference u WHERE u.id=:id";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Reference u";
	private static final String PARAM_ID = "id";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Reference creer(Reference reference) throws DaoException {
		try {
			em.persist(reference);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return reference;
	}

	public void modifier(Reference reference) throws DaoException {
		try {
			em.merge(reference);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void supprimer(Reference reference) throws DaoException {
		try {
			em.remove(reference);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Reference trouverId(Long id) throws DaoException {
		Reference reference = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			reference = (Reference) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return reference;
	}

	public List<Reference> listerReferences() throws DaoException{
		List<Reference> references = null;
		TypedQuery<Reference> requete = em.createQuery(JPQL_SELECT_ALL, Reference.class);
		try {
			references = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return references;
	}
}