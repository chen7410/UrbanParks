/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a volunteer.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class Volunteer extends User implements Serializable {

	/** A generated serial version UID for object Serialization.*/
	private static final long serialVersionUID = 1L;
	
	private final static int MAX_DAYS_TO_SIGN_UP = 2;
	
	private final static int JOB_START_DATE_IS_MIN_DAYS_AWAY= 3;
	
	/**
	 * List of jobs this volunteer has signed up for.
	 */
	private List<Integer> myJobs;
	
	public Volunteer(final String theUserName, final String theFirstName,
						final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		myJobs = new ArrayList<>();
		this.setUserType("Volunteer");
	}
	
	/**
	 * Cancel the specified job from a Volunteer job list.
	 * Precondition: the specified job must be in a volunteer job list.
	 * Postcondition: the specified job remove from a volunteer job list.
	 * @param theJob the specified that being removed from the list.
	 * @return true if the job remove from the list. otherwise, throw exception.
	 * @exception IllegalArgumentException when try to cancel a job that is 
	 * less than the minimum days from current date.
	 */
	public boolean cancelJob(final Job theJob) {
		LocalDate allowedDate = 
				LocalDate.now().plusDays(JOB_START_DATE_IS_MIN_DAYS_AWAY);
		if (!theJob.getStartDate().isBefore(allowedDate)) {
			return myJobs.remove(theJob);
		} else {
			throw new IllegalArgumentException("Can not cancel a"
					+ " job that is less than " + 
					JOB_START_DATE_IS_MIN_DAYS_AWAY + "days "
							+ "from current date.");
		}
	}
	
	/**
	 * Sign up a new job.
	 * Per-condition: the job must be checked before sign up.
	 * Post-condition: the job add to the volunteer job list.
	 * 
	 * @param theJob 
	 * 			the job a volunteer want to sign up.
	 */
	public void signup(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}
	
	/**
	 * Check whether the start date of the jobs being signed up is allowed
	 * by the minimum day from the current date.
	 * 
	 * @param theJob 
	 * 			the job that being check.
	 * @return true if the stated date of the job is greater than the
	 * 			minimum day current date and false otherwise.
	 */
	public boolean isAtLeastMinDays(final Job theJob) {
		boolean result = false;
		LocalDate date = LocalDate.now().plusDays(MAX_DAYS_TO_SIGN_UP);
		if (!date.isAfter(theJob.getStartDate())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Check whether the candidate job has conflict with the job has
	 * already signed up.
	 * 
	 * @param theCandidateJob
	 *            the candidate job.
	 * @param theCurrentJob
	 *            the job has already signed up.
	 * @return true if the candidate job does not conflict with the
	 * 			job has already signed up; false otherwise.
	 */
	public boolean isSameDayConflict(final Job theCandidateJob,
										final Job theCurrentJob) {
		boolean overlaps = false;
		overlaps = theCurrentJob.isOverLappingDay(theCandidateJob)
					|| overlaps;
		return overlaps;
	}
	
	/**
	 * Return the minimums days from today that allow to signed up.
	 * 
	 * @return the minimums days from today that allow to signed up.
	 */
	public int getMaxDaysToSignUp() {
		return MAX_DAYS_TO_SIGN_UP;
	}
	
	/**
	 * Return a list of job ID that a volunteer has signed up.
	 * 
	 * @return a list of job ID that a volunteer has signed up.
	 */
	public List<Integer> getJobList() {
		return myJobs;
	}
}