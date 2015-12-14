package ua.nure.lubchenko.wepapp.dao.memory;

import java.util.ArrayList;
import java.util.List;

import ua.nure.lubchenko.webapp.entity.User;

public class UserStorage {
	public static List<User> users = new ArrayList<User>();
	public static int userIdCounter = 0;

	static {
		User spiderMan = new User();
		spiderMan.setEmail("spiderMan@gmail.com");
		spiderMan.setName("Spider");
		spiderMan.setSurname("Man");
		spiderMan.setPassword("spider");
		
		User batMan = new User();
		batMan.setEmail("batMan@ukr.net");
		batMan.setName("Bat");
		batMan.setSurname("Man");
		batMan.setPassword("bat");
		
		User wonderWoman = new User();
		wonderWoman.setEmail("wonderWoman@gmail.com");
		wonderWoman.setName("Wonder");
		wonderWoman.setSurname("Woman");
		wonderWoman.setPassword("wonder");
		
		putUser(spiderMan);
		putUser(batMan);
		putUser(wonderWoman);
	}

	public static User putUser(User user) {
		user.setId(userIdCounter++);
		users.add(user);
		System.out.println(users);
		return user;
	}
	
	public static List<User> all() {
		return users;
	}
}
