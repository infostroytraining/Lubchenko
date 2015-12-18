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
	private final static Logger log = LogManager.getLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Controller#doGet");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("Controller#doPost");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.entry();
		log.info("Controller starts");
		log.trace("Setting utf-8 as character encoding");
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("action");
		log.trace("Action name: "+actionName);
		
		Action action = ActionContainer.getAction(actionName);
		log.trace("Obtained action: "+action);
		
		log.trace("Performing an action..");
		String forward = action.perform(request, response);
		log.trace("Forward adress: " + forward);

		if (forward != null) {
			// response.sendRedirect(request.getContextPath() + forward);
			getServletContext().getRequestDispatcher(forward).forward(request, response);
		}
		
		log.info("Controller finished");
		log.exit();
	}
}
