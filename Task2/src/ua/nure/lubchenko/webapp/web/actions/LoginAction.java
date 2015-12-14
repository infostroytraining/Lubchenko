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
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = null;
		String forward;

		// validation
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			message = "Password and/or login cannot be empty";
			request.setAttribute("message", message);
			forward = Path.HOME_PAGE;
			return forward;
		}

		User user = null;
		try {
			log.trace("create user from storage by email: "+email);
			user = userService.getByEmail(email);
			log.trace("USER : "+user);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("USER: " + user);
		HttpSession session = request.getSession();

		// checking the password and email
		if (user == null || !password.equals(user.getPassword())) {
			message = "User with such e-mail/password not founded";
			request.setAttribute("message", message);
			forward = Path.HOME_PAGE;
		} else {
			forward = Path.WELCOME_PAGE;
		}
		session.setAttribute("user", user);
		return forward;
	}

}
