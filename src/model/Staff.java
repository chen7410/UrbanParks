package model;

public class Staff extends User{

	/** A generated serial version UID for object Serialization.*/
	private static final long serialVersionUID = 1L;

	public Staff(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
	}
	
	public boolean isPositiveInteger(final int theNumber) {
		return theNumber > 0;
	}

}
