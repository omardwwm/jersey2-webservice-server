package fr.doranco.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class DataSourceConnexion {

private Connection conn = null;
	
	public static DataSourceConnexion INSTANCE = new DataSourceConnexion();

	private DataSourceConnexion() {
		String url = "jdbc:mysql://localhost:3306/project_ecommercedb";
		String user = "root";
		String pass = "" ;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
				
	}

	public static DataSourceConnexion getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataSourceConnexion();
		}
		return INSTANCE;
	}

	public Connection getConn() {
		return conn;
	}
}
