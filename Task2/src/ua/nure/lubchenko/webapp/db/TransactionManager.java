package ua.nure.lubchenko.webapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.db.exception.TransactionException;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class TransactionManager {
	private final static Logger log = LogManager.getLogger();

	public <T> T doTask(Transaction<T> transaction, int transactionIsolation) throws TransactionException {
		log.entry(transaction, transactionIsolation);
		log.info("Task performing");
		try {
			log.trace("Getting connection");
			Connection connection = ConnectionHolder.getConnection();
			
			log.trace("Executing transaction");
			T result = transaction.execute();
			
			log.trace("Closing connection");
			connection.close();
			
			log.info("Finish performing task");
			log.exit(result);
			return result;
		} catch (DAOException | SQLException e) {
			log.error("An {} occurred",e);
			throw new TransactionException(e);
		}
	}
}
