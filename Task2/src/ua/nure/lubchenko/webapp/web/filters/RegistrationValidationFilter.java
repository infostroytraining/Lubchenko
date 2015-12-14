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


/**
 * Servlet Filter implementation class RegistrationValidationFilter
 */
@WebFilter("/RegistrationValidationFilter")
public class RegistrationValidationFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter start");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println(email);
		String captcha = request.getParameter("captchaCodeTextBox");
		String forward = "/registration.jsp";
		Pattern emailPattern = Pattern.compile("[a-z][0-9a-z.-_]*@[a-z]+.[a-z]+");
		Matcher emailMatcher = emailPattern.matcher(email.toLowerCase());
		if (name != null && !name.isEmpty() && surname != null && !surname.isEmpty() && password != null
				&& password.length()>5 && email!=null && emailMatcher.matches() && captcha!=null && !captcha.isEmpty()){
			chain.doFilter(request, response);			
		}else{
			String message = "";
			if(!emailMatcher.matches()) message += "Wrong E-mail form"+System.lineSeparator();
			if(name.isEmpty()) message += "Fill the 'name' field!"+System.lineSeparator();
			if(surname.isEmpty()) message += "Fill the 'surname' field!"+System.lineSeparator();
			if(password.length()<=5) message += "Password shoud not be less than 6 simbols"+System.lineSeparator();
			if(captcha.isEmpty()) message += "Fill the 'captha' field";
			System.out.println(message);
			request.setAttribute("message", message);
			request.getRequestDispatcher(forward).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
