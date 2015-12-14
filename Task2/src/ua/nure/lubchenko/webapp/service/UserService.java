package ua.nure.lubchenko.webapp.service;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;

public interface UserService {

	public User add(User answer) throws ServiceException;

	public User getById(int id) throws ServiceException;
	
	public User getByEmail(String email) throws ServiceException;
}
