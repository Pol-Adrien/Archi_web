<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ page import="org.springframework.web.client.RestTemplate"%>
<%@ page import="com.dto.VilleFrance"%>
<head>
<meta charset="ISO-8859-1">
<title>Calcul distance entre deux villes</title>
</head>
<body>

	<%
		RestTemplate restTemplate = new RestTemplate();
		VilleFrance[] listeVilles= restTemplate.getForObject("http://localhost:8181/get", VilleFrance[].class);
	 	String[] noms = new String[listeVilles.length];
	 	String[] codeCommune = new String[listeVilles.length];
	
		HttpSession sess = request.getSession();
		sess.setAttribute("listeVilles", listeVilles);
		for (int i = 0; i < listeVilles.length; i++) {
			noms[i] = listeVilles[i].getNomCommune();
			codeCommune[i] = listeVilles[i].getCodeCommuneINSEE();
		}
	%>
	
	<form action="DistanceServlet" method="post">
		<select name="ville1">
			<%for(int i = 0; i<noms.length; i++){%> 
					<option value=<%out.println(codeCommune[i]);%>>
					<%out.println(noms[i]);%></option>
	  		<%}%> 
		</select>
		<select name="ville2">
			<%for(int i = 0; i<noms.length; i++){%> 
					<option value=<%out.println(codeCommune[i]);%>>
					<%out.println(noms[i]);%></option>
	  		<%}%> 
		</select>
		<input type="submit" name="valider">	
	</form>
	
</body>
</html>