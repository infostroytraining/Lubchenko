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
		log.entry();
		//if (connection.isClosed()||connection==null) {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "200222091712";
			try {
				log.trace("Gettin connection on URL = {}, USER = {}",url,user);
				connection = DriverManager.getConnection(url, user, password);
				log.trace("Finnished getting connection");
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		//	}
			log.exit(connection);
		return connection;
	}
}
