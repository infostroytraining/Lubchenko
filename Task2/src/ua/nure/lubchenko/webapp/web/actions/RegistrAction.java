package ua.nure.lubchenko.webapp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import botdetect.web.Captcha;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.webapp.web.Path;

public class RegistrAction extends Action {
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = (UserService) request.getServletContext().getAttribute("userService");

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String imageUrl = request.getParameter("imageUrl");
		String message = null;
		String forward = Path.HOME_PAGE;

		try {
			if (userService.emailAlreadyInUse(email)) {
				message = "E-mail is already in use";
				forward = Path.REGISTRATION_PAGE;
				return forward;
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		if (!password.equals(confirmPassword)) {
			message = "Passwords are not equal";
			System.out.println(message);

			request.setAttribute("message", message);
			forward = Path.REGISTRATION_PAGE;
			return forward;
		}

		Captcha captcha = Captcha.load(request, "captcha");

		if ("POST".equalsIgnoreCase(request.getMethod())) {
			while (request.getAttributeNames().hasMoreElements()) {
				System.out.println(request.getAttributeNames().nextElement());
			}
			System.out.println(captcha);
			boolean isHuman = captcha.validate(request, request.getParameter("captchaCodeTextBox"));
			if (!isHuman) {
				message = "Captcha does not match";
				request.setAttribute("message", message);
				forward = Path.REGISTRATION_PAGE;
				return forward;
			} else {

				User user = new User();
				user.setName(name);
				user.setPassword(password);
				user.setSurname(surname);
				user.setEmail(email);
				user.setImageUrl(imageUrl);
				try {
					userService.add(user);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				message = "Registration succesfull";
			}
		}
		return forward;
	}
}
