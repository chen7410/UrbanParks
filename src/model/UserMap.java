/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class that will be our User collection. This class is
 * implemented using HashMap. The key of this collection is
 * a String object which will be the user's name, the value
 * will be a User object.
 * 
 * @author  Group 7
 * @version February 12, 2018
 * @see     Collection
 * @see     HashMap
 */
public class UserMap {
		
	/**
	 * The default name of the serialized file where all users'
	 * data are loaded/stored.
	 */
	public static final String USERS_DATA_FILE = "UsersInformations.ser";
	
	private HashMap<String, User> myUsers;
		
	
	/**
	 * Constructs an empty UserMap. The key is an String and the
	 * value is a User object.
	 */
	public UserMap() {
		myUsers = new HashMap<String, User>();
	}

	 /** 
	 * Stores users' data into a serialized file on the local machine. 
	 * File type must be a .ser.
	 * 
	 * @param theFilename
	 *            The name of the serialized file where users' data
	 *            are written to.
	 * @throws IOException when an I/O exception (either failed
	 * 										or interrupted) occurs.
	 */
	public void storeUserMap(final String theFilename) {
		try {
			FileOutputStream file = new FileOutputStream(theFilename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(myUsers);
			out.close();
			file.close();
		} catch (IOException theIOException) {
			theIOException.printStackTrace();
			System.out.println("Save users information fail!");
		}
	}
	
	/**
	 * Loads user information from a file, using the given file name,
	 * into the UserMap. The file must be create by the
	 * storeUserMap method.
	 * 
	 * @param theFilename
	 *            The serialized file name from where users' data
	 *            are loaded from and put in this UserMap.
	 * @throws IOException when an I/O exception (either failed
	 * 										or interrupted) occurs.          
	 */
	@SuppressWarnings("unchecked")
	public void loadUserMap(final String theFilename) {
		try {
			FileInputStream file = new FileInputStream(theFilename);
			ObjectInputStream in = new ObjectInputStream(file);
			myUsers = (HashMap<String, User>) in.readObject();
			in.close();
			file.close();
		} catch (FileNotFoundException theFileNotFoundException) {
			System.out.println("No such a file!");
			theFileNotFoundException.printStackTrace();
		} catch (IOException theIOException) {
			theIOException.printStackTrace();
			System.out.println("Load users information fail!");
		} catch (ClassNotFoundException theClassNotFoundException) {
			System.out.println("Class not found exception");
			theClassNotFoundException.printStackTrace();
		}
	}
	
	/**
	 * Adds a user to this UserMap.
	 * 
	 * @param theUser user that is being added to this UserMap.
	 * 		  		  null will be added if theUser is null. 
	 */
	public void addUser(final User theUser) {
		myUsers.put(theUser.getUserName(), theUser);
	}
	
	/**
	 * Retrieves a user from this UserMap.
	 * 
	 * @param theUser the User that being retrieved from an UserMap.
	 * @return a user object or null if the user doesn't exist.
	 */
	public User getUser(final String theUsername) {
		if (myUsers.containsKey(theUsername)) {
			return myUsers.get(theUsername);
		}
		return null;
	}
	
	/**
	 * Returns a list of the names of all the users in the system.
	 * 
	 * @return an ArrayList of all the users' names.
	 */
	public List<String> getAllUserNames() {
		List<String> users = new ArrayList<>(myUsers.keySet());
		List<String> userList = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			userList.add(users.get(i));
		}
		return userList;
	}
}