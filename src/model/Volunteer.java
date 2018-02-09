/**
 * T CSS 360 - Winter 2018
 * Team: Group 7
 * Urban Parks Project
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends User implements Serializable {
	
	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;

	private final static int MAX_DAYS_TO_SIGN_UP = 2;
	
	/**
	 * List of jobs this volunteer has signed up for.
	 */
	private List<Integer> myJobs;
	
	
	
	public Volunteer(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
		myJobs = new ArrayList<>();
		this.setUserType("Volunteer");
	}
	
	public void signup(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}
	
	/**
	 * check whether the the start date of the jobs being signed up is allowed 
	 * by the MAX_DAYS_TO_SIGN_UP;
	 * @param theJob the job that being check.
	 * @return true if the job date is at least two days after current date; false otherwise.
	 */
	public boolean isAtLeastMinDays(final Job theJob) {
		boolean result = false;
		final Period period = LocalDate.now().until(theJob.getStartDate());
		final int dayDifferent = period.getDays();
		if (dayDifferent >= MAX_DAYS_TO_SIGN_UP) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @return true if there is conflict, false otherwise.
	 */
	public boolean isSameDayConflict(final Job theCandidateJob, final Job theCurrentJob) {
		boolean overlaps = false;
		overlaps = theCurrentJob.isOverLappingDay(theCandidateJob) || overlaps;
		return overlaps;
	}
	
	public int getMaxDaysToSignUp() {
		return MAX_DAYS_TO_SIGN_UP;
	}
	
	public List<Integer> getJobList() {
		return myJobs;
	}
}
