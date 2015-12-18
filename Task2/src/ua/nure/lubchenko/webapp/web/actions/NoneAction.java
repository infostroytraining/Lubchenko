package ua.nure.lubchenko.webapp.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoneAction extends Action {
	private static Logger log = LogManager.getLogger();

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		log.info("NoneAction");
		return null;
	}

}
