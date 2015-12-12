package ua.nure.lubchenko.Task2.dao;

import ua.nure.lubchenko.Task2.entity.User;

public interface UserDAO extends DAO<User> {

	public User getByEmail(String email);
}
