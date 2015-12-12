package ua.nure.lubchenko.Task2.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.lubchenko.Task2.dao.DAOFactory;
import ua.nure.lubchenko.Task2.dao.UserDAO;
import ua.nure.lubchenko.Task2.entity.User;
import ua.nure.lubchenko.Task2.web.Path;

public class LoginAction extends Action {

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = null;
		String forward;
		
		//validation
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			message = "Password and/or login cannot be empty";
			request.setAttribute("message", message);
			forward = Path.HOME_PAGE;
			return forward;
		}
		
		DAOFactory daof = DAOFactory.getDaoFactory();
		UserDAO udao = daof.getUserDao();
		User user = udao.getByEmail(email);
		System.out.println("USER: "+user);
		HttpSession session = request.getSession();
		
		//checking the password and email
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
