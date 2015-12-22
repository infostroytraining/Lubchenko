package ua.nure.lubchenko.webapp.web.actions;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.nure.lubchenko.webapp.web.Path;

@RunWith(MockitoJUnitRunner.class)
public class NoneActionTest {
	Action action = new NoneAction();
	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Test
	public void testPerform() {
		String expected = Path.ERROR_PAGE;
		String actual = action.perform(request, response);
		assertEquals(expected, actual);
	}

}
