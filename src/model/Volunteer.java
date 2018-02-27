/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package model;

import java.io.Serializable;

/**
 * This class represents a volunteer.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class Volunteer extends User implements Serializable {

	/** A generated serial version UID for object Serialization.*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * {@inheritDoc}}
	 */
	public Volunteer(final String theUserName, final String theFirstName,
						final String theLastName) {
		super(theUserName, theFirstName, theLastName);
	}

	/**
	 * Cancels the specified job in the Volunteer's job list. If the job is not
	 * in the job list. The list remain unchanged and return false.
	 * 
	 * Pre-condition: The specified job must be in the job list.
	 * Post-condition: The specified job is removed from the volunteer's
	 * 					job list.
	 * @param theJob
	 *            The specified job that is being removed from the list.
	 * @return true if the job is removed from the list; false otherwise.
	 */
	public boolean cancelJob(final Job theJob) {
		if (theJob.isJobRemovable()) {
			return myJobs.remove(Integer.valueOf(theJob.getJobID()));
		}
		return false;
	}

	/**
	 * Signing up for a new job.
	 * 
	 * Per-condition: The job must be checked before this method is
	 * 					called.
	 * Post-condition: The job is added to the volunteer's job list.
	 * @param theJob
	 *            The job a volunteer wants to sign up for.
	 */
	public void signup(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}

	/**
	 * Check whether the start date of the jobs being signed up is allowed by
	 * the minimum day from the current date.
	 * 
	 * Pre-condition: The Job has been initialized with a non-null values.
	 * @param theJob
	 *            the job that being check.
	 * @return true if the stated date of the job is greater than the
	 *         minimum day current date and false otherwise.
	 */
	public boolean isAtLeastMinDays(final Job theJob) {
		return theJob.isAtLeastMinDays(Job.MIN_DAYS_TO_SIGN_UP);
	}

	/**
	 * Check whether the candidate job has conflict with the job they
	 * have already signed up for.
	 * 
	 * Pre-condition: The Job has been initialized with a non-null values.
	 * @param theCandidateJob
	 *            the candidate job.
	 * @param theCurrentJob
	 *            the job has already signed up.
	 * @return true if the candidate job does not conflict with the
	 *         			job has already signed up; false otherwise.
	 */
	public boolean isSameDayConflict(final Job theCandidateJob,
										final JobMap theJobList) {
		boolean overlaps = false;
		for (int i = 0; i < myJobs.size(); i++) {
			overlaps = overlaps || theCandidateJob.isSameDayConflict
						(theJobList.getJob(myJobs.get(i)));
		}
		return overlaps;
	}
}