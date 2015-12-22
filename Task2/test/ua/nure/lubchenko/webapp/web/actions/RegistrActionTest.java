package ua.nure.lubchenko.webapp.web.actions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.lubchenko.webapp.entity.User;
import ua.nure.lubchenko.webapp.service.UserService;
import ua.nure.lubchenko.webapp.service.exception.ServiceException;
import ua.nure.lubchenko.webapp.web.Path;

@RunWith(MockitoJUnitRunner.class)
public class RegistrActionTest {
	private static final String MESSAGE = "message";

	Action action = new RegistrAction();

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private ServletContext context;

	@Mock
	private UserService userService;

	@Mock
	private RequestDispatcher dispatcher;

	@Before
	public void init() {
		when(request.getServletContext()).thenReturn(context);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(context.getAttribute("userService")).thenReturn(userService);

		when(request.getParameter("email")).thenReturn("spiderman@gmail.com");
		when(request.getParameter("name")).thenReturn("Spider");
		when(request.getParameter("surname")).thenReturn("Man");
		when(request.getParameter("password")).thenReturn("spider321");
		when(request.getParameter("confirmPassword")).thenReturn("spider321");
	}

	@Test
	public void testPerformAction() throws ServiceException {
		String expected = Path.HOME_PAGE;
		String actual = action.perform(request, response);

		assertEquals(expected, actual);
		verify(userService).add(any(User.class));
		verify(request).setAttribute(eq(MESSAGE), eq("Registration successful"));
	}

	@Test
	public void testPerformActionWithExcistingEmail() throws ServiceException {
		String expected = Path.REGISTRATION_PAGE;
		
		when(userService.emailAlreadyInUse(anyString())).thenReturn(true);
		
		String actual = action.perform(request, response);
		
		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("E-mail is already in use"));
	}

	@Test
	public void testPasswodrConfirmNotEquals() throws ServiceException {
		String expected = Path.REGISTRATION_PAGE;
		when(request.getParameter("confirmPassword")).thenReturn("wrongPass");
		String actual = action.perform(request, response);

		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("Passwords are not equal"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUserServiceThrowsExeptionWhenCheckingEmail() throws ServiceException {
		when(userService.emailAlreadyInUse(anyString())).thenThrow(ServiceException.class);
		String expected = Path.ERROR_PAGE;
		String actual = action.perform(request, response);		
		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("Some error on server occured :("));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUserServiceThrowsExeptionWhenAddNewUser() throws ServiceException {
		when(userService.add(any(User.class))).thenThrow(ServiceException.class);
		String expected = Path.ERROR_PAGE;
		String actual = action.perform(request, response);		
		assertEquals(expected, actual);
		verify(request).setAttribute(eq(MESSAGE), eq("Some error on server occured :("));	}
}
