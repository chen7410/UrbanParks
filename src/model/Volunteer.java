/**
 * T CSS 360 - Winter 2018
 * Team: Group 7
 * Urban Parks Project
 */

package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Volunteer extends User {
	
	private final static int MAX_DAYS_TO_SIGN_UP = 2;
	
	private List<Job> myJobs;
	
	public Volunteer(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
		myJobs = new ArrayList<Job>();
		this.setMyUserType("Volunteer");
	}
	
	public void signup(final Job theJob) {
		
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
	 * @return
	 */
	public boolean isSameDayConflict(Job theCandidateJob) {
		boolean overlaps = false;
		for (Job j : myJobs) {
			overlaps = j.isOverLappingDay(theCandidateJob) || overlaps;
		}
		
		return overlaps;
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
	public int getMaxDaysToSignUp() {
		return MAX_DAYS_TO_SIGN_UP;
	}
}