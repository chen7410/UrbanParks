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
 * 
 * 
 * @author Group 7
 * @version February 21, 2018
 */
public class JobTest {
	
	public final int ARBITRARY_NUMBER = 3;
	
	private Job myJobStartsOnCurrentDay;
	private Job myJobStartsPriorToCurrentDay;
	private Job myJobStartsMoreThanMinDaysAway;
	private Job myJobStartsExactlyMinDaysAway;
	
	private ParkManager myPM;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		myPM = new ParkManager("brook", "Brook", "Negussie");
		
		LocalDate myDateIsPriorToCurrentDay = LocalDate.now().minusDays(1);
		LocalDate myDateIsMoreThanMinDaysAway = LocalDate.now()
				.plusDays(Job.MIN_DAYS_TO_SIGN_UP + ARBITRARY_NUMBER);
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
		
		myPM.createJob(myJobStartsOnCurrentDay);
		myPM.createJob(myJobStartsPriorToCurrentDay);
		myPM.createJob(myJobStartsMoreThanMinDaysAway);
		myPM.createJob(myJobStartsExactlyMinDaysAway);
	}

	@Test
	public void isJobRemoveable_JobStartsOnCurrentDay_False() {
		// assertFalse(myPM.);
		
		// assertFalse(myPM.isJobRemovable(myJobStartsOnCurrentDay));
	}
}