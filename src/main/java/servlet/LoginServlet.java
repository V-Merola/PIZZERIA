package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CrudDAO;
import java.util.List;

import model.Impasto;
import model.Ingrediente;
import model.Utente;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CrudDAO utenteDAO = new CrudDAO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Utente utente = utenteDAO.readUtente(username, password);

		if (utente != null) {

			System.out.println("Utente in LoginServlet: " + utente);

			List<Impasto> impastoList = utenteDAO.readAllImpasto();
			List<Ingrediente> ingredientiList = utenteDAO.readAllIngredienti();

			// request.setAttribute("Utente", utente);

			request.getSession().setAttribute("Utente", utente);

			request.setAttribute("impastoList", impastoList);
			request.setAttribute("ingredienteList", ingredientiList);

			request.getRequestDispatcher("dashboard.jsp").forward(request, response);

			// HttpSession session = request.getSession();

			// response.sendRedirect("DashboardServlet");

			// INVIARE AL JSP ANCHE LA LISTA DELLE PIZZE INSERITE DALL'UTENTE

		} else {
			request.setAttribute("errore", "Credenziali errate");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}
