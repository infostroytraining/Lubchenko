package ua.nure.lubchenko.webapp.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class MemoUserService implements UserService {

	private UserDAO UserDAO;
	private static Logger log = LogManager.getLogger();

	public MemoUserService(UserDAO UserDAO) {
		log.info("MemoUserService instance creating");
		this.UserDAO = UserDAO;
	}

	public List<User> getAll() throws DAOException {
		return UserDAO.getAll();
	}

	@Override
	public User add(User user) throws ServiceException {
		log.entry();
		log.info("Adding user : {} to the memory storage", user);
		User createdUser = null;

		log.trace("Checking if argument!=null");
		if (user != null) {
			try {
				log.trace("Creating user with ID in UserDAO");
				createdUser = UserDAO.create(user);
			} catch (DAOException e) {
				log.error("DAOException {} ocured", e);
				throw new ServiceException(e);
			}
		}
		log.info("Finished adding user");
		log.exit(createdUser);
		return createdUser;
	}

	@Override
	public User getById(int id) throws ServiceException {
		log.entry();
		log.info("Getting user by ID = {}", id);
		User user = UserDAO.get(id);
		log.exit(user);
		return user;
	}

	@Override
	public User getByEmail(String email) throws ServiceException {
		log.entry();
		log.info("Getting user by e-mail = {}", email);
		User user = UserDAO.getByEmail(email);
		log.exit(user);
		return user;
	}

	@Override
	public boolean emailAlreadyInUse(String email) throws ServiceException {
		log.info("Checking if email is already in use");
		return getByEmail(email) != null;
	}

}
