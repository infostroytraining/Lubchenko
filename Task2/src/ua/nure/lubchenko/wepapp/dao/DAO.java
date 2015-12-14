package ua.nure.lubchenko.wepapp.dao;

import java.util.List;

import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public interface DAO<T> {
	T create(T entity) throws DAOException;

	T get(int id);

	T update(T entity);

	void remove(int id);

	List<T> getAll() throws DAOException;
	
}
