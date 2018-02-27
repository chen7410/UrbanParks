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
 * @version February 26, 2018
 */
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String myUserName;
	private String myFirstName;
	private String myLastName;
	private String myUserType;
	protected List<Integer> myJobs;

	/**
	 * Constructs a user with the given username, first name, and last name;
	 * initializes a collection of job ID belongs to this user.
	 * 
	 * Pre-condition: The given parameters must not be null nor empty.
	 * 
	 * @param theUserName
	 *            the username of this user.
	 * @param theFirstName
	 *            the first name of this user.
	 * @param theLastName
	 *            the last name of this user.
	 * @throws IllegalArgumentException
	 *             if any of the given parameters are either null or empty.
	 */
	protected User(final String theUserName, final String theFirstName, final String theLastName) {

		if (theUserName == null || theFirstName == null || theLastName == null || theUserName.isEmpty()
				|| theFirstName.isEmpty() || theLastName.isEmpty()) {
			throw new IllegalArgumentException();
		}
		myUserName = theUserName;
		myFirstName = theFirstName;
		myLastName = theLastName;
		myJobs = new ArrayList<>();
	}

	/**
	 * Returns the username of this user. This username is a non-null string.
	 * 
	 * @return the username of this user.
	 */
	public String getUserName() {
		return myUserName;
	}

	/**
	 * Returns the first name of this user. This first name is a non-null string.
	 * 
	 * @return the first name of this user.
	 */
	public String getFirstName() {
		return myFirstName;
	}

	/**
	 * Returns the last name of this user. This last name is a non-null string.
	 * 
	 * @return the last name of this user.
	 */
	public String getLastName() {
		return myLastName;
	}

	/**
	 * Returns the user type of this user. This user type is a non-null string.
	 * 
	 * @return the user type of this user.
	 */
	public String getUserType() {
		return myUserType;
	}

	protected final void setUserType(final String theUserType) {
		myUserType = theUserType;
	}

	/**
	 * Returns a list of job belongs to this user. This list can be empty but not
	 * null. The job collection argument cannot be null.
	 * 
	 * @param theJobList
	 *            a collection of all the jobs in the system.
	 * @return a list of job belongs to this user.
	 * @throws IllegalArgumentException if theJobList is null
	 */
	public List<Job> getJobList(final JobMap theJobList) {
		if(theJobList == null) {
			throw new IllegalArgumentException("The job collection cannot be null");
		}
		List<Job> jobList = new ArrayList<>();
		for (int jobID : myJobs) {
			Job j = theJobList.getJob(jobID);
			if (j != null) {
				jobList.add(j);
			}
		}
		return jobList;
	}
}