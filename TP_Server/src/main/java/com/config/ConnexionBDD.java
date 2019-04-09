package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	
	public Connection connexion() throws SQLException {
		Connection connect = null;
		try {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Ville_France?user=root&DatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}

}
