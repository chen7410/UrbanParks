package model;

import java.io.Serializable;

/**
 * This class represents a single user in the system.
 * 
 * @author Group 7
 *
 */
public abstract class User implements Serializable {

	private static final long serialVersionUID = 4L;
	/** user name*/
	private String myUserName;
	/** first name */
	private String myFirstName;
	/** last name */
	private String myLastName;
	/** user type */
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
	public User(final String theUserName, final String theFirstName, final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = "undefined";
	}

	/**
	 * return the user name of this user.
	 * 
	 * @return the user name of this user.
	 */
	public String getUserName() {
		return myUserName;
	}

	/**
	 * return the last name of this user.
	 * 
	 * @return the last name of this user.
	 */
	public String getFirstName() {
		return myFirstName;
	}

	/**
	 * return the last name of this user.
	 * 
	 * @return the last name of this user.
	 */
	public String getLastName() {
		return myLastName;
	}

	/**
	 * return the user type of this user.
	 * 
	 * @return the user type of this user.
	 */
	public String getUserType() {
		return myUserType;
	}

	/**
	 * this method is called from subclass, should never be override
	 * 
	 * @param theUserType
	 *            the specified user type.
	 */
	public final void setUserType(final String theUserType) {
		this.myUserType = theUserType;
	}

	/**
	 * display an user's information, for testing.
	 */
	@Override
	public String toString() {
		return "| User type: " + myUserType + " | User name: " + myUserName + " | First name: " + myFirstName
				+ " | Last name: " + myLastName;
	}
}
