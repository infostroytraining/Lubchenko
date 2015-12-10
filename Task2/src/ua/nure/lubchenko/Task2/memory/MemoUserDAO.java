package ua.nure.lubchenko.Task2.memory;

import ua.nure.lubchenko.Task2.dao.UserDAO;
import ua.nure.lubchenko.Task2.entity.User;


public class MemoUserDAO implements UserDAO{
	public User create(User user) {
		Storage.putUser(user);
		return null;
	}

	public User get(int id) {
		for (User user : Storage.users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public void update(User inst) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(User inst) {
		// TODO Auto-generated method stub
		return false;
	}
}
