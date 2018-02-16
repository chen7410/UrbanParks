/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.JobMap;
import model.ParkManager;
import model.Volunteer;

/**
 * JUnit tests for volunteer.
 * 
 * @author Group 7
 * @version February 12, 2018
 */
public class VolunteerTest {
	
	/*
	 * These dates are used to create different jobs on different
	 * dates to test whether the different business rules are
	 * satisfied. 
	 */
	
	private static final LocalDate myFirstJobStartDate = 
			LocalDate.now().plusDays(10);
	private static final LocalDate myFirstJobEndDate = 
			myFirstJobStartDate.plusDays(3);;
	
	private static final LocalDate mySecondJobStartDate = 
			LocalDate.now().plusDays(16);
	private static final LocalDate mySecondJobEndDate = 
			mySecondJobStartDate.plusDays(3);
	
	private static final LocalDate myFirstJobCandidateStartDate = 
			LocalDate.now().plusDays(14);
	private static final LocalDate myFirstJobCandidateEndDate = 
			myFirstJobCandidateStartDate.plusDays(3);
	
	private static final LocalDate mySecondJobCandidateStartDate = 
			LocalDate.now().plusDays(18);
	private static final LocalDate mySecondJobCandidateEndDate = 
			mySecondJobCandidateStartDate;
	
	private static final LocalDate myThirdJobCandidateStartDate = 
			LocalDate.now().plusDays(16);
	private static final LocalDate myThirdJobCandidateEndDate = 
			myThirdJobCandidateStartDate;
	
	private static final LocalDate myJobStartDateLessThanTwoDaysAway =
			LocalDate.now().plusDays(1);
	
	private static final LocalDate myJobStartDateTwoDaysAway = 
			LocalDate.now().plusDays(3);
	
	private static final LocalDate myJobStartDateMoreThanTwoDaysAway = 
			LocalDate.now().plusDays(20);
	
	private static final LocalDate myJobEndDate = 
			LocalDate.now().plusDays(0); 
	
	private Job myFirstJob;
	private Job myFirstJobCandidate;
	private Job mySecondJob;
	private Job mySecondJobCandidate;
	private Job myThirdJobCandidate;
	
	private Job myJobLessThanTwoDaysAway;
	private Job myJobEqualsToTwoDaysAway;
	private Job myJobMoreThanTwoDayAway;
	
	private JobMap myJobMap;
	
	private Volunteer myAnyVolunteer;
	private Volunteer myVolunteer;
	private ParkManager myParkManager;
	
	/**
	 * Initialize filed.
	 */
	@Before
	public void setUp() {
		
		myVolunteer = new Volunteer("hasnah", "Hasnah", "Said");
		myParkManager = new ParkManager("brook", "Brook", "Negussie");
		myJobMap = new JobMap();
		
		myFirstJob = new Job(myFirstJobStartDate, myFirstJobEndDate, 
								"Discovery Park", myParkManager,
								"Seattle", "Pick up leaves");
		mySecondJob = new Job(mySecondJobStartDate, mySecondJobEndDate, 
								"Seward Park", myParkManager,
								"Seattle", "Pick up leaves");
		
		myFirstJobCandidate = new Job(myFirstJobCandidateStartDate,
										myFirstJobCandidateEndDate,
										"Volunteer Park", myParkManager,
										"Seattle", "Pick up leaves");
		mySecondJobCandidate = new Job(mySecondJobCandidateStartDate, 
										mySecondJobCandidateEndDate,
										"Gas Works Park", myParkManager, 
										"Seattle", "Pick up leaves");
		myThirdJobCandidate = new Job(myThirdJobCandidateStartDate, 
										myThirdJobCandidateEndDate,
										"Lincoln Park", myParkManager,
										"Seattle", "Pick up leaves");
		myJobMap.addJob(myFirstJob);
		myJobMap.addJob(mySecondJob);
		
		myAnyVolunteer = new Volunteer("Hasnah Said", "Hasnah", "Said");
		myJobLessThanTwoDaysAway = new Job(myJobStartDateLessThanTwoDaysAway, 
											myJobEndDate, "Discover Park",
											myParkManager, "Seattle, WA",
											"Pick up leaves");
		myJobEqualsToTwoDaysAway = new Job(myJobStartDateTwoDaysAway,
											myJobEndDate, "Cherry Park",
											myParkManager, "Seattle, "
											+ "WA", "Pick up leaves");
		myJobMoreThanTwoDayAway = new Job(myJobStartDateMoreThanTwoDaysAway,
											myJobEndDate, "Kerry Park",
											myParkManager, "Seattle, WA",
											"Pick up leaves");
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
		assertFalse(myVolunteer.isSameDayConflict(myThirdJobCandidate));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job starts the same day as the end of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobStartsSameDayAsEndOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(mySecondJob);
		assertTrue(myVolunteer.isSameDayConflict(mySecondJobCandidate));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job ends the same day as the start of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobEndSameDayAsStartOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(mySecondJob);
		assertTrue(myVolunteer.isSameDayConflict(myFirstJobCandidate));
	}
	
	/**
	 * Test a volunteer signs up for job that begins much more than the minimum number of 
	 * calendar days from the current date.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginMoreThanMinimumNumberDays_True() {
		assertTrue("Start date: " + myJobMoreThanTwoDayAway
					.getStartDate(), myAnyVolunteer
					.isAtLeastMinDays(myJobMoreThanTwoDayAway));
	}
	
	/**
	 * Test a volunteer signs up for job that begins the day after
	 * tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginExactMinimumNumberDays_True() {
		assertTrue("Start date: " + myJobEqualsToTwoDaysAway
					.getStartDate(), myAnyVolunteer
					.isAtLeastMinDays(myJobEqualsToTwoDaysAway));
	}
	
	/**
	 * Test a volunteer signs up for job that begins tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginLessThanMinimumNumberDays_False() {
		assertFalse("Start date: " + myJobLessThanTwoDaysAway.getStartDate(), 
					myAnyVolunteer.isAtLeastMinDays(myJobLessThanTwoDaysAway));
	}
}