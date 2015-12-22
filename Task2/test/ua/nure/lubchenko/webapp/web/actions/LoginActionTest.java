package ua.nure.lubchenko.webapp.web.actions;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.webapp.web.Path;

@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {

	private static final String MESSAGE = "message";
	private static final String EMAIL = "userEmail@marwel.com";
	private static final String PASSWORD = "userPassword";
	Action action = new LoginAction();
	User user;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private ServletContext context;
	
	@Mock
	private UserService service;
	
	@Mock
	private HttpSession session;
	
	@Before
	public void init(){
		when(request.getServletContext()).thenReturn(context);
		when(context.getAttribute("userService")).thenReturn(service);
	}
	
	@Test
	public void testPerformAction() throws ServiceException {
		String expected = Path.WELCOME_PAGE;
		user = getUser(EMAIL, PASSWORD);
		
		when(request.getParameter("email")).thenReturn(EMAIL);	
		when(request.getParameter("password")).thenReturn(PASSWORD);
		when(service.getByEmail(anyString())).thenReturn(user);
		when(request.getSession()).thenReturn(session);
		
		String actual = action.perform(request, response);
		
		assertEquals(expected, actual);
		verify(session).setAttribute(eq("user"), any(User.class));
	}
	
	@Test
	public void testPerformActionUserEnteredWrongPassword() throws ServiceException {
		String expected = Path.HOME_PAGE;
		user = getUser(EMAIL, "wrongPassword");
		
		when(request.getParameter("email")).thenReturn(EMAIL);	
		when(request.getParameter("password")).thenReturn(PASSWORD);
		when(service.getByEmail(anyString())).thenReturn(user);
		when(request.getSession()).thenReturn(session);
		String actual = action.perform(request, response);
		
		assertEquals(expected, actual);	
		verify(request).setAttribute(eq(MESSAGE), eq("User with such e-mail/password not founded"));
	}
	
	@Test
	public void testPerformActionCustumerMissedEmailField() throws ServiceException {
		
		String expected = Path.HOME_PAGE;
		when(request.getParameter("email")).thenReturn(null);	
		when(request.getParameter("password")).thenReturn(PASSWORD);
		String actual = action.perform(request, response);
		
		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("Password and/or login cannot be empty"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPerformActionThrowsServiceExeption() throws ServiceException {
		
		user = getUser(EMAIL,PASSWORD);
		
		String expected = Path.ERROR_PAGE;
		when(request.getParameter("email")).thenReturn(EMAIL);	
		when(request.getParameter("password")).thenReturn(PASSWORD);
		when(service.getByEmail(anyString())).thenThrow(ServiceException.class);

		String actual = action.perform(request, response);
		
		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("Some error on server occured :("));
	}
	
	private User getUser(String email, String password){
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		return user;
	}
}
