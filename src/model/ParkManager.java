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
 * This class represents a park manager.
 * @author Group 7
 * @version February 9, 2018
 *
 */
public class ParkManager extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**The maximum number of pending jobs in the system.*/
	private static final int MAX_JOB_AMOUNT = 20;

	private static final int MAX_JOB_LENGTH = 3;

	private static final int MAX_END_DAY = 75;
	
	/**
	 * List of job IDs this Park manager manages.
	 */
	private List<Integer> myJobs;

	public ParkManager(final String theUserName, final String theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		this.setUserType("Park Manager");
		myJobs = new ArrayList<>();
	}

	/**
	 * Submit a new job to the system.
	 * Per-condition: the job must be checked before submit.
	 * Post-condition: the job add to the park manager job list.
	 * @param theJob the job that is being submitted.
	 */
	public void createJob(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}

	/**
	 * Check whether the pending jobs is less than the maximum number allowed by the system.
	 * @param theJobList the job map that is being checked.
	 * @return true if the current number of pending jobs is less than the maximum number allowed;
	 * 			false otherwise.
	 */
	public boolean isLessThanMaxJobs(final JobMap theJobList) {
		return theJobList.size() < MAX_JOB_AMOUNT;
	}
	
	/**
	 * Check if the job is not more than the max days.
	 * 
	 * @param theJob calculate the length of theJob
	 * @return true if theJob is within the maximum number of pending jobs in the system.
	 * 		   false otherwise.
	 */
	public boolean isJobWithinMaxDays(final Job theJob) {
		boolean withinMaxDays = true;

		LocalDate jobStartDate = theJob.getStartDate();
		LocalDate jobEndDate = theJob.getEndDate();

		Period diff = Period.between(jobStartDate, jobEndDate);
		int daysDifference = diff.getDays();

		if (daysDifference > MAX_JOB_LENGTH) {
			return false;
		}

		return withinMaxDays;
	}

	/**
	 * Test if the job end date is less than or equal MAX_END_DAY days from now.
	 * @param theJob The job to check
	 * @return True if end date is MAX_END_DAY days or less from now.
	 */
	public boolean isJobEndsWithinMaxDays (final Job theJob) {
		return theJob.getEndDate().isBefore(LocalDate.now().plusDays(MAX_END_DAY + 1));
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
	public int getMaxJobAmount() {
		return MAX_JOB_AMOUNT;
	}
	
	public int getMaxJobLength() {
		return MAX_JOB_LENGTH;
	}
	
	public int getMaxEndDay() {
		return MAX_END_DAY;
	}
	
	/**
	 * Return a list of job ID of a park manager's pending job.
	 * @return a list of job ID of a park manager's pending job.
	 */
	public List<Integer> getJobList() {
		return myJobs;
	}
}
