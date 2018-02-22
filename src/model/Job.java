/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Job class holds information relating to a job which then would be
 * added to the JobMap if it satisfies all the requirements.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class Job implements Serializable, Comparable<Job> {

	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;
	
	public final static int MIN_DAYS_TO_SIGN_UP = 3;
	
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
	 * 
	 * @param theMinimumDaysToSignUp must be positive.
	 * @return
	 */
	public boolean isAtLeastMinDays(final int theMinimumDaysToSignUp) {
        LocalDate minimumDate = LocalDate.now().plusDays(theMinimumDaysToSignUp);
	    return !myStartDate.isBefore(minimumDate);
    }
	
	/**
	 * Precondition: the job must not be null.
	 * @param theJob 
	 * @return true if there is conflict; false otherwise.
	 */
	public boolean isSameDayConflict(final Job theJob) {
		boolean overlaps = false;
		overlaps = overlaps
				|| getStartDate().isEqual(theJob.getStartDate())
				|| getStartDate().isEqual(theJob.getEndDate())
				|| getEndDate().isEqual(theJob.getStartDate())
				|| getEndDate().isEqual(theJob.getEndDate())
				|| getEndDate().isAfter(theJob.getStartDate())
				&& getEndDate().isBefore(theJob.getEndDate())
				|| getStartDate().isAfter(theJob.getStartDate())
				&& getStartDate().isBefore(theJob.getEndDate());
		return overlaps;
	}
	
	/**
	 * Check if a job is able to be removed.
	 * 
	 * @return true if the job is removable; false otherwise.
	 */
	public boolean isJobRemovable() {
		LocalDate minimumDate = LocalDate.now()
								.plusDays(Job.MIN_DAYS_TO_SIGN_UP);
		
		return myStartDate.isEqual(minimumDate)
								|| myEndDate.isAfter(minimumDate);
	}
	
	/**
	 * Check if the job is not more than the max days.
	 * 
	 * @return true if theJob is within the maximum number of pending
	 * 			jobs in the system false otherwise.
	 */
	public boolean isJobWithinMaxDays() {
		boolean withinMaxDays = true;
		Long daysDifference = ChronoUnit.DAYS.between(myStartDate,
													myEndDate);

		if (daysDifference > Job.MAX_JOB_LENGTH) {
			return false;
		}
		return withinMaxDays;
	}

	/**
	 * Test if the job end date is less than or equal MAX_END_DAY
	 * days from now.
	 * 
	 * @param theJob The job to check
	 * @return True if end date is MAX_END_DAY days or less from now.
	 */
	public boolean isJobEndsWithinMaxDays() {
		return myEndDate.isBefore(LocalDate.now().plusDays
													(Job.MAX_END_DAY + 1));
	}
	
	public boolean isJobWithinDates(final LocalDate theStartDate,
									final LocalDate theEndDate) {
		return !myStartDate.isBefore(theStartDate)
				&& !myEndDate.isAfter(theEndDate);
	}
	
	/**
	 * @return String summary of the job in the following format:
	 * 		   Park Name: Start Date - End Date 
	 */
	public String getJobSummary() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		StringBuilder sb = new StringBuilder(100);
		sb.append(myStartDate.format(formatter));
		sb.append(" - ");
		sb.append(myEndDate.format(formatter) + ": ");
		sb.append(getParkName());
		return sb.toString();
	}
	
	public List<String> getJobDetailsList() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		List<String> details = new ArrayList<>();
		details.add("Park name: " + myParkName);
		details.add("Park manager: " + myPM.getFirstName() + ' ' + myPM.getLastName());
		details.add("Park location: " + myLocation);
		details.add("Job start date: " + myStartDate.format(dateFormatter));
		details.add("Job end date: " + myEndDate.format(dateFormatter));
		details.add("Job description: " + myDescription);
		return details;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		StringBuilder sb = new StringBuilder(100);
		sb.append(">>> Park name: " + myParkName + '\n');
		sb.append("    Park manager: " + myPM.getFirstName() + ' ' + myPM.getLastName() + '\n');
		sb.append("    Park location: " + myLocation + '\n');
		sb.append("    Job start date: " + myStartDate.format(dateFormatter) + '\n');
		sb.append("    Job end date: " + myEndDate.format(dateFormatter) + '\n');
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