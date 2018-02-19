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
import java.util.HashMap;

/**
 * This class represents all user in the system.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class UserMap {
	
	/** A map that stores all users. key: user name value: user object.*/
	private HashMap<String, User> myUsers;
	
	public UserMap() {
		myUsers = new HashMap<String, User>();
	}
	
	/**
	 * Add a user to the UserMap.
	 * 
	 * @param theUser
	 *            the User that being added to an UserMap.
	 */
	public void addUser(final User theUser) {
		myUsers.put(theUser.getUserName(), theUser);
	}
	
	/**
	 * Retrieves a user from an UserMap.
	 * 
	 * @param theUser the User that being retrieved from an UserMap.
	 * @return a user or null if the user doesn't exist.
	 */
	public User getUser(final String theUsername) {
		if (myUsers.containsKey(theUsername)) {
			return myUsers.get(theUsername);
		}
		return null;
	}
	
	/**
	 * Outputting all users information into a file specified by the
	 * given file name. File type must be a .ser.
	 * 
	 * @param theFilename
	 *            The name of the file where the user informations
	 *            should be placed.
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
	 * Loading user information from a file, using the given file name,
	 * into the UserMap. The file must be create by the storeUserMap method.
	 * 
	 * @param theFilename
	 *            The file name from where the user informations
	 *            should be gathered.
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
}