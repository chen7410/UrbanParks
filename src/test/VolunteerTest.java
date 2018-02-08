/**
 * T CSS 360 - Winter 2018
 * Team: Group 7
 * Urban Parks Project
 */

package test;

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
	
	private static final LocalDate myFirstJobStartDate = LocalDate.now().plusDays(10);
	private static final LocalDate myFirstJobEndDate = myFirstJobStartDate;
	
	private static final LocalDate mySecondJobStartDate = LocalDate.now().plusDays(16);
	private static final LocalDate mySecondJobEndDate = mySecondJobStartDate.plusDays(2);
	
	private static final LocalDate myFirstJobCandidateStartDate = LocalDate.now().plusDays(13);
	private static final LocalDate myFirstJobCandidateEndDate = myFirstJobCandidateStartDate.plusDays(2);
	
	private static final LocalDate mySecondJobCandidateStartDate = LocalDate.now().plusDays(18);
	private static final LocalDate mySecondJobCandidateEndDate = mySecondJobCandidateStartDate;
	
	private static final LocalDate myThirdJobCandidateStartDate = LocalDate.now().plusDays(16);
	private static final LocalDate myThirdJobCandidateEndDate = myThirdJobCandidateStartDate;
	
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
	
	
	private Job myFirstJob;
	private Job myFirstJobCandidate;
	private Job mySecondJob;
	private Job mySecondJobCandidate;
	private Job myThirdJobCandidate;
	
	private Volunteer myVolunteer;
	
	/**
	 * initialize filed.
	 */
	@Before
	public void setUp() {
		
		myVolunteer = new Volunteer("hasnah", "Hasnah", "Said");
		myParkManager = new ParkManager("brook", "Brook", "Negussie");
		
		myFirstJob = new Job(myFirstJobStartDate, myFirstJobEndDate, "Discovery Park", myParkManager, "Seattle");
		mySecondJob = new Job(mySecondJobStartDate, mySecondJobEndDate, "Seward Park", myParkManager, "Seattle");
		
		myFirstJobCandidate = new Job(myFirstJobCandidateStartDate, myFirstJobCandidateEndDate, "Volunteer Park", myParkManager, "Seattle");
		mySecondJobCandidate = new Job(mySecondJobCandidateStartDate, mySecondJobCandidateEndDate, "Gas Works Park", myParkManager, "Seattle");
		myThirdJobCandidate = new Job(myThirdJobCandidateStartDate, myThirdJobCandidateEndDate, "Lincoln Park", myParkManager, "Seattle");
		
		myAnyVolunteer = new Volunteer("Hasnah Said", "Hasnah", "Said");
		myJobLessThanTwoDaysAway = new Job(myJobStartDateLessThanTwoDaysAway, myJobEndDate, "Discover Park", myParkManager, "Seattle, WA");
		myJobEqualsToTwoDaysAway = new Job(myJobStartDateTwoDaysAway, myJobEndDate, "Cherry Park", myParkManager, "Seattle, WA");
		myJobMoreThanTwoDayAway = new Job(myJobStartDateMoreThanTwoDaysAway, myJobEndDate, "Kerry Park", myParkManager, "Seattle, WA");
	}
	
	/**
	 * Checking if a volunteer can sign up for a job when they don't
	 * have any conflicting jobs.
	 */
	@Test
	public void isSameDayConflict_VolunteerHasNoCurrentJobs_False() {
		assertFalse(myVolunteer.isSameDayConflict(myFirstJobCandidate));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even when they
	 * have other jobs which do not conflict.
	 */
	@Test
	public void isSameDayConflict_VolunteerHasCurrentJobsButNotConflicting_False() {
		myVolunteer.signup(myFirstJob);
		myVolunteer.signup(mySecondJob);
		
		assertFalse(myVolunteer.isSameDayConflict(myFirstJobCandidate));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job starts the same day as the end of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobStartsSameDayAsEndOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(myFirstJob);
		myVolunteer.signup(mySecondJob);
		
		assertTrue(myVolunteer.isSameDayConflict(mySecondJobCandidate));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job ends the same day as the start of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobEndSameDayAsStartOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(myFirstJob);
		myVolunteer.signup(mySecondJob);
		
		assertTrue(myVolunteer.isSameDayConflict(myThirdJobCandidate));
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
