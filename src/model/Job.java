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
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * @param theCandidateJob
	 * @return true when jobs overlap otherwise return false.
	 * 
	 */
	public boolean isOverLappingDay(Job theCandidateJob) {
		boolean overlaps = false;

		if (theCandidateJob.getStartDate().isAfter(myStartDate) 
				&& theCandidateJob.getStartDate().isBefore(myEndDate)) {
			overlaps = true;
		} else if (theCandidateJob.getEndDate().isAfter(myStartDate)
				&& theCandidateJob.getEndDate().isBefore(myEndDate)) {
			overlaps = true;
		} else if (theCandidateJob.getStartDate().isEqual(myStartDate)
				|| theCandidateJob.getStartDate().isEqual(myEndDate)) {
			overlaps = true;
		} else if (theCandidateJob.getEndDate().isEqual(myStartDate)
				|| theCandidateJob.getEndDate().isEqual(myEndDate)) {
			overlaps = true;
		}

		return overlaps;
	}
	
	/**
	 * @return format the job details. 
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
}