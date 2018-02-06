package model;

/**
 * This class represents a single user in the system.
 * @author Group 7
 *
 */
public abstract class User {
	private String myUserName;

	private String myFirstName;

	private String myLastName;
	
	private String myUserType;

	public User(final String theUserName, final String theFirstName, final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = "undefined";
	}

	public String getMyUserName() {
		return myUserName;
	}

	public void setMyUserName(final String theUserName) {
		this.myUserName = theUserName;
	}

	public String getMyFirstName() {
		return myFirstName;
	}

	public void setMyFirstName(final String theFirstName) {
		this.myFirstName = theFirstName;
	}

	public String getMyLastName() {
		return myLastName;
	}

	public void setMyLastName(final String theLastName) {
		this.myLastName = theLastName;
	}
	
	public final String getMyUserType() {
		return myUserType;
	}

	public final void setMyUserType(final String theUserType) {
		this.myUserType = theUserType;
	}
	
	/**
	 * Display an user's information.
	 */
	@Override
	public String toString() {
		return "User type: " + myUserType +
				" | User name: " + myUserName + 
				" | First name: " + myFirstName + 
				" | Last name: " + myLastName;
		
	}
}
