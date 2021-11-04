package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Gestionnaire;

@Stateless
public class TableGestionnaireDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Gestionnaire u WHERE u.id=:id";
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM Gestionnaire u WHERE u.email=:email";
	private static final String JPQL_SELECT_PAR_PSEUDO = "SELECT u FROM Gestionnaire u WHERE u.pseudo=:pseudo";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Gestionnaire u";
	private static final String PARAM_ID = "id";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PSEUDO = "pseudo";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Gestionnaire creer(Gestionnaire gestionnaire) throws DaoException {
		try {
			em.persist(gestionnaire);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
	    }
		return gestionnaire;
	}

	public void modifier(Gestionnaire gestionnaire) throws DaoException {
		try {
			em.merge(gestionnaire);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
	    }
	}

	public void supprimer(Gestionnaire gestionnaire) throws DaoException {
		try {
			Gestionnaire gestionnaireBDD = em.find(Gestionnaire.class, gestionnaire.getId());
			em.remove(gestionnaireBDD);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
	    }
	}

	public Gestionnaire trouverId(Long id) throws DaoException {
		Gestionnaire gestionnaire = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			gestionnaire = (Gestionnaire) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
	    }
		return gestionnaire;
	}

	public Gestionnaire trouverEmail(String email) throws DaoException {
		Gestionnaire gestionnaire = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);
		try {
			gestionnaire = (Gestionnaire) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
	    }
		return gestionnaire;
	}

	public Gestionnaire trouverPseudo(String pseudo) throws DaoException {
		Gestionnaire gestionnaire = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_PSEUDO);
		requete.setParameter(PARAM_PSEUDO, pseudo);
		try {
			gestionnaire = (Gestionnaire) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return gestionnaire;
	}

	public List<Gestionnaire> listerGestionnaires() throws DaoException {
		List<Gestionnaire> gestionnaires = null;
		TypedQuery<Gestionnaire> requete = em.createQuery(JPQL_SELECT_ALL, Gestionnaire.class);
		try {
			gestionnaires = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
	    }
		return gestionnaires;
	}
}
