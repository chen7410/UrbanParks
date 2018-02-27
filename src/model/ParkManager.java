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

	/** A generated serial version UID for object Serialization.*/
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}}
	 */
	public ParkManager(final String theUserName, final String
						theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		setUserType("Park Manager");
	}

	/**
	 * Submit a new job to the system.
	 * 
	 * Pre-condition: the job must be checked before submit.
	 * Post-condition: the job add to the park manager job list.
	 * @param theJob the job that is being submitted.
	 */
	public void createJob(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}
	
	/**
	 * Removes a job only removed from the Park Manager's list and
	 * not from the JobMap.
	 * 
	 * Pre-condition: The job passed in is in the ParkManager's
	 * 					job list. 
	 * @param theJob The given job to be removed.
	 * @return true if the job is able to be removed depending on if
	 * 				the minimum number of days business rule is satisfied
	 * 				and it was removed was successfully.
	 * 		false if it is not able to be removed because it fails to
	 * 				satisfied the minimum number of days business rule
	 * 				or there is an issue removing it from the list.
	 */
	public boolean removeJob(final Job theJob) {
		if (theJob.isJobRemovable()) {
			return myJobs.remove(Integer.valueOf(theJob.getJobID()));
		}
		return false;
	}
}