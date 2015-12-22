package ua.nure.lubchenko.webapp.service.exception;

import ua.nure.lubchenko.webapp.web.Path;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 5796105116464714252L;
	public ServiceException(Throwable exeption) {
		super(exeption);
	}
	public String getForward(){
		return Path.ERROR_PAGE;
	}
}
