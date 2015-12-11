package ua.nure.lubchenko.Task2.web.actions;

import java.util.Map;
import java.util.TreeMap;
import static ua.nure.lubchenko.Task2.web.actions.ActionNames.*;

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
