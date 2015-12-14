package ua.nure.lubchenko.webapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.lubchenko.webapp.db.exception.TransactionException;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class TransactionManager {
	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		try {
			Connection connection = ConnectionHolder.getConnection();
			T result = transaction.execute();
			connection.close();
			return result;
		} catch (DAOException | SQLException e) {
			throw new TransactionException(e);
		}
	}
}
