package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.VilleFrance;

/**
 * Servlet implementation class DistanceServlet
 */
@WebServlet("/DistanceServlet")
public class DistanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistanceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codeVille1 = request.getParameter("ville1");
		String codeVille2 = request.getParameter("ville2");
		
		HttpSession sess = request.getSession();
		VilleFrance[] listeVilles = (VilleFrance[]) sess.getAttribute("listeVilles");
		
		VilleFrance ville1 = null;
		VilleFrance ville2 = null;
		for (int i = 0; i < listeVilles.length; i++) {

			if (listeVilles[i].getCodeCommuneINSEE().equals(codeVille1)) {
				System.out.println(listeVilles);
				ville1 = listeVilles[i];
			} 
			if (listeVilles[i].getCodeCommuneINSEE().equals(codeVille2)) {
				ville2 = listeVilles[i];
			}
		}
		double dist = distance(ville1, ville2);

		sess.setAttribute("nomVille1", ville1.getNomCommune());
		sess.setAttribute("nomVille2", ville2.getNomCommune());
		sess.setAttribute("dist", dist);

		RequestDispatcher dispat = request.getRequestDispatcher("distance.jsp");
		dispat.forward(request, response);
	}

	double distance(VilleFrance ville1, VilleFrance ville2) {
		System.out.println(ville1);
		System.out.println(ville2);
		if (ville1.getNomCommune().equals(ville2.getNomCommune()))
			return 0;
		else {
			double lat1 = Double.parseDouble(ville1.getLatitude());
			double lat2 = Double.parseDouble(ville2.getLatitude());
			double long1 = Double.parseDouble(ville1.getLongitude());
			double long2 = Double.parseDouble(ville2.getLongitude());

			double distLat = (lat2 - lat1) * (Math.PI / 180); 
			double distLong = (long2 - long1) * (Math.PI / 180);

			// Formule de Haversine
			double a = Math.sin(distLat/2) * Math.sin(distLat/2) + Math.cos(lat1 * (Math.PI/180))
					* Math.cos(lat2 * (Math.PI/180)) * Math.sin(distLong/2) * Math.sin(distLong/2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double d = 6371 * c;

			return d;
		}

	}

}
