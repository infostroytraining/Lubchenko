package ua.nure.lubchenko.webapp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.webapp.web.Path;

public class LoginAction extends Action {
	private static Logger log = LogManager.getLogger();

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		log.entry();
		log.info("LoginAction starts");
		
		log.trace("Getting userServise from servlet context by attribute");
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");
		
		String email = request.getParameter("email");
		log.trace("Obtained parmetr: E-mail = "+ email);
		
		String password = request.getParameter("password");
		log.trace("Obtained parmetr: password = "+ password);

		String message = null;
		String forward;

		// validation
		log.trace("Checking input parametrs for validation");
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			message = "Password and/or login cannot be empty";
			log.warn(message);
			request.setAttribute("message", message);
			
			forward = Path.HOME_PAGE;
			log.trace("Forward adress: " + forward);
			
			log.info("LoginAction was interapted");
			return forward;
		}

		User user = null;
		try {
			log.trace("Creating user instance from storage by email: "+email);
			
			user = userService.getByEmail(email);
			log.trace("Obtained user: "+user);
		} catch (ServiceException e) {
			log.error("ServiceException {} ocured",e);
			e.printStackTrace();
		}
		
		log.trace("Getting new session...");
		HttpSession session = request.getSession();
		log.trace("Obtained session creation time: {}; ID number: {} ", session.getCreationTime(), session.getId());

		// checking the password and email
		if (user == null || !password.equals(user.getPassword())) {
			message = "User with such e-mail/password not founded";
			log.warn(message);
			request.setAttribute("message", message);
			forward = Path.HOME_PAGE; 
			log.trace("Forward adress: " + forward);

			log.info("LoginAction was interapted");
			return forward;
		} else {
			forward = Path.WELCOME_PAGE;
			log.trace("Forward adress: " + forward);
		}
		
		session.setAttribute("user", user);
		log.trace("User {} {} was logged",user.getName(), user.getSurname());
		
		log.info("LoginAction finished successful");
		return forward;
	}

}
