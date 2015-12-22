package ua.nure.lubchenko.webapp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.web.Path;

public class NoneAction extends Action {
	private static Logger log = LogManager.getLogger();

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		log.info("NoneAction");
		return Path.ERROR_PAGE;
	}

}
