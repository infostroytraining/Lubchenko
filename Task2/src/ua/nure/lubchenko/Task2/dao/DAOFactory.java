package ua.nure.lubchenko.Task2.dao;

import ua.nure.lubchenko.Task2.dao.memory.MemoUserDAO;

public class DAOFactory {
	private static DAOFactory daof;

	public synchronized static DAOFactory getDaoFactory() {
		if (daof == null) {
			daof = new DAOFactory();
		}
		return daof;
	}
	
	public UserDAO getUserDao(){
		return new MemoUserDAO(); 
	}
}
