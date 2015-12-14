package ua.nure.lubchenko.webapp.db;

import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public interface Transaction<T> {
	public T execute() throws DAOException;
}
