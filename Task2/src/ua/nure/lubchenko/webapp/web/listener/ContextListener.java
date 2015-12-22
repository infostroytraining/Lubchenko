package ua.nure.lubchenko.webapp.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.service.UserService;

/**
 * Application Lifecycle Listener implementation class ConetxtListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static Logger logger = LogManager.getLogger(ContextListener.class);
	private static final String STORAGE_INIT_PARAMETER = "storage";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Context listener initialization ...");

		ServletContext context = sce.getServletContext();

		initStorage(context);
		//initImageUploaderParams(context);
		logger.info("Context listener initializatin finished");
	}

	private void initStorage(ServletContext context) {
		String storageMode = context.getInitParameter(STORAGE_INIT_PARAMETER);
		logger.info("Try to initialize service for {} storage mode", storageMode);
		UserService userService = ServiceFactory.getUserService(storageMode);
		logger.info("Service initialized. Service: {}", userService);
		context.setAttribute("userService", userService);
	}

//	private void initImageUploaderParams(ServletContext context) {
//		String realPath = context.getRealPath("userImage/");
//        context.setAttribute("PHOTO_DIR", realPath + File.separator);
//	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Servlet context is destroyed");
	}
}
