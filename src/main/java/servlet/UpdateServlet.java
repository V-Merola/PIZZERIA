package servlet;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CrudDAO;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CrudDAO dao = new CrudDAO();

		// String impastoNome = request.getParameter("impasto");
		String nomePizza = request.getParameter("nomePizza");

		Long idImpasto = (request.getParameter("impasto") != null && !request.getParameter("impasto").isEmpty())
				? Long.parseLong(request.getParameter("impasto"))
				: null;

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

		Long idPizza = (request.getParameter("pizzaId") != null && !request.getParameter("pizzaId").isEmpty())
				? Long.parseLong(request.getParameter("pizzaId"))
				: null;

		// Stampa di prova
		System.out.println("UPDATE SERVLET: " + "idPizza= " + idPizza + "\nnomePizza: " + nomePizza + "\nimpasto: "
				+ impasto.getNome() + "\nIngredienti: " + ingredientiList);

		// dao.updatePizza(idPizza, nomePizza, impasto, ingredientiList);

		Utente utente = (Utente) request.getSession().getAttribute("Utente");

		Pizza pizza = new Pizza();
		pizza.setId(idPizza);
		pizza.setNome(nomePizza);
		pizza.setImpasto(impasto);
		pizza.setIngredienti(ingredientiList);
		pizza.setUtente(utente);

		dao.updatePizza(pizza);

		// utente.setPizze(dao.readAllPizza(utente));
		utente.setPizze(dao.readAllPizzaByIdUtente(utente.getId()));

		request.setAttribute("impastoList", dao.readAllImpasto());
		request.setAttribute("ingredienteList", dao.readAllIngredienti());

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

}
