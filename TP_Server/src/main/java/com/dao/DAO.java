package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.VilleFrance;

public class DAO {
	
	//protected Connection connexion = null;
	
	//Constructeur
	public DAO() {
		super();
	}
	
	//Accesseurs
	public Connection getConnexion() throws SQLException {
		Connection connect = null;
		try {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Ville_France?user=root&DatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	//Méthodes
	public void creer(VilleFrance ville) {
		try {
			Connection connect = getConnexion();
			Statement stm = connect.createStatement();
			String requete = "INSERT INTO Ville_France VALUES (" + ville.getCodeCommuneINSEE() 
								+ ", '" + ville.getNomCommune() + "', " + ville.getCodePostal() 
								+ ", '" + ville.getLibelleAcheminement() + "', '" + ville.getLigne5() 
								+ "', " + ville.getLatitude() + ", " + ville.getLongitude() + ")";
			System.out.println(requete);
			stm.executeUpdate(requete);
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimer(String codePostal) {
		try {
			Connection connect = getConnexion();
			Statement stm = connect.createStatement();
			String requete = "DELETE FROM Ville_France WHERE Code_postal = " + codePostal;
			stm.executeUpdate(requete);
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifier(VilleFrance ville) {
		
	}
	
	public List<VilleFrance> trouver(String nomCommune, String codePostal) {
		return null;
	}
	
	public List<VilleFrance> lister() {
		List<VilleFrance> villes = new ArrayList<>();
		try {
			Connection connect = getConnexion();
			Statement stm = connect.createStatement();
			String requete = "SELECT * FROM Ville_France";
			ResultSet rset = stm.executeQuery(requete);
			while(rset.next()) {
				villes.add(new VilleFrance(rset.getString(1), rset.getString(2), rset.getString(3),
											rset.getString(4), rset.getString(5), rset.getString(6),
											rset.getString(7)));
			}
			rset.close();
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;
	}
	
}
