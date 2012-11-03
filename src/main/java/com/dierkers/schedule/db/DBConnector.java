package com.dierkers.schedule.db;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
	private Connection con;
	private Statement stmt;

	public DBConnector() {
		try {
			con = getConnection();
		} catch (URISyntaxException e) {
			System.err.println("Error connecting to the DB");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("SQLException connecting to the DB");
			e.printStackTrace();
		}

	}

	public ResultSet query(String query) {
		try {
			stmt = con.createStatement();
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Error performing query: " + query);
			return null;
		}

	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + "/" + dbUri.getPort();

		return DriverManager.getConnection(dbUrl, username, password);
	}
}
