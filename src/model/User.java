/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a single user in the system.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public abstract class User implements Serializable {

	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;
	
	private static final int MIN_NUM_DAYS_IN_THE_FUTURE = 3;
	
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myUserType;
	
	/**
	 * List of jobs this volunteer has signed up for.
	 */
	protected List<Job> myJobs;
	
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
	protected User(final String theUserName, final String theFirstName,
			final String theLastName) {
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserType = "undefined";
		myJobs = new ArrayList<>();
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
	
	public int getMinDaysInTheFuture() {
		return MIN_NUM_DAYS_IN_THE_FUTURE;
	}
	
	public boolean isJobRemovable(final Job theJob) {
		LocalDate minimumDate = LocalDate.now().plusDays(Job.MAX_DAYS_TO_SIGN_UP);
		return theJob.getStartDate().isEqual(minimumDate)
				|| theJob.getStartDate().isAfter(minimumDate);
	}

	/**
	 * This method is called from subclass, should never be overridden.
	 * 
	 * @param theUserType
	 *            the specified user type.
	 */
	public final void setUserType(final String theUserType) {
		myUserType = theUserType;
	}
	
	/**
	 * Returns a list of job belongs to this user.
	 * This list can be empty but not null.
	 * 
	 * @return a list of job belongs to this user.
	 */
	public List<Job> getJobList() {
		return myJobs;
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