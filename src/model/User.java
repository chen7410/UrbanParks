/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;
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
	
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myUserType;
	
	/**
	 * List of jobs this volunteer has signed up for.
	 */
	protected List<Integer> myJobs;
	
	/**
	 * Initialize fields.
	 * 
	 * Pre-condition: The given parameters must not be null nor empty.
	 * @throws IllegalArgumentException If the given parameters are
	 * 										either null or empty.
	 * @param theUserName
	 *            the specified user name.
	 * @param theFirstName
	 *            the specified first name.
	 * @param theLastName
	 *            the specified last name.
	 */
	protected User(final String theUserName, final String theFirstName,
					final String theLastName) {
		
		if (theUserName == null || theFirstName == null ||
				theLastName == null || theUserName.isEmpty() ||
				theFirstName.isEmpty() || theLastName.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
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
	public List<Job> getJobList(final JobMap theJobList) {
		List<Job> jobList = new ArrayList<>();
		for (int jobID : myJobs) {
			Job j = theJobList.getJob(jobID);
			if(j != null) {
				jobList.add(j);
			}
		}
		return jobList;
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