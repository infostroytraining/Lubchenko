package ua.nure.lubchenko.webapp.web.actions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.lubchenko.webapp.web.ControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ LoginActionTest.class, NoneActionTest.class, RegistrActionTest.class, ControllerTest.class })
public class AllServletTests {

}
