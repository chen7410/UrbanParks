/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;

/**
 * This class represents a park manager.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ParkManager extends User implements Serializable {

	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;

	public ParkManager(final String theUserName, final String
						theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		setUserType("Park Manager");
	}

	/**
	 * Submit a new job to the system.
	 * Per-condition: the job must be checked before submit.
	 * Post-condition: the job add to the park manager job list.
	 * @param theJob the job that is being submitted.
	 */
	public void createJob(final Job theJob) {
		myJobs.add(theJob);
	}
}