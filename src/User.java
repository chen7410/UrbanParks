
public abstract class User {
	private String myUserName;

	private String myFirstName;

	private String myLastName;

	public User(final String theUserName, final String theFirstName, final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
	}

	public String getMyUserName() {
		return myUserName;
	}

	public void setMyUserName(final String myUserName) {
		this.myUserName = myUserName;
	}

	public String getMyFirstName() {
		return myFirstName;
	}

	public void setMyFirstName(final String myFirstName) {
		this.myFirstName = myFirstName;
	}

	public String getMyLastName() {
		return myLastName;
	}

	public void setMyLastName(final String myLastName) {
		this.myLastName = myLastName;
	}
}
