package ua.nure.lubchenko.webapp.web.listener;

import java.util.ServiceConfigurationError;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.db.TransactionManager;
import ua.nure.lubchenko.webapp.service.MemoUserService;
import ua.nure.lubchenko.webapp.service.TransactionalUserService;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.wepapp.dao.UserDAO;
import ua.nure.lubchenko.wepapp.dao.memory.MemoUserDAO;
import ua.nure.lubchenko.wepapp.dao.postgre.PostgreUserDAO;

public class ServiceFactory {

	public static final String MEMORY = "memory";
	public static final String DB = "db";
	private static final String POSTGRE_DRIVER = "org.postgresql.Driver";

	private static Logger logger = LogManager.getLogger();

	public static UserService getUserService(String type) {
	logger.info("Service factory starts");
		if (type == null || type.isEmpty()) {
			logger.fatal("Could initialize application. Source type is null or empty");
			throw new IllegalArgumentException();
		}
		if (type.equals(MEMORY)) {
			logger.trace("Storage type : "+type);
			return initMemoryService();
		} else if (type.equals(DB)) {
			logger.trace("Storage type : "+type);

			loadPostgreDriver();
			return initTransactionalService();
		} else {
			logger.fatal("Could initialize application with source type {}", type);
			throw new ServiceConfigurationError("Could initialize application with source type [" + type + "]");
		}
	}

	private static void loadPostgreDriver() {
		logger.info("Loading Postgre Driver...");
		try {
			Class.forName(POSTGRE_DRIVER);
			logger.info("Finished loading Postgre Driver");
		} catch (ClassNotFoundException e) {
			logger.fatal("Could not load {  } driver", POSTGRE_DRIVER);
			throw new ServiceConfigurationError("Could not load " + POSTGRE_DRIVER + " driver");
		}
	}

	private static UserService initMemoryService() {
		logger.info("Initializating memory service...");
		UserDAO userDAO = new MemoUserDAO();
		logger.info("Finished initializating memory service");
		return new MemoUserService(userDAO);
	}

	private static UserService initTransactionalService() {
		logger.info("Initializating transactional service...");
		TransactionManager transactionManager = new TransactionManager();
		UserDAO userDAO = new PostgreUserDAO();
		logger.info("Finished initializating transactional service...");
		return new TransactionalUserService(transactionManager, userDAO);
	}
}

