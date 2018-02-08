package model;

import java.io.Serializable;

/**
 * This class represents a single user in the system.
 * @author Group 7
 *
 */
public abstract class User implements Serializable  {
	
	private static final long serialVersionUID = 9L;
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myUserType;

	/**
	 * initialize fields.
	 * @param theUserName the specified user name.
	 * @param theFirstName the specified first name.
	 * @param theLastName the specified last name.
	 */
	public User(final String theUserName, final String theFirstName, final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = "undefined";
	}

	/**
	 * return the last name of this user.
	 * @return the last name of this user.
	 */
	public String getMyUserName() {
		return myUserName;
	}

	/**
	 * return the last name of this user.
	 * @return the last name of this user.
	 */
	public String getMyFirstName() {
		return myFirstName;
	}
	
	/**
	 * return the last name of this user.
	 * @return the last name of this user.
	 */
	public String getMyLastName() {
		return myLastName;
	}

	/**
	 * return the user type of this user.
	 * @return the user type of this user.
	 */
	public String getMyUserType() {
		return myUserType;
	}

	/**
	 * this method is called from subclass, should never be override
	 * @param theUserType the specified user type.
	 */
	public final void setMyUserType(final String theUserType) {
		this.myUserType = theUserType;
	}
	
	/**
	 * display an user's information, for testing.
	 */
	@Override
	public String toString() {
		return "| User type: " + myUserType +
				" | User name: " + myUserName + 
				" | First name: " + myFirstName + 
				" | Last name: " + myLastName + " |";
	}
}
