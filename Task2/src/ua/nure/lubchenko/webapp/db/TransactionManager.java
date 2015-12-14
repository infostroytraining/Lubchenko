package ua.nure.lubchenko.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.nure.lubchenko.webapp.db.exception.TransactionException;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class TransactionManager {
	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		try {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String user = "postgres";
			String password = "200222091712";
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("CONNNNNNECTION   ====> "+connection);
			T result = transaction.execute();
			connection.close();
			return result;
		} catch (DAOException | SQLException e) {
			throw new TransactionException(e);
		}
	}
}
