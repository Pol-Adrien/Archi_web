<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Distance</title>
</head>
<body>
	La distance entre
	<%=session.getAttribute("nomVille1")%>
	et
	<%=session.getAttribute("nomVille2")%>
	est de
	<%=session.getAttribute("dist")%>
	km.
	<br>
	<a href="index.jsp"> Retour </a>
	<br>
</body>
</html>