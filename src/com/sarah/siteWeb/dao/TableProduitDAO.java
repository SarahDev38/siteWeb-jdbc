package com.sarah.siteWeb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.sarah.siteWeb.entities.Produit;

@Stateless
public class TableProduitDAO {
	private static final String JPQL_SELECT_PAR_ID = "SELECT u FROM Produit u WHERE u.id=:id";
	private static final String JPQL_SELECT_PAR_MOT_RECHERCHE = "SELECT DISTINCT u FROM Produit u WHERE u.client.id=:client_id";
	private static final String SQL_SELECT_PAR_MOT_RECHERCHE = "SELECT * FROM Produit LEFT JOIN Reference ON produit.reference.id = reference.id WHERE MATCH(produit.nom) AGAINST (? IN BOOLEAN MODE) OR MATCH(reference.description) AGAINST (? IN BOOLEAN MODE);";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM Produit u";
	private static final String PARAM_ID = "id";
	private static final String PARAM_RECHERCHE = "recherche";

	@PersistenceContext(unitName = "siteWeb_PU")
	private EntityManager em;

	public Produit creer(Produit produit) throws DaoException {
		try {
			em.persist(produit);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return produit;
	}

	public void modifier(Produit produit) throws DaoException {
		try {
			em.merge(produit);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public void supprimer(Produit produit) throws DaoException {
		try {
			em.remove(produit);
			em.flush();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Produit trouverId(Long id) throws DaoException {
		Produit produit = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_ID);
		requete.setParameter(PARAM_ID, id);
		try {
			produit = (Produit) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return produit;
	}

	public List<Produit> listerProduits() throws DaoException{
		List<Produit> produits = null;
		TypedQuery<Produit> requete = em.createQuery(JPQL_SELECT_ALL, Produit.class);
		try {
			produits = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return produits;
	}

	@SuppressWarnings("unchecked")
	public List<Produit> listerProduitsRechercheSQL(String recherche) throws DaoException{
		List<Produit> produits = null;
		Query requete = em.createNativeQuery(SQL_SELECT_PAR_MOT_RECHERCHE, Produit.class);
		requete.setParameter(1, recherche);
		requete.setParameter(2, recherche);
		requete.setParameter(PARAM_RECHERCHE, recherche);
		try {
			produits = (List<Produit>) requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return produits;
	}

	public List<Produit> listerProduitsRecherche(String recherche) throws DaoException{
		List<Produit> produits = null;
		TypedQuery<Produit> requete = em.createQuery(JPQL_SELECT_PAR_MOT_RECHERCHE, Produit.class);
		requete.setParameter(PARAM_RECHERCHE, recherche);
		try {
			produits = requete.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return produits;
	}

}
