package ua.nure.lubchenko.webapp.service;

import java.util.List;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.exception.DAOException;

public class MemoUserService implements UserService {

	private UserDAO UserDAO;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByEmail(String email) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
