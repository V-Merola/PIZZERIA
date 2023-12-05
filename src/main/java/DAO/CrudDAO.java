package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

public class CrudDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERS");

	/* verifica se l'utente è presente nel database */
	public Utente readUtente(String username, String password) {
		List<Utente> listaUtente = new ArrayList();
		EntityManager entityManager = emf.createEntityManager();

		try {
			entityManager.getTransaction().begin();

			// HQL
			String queryHQL = "SELECT u FROM Utente u WHERE u.username = :username AND u.password = :password";
			Query query = entityManager.createQuery(queryHQL, Utente.class);
			query.setParameter("username", username);
			query.setParameter("password", password);

			listaUtente = query.getResultList();

			entityManager.getTransaction().commit();

			return listaUtente.isEmpty() ? null : listaUtente.get(0);
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	// creazione pizza
	public void createPizza(Pizza pizza) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(pizza);
			Utente utente = pizza.getUtente();
			utente.getPizze().add(pizza);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	// Recupero la pizza tramite idPizza
	public Pizza getPizzaById(Long pizzaId) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			Pizza pizza = entityManager.find(Pizza.class, pizzaId);
			entityManager.getTransaction().commit();

			return pizza;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	// Recupero tutte le pizze dell'utente tramite idUtente

	public List<Pizza> readAllPizzaByIdUtente(Long idUtente) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Utente utente = entityManager.find(Utente.class, idUtente);

			if (utente == null) {
				return null;
			}
			List<Pizza> pizzaList = utente.getPizze();
			entityManager.getTransaction().commit();
			return pizzaList;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	// Update pizza
	public void updatePizza(Pizza pizza) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(pizza);
			entityManager.flush();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	// ELimina pizza tramite idPizza

	public void deletePizzaById(Long idPizza) {
		EntityManager entityManager = emf.createEntityManager();
		try {

			entityManager.getTransaction().begin();
			Pizza pizza = entityManager.find(Pizza.class, idPizza);
			entityManager.remove(pizza);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	// METODI PER IMPASTO E INGREDIENTI
	// recupera la lista degli impasti

	public static List<Impasto> readAllImpasto() {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			String queryHQL = "SELECT i FROM Impasto i";
			Query query = entityManager.createQuery(queryHQL, Impasto.class);
			List<Impasto> impasti = query.getResultList();
			entityManager.getTransaction().commit();

			return impasti;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	public static Impasto getImpastoById(Long idImpasto) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Impasto impasto = entityManager.find(Impasto.class, idImpasto);
			entityManager.getTransaction().commit();
			return impasto;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}

	}

	// recupera la lista ingredienti

	public List<Ingrediente> readAllIngredienti() {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();

			String queryHQL = "SELECT i FROM Ingrediente i";
			Query query = entityManager.createQuery(queryHQL, Ingrediente.class);
			List<Ingrediente> ingredienti = query.getResultList();

			entityManager.getTransaction().commit();

			return ingredienti;
		} catch (NoResultException e) {
			return null;
		} finally {
			entityManager.close();
		}
	}

	// recupero gli ingredienti scelti dall'utente dal database tramite id
	public List<Ingrediente> getIngredientiById(Long[] ingredientiId) {
		EntityManager entityManager = emf.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<Ingrediente> listaIngredienti = new ArrayList<>();
			for (Long id : ingredientiId) {
				Ingrediente ingrediente = entityManager.find(Ingrediente.class, id);
				if (ingrediente != null) {
					listaIngredienti.add(ingrediente);
				}
			}
			entityManager.getTransaction().commit();
			return listaIngredienti;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
	}

}
