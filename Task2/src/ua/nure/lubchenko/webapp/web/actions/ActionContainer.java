package ua.nure.lubchenko.webapp.web.actions;

import static ua.nure.lubchenko.webapp.web.actions.ActionNames.*;

import java.util.Map;
import java.util.TreeMap;

public class ActionContainer {
private static Map<String, Action> container;
	
	static {
		container = new TreeMap<>();

		container.put(REGISTER_ACTION_NAME, new RegistrAction());
		container.put(LOGIN_ACTION_NAME, new LoginAction());
//		container.put(LOGOUT_ACTION_NAME, new LogoutAction());
//		container.put(SHOW_USERS_ACTION_NAME, new ShowUsersAction());
//
		container.put(NONE_ACTION_NAME, new NoneAction());
	}
	
	public static Action getAction(String actionName){
		
		if (actionName != null && container.containsKey(actionName)){
			return container.get(actionName);
		} else {
			return container.get(NONE_ACTION_NAME);
		}
		
	}
}
