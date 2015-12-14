package ua.nure.lubchenko.wepapp.dao;

import ua.nure.lubchenko.webapp.entity.User;

public interface UserDAO extends DAO<User> {

	public User getByEmail(String email);
}
