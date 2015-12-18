package ua.nure.lubchenko.webapp.web.filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.nure.lubchenko.webapp.web.Path;

/**
 * Servlet Filter implementation class RegistrationValidationFilter
 */
@WebFilter("/RegistrationValidationFilter")
public class RegistrationValidationFilter implements Filter {
private static final Logger log = LogManager.getLogger();
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.info("Filter#destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Filter starts");
		
		String name = request.getParameter("name");
		log.trace("Obtained parametr : name = "+name);
		
		String surname = request.getParameter("surname");
		log.trace("Obtained parametr : surname = "+surname);

		String password = request.getParameter("password");
		log.trace("Obtained parametr : password = "+password);

		String email = request.getParameter("email");
		log.trace("Obtained parametr : email = "+email);

		String captcha = request.getParameter("captchaCodeTextBox");
		log.trace("Obtained parametr : captchaCodeTextBox = "+captcha);

		String forward = Path.REGISTRATION_PAGE;
		log.trace("Forward path = "+forward);

		Pattern emailPattern = Pattern.compile("[a-z][0-9a-z.-_]*@[a-z]+.[a-z]+");
		Matcher emailMatcher = emailPattern.matcher(email.toLowerCase());
		
		if (name != null && !name.isEmpty() && surname != null && !surname.isEmpty() && password != null
				&& password.length() > 5 && email != null && emailMatcher.matches() && captcha != null
				&& !captcha.isEmpty()) {
			log.trace("All registration parametrs are valid");
			
			log.info("Registration Validation Filter finished successful");
			chain.doFilter(request, response);
		} else {
			String message = "";
			if (!emailMatcher.matches())
				message += "Wrong E-mail form" + System.lineSeparator();
			if (name.isEmpty())
				message += "Fill the 'name' field!" + System.lineSeparator();
			if (surname.isEmpty())
				message += "Fill the 'surname' field!" + System.lineSeparator();
			if (password.length() <= 5)
				message += "Password shoud not be less than 6 simbols" + System.lineSeparator();
			if (captcha.isEmpty())
				message += "Fill the 'captha' field";
			log.warn(message);
			request.setAttribute("message", message);
			
			log.info("Filter was interrapted");
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		log.info("Filter#init(FilterConfig)");
	}

}
