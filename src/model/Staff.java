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
	
	/**
	 * Set the maximum pending jobs allowed by the system.
	 * @param theMaxJobAmount
	 * @param theJobmap
	 * @throws IllegalArgumentException if theJobAmount <= 0 or theJobmap == null.
	 */
	public void setMaxJobAmount(final int theMaxJobAmount, final JobMap theJobmap) {
		if (theMaxJobAmount <= 0) {
			throw new IllegalArgumentException("Illegal job amount: " + theMaxJobAmount);
		}
		
		if (theJobmap == null) {
			throw new IllegalArgumentException("Jobmap cannot be null");
		}
		
		theJobmap.setMaxJobAmount(theMaxJobAmount);
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
