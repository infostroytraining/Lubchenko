package ua.nure.lubchenko.wepapp.dao.memory;

import java.util.List;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class MemoUserDAO implements UserDAO {
	
//	private UserStorage storage;
//
//	public MemoUserDAO(UserStorage storage) {
//		this.storage = storage;
//	}

	public User create(User user) {
		UserStorage.putUser(user);
		return null;
	}

	public User get(int id) {
		for (User user : UserStorage.users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User getByEmail(String email) {
		for (User user : UserStorage.users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() throws DAOException {
		return UserStorage.all();
	}
}
