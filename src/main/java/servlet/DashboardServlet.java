package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CrudDAO;
import model.*;

/**
 * Servlet implementation class DashboardServlet
 */
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String azioneDaEffettuare = request.getParameter("action");

		if (azioneDaEffettuare != null) {
			switch (azioneDaEffettuare) {
			case "salvaPizza":
				aggiungiPizza(request, response);
				break;
			case "eliminaPizza":
				eliminaPizza(request, response);
				break;
			case "updatePizza":
				redirectUpdatePizza(request, response);
			default:
				break;
			}
		}

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	private void aggiungiPizza(HttpServletRequest request, HttpServletResponse response) {
		// String impastoNome = request.getParameter("impasto");
		String nomePizza = request.getParameter("nomePizza");

		Long idImpasto = (request.getParameter("impasto") != null && !request.getParameter("impasto").isEmpty())
				? Long.parseLong(request.getParameter("impasto"))
				: null;
		System.out.print("id Impasto servlet dashboard = " + idImpasto + "\n");

		CrudDAO dao = new CrudDAO();

		//recuperare impasto tramite id
		// Impasto impasto = dao.getImpasto(impastoNome);
		Impasto impasto = dao.getImpastoById(idImpasto);

		// String[] ingredientiNomi = request.getParameterValues("ingrediente");
		// List<Ingrediente> ingredientiList = new ArrayList<>();
		// ingredientiList = dao.getIngredienti(Arrays.asList(ingredientiNomi));

		Long[] ingredientiId = null;
		String[] recuperoId = request.getParameterValues("ingrediente");
		if (recuperoId != null) {
			ingredientiId = new Long[recuperoId.length];
			for (int i = 0; i < recuperoId.length; i++) {
				ingredientiId[i] = Long.parseLong(recuperoId[i]);
			}
		}
		List<Ingrediente> ingredientiList = dao.getIngredientiById(ingredientiId);

		System.out.println("ingredienti in DashSEr: " + ingredientiList);
		// recupero l'utente dalla sessione
		Utente utente = (Utente) request.getSession().getAttribute("Utente");

		// dao.createPizza(utente, nomePizza, impasto, ingredientiList);

		Pizza pizza = new Pizza();
		pizza.setNome(nomePizza);
		pizza.setImpasto(impasto);
		pizza.setIngredienti(ingredientiList);
		pizza.setUtente(utente);

		dao.createPizza(pizza);

		request.setAttribute("impastoList", dao.readAllImpasto());
		request.setAttribute("ingredienteList", dao.readAllIngredienti());

	}

	private void eliminaPizza(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Eliminata......");
		CrudDAO dao = new CrudDAO();
		Utente utente = (Utente) request.getSession().getAttribute("Utente");

		Long idPizza = Long.parseLong(request.getParameter("pizzaId"));

		System.out.println("Id pizza eliminata = " + idPizza);

		dao.deletePizzaById(idPizza);

		// recuperando la lista pizze utente direttamente dalla sessione tramite id
		// utente, dopo l'eliminazione non mi aggionrna la tabella con le pizze
		// eliminate. quindi aggiorno la lista pizze utente
		// utente.setPizze(dao.readAllPizza(utente));
		utente.setPizze(dao.readAllPizzaByIdUtente(utente.getId()));

		request.setAttribute("impastoList", dao.readAllImpasto());
		request.setAttribute("ingredienteList", dao.readAllIngredienti());

	}

	public void redirectUpdatePizza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CrudDAO dao = new CrudDAO();

		// Long idPizza = Long.parseLong(request.getParameter("pizzaId"));
		Long idPizza = (request.getParameter("pizzaId") != null && !request.getParameter("pizzaId").isEmpty())
				? Long.parseLong(request.getParameter("pizzaId"))
				: null;

		System.out.println("DashboardServlet. IdPizza: " + idPizza);
		Pizza pizza = dao.getPizzaById(idPizza);

		request.setAttribute("pizzaUpdate", pizza);
		request.setAttribute("impastoList", dao.readAllImpasto());
		request.setAttribute("ingredienteList", dao.readAllIngredienti());

		request.getRequestDispatcher("updatePizza.jsp").forward(request, response);
	}
}
