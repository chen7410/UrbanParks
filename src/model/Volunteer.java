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
 * @version February 12, 2018
 */
public class Volunteer extends User implements Serializable {

	/** A generated serial version UID for object Serialization. */
	private static final long serialVersionUID = 1L;

	public Volunteer(final String theUserName, final String theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		setUserType("Volunteer");
	}

	/**
	 * Cancel the specified job from a Volunteer job list. Precondition: the
	 * specified job must be checked. Postcondition: the specified job removed
	 * from a volunteer job list.
	 * 
	 * @param theJob
	 *            the specified that being removed from the list.
	 * @return true if the job remove from the list. otherwise, throw exception.
	 * @exception IllegalArgumentException
	 *                when try to cancel a job that is less than the minimum
	 *                days from the current date.
	 */
	public void cancelJob(final Job theJob) {
		myJobs.remove(theJob);
	}

	/**
	 * Sign up a new job. Per-condition: the job must be checked before sign up.
	 * Post-condition: the job add to the volunteer job list.
	 * 
	 * @param theJob
	 *            the job a volunteer want to sign up.
	 */
	public void signup(final Job theJob) {
		myJobs.add(theJob);
	}

	/**
	 * Check whether the start date of the jobs being signed up is allowed by
	 * the minimum day from the current date.
	 * 
	 * @param theJob
	 *            the job that being check.
	 * @return true if the stated date of the job is greater than the minimum
	 *         day current date and false otherwise.
	 */
	public boolean isAtLeastMinDays(final Job theJob) {
		return theJob.isAtLeastMinDays(Job.MIN_DAYS_TO_SIGN_UP);
	}

	/**
	 * Check whether the candidate job has conflict with the job has already
	 * signed up.
	 * 
	 * @param theCandidateJob
	 *            the candidate job.
	 * @param theCurrentJob
	 *            the job has already signed up.
	 * @return true if the candidate job does not conflict with the job has
	 *         already signed up; false otherwise.
	 */
	public boolean isSameDayConflict(final Job theCandidateJob) {
		boolean overlaps = false;
		for (int i = 0; i < myJobs.size(); i++) {
			overlaps = overlaps || theCandidateJob.isSameDayConflict(myJobs.get(i));
		}
		return overlaps;
	}
}