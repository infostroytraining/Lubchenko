package ua.nure.lubchenko.webapp.db;

import java.sql.Connection;

public class ConnectionHolder {
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();

	public static void setConnection(Connection connection) {
		connectionHolder.set(connection);
	}

	public static Connection getConnection() {
		return connectionHolder.get();
	}
}
