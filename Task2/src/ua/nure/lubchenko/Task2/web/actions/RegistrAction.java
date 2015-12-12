package ua.nure.lubchenko.Task2.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import botdetect.web.Captcha;
import ua.nure.lubchenko.Task2.dao.DAOFactory;
import ua.nure.lubchenko.Task2.dao.UserDAO;
import ua.nure.lubchenko.Task2.entity.User;

public class RegistrAction extends Action {
	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String imageUrl = request.getParameter("imageUrl");
		String message = null;
		String forward = "/homePage.jsp";
		if (!password.equals(confirmPassword)) {
			message = "Passwords are not equal";
			System.out.println(message);

			request.setAttribute("message", message);
			forward = "/registration.jsp";
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
				forward = "/registration.jsp";
				return forward;

			} else {

				DAOFactory daof = DAOFactory.getDaoFactory(); 
				UserDAO udao = daof.getUserDao();
				User user = new User();
				user.setName(name);
				user.setPassword(password);
				user.setSurname(surname);
				user.setEmail(email);
				user.setImageUrl(imageUrl);

				udao.create(user);
				message = "Registration succesfull";
			}
		}
		return forward;
	}
}
