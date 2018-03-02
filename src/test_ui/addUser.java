package test_ui;

import model.UserMap;

public class addUser {
	private static UserMap myUsers;

	public static void main(String[] args) {
		myUsers = new UserMap();
		myUsers.loadUserMap(myUsers.USERS_DATA_FILE);
		
		//Staff tom = new Staff("tom", "Tom", "Hanks");
		//myUsers.addUser(tom);
		System.out.println(myUsers.getUser("tom"));
		System.out.println(myUsers.getAllUserNames().toString());

	}

}
