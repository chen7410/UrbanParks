/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents an Urban Parks staff.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class Staff extends User {

	/** A generated serial version UID for object Serialization.*/
	private static final long serialVersionUID = 1L;

	public Staff(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
	}
	
	
	public void setMaxJobAmount(final int theJobAmount) {
		Job.MAX_JOB_AMOUNT = theJobAmount;
	}
	
	/**
	 * Returns a list with jobs between a start date and an end date, inclusive.
	 * 
	 * @param theStartDate Beginning of the period.
	 * @param theEndDate  End of the period.
	 * @param theJobs List of all jobs in the system.
	 * @return jobList A list of all the jobs within the period specified.
	 */
	public ArrayList<Job> getJobsInPeriod (final LocalDate theStartDate, 
					final LocalDate theEndDate, final JobMap theJobs) {
		return theJobs.getJobsInPeriod(theStartDate, theEndDate);
	}

}
