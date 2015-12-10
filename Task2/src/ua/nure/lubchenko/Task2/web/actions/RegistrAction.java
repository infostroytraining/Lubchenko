package ua.nure.lubchenko.Task2.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.lubchenko.Task2.dao.DAO;
import ua.nure.lubchenko.Task2.entity.User;
import ua.nure.lubchenko.Task2.memory.MemoUserDAO;

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
//TODO: validation

		DAO<User> mudao = new MemoUserDAO();

		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setSurname(surname);
		user.setEmail(email);
		user.setImageUrl(imageUrl);

		mudao.create(user);
		message = "Registration succesfull";

		return forward;
	}
}
