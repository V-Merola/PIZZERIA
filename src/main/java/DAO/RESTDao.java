package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Utente;
import util.JPAUtil;


public class RESTDao {
	private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

	// CLASSE CHE GESTISCE I METODI CRUD PER IL REST

	// CREATE UENTE
	public static Utente createUtente(Utente utente) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(utente);
			entityManager.getTransaction().commit();
			return utente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

	// CREATE IMPASTO
	public static Impasto createImpasto(Impasto impasto) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(impasto);
			entityManager.getTransaction().commit();
			return impasto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

	// READ UTENTE
	public static Utente readUtente(Long idUtente) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Utente utente = entityManager.find(Utente.class, idUtente);
			entityManager.getTransaction().commit();
			return utente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}

	// READ IMPASTO
	public static Impasto readImpasto(Long idImpasto) {
		return CrudDAO.getImpastoById(idImpasto);
	}

	// READ ALL UTENTE
	public static List<Utente> readAllUtente() {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			TypedQuery<Utente> query = entityManager.createQuery("SELECT u FROM Utente u", Utente.class);
			List<Utente> listaEmployee = query.getResultList();
			entityManager.getTransaction().commit();
			return listaEmployee;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	// READ ALL IMPASTO
	public static List<Impasto> readAllImpasto() {
		return CrudDAO.readAllImpasto();
	}

	// UPDATE UTENTE
	public static Utente updateUtente(Utente utente) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(utente);
			entityManager.getTransaction().commit();
			return utente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}

	// UPDATE IMPASTO
	public static Impasto updateImpasto(Impasto impasto) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(impasto);
			entityManager.getTransaction().commit();
			return impasto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}

	}

	// DELETE UTENTE
	public static String deleteUtente(Long idUtente) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Utente utente = entityManager.find(Utente.class, idUtente);

			if (utente != null) {
				entityManager.remove(utente);
			}
			entityManager.getTransaction().commit();
			return "utente: "+ idUtente + " eliminato"; 
		} catch (Exception e) {
			e.printStackTrace();
			return "utente: "+ idUtente + " non eliminato"; 
		} finally {
			entityManager.close();
		}

	}

	// DELETE IMPASTO
	public static String deleteImpasto(Long idImpasto) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Impasto impasto = entityManager.find(Impasto.class, idImpasto);

			if (impasto != null) {
				entityManager.remove(impasto);
			}
			entityManager.getTransaction().commit();
			return "impasto " + idImpasto + " eliminato";
		} catch (Exception e) {
			e.printStackTrace();
			return "impasto " + idImpasto + " non eliminato";
		} finally {
			entityManager.close();
		}
	}

}
