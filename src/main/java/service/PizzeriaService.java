package service;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import DAO.CrudDAO;
import model.*;


@Path("/pizzeria")
public class PizzeriaService {
	
	private CrudDAO crudDAO;
	
	public PizzeriaService() {
		this.crudDAO = new CrudDAO();
	}
	
	/*Gestione dei servizi Web*/
	/*
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Employee> getEmployees_JSON() {
        List<Employee> listOfCountries = EmployeeDAO.getAllEmployees();
        return listOfCountries;
    }
	 */

	//GET ALL PIZZA
	@GET
	@Path("/pizze/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Pizza> getPizze_JSON(@PathParam("id") Long id){
		List<Pizza> listOfPizza = crudDAO.readAllPizzaByIdUtente(id);		
		return listOfPizza;
	}
	
	//GET PIZZA BY ID
	//POST PIZZA
	//PUT PIZZA
	//DELETE PIZZA
}
