/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;

/**
 * This class represents a single user in the system.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myUserType;
	
	/**
	 * initialize fields.
	 * 
	 * @param theUserName
	 *            the specified user name.
	 * @param theFirstName
	 *            the specified first name.
	 * @param theLastName
	 *            the specified last name.
	 */
	public User(final String theUserName, final String theFirstName,
			final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = "undefined";
	}

	public String getUserName() {
		return myUserName;
	}

	public String getFirstName() {
		return myFirstName;
	}

	public String getLastName() {
		return myLastName;
	}

	public String getUserType() {
		return myUserType;
	}

	/**
	 * This method is called from subclass, should never be overridden.
	 * 
	 * @param theUserType
	 *            the specified user type.
	 */
	public final void setUserType(final String theUserType) {
		this.myUserType = theUserType;
	}

	/**
	 * Display an user's information, for testing.
	 */
	@Override
	public String toString() {
		return "| User type: " + myUserType + " | User name: "
				+ myUserName + " | First name: " + myFirstName
				+ " | Last name: " + myLastName;
	}
}