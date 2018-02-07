package model;
import java.util.HashMap;
import java.util.Set;

/**
 * This class represents all user in the system.
 * @author Group 7
 *
 */
public class UserMap {
	/**
	 * key is user name;value is user.
	 */
	private HashMap<String, User> myUsers;
	
	public UserMap() {
		myUsers = new HashMap<String, User>();
	}
	
	/**
	 * Add an user to an UserMap.
	 * @param theUser the User that being added to an UserMap.
	 */
	public void addUser(final User theUser) {
		myUsers.put(theUser.getMyUserName(), theUser);
	}
	
	/**
	 * retrieved an user from an UserMap.
	 * @param theUser the User that being retrieved from an UserMap.
	 * @return an user or null if the user doesn't exist.
	 */
	public User getUser(final String theUsername) {
		if (myUsers.containsKey(theUsername)) {
			return myUsers.get(theUsername);
		}
		return null;
	}
	
	/**
	 * Print all users in this UserMap, for testing.
	 */
	public void viewAllUser() {
		Set<String> userNames = myUsers.keySet();
		System.out.println(userNames.toString());
	}
	
}
