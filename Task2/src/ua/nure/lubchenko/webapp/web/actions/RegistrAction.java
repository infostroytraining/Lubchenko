package ua.nure.lubchenko.webapp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.webapp.web.Path;

public class RegistrAction extends Action {
	private final static Logger log = LogManager.getLogger();

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {

		log.trace("Registr action strarts");

		log.trace("Getting userServise from servlet context by attribute");
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");

		String name = request.getParameter("name");
		log.trace("Obtained parmetr: name = " + name);

		String surname = request.getParameter("surname");
		log.trace("Obtained parmetr: surname = " + surname);

		String password = request.getParameter("password");
		log.trace("Obtained parmetr: password = " + password);

		String confirmPassword = request.getParameter("confirmPassword");
		log.trace("Obtained parmetr: confirmPassword = " + confirmPassword);

		String email = request.getParameter("email");
		log.trace("Obtained parmetr: email = " + email);

		String imageUrl = request.getParameter("imageUrl");
		log.trace("Obtained parmetr: imageUrl = " + imageUrl);

		String message = null;
		String forward = Path.HOME_PAGE;
		log.trace("Forward adress: " + forward);

		try {
			if (userService.emailAlreadyInUse(email)) {
				message = "E-mail is already in use";
				request.setAttribute("message", message);
				log.warn(message);
				forward = Path.REGISTRATION_PAGE;
				log.trace("Forward adress: " + forward);

				return forward;
			}
		} catch (ServiceException e) {
			log.error("ServiceException - {} ocurred", e);
			
			message = "Some error on server occured :(";
			log.warn(message);
			request.setAttribute("message", message);
			forward = e.getForward(); 
			log.trace("Forward adress: " + forward);

			log.info("LoginAction was interapted");
			return forward;			
		}
		if (!password.equals(confirmPassword)) {
			message = "Passwords are not equal";
			log.warn(message);
			request.setAttribute("message", message);
			forward = Path.REGISTRATION_PAGE;
			log.trace("Forward adress: " + forward);
			log.info("Registration action was interrapted");
			return forward;
		}

		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setSurname(surname);
		user.setEmail(email);
		user.setImageUrl(imageUrl);

		log.trace("Finished setting user fields");
		try {
			log.trace("Adding user to the storage");
			userService.add(user);
		} catch (ServiceException e) {
			log.error("ServiceException - {} ocurred", e);
			message = "Some error on server occured :(";
			log.warn(message);
			request.setAttribute("message", message);
			forward = e.getForward(); 
			log.trace("Forward adress: " + forward);

			log.info("LoginAction was interapted");
			return forward;			
		}
		message = "Registration successful";
		request.setAttribute("message", message);
		log.info("Registration action finished successful");
		return forward;
	}
}
