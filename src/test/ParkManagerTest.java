/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import model.Job;
import model.ParkManager;

/**
 * JUnit to test the business rules for ParkManager.java
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ParkManagerTest {

	private ParkManager myPM;

	private Job myJobEndsInFewerDaysThanMaxDays;
	private Job myJobEndInMaxDays;
	private Job myJobEndsInOneMoreDayThanMaxDays;
	private Job myJobTakesOneDayLessThanMax;
	private Job myJobTakesExactlyMaxDays;
	private Job myJobTakesOneDayMoreThanMax;

	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("hasnahsaid", "Hasnah", "Said");
		int random = (int) (Math.random() * Job.MAX_END_DAY);
		myJobEndsInFewerDaysThanMaxDays = new Job(LocalDate.now(), 
				LocalDate.now().plusDays(random), "Cal Anderson", myPM, 
				"Seattle, WA", "Pick up leaves");
		myJobEndInMaxDays = new Job(LocalDate.now(), 
				LocalDate.now().plusDays(Job.MAX_END_DAY), 
				"Gas Works Park", myPM, "Seattle, WA", "Pick up leaves");
		
		myJobEndsInOneMoreDayThanMaxDays = new Job(LocalDate.now(),
				LocalDate.now().plusDays(Job.MAX_END_DAY + 1), 
				"Cal Anderson", myPM, "Seattle, WA", "Pick up leaves");
	
		// A job that takes one less day than max.
		 myJobTakesOneDayLessThanMax = new Job(LocalDate.now(),
				 LocalDate.now().plusDays(Job.MAX_JOB_LENGTH - 1), "Cal Anderson",
				 myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes exactly the max days.
		 myJobTakesExactlyMaxDays = new Job(LocalDate.now(), 
				 LocalDate.now().plusDays(Job.MAX_JOB_LENGTH), "Gas Works Park", 
				 myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes one more than max days.
		myJobTakesOneDayMoreThanMax = new Job(LocalDate.now(), 
				LocalDate.now().plusDays(Job.MAX_JOB_LENGTH + 1), "Volunteer Park", 
				myPM, "Seattle, WA", "Pick up leaves");
	}

	@Test
	public void isJobEndsWithinMaxDays_JobEndsInFewerDaysThanMaxDays_True() {
		assertTrue("End date: " + myJobEndsInFewerDaysThanMaxDays
				.getEndDate(), myJobEndsInFewerDaysThanMaxDays.isJobEndsWithinMaxDays());
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsInMaxDays_True() {
		assertTrue("End date: " + myJobEndInMaxDays
				.getEndDate(), myJobEndInMaxDays.isJobEndsWithinMaxDays());
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsInOneMoreDayThanMaxDays_False() {
		assertFalse("End date: " + myJobEndsInOneMoreDayThanMaxDays
				.getEndDate(), myJobEndsInOneMoreDayThanMaxDays.isJobEndsWithinMaxDays());
	}

	@Test
	public void isJobWithinMaxDays_JobTakesOneFewerDayThanMax_True() {
		assertTrue("The job takes one day less than the maximum days.", 
				myJobTakesOneDayLessThanMax.isJobWithinMaxDays());
	}

	@Test 
	public void isJobWithinMaxDays_JobTakesExactlyMaxDays_True() {
		assertTrue("The job takes exactly the maximum days",
				myJobTakesExactlyMaxDays.isJobWithinMaxDays());
	}

	@Test
	public void isJobWithinMaxDays_JobTakesOneMoreDayThanMax_False() {
		assertFalse("The job takes one day more than the maximum days.", 
				myJobTakesOneDayMoreThanMax.isJobWithinMaxDays());
	}
}
