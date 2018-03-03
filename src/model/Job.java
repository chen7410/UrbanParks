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
 * Job class holds information relating to a job which then would be added to
 * the JobMap. 
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class Job implements Serializable, Comparable<Job> {

	/**
	 * A generated serial version UID for object Serialization.
	 */
	private static final long serialVersionUID = 1L;

	public final static int MIN_DAYS_TO_SIGN_UP = 3;

	public static final int MAX_JOB_LENGTH = 4;

	/** The system cannot add jobs that goes beyond the maximum end date */
	public static final int MAX_END_DAY = 60;

	private final int myJobID;

	private LocalDate myStartDate;

	private LocalDate myEndDate;

	private String myParkName;

	private ParkManager myPM;

	private String myLocation;

	private String myDescription;

	/**
	 * No null or empty parameters during construction.
	 * @param theStartDate
	 * @param theEndDate
	 * @param theParkName
	 * @param thePM
	 * @param theLocation
	 * @param theDescription
	 * @throws IllegalArgumentException if null or empty fields pass in during construction.
	 */
	public Job(final LocalDate theStartDate, final LocalDate theEndDate, final String theParkName,
			final ParkManager thePM, final String theLocation, final String theDescription) {

		checkArgumentException(theStartDate, theEndDate, theParkName, thePM, theLocation, theDescription);

		myStartDate = theStartDate;
		myEndDate = theEndDate;
		myParkName = theParkName;
		myPM = thePM;
		myJobID = this.hashCode();
		myLocation = theLocation;
		myDescription = theDescription;
	}

	/**
	 * Check whether a Job has valid arguments during construction.
	 * 
	 * @param theStartDate
	 * @param theEndDate
	 * @param theParkName
	 * @param thePM
	 * @param theLocation
	 * @param theDescription
	 * @throws IllegalArgumentException
	 *             if theStartDate == null or theEndDate == null or 
	 *             theParkName == null || theParkName.isEmpty() or 
	 *             thePM == null or theLocation == null || theLocation.isEmpty() or
	 *             theDescription == null || theDescription.isEmpty().
	 */
	private void checkArgumentException(final LocalDate theStartDate, final LocalDate theEndDate,
			final String theParkName, final ParkManager thePM, final String theLocation, final String theDescription) {
		if (theStartDate == null) {
			throw new IllegalArgumentException("Start date cannot be null");
		}
		if (theEndDate == null) {
			throw new IllegalArgumentException("End date cannot be null");
		}
		if (theParkName == null || theParkName.isEmpty()) {
			throw new IllegalArgumentException("Park name cannot be null or empty");
		}
		if (thePM == null) {
			throw new IllegalArgumentException("Park manager cannot be null");
		}
		if (theLocation == null || theLocation.isEmpty()) {
			throw new IllegalArgumentException("Location cannot be null");
		}
		if (theDescription == null || theDescription.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null");
		}
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
	 * Check whether a Job is at least minimum days in future.
	 * 
	 * @param theMinimumDaysInFuture 
	 *            must be non-negative.
	 * @return true if a Job is at least minimum days in future; false
	 *         otherwise.
	 */
	public boolean isAtLeastMinDays(final int theMinimumDaysInFuture) {
		if (theMinimumDaysInFuture < 0) {
			throw new IllegalArgumentException("Illegal day: " + theMinimumDaysInFuture);
		}

		LocalDate minimumDate = LocalDate.now().plusDays(theMinimumDaysInFuture);
		return !myStartDate.isBefore(minimumDate);
	}

	/**
	 * Check whether a Job is overlapped with theJob
	 * if they have overlapping start and end dates.
	 * 
	 * @param theJob
	 * @return true if a Job is overlapped with theJob; false otherwise.
	 */
	public boolean isSameDayConflict(final Job theJob) {
		if (theJob == null) {
			throw new IllegalArgumentException("The job cannot be null.");
		}

		boolean overlaps = false;
		overlaps = overlaps || getStartDate().isEqual(theJob.getStartDate())
				|| getStartDate().isEqual(theJob.getEndDate()) || getEndDate().isEqual(theJob.getStartDate())
				|| getEndDate().isEqual(theJob.getEndDate())
				|| getEndDate().isAfter(theJob.getStartDate()) && getEndDate().isBefore(theJob.getEndDate())
				|| getStartDate().isAfter(theJob.getStartDate()) && getStartDate().isBefore(theJob.getEndDate());
		return overlaps;
	}

	/**
	 * Check if a job is able to be removed. The removable job must be 
	 * 
	 * @return true if a job is removable; false otherwise.
	 */
	public boolean isJobRemovable() {
		LocalDate minimumDate = LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP);

		return myStartDate.isEqual(minimumDate) || myEndDate.isAfter(minimumDate);
	}

	/**
	 * Check if the job is not more than the max days.
	 * 
	 * @return true if theJob is within Job.MAX_JOB_LENGTH inclusive,
	 * 		 false otherwise.
	 */
	public boolean isJobWithinMaxDays() {
		boolean withinMaxDays = true;
		Long daysDifference = ChronoUnit.DAYS.between(myStartDate, myEndDate);

		if (daysDifference >= Job.MAX_JOB_LENGTH) {
			return false;
		}
		return withinMaxDays;
	}
	
	/**
	 * Checks to see if the job is already passed by comparing the start date with today's date.
	 * @return true if the job is already passed; false otherwise.
	 */
	public boolean isPassed() {
		return myStartDate.isBefore(LocalDate.now());
	}
	
	/**
	 * Checks to see if the start date of this job is before the end date
	 * @return true if the job's start date is before the job end date; false otherwise.
	 */
	public boolean isStartDateBeforeEnddate() {
		return myStartDate.isBefore(myEndDate);
	}

	/**
	 * Test if the job end date is less than or equal MAX_END_DAY days from now.
	 * 
	 * @param theJob
	 *            The job to check
	 * @return True if end date is MAX_END_DAY days or less from now.
	 */
	public boolean isJobEndsWithinMaxDays() {
		return myEndDate.isBefore(LocalDate.now().plusDays(Job.MAX_END_DAY + 1));
	}

	/**
	 * Check whether a Job is within theStartDate and theEndDate, inclusive.
	 * 
	 * @param theStartDate
	 * @param theEndDate
	 * @return true if it is; false otherwise.
	 */
	public boolean isJobWithinDates(final LocalDate theStartDate, final LocalDate theEndDate) {
		if (theStartDate == null) {
			throw new IllegalArgumentException("Start date cannot be null");
		}
		if (theEndDate == null) {
			throw new IllegalArgumentException("End date cannot be null");
		}
		if (theStartDate.isAfter(theEndDate)) {
			throw new IllegalArgumentException("Start date cannot be after end date");
		}
		return !myStartDate.isBefore(theStartDate) && !myEndDate.isAfter(theEndDate);
	}

	/**
	 * @return String summary of the job in the following format: Park Name:
	 *         Start Date - End Date
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

	/**
	 * 
	 * @return the detail of this job.
	 */
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
	public int compareTo(Job theJob) {
		if (myStartDate.isBefore(theJob.getStartDate())) {
			return -1;
		} else if (myStartDate.isEqual(theJob.getStartDate()) && myEndDate.isBefore(theJob.getEndDate())) {
			return -1;
		} else if (myStartDate.isAfter(theJob.getStartDate())) {
			return 1;
		} else {
			return 0;
		}
	}

}