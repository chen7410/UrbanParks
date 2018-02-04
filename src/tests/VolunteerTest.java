package tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.ParkManager;
import model.Volunteer;

/**
 * Test for business rule 1a.
 * 1) As a Volunteer I want to sign up for a job
 * 		b) A volunteer may sign up only if the job begins at least a minimum number of calendar days after the current date, default of 2.
		   i)   Volunteer signs up for job that begins much more than the minimum number of calendar days from the current date.
		   ii)  Volunteer signs up for job that begins exactly the minimum number of calendar days from the current date.
		   iii) Volunteer signs up for job that begins less than the minimum number of calendar days from the current date.
 */

/**
 * @author Minqing Chen
 * @version 02/04/2018
 *
 */
public class VolunteerTest {
	private Job myJobLessThanTwoDaysAway;
	private Job myJobEqualsToTwoDaysAway;
	private Job myJobMoreThanTwoDayAway;			
	
	private static final LocalDate myJobStartDateToday = LocalDate.now();
	private static final LocalDate myJobStartDateLessThanTwoDaysAway = LocalDate.now().plusDays(1);
	private static final LocalDate myJobStartDateTwoDaysAway = LocalDate.now().plusDays(2);
	private static final LocalDate myJobStartDateMoreThanTwoDaysAway = LocalDate.now().plusDays(20);
	
	/** job's start date = end date */
	private static final LocalDate myJobEndDate = LocalDate.now().plusDays(0); 
	
	private ParkManager myParkManager;
	private Volunteer myAnyVolunteer;
	
	@Before
	public void setUp() {
		myParkManager = new ParkManager("Matthew Chen", "Matthew", "Chen");
		myAnyVolunteer = new Volunteer("Hasnah Said", "Hasnah", "Said");
		myJobLessThanTwoDaysAway = new Job(myJobStartDateLessThanTwoDaysAway, myJobEndDate, "Discover Park", myParkManager);
		myJobEqualsToTwoDaysAway = new Job(myJobStartDateTwoDaysAway, myJobEndDate, "Cherry Park", myParkManager);
		myJobMoreThanTwoDayAway = new Job(myJobStartDateMoreThanTwoDaysAway, myJobEndDate, "Kerry Park", myParkManager);
		
//		int dayDifferent = myJobStartDateToday.until(myJobStartDateLessThanTwoDaysAway).getDays();
//		System.out.println("start date " + myJobStartDateToday.toString());
//		System.out.println("end date " + myJobStartDateLessThanTwoDaysAway.toString());
//		System.out.println("day different " + dayDifferent);
	}
	
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginMoreThanTwoDays_True() {
		assertTrue(myAnyVolunteer.isAtLeastMinDays(myJobMoreThanTwoDayAway));
	}
	
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginExactlyTwoDays_True() {
		assertTrue(myAnyVolunteer.isAtLeastMinDays(myJobEqualsToTwoDaysAway));
	}
	
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginLessThanTwoDays_False() {
		assertFalse(myAnyVolunteer.isAtLeastMinDays(myJobLessThanTwoDaysAway));
	}
}
