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
 * @version February 17, 2018
 */
public class VolunteerTest {
	
	/*
	 * These dates are used to create different jobs on different
	 * dates to test whether the different business rules are
	 * satisfied. 
	 */
	private LocalDate myFirstJobStartDate;
	private LocalDate myFirstJobEndDate;
	
	private LocalDate mySecondJobStartDate;
	private LocalDate mySecondJobEndDate;
	
	private LocalDate myFirstJobCandidateStartDate;
	private LocalDate myFirstJobCandidateEndDate;
	
	private LocalDate mySecondJobCandidateStartDate;
	private LocalDate mySecondJobCandidateEndDate;
	
	private LocalDate myThirdJobCandidateStartDate;
	private LocalDate myThirdJobCandidateEndDate;
	
	private LocalDate myJobStartDateLessThanMinDaysAway;
	private LocalDate myJobStartDateMinDaysAway;
	
	private LocalDate myJobStartDateMoreThanMinDaysAway;
	private  LocalDate myJobEndDate; 
	
	private Job myFirstJob;
	private Job myFirstJobCandidate;
	private Job mySecondJob;
	private Job mySecondJobCandidate;
	private Job myThirdJobCandidate;
	
	private Job myJobLessThanMinDaysAway;
	private Job myJobEqualsToMinDaysAway;
	private Job myJobMoreThanMinDayAway;
	
	private JobMap myJobMap;
	
	private Volunteer myAnyVolunteer;
	private Volunteer myVolunteer;
	private ParkManager myParkManager;
	
	/**
	 * Initialize filed.
	 */
	@Before
	public void setUp() {
		myFirstJobStartDate = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 7);
		myFirstJobEndDate = 
				myFirstJobStartDate.plusDays(3);
		
		
		mySecondJobStartDate = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 13);
		mySecondJobEndDate = 
				mySecondJobStartDate.plusDays(3);
		
		myFirstJobCandidateStartDate = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 11);
		myFirstJobCandidateEndDate = 
				myFirstJobCandidateStartDate.plusDays(3);
		
		mySecondJobCandidateStartDate = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 15);
		mySecondJobCandidateEndDate = 
				mySecondJobCandidateStartDate;
		
		myThirdJobCandidateStartDate = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 13);
		myThirdJobCandidateEndDate = 
				myThirdJobCandidateStartDate;
		
		myJobStartDateLessThanMinDaysAway =
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP - 1);
		myJobStartDateMinDaysAway = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP);
		
		myJobStartDateMoreThanMinDaysAway = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 20);
		myJobEndDate = 
				LocalDate.now().plusDays(0); 
		
		
		
		myVolunteer = new Volunteer("hasnah", "Hasnah", "Said");
		myParkManager = new ParkManager("brook", "Brook", "Negussie");
		myJobMap = new JobMap();
		
		myFirstJob = new Job(myFirstJobStartDate, myFirstJobEndDate, 
								"Discovery Park", myParkManager,
								"Seattle", "Pick up leaves");
		mySecondJob = new Job(mySecondJobStartDate, mySecondJobEndDate, 
								"Seward Park", myParkManager,
								"Seattle", "Pick up leaves");

//		System.out.println("s: " + mySecondJob.getStartDate().toString());
//		System.out.println("e: " + mySecondJob.getEndDate().toString());
		
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
		myJobLessThanMinDaysAway = new Job(myJobStartDateLessThanMinDaysAway, 
											myJobEndDate, "Discover Park",
											myParkManager, "Seattle, WA",
											"Pick up leaves");
		myJobEqualsToMinDaysAway = new Job(myJobStartDateMinDaysAway,
											myJobEndDate, "Cherry Park",
											myParkManager, "Seattle, "
											+ "WA", "Pick up leaves");
		
//		System.out.println(myJobEqualsToMinDaysAway.getStartDate().toString());
//		System.out.println(myJobEqualsToMinDaysAway.getEndDate().toString());
		
		myJobMoreThanMinDayAway = new Job(myJobStartDateMoreThanMinDaysAway,
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
			assertFalse(myVolunteer.isSameDayConflict(myFirstJobCandidate, myJobMap));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even when they
	 * have other jobs which do not conflict.
	 */
	@Test
	public void isSameDayConflict_VolunteerHasCurrentJobsButNotConflicting_False() {
		myVolunteer.signup(myFirstJob);
		assertFalse(myVolunteer.isSameDayConflict(myThirdJobCandidate, myJobMap));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job starts the same day as the end of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobStartsSameDayAsEndOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(mySecondJob);
		assertTrue(myVolunteer.isSameDayConflict(mySecondJobCandidate, myJobMap));
	}
	
	/**
	 * Checking if a volunteer can sign up for a job even though the
	 * potential job ends the same day as the start of an existing job.
	 */
	@Test
	public void isSameDayConflict_ConflictingJobEndSameDayAsStartOfJobAlreadySignedUpFor_True() {
		myVolunteer.signup(mySecondJob);
		assertTrue(myVolunteer.isSameDayConflict(myFirstJobCandidate, myJobMap));
		
		System.out.println(mySecondJob.getStartDate().toString());
		System.out.println(mySecondJob.getEndDate().toString());
	}
	
	/**
	 * Test a volunteer signs up for job that begins much more than the minimum number of 
	 * calendar days from the current date.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginMoreThanMinimumNumberDays_True() {
		assertTrue("Start date: " + myJobMoreThanMinDayAway
					.getStartDate(), myAnyVolunteer
					.isAtLeastMinDays(myJobMoreThanMinDayAway));
	}
	
	/**
	 * Test a volunteer signs up for job that begins the day after
	 * tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginExactMinimumNumberDays_True() {
		assertTrue("Start date: " + myJobEqualsToMinDaysAway
					.getStartDate(), myAnyVolunteer
					.isAtLeastMinDays(myJobEqualsToMinDaysAway));
	}
	
	/**
	 * Test a volunteer signs up for job that begins tomorrow.
	 */
	@Test
	public void isAtLeastMinDays_VolunteersSignUpJobsBeginLessThanMinimumNumberDays_False() {
		assertFalse("Start date: " + myJobLessThanMinDaysAway.getStartDate(), 
					myAnyVolunteer.isAtLeastMinDays(myJobLessThanMinDaysAway));
	}
}