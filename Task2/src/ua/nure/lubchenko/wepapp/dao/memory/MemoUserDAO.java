package ua.nure.lubchenko.wepapp.dao.memory;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class MemoUserDAO implements UserDAO {
	Logger log = LogManager.getLogger();
	// private UserStorage storage;
	//
	// public MemoUserDAO(UserStorage storage) {
	// this.storage = storage;
	// }

	public User create(User user) {
		log.entry(user);
		user = UserStorage.putUser(user);
		log.exit(user);
		return user;
	}

	public User get(int id) {
		log.trace("getting user by id from memo storage");
		for (User user : UserStorage.users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User getByEmail(String email) {
		log.trace("getting user by email from memo storage");
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