package ua.nure.lubchenko.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionHolder {
	private static final Logger log = LogManager.getLogger();
	private static Connection connection;

	public static Connection getConnection() {
		//if (connection.isClosed()||connection==null) {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "200222091712";
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		//	}
		return connection;
	}
}
