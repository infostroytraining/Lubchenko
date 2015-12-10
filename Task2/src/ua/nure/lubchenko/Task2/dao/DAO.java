package ua.nure.lubchenko.Task2.dao;

public interface DAO<T> {
	T create(T inst);

	T get(int id);

	void update(T inst);

	boolean delete(T inst);
}
