package ua.nure.lubchenko.webapp.service;

import java.sql.Connection;

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

	public TransactionalUserService(TransactionManager transactionManager, UserDAO UserDAO) {
		this.transactionManager = transactionManager;
		this.UserDAO = UserDAO;
	}

	@Override
	public User add(final User user) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return UserDAO.create(user);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User getById(int id) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return UserDAO.get(id);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User getByEmail(String email) throws ServiceException {
		try {
			return transactionManager.doTask(new Transaction<User>() {
				@Override
				public User execute() throws DAOException {
					return UserDAO.getByEmail(email);
				}
			}, Connection.TRANSACTION_READ_COMMITTED);
		} catch (TransactionException e) {
			throw new ServiceException(e);
		}
	}
}
