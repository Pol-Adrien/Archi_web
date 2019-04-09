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

	<%RestTemplate restTemplate = new RestTemplate();
	VilleFrance[] villes= restTemplate.getForObject("http://localhost:8181/get", VilleFrance[].class);
 	String[] noms = new String[villes.length];
	
// 	String[] attributsVilles = villes.split(" ");
// 	out.println(villes[0].getNomCommune());
// 	out.println("<br>");
// 	out.println(villes[1]);
	for(int i = 0; i<villes.length; i++){
		noms[i] = villes[i].getNomCommune();
// 		out.println(noms[i]);
	}
	%>
	
	<form action="AccueilServ" method="post">
		<select name="ville1">
			<%for(int i = 0; i<noms.length; i++){%> 
					<option value=<%out.println(noms[i]);%>>
					<%out.println(noms[i]);%></option>
	  		<%}%> 
		</select>
		
		<select name="ville2">
			<%for(int i = 0; i<noms.length; i++){%> 
					<option value=<%out.println(noms[i]);%>>
					<%out.println(noms[i]);%></option>
	  		<%}%> 
		</select>
	
	</form>

</body>
</html>