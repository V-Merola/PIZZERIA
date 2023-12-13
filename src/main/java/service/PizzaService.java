package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.CrudDAO;
import model.*;

@WebService
public class PizzaService {

	private CrudDAO crudDAO;

	public PizzaService() {
		this.crudDAO = new CrudDAO();
	}

	// GET ALL PIZZA BY ID UTENTE
	@WebMethod
	public List<Pizza> getPizzeByIdUtente(Long id) {
		List<Pizza> listOfPizza = crudDAO.readAllPizzaByIdUtente(id);
		return listOfPizza;
	}

	// GET PIZZA BY ID
	@WebMethod
	public Pizza getPizzaById(Long id) {
		Pizza pizza = crudDAO.getPizzaById(id);
		return pizza;
	}

	// POST PIZZA-rivedere
	@WebMethod
	public String addPizza(Pizza pizza) {
		crudDAO.createPizza(pizza);
		return "Pizza creata";
	}

	// PUT PIZZA
	@WebMethod
	public String updatePizza(Pizza pizzaUpdate) {
		crudDAO.updatePizza(pizzaUpdate);
		return "Pizza aggiornata";
	}

	// DELETE PIZZA
	@WebMethod
	public String deletePizza(Long idPizza) {
		crudDAO.deletePizzaById(idPizza);
		return "Pizza eliminata";
	}
}
