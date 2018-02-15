/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Job class holds information relating to a job which then would be added t
 * JobMap if it satisfies all the requirements.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class Job implements Serializable, Comparable<Job> {

	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;
	
	public final static int MAX_DAYS_TO_SIGN_UP = 3;
	
	/** The maximum number of pending jobs in the system.*/
	public static int MAX_JOB_AMOUNT = 10;

	public static final int MAX_JOB_LENGTH = 4;

	/** The system cannot add jobs that goes beyond the maximum end date*/
	public static final int MAX_END_DAY = 60;

	private final int myJobID;

	private LocalDate myStartDate;

	private LocalDate myEndDate;

	private String myParkName;

	private ParkManager myPM;

	private String myLocation;

	private String myDescription;

	public Job(final LocalDate theStartDate, final LocalDate theEndDate, 
			final String theParkName, final ParkManager thePM, 
			final String theLocation, final String theDescription) {
		
		myStartDate = theStartDate;
		myEndDate = theEndDate;
		myParkName = theParkName;
		myPM = thePM;
		myJobID = this.hashCode();
		myLocation = theLocation;
		myDescription = theDescription;
	}

	public int getJobID() {
		return myJobID;
	}

	public LocalDate getStartDate() {
		return myStartDate;
	}

	public LocalDate getEndDate() {
		return myEndDate;
	}

	public String getParkName() {
		return myParkName;
	}

	public ParkManager getParkManager() {
		return myPM;
	}

	public String getMyLocation() {
		return myLocation;
	}

	public String getDescription() {
		return myDescription;
	}
	
	/**
	 * @return String summary of the job in the following format:
	 * 		   Park Name: Start Date - End Date 
	 */
	public String getJobSummary() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		StringBuilder sb = new StringBuilder(100);
		sb.append(getParkName() + ": ");
		sb.append(myStartDate.format(formatter));
		sb.append(" - ");
		sb.append(myEndDate.format(formatter));
		return sb.toString();
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		StringBuilder sb = new StringBuilder(100);
		sb.append(">>> Park name: " + myParkName + '\n');
		sb.append("    Park manager: " + myPM.getFirstName() + ' ' + myPM.getLastName() + '\n');
		sb.append("    Park location: " + myLocation + '\n');
		sb.append("    Job start date: " + myStartDate.format(formatter) + '\n');
		sb.append("    Job end date: " + myEndDate.format(formatter) + '\n');
		sb.append("    Job description: " + myDescription + "\n");
		return sb.toString();
	}

	@Override
	public int compareTo(Job o) {
		if (myStartDate.isBefore(o.getStartDate())) {
			return -1;
		} else if(myStartDate.isEqual(o.getStartDate()) && myEndDate.isBefore(o.getEndDate())) {
			return -1;
		} else if (myStartDate.isAfter(o.getStartDate())) {
			return 1;
		} else {
			return 0;
		}
	}
}