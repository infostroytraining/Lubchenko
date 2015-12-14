package ua.nure.lubchenko.webapp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.web.actions.Action;
import ua.nure.lubchenko.webapp.web.actions.ActionContainer;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 8915343610789783941L;
	private static Logger logger = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		logger.trace("In controller");
		System.out.println("In controller");
		String actionName = request.getParameter("action");
		System.out.println("action : " + actionName);
		Action action = ActionContainer.getAction(actionName);
		String forward = action.perform(request, response);

		if (forward != null) {
			// response.sendRedirect(request.getContextPath() + forward);
			getServletContext().getRequestDispatcher(forward).forward(request, response);
		}
	}
}
