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
		this.UserDAO = UserDAO;
	}

	public List<User> getAll() throws DAOException {
		return UserDAO.getAll();
	}

	@Override
	public User add(User user) throws ServiceException {
		User createdUser = null;
		
		if (user != null) {
			try {
				createdUser = UserDAO.create(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return createdUser;
	}

	@Override
	public User getById(int id) throws ServiceException {
		log.trace("serching user by id:  "+id);
		User user = UserDAO.get(id);
		log.exit(user);
		return user;
	}

	@Override
	public User getByEmail(String email) throws ServiceException {
		log.trace("serching user by email:  "+email);
		User user = UserDAO.getByEmail(email);
		log.exit(user);
		return user;
	}
	
	@Override
	public boolean emailAlreadyInUse(String email) throws ServiceException{
		return getByEmail(email)!=null;
	}

}
