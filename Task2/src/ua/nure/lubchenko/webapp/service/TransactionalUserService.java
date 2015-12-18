package ua.nure.lubchenko.webapp.service;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.db.Transaction;
import ua.nure.lubchenko.webapp.db.TransactionManager;
import ua.nure.lubchenko.webapp.db.exception.TransactionException;
import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class TransactionalUserService implements UserService{
	private TransactionManager transactionManager;
	private UserDAO UserDAO;
	private final static Logger log = LogManager.getLogger();
	
	public TransactionalUserService(TransactionManager transactionManager, UserDAO UserDAO) {
		log.info("TransactionalUserService instance creating");
		this.transactionManager = transactionManager;
		this.UserDAO = UserDAO;
	}

	@Override
	public User add(final User user) throws ServiceException {
		log.entry();
		log.info("Adding user : {} to the db", user);
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					log.info("Finished adding user.");
					log.exit();
					return UserDAO.create(user);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			log.error("TransactionException: {} ocured",e);
			throw new ServiceException(e);
		}
	}

	@Override
	public User getById(int id) throws ServiceException {
		log.entry();
		log.info("Getting user by ID = {}",id);

		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					log.info("Finished getting user.");
					log.exit();
					return UserDAO.get(id);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			log.error("TransactionException: {} ocured",e);
			throw new ServiceException(e);
		}
	}

	@Override
	public User getByEmail(String email) throws ServiceException {
		log.entry();
		log.info("Getting user by e-mail = {}",email);
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					log.info("Finished getting user.");
					log.exit();
					return UserDAO.getByEmail(email);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			log.error("TransactionException: {} ocured",e);
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean emailAlreadyInUse (String email) throws ServiceException {
		log.info("Checking if email is already in use");
		return getByEmail(email)!=null;
	}
}
