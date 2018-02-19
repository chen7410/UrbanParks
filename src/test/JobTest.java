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
import model.ParkManager;

/**
 * Testing the isJobRemovable method in the Job class.
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class JobTest {
	
	private Job myJobStartsOnCurrentDay;
	private Job myJobStartsPriorToCurrentDay;
	private Job myJobStartsMoreThanMinDaysAway;
	private Job myJobStartsExactlyMinDaysAway;
	
	private ParkManager myPM;

	@Before
	public void setUp() {
		myPM = new ParkManager("brook", "Brook", "Negussie");
		
		LocalDate myDateIsPriorToCurrentDay = LocalDate.now().minusDays(1);
		LocalDate myDateIsMoreThanMinDaysAway = LocalDate.now()
				.plusDays(Job.MIN_DAYS_TO_SIGN_UP + Job.MIN_DAYS_TO_SIGN_UP);
		LocalDate myDateIsExactlyMinDaysAway = LocalDate.now()
				.plusDays(Job.MIN_DAYS_TO_SIGN_UP);
		
		myJobStartsOnCurrentDay = new Job(LocalDate.now(),
				LocalDate.now(), "Discovery Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsPriorToCurrentDay = new Job(myDateIsPriorToCurrentDay,
				myDateIsPriorToCurrentDay.plusDays(2), "Seward Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsMoreThanMinDaysAway = new Job(myDateIsMoreThanMinDaysAway,
				myDateIsMoreThanMinDaysAway, "Volunteer Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsExactlyMinDaysAway = new Job(myDateIsExactlyMinDaysAway,
				myDateIsExactlyMinDaysAway.plusDays(1), "Gas Works Park", myPM, 
				"Seattle", "Pick up leaves");
	}
	
	/**
	 * Tests to see if a job which starts on the current day can be
	 * removed.
	 */
	@Test
	public void isJobRemovable_JobStartsOnCurrentDay_False() {
		assertFalse(myJobStartsOnCurrentDay.isJobRemovable());
	}
	
	/**
	 * Tests to see if a job which started yesterday can be removed.
	 */
	@Test
	public void isJobRemovable_JobStartsPriorToCurrentDay_False() {
		assertFalse(myJobStartsPriorToCurrentDay.isJobRemovable());
	}
	
	/**
	 * Tests to see if a job which more than the minimum number of
	 * days away can be removed.
	 */
	@Test
	public void isJobRemovable_JobStartsMoreThanMinDaysAway_True() {
		assertTrue(myJobStartsMoreThanMinDaysAway.isJobRemovable());
	}
	
	/**
	 * Tests to see if a job which starts exactly the minimum number
	 * of days away can be removed.
	 */
	@Test
	public void isJobRemovable_JobStartsExactlyMinDaysAway_True() {
		assertTrue(myJobStartsExactlyMinDaysAway.isJobRemovable());
	}
}