package ua.nure.lubchenko.webapp.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import botdetect.web.Captcha;
import ua.nure.lubchenko.webapp.web.Path;

/**
 * Servlet Filter implementation class CaptchaValodationFilter
 */
@WebFilter("/CaptchaValodationFilter")
public class CaptchaCheckFilter implements Filter {
	private final static Logger log = LogManager.getLogger();

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.info("CaptchaFilter#destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if ("registr".equals(request.getParameter("action"))) {
			log.info("CAPCHA FILTR #dofiltr");

			log.info("Checking captcha...");
			Captcha captcha = Captcha.load(request, "captcha");
			if ("POST".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
				boolean isHuman = captcha.validate(((HttpServletRequest) request),
						request.getParameter("captchaCodeTextBox"));
				if (isHuman) {
					log.info("Passed captcha");

					chain.doFilter(request, response);
				} else {
					String message = "Captcha does not match";

					String forward = Path.REGISTRATION_PAGE;
					log.trace("Forward adress: " + forward);
					log.warn(message);
					request.setAttribute("message", message);
					log.info("Filter was interrapted");
					request.getRequestDispatcher(forward).forward(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		log.info("CaptchaFilter#init");
	}

}
