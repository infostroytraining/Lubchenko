package ua.nure.lubchenko.webapp.web;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.lubchenko.webapp.web.actions.ActionNames;
import ua.nure.lubchenko.webapp.web.Controller;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
	Controller controller = new Controller();
	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Mock
	private ServletContext context;

	@Mock
	private RequestDispatcher dispatcher;
	
	@Before
	public void init(){
		when(request.getServletContext()).thenReturn(context);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
	}

	@Test
	public void doPostTest() throws ServletException, IOException {
		
		when(request.getParameter("action")).thenReturn(ActionNames.NONE_ACTION_NAME);
		controller.doPost(request, response);
		
		verify(request).getRequestDispatcher(Path.ERROR_PAGE);
	}
	
	@Test
	public void doGetTest() throws ServletException, IOException {
		
		when(request.getParameter("action")).thenReturn(ActionNames.NONE_ACTION_NAME);
		controller.doGet(request, response);
		
		verify(request).getRequestDispatcher(Path.ERROR_PAGE);
	}
}
