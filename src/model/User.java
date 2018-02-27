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

	protected User(final String theUserName, final String theFirstName, final String theLastName) {
		if (theUserName == null || theFirstName == null || theLastName == null) {
			throw new NullPointerException();
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
	 * null.
	 * 
	 * @param theJobList a collection of 
	 * @return a list of job belongs to this user.
	 */
	public List<Job> getJobList(final JobMap theJobList) {
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