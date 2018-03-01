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
	
	/**
	 * {@inheritDoc}}
	 */
	public Staff(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
	}
	
	/**
	 * Set the maximum pending jobs allowed by the system.
	 * 
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
	 * @param theJobMap List of all jobs in the system.
	 * @return jobList A list of all the jobs within the period specified.
	 * @throws IllegalArgumentException theStartDate or theEndDate or 
	 * 			theJobMap == null; or if Start date after end date or 
	 * 			End date before start date
	 */
	public ArrayList<Job> getJobsInPeriod (final LocalDate theStartDate, 
					final LocalDate theEndDate, final JobMap theJobMap) {
		if (theStartDate == null) {
			throw new IllegalArgumentException("Start date cannot be null");
		}
		
		if (theEndDate == null) {
			throw new IllegalArgumentException("End date cannot be null");
		}
		
		if (theJobMap == null) {
			throw new IllegalArgumentException("Job map cannot be null");
		}
		
		if (theStartDate.isAfter(theEndDate)) {
			throw new IllegalArgumentException("Start date cannot after end date");
		}
		
		if (theEndDate.isBefore(theStartDate)) {
			throw new IllegalArgumentException("End date cannot before start date");
		}
		return theJobMap.getJobsInPeriod(theStartDate, theEndDate);
	}
}