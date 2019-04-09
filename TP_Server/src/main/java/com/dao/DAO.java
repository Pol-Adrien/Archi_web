package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.ConnexionBDD;
import com.dto.VilleFrance;

public class DAO {
	
	private ConnexionBDD connexionBDD = new ConnexionBDD();;
	
	//Constructeur
	public DAO() {
		super();
	}
	
	//Accesseur
	public Connection getConnexion() throws SQLException {
		return this.connexionBDD.connexion();
	}
	
	//Méthodes
	public void creer(VilleFrance ville) {
		String requete = "INSERT INTO Ville_France VALUES (?,?,?,?,?,?,?)";
		try {
			Connection connect = getConnexion();
			PreparedStatement stm = connect.prepareStatement(requete);
			
			stm.setString(1,ville.getCodeCommuneINSEE()); 
			stm.setString(2,ville.getNomCommune()); 
			stm.setString(3,ville.getCodePostal()); 
			stm.setString(4,ville.getLibelleAcheminement()); 
			stm.setString(5,ville.getLigne5()); 
			stm.setString(6,ville.getLatitude()); 
			stm.setString(7,ville.getLongitude());
			
			stm.executeUpdate();
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimer(String codePostal) {
		String requete = "DELETE FROM Ville_France WHERE Code_postal = ?";
		try {
			Connection connect = getConnexion();
			PreparedStatement stm = connect.prepareStatement(requete);
			stm.setString(1, codePostal);
			stm.executeUpdate();
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifier(String codeCommuneINSEE, String modif, int indiceModif) {
		List<String> requetes = new ArrayList<>();
		requetes.add("UPDATE Ville_France SET `Nom_commune`=? WHERE Code_postal = ? ");
		requetes.add("UPDATE Ville_France SET `Code_postal`=? WHERE Code_postal = ? ");
		requetes.add("UPDATE Ville_France SET `Libelle_acheminement`=? WHERE Code_postal = ? ");
		requetes.add("UPDATE Ville_France SET `Ligne_5`=? WHERE Code_postal = ? ");
		requetes.add("UPDATE Ville_France SET `Latitude`=? WHERE Code_postal = ? ");
		requetes.add("UPDATE Ville_France SET `Longitude`=? WHERE Code_postal = ? ");
		
		try {
			Connection connect = getConnexion();
			PreparedStatement stm = connect.prepareStatement(requetes.get(indiceModif-2));
			stm.setString(1, modif);
			stm.setString(2, codeCommuneINSEE);
			stm.executeUpdate();
			stm.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<VilleFrance> trouver(String codePostal) {
		List<VilleFrance> villes = new ArrayList<>();
		try {
			Connection connect = getConnexion();
			Statement stm = connect.createStatement();
			String requete = "SELECT * FROM Ville_France WHERE Code_postal = " + codePostal;
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
