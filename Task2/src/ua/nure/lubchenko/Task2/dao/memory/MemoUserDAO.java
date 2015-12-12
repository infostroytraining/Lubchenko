package ua.nure.lubchenko.Task2.dao.memory;

import ua.nure.lubchenko.Task2.dao.UserDAO;
import ua.nure.lubchenko.Task2.entity.User;

public class MemoUserDAO implements UserDAO {
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
		for (User user : Storage.users) {
			if (user.getId() == inst.getId()) {
				user = inst;
			}
		}
	}

	@Override
	public boolean delete(User inst) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public User getByEmail(String email) {
		for (User user : Storage.users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
}
