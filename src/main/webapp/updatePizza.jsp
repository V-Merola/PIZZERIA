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
		Dashboard di modifica di
		<%=((Utente) request.getSession().getAttribute("Utente")).getUsername()%>
	</h1>

	<form action="UpdateServlet" method="post">
		<%
		Pizza pizzaDaModificare = (Pizza) request.getAttribute("pizzaUpdate");
		%>

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
										boolean checked = false;

										if (impasto.getNome().equals(pizzaDaModificare.getImpasto().getNome())) {
									checked = true;
										}
								%>
								<tr>
									<td><input type="radio" name="impasto"
										value="<%=impasto.getId()%>" <%=checked ? "checked" : ""%>></td>
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
										boolean checked = false;
										for (Ingrediente pizzaIngrediente : pizzaDaModificare.getIngredienti()) {
									if (ingrediente.getId().equals(pizzaIngrediente.getId())) {
										checked = true;
										break;
									}
										}
								%>
								
								<tr>
									<td><input type="checkbox" name="ingrediente"
										value="<%=ingrediente.getId()%>" <%=checked ? "checked" : ""%>></td>
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
			type="text" id="nomePizza" name="nomePizza"
			value="<%=pizzaDaModificare.getNome()%>"> <input
			type="hidden" name="pizzaId" value="<%=pizzaDaModificare.getId()%>">
		<input type="hidden" name="action" value="salvaPizza"><br>
		<br> <input type="submit" value="Modifica Pizza">
		<div>
			<h2>Pizza da modificare:</h2>
			<table border="1">

				<thead>
					<tr>
						<th>Nome Pizza</th>
						<th>Impasto</th>
						<th>Ingredienti</th>
					</tr>
				</thead>

				<tbody>
					<%
					if (pizzaDaModificare != null) {
					%>
					<tr>

						<td><%=pizzaDaModificare.getNome()%></td>

						<td><%=pizzaDaModificare.getImpasto().getNome()%></td>
						<td>
							<ul>
								<%
								for (Ingrediente ingrediente : pizzaDaModificare.getIngredienti()) {
								%>
								<li><%=ingrediente.getNome()%></li>
								<%
								}
								%>
							</ul>
						</td>

					</tr>
					<%
					} else {
					%>
					<tr>
						<td colspan="4">Nessuna pizza da modificare</td>
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