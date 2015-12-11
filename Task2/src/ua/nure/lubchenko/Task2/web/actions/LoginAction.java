package ua.nure.lubchenko.Task2.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = null;
		String forward = "/welcomePage.jsp";

		// if (login == null || password == null || login.isEmpty() ||
		// password.isEmpty()) {
		// message = "Password and/or login cannot be empty";
		// request.setAttribute("message", message);
		// return forward;
		// }
		// User user = new UserDAO().getUserByLogin(login);

		HttpSession session = request.getSession();

//		if (user == null || !password.equals(user.getPassword())) {
//			message = "User with such login/password not founded";
//			request.setAttribute("message", message);
//			forward = Path.LOGIN_PAGE;
//		} else {
//
//			forward = "/welcomePage.jsp";
//
//		}
//		session.setAttribute("user", user);
		return forward;

	}

}
