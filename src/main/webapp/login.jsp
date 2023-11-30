<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<h1>Pagina di Login</h1>
	<form action="LoginServlet" method="post">
		username: <input type="text" name="username" id="username" required><br>
		password : <input type="password" name="password" id="password"
			required><br> <input type="submit" value="Login">

		<!-- visualizzazione di messaggi di errore -->
		<c:if test="${not empty requestScope.errore}">
			<p style="color: red">${requestScope.errore}</p>
		</c:if>


	</form>
</body>
</html>