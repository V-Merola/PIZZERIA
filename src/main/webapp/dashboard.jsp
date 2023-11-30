<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<!DOCTYPE html>

<html>

<head>
<title>Dashboard</title>
</head>
<body>
	<h1>
		Dashboard di
		<%=((Utente) request.getSession().getAttribute("Utente")).getUsername()%>
	</h1>

	<form action="DashboardServlet" method="post">
		<table>
			<tr>
				<td>
					<div>
						<h2>Seleziona impasto:</h2>
						<table border="1">
							<tbody>
								<%
								List<Impasto> impastiLista = (List<Impasto>) request.getAttribute("impastoList");
								if (impastiLista != null && !impastiLista.isEmpty()) {
									for (Impasto impasto : impastiLista) {
								%>
								<tr>
									<td><input type="radio" name="impasto"
										value="<%=impasto.getId()%>"></td>
									<td><%=impasto.getNome()%></td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>
					</div>
				</td>
				<td>
					<div>
						<h2>Seleziona ingredienti:</h2>
						<table border="1">
							<tbody>
								<%
								List<Ingrediente> ingredienti = (List<Ingrediente>) request.getAttribute("ingredienteList");
								if (ingredienti != null && !ingredienti.isEmpty()) {
									for (Ingrediente ingrediente : ingredienti) {
								%>
								<tr>
									<td><input type="checkbox" name="ingrediente"
										value="<%=ingrediente.getId()%>"></td>
									<td><%=ingrediente.getNome()%></td>
								</tr>
								<%
								}
								}
								%>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</table>
		<br> <label for="nomePizza">Nome pizza:</label> <input
			type="text" id="nomePizza" name="nomePizza" required> <input
			type="hidden" name="action" value="salvaPizza"> <input
			type="submit" value="Invia">
		<div>
			<h2>Pizze create:</h2>
			<table border="1">

				<thead>
					<tr>
						<th>Nome Pizza</th>
						<th>Impasto</th>
						<th>Ingredienti</th>
						<th>Elimina</th>
						<th>Modifica</th>
					</tr>
				</thead>

				<tbody>
					<%
					//List<model.Pizza> pizzaList = (List<model.Pizza>) request.getAttribute("pizzaList");
					//Recupero la lista direttamente dall'utente della sessione
					List<model.Pizza> pizzaList = ((Utente) request.getSession().getAttribute("Utente")).getPizze();
					if (pizzaList != null && !pizzaList.isEmpty()) {
						for (Pizza pizza : pizzaList) {
					%>
					<tr>

						<td><%=pizza.getNome()%></td>

						<td>
							<%
							Impasto impasto = pizza.getImpasto();
							if (impasto != null) {
							%> <%=impasto.getNome()%> <%
 							} else {
 									%> Impasto non disponibile <%
 								}
 							%>
						</td>
						<td>
							<ul>
								<%
								for (Ingrediente ingrediente : pizza.getIngredienti()) {
								%>
								<li><%=ingrediente.getNome()%></li>
								<%
								}
								%>
							</ul>
						</td>
						<td>
							<form action="DashboardServlet" method="post">
								<input type="hidden" name="action" value="eliminaPizza">
								<input type="hidden" name="pizzaId" value="<%=pizza.getId()%>">
								<input type="submit" value="Elimina Pizza">
							</form>
						</td>
						<td>
							<form action="DashboardServlet" method="post">
								<input type="hidden" name="action" value="updatePizza">
								<input type="hidden" name="pizzaId" value="<%=pizza.getId()%>">
								<input type="submit" value="Modifica Pizza">
							</form>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="4">Nessuna pizza creata</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</form>
</body>

</html>
