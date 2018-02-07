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
	/**the start date of a job is tomorrow.*/
	private Job myJobLessThanTwoDaysAway;
	
	/**the start date of a job is the day after tomorrow.*/
	private Job myJobEqualsToTwoDaysAway;

	/**the start date of a job is more than the day after tomorrow.*/
	private Job myJobMoreThanTwoDayAway;			
	
	/**tomorrow.*/
	private static final LocalDate myJobStartDateLessThanTwoDaysAway = LocalDate.now().plusDays(1);
	
	/**the day after tomorrow.*/
	private static final LocalDate myJobStartDateTwoDaysAway = LocalDate.now().plusDays(2);
	
	/**the date that is more than the day after tomorrow.*/
	private static final LocalDate myJobStartDateMoreThanTwoDaysAway = LocalDate.now().plusDays(20);
	
	/**end date of a job.*/
	private static final LocalDate myJobEndDate = LocalDate.now().plusDays(0); 
	
	/**any park manager.*/
	private ParkManager myParkManager;
	
	/**any volunteer.*/
	private Volunteer myAnyVolunteer;
	
	/**
	 * initialize filed.
	 */
	@Before
	public void setUp() {
		myParkManager = new ParkManager("Matthew Chen", "Matthew", "Chen");
		myAnyVolunteer = new Volunteer("Hasnah Said", "Hasnah", "Said");
		myJobLessThanTwoDaysAway = new Job(myJobStartDateLessThanTwoDaysAway, myJobEndDate, "Discover Park", myParkManager);
		myJobEqualsToTwoDaysAway = new Job(myJobStartDateTwoDaysAway, myJobEndDate, "Cherry Park", myParkManager);
		myJobMoreThanTwoDayAway = new Job(myJobStartDateMoreThanTwoDaysAway, myJobEndDate, "Kerry Park", myParkManager);
	}
	
	/**
	 * test a volunteer signs up for job that begins much more than the minimum number of calendar days from the current date.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginMoreThanTwoDays_True() {
		assertTrue("Start date: " + myJobMoreThanTwoDayAway.getStartDate(), myAnyVolunteer.isAtLeastMinDays(myJobMoreThanTwoDayAway));
	}
	
	/**
	 * test a volunteer signs up for job that begins the day after tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginExactlyTwoDays_True() {
		assertTrue("Start date: " + myJobEqualsToTwoDaysAway.getStartDate(), myAnyVolunteer.isAtLeastMinDays(myJobEqualsToTwoDaysAway));
	}
	
	/**
	 * test a volunteer signs up for job that begins tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginLessThanTwoDays_False() {
		assertFalse("Start date: " + myJobLessThanTwoDaysAway.getStartDate(), myAnyVolunteer.isAtLeastMinDays(myJobLessThanTwoDaysAway));
	}
}
