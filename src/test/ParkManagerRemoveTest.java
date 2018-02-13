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
public class ParkManagerRemoveTest {
	
	private LocalDate myStartDateStartsOnCurrentDay;
	private LocalDate myEndDateEndsOnCurrentDay;
	
	private LocalDate myStartDateStartsPriorToCurrentDay;
	private LocalDate myEndDateEndsPastCurrentDay;
	
	private LocalDate myStartDateStartsMoreThanMinDaysAway;
	private LocalDate myEndDateEndsMoreThanMinDaysAway;
	
	private LocalDate myStartDateStartsExactlyMinDaysAway;
	private LocalDate myEndDateEndsExactlyMinDaysAway;
	
	private Job myJobStartsOnCurrentDay;
	private Job myJobStartsPriorToCurrentDay;
	private Job myJobStartsMoreThanMinDaysAway;
	private Job myJobStartsExactlyMinDaysAway;
	
	private ParkManager myPM;
	
	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("brook", "Brook", "Negussie");
		
		myStartDateStartsOnCurrentDay = LocalDate.now();
		myEndDateEndsOnCurrentDay = myStartDateStartsOnCurrentDay;
		
		myStartDateStartsPriorToCurrentDay = LocalDate.now().minusDays(1);
		myEndDateEndsPastCurrentDay = myStartDateStartsPriorToCurrentDay.plusDays(2);
		
		myStartDateStartsMoreThanMinDaysAway = LocalDate.now().plusDays(myPM.getMinDaysInTheFuture() + 3);
		myEndDateEndsMoreThanMinDaysAway = myStartDateStartsMoreThanMinDaysAway;
		
																					// *** Should there be a plus one in here??? ***
		myStartDateStartsExactlyMinDaysAway = LocalDate.now().plusDays(myPM.getMinDaysInTheFuture() + 1);
		myEndDateEndsExactlyMinDaysAway = myStartDateStartsExactlyMinDaysAway.plusDays(1);
		
		myJobStartsOnCurrentDay = new Job(myStartDateStartsOnCurrentDay,
				myEndDateEndsOnCurrentDay, "Discovery Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsPriorToCurrentDay = new Job(myStartDateStartsPriorToCurrentDay,
				myEndDateEndsPastCurrentDay, "Seward Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsMoreThanMinDaysAway = new Job(myStartDateStartsMoreThanMinDaysAway,
				myEndDateEndsMoreThanMinDaysAway, "Volunteer Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsExactlyMinDaysAway = new Job(myStartDateStartsExactlyMinDaysAway,
				myEndDateEndsExactlyMinDaysAway, "Gas Works Park", myPM, 
				"Seattle", "Pick up leaves");
		
		myPM.createJob(myJobStartsOnCurrentDay);
		myPM.createJob(myJobStartsPriorToCurrentDay);
		myPM.createJob(myJobStartsMoreThanMinDaysAway);
		myPM.createJob(myJobStartsExactlyMinDaysAway);
	}
	
	@Test
	public void removeJob_JobStartsOnCurrentDay_False() {
		assertFalse(myPM.removeJob(myJobStartsOnCurrentDay));
	}
	
	@Test
	public void removeJob_JobStartsPriorToCurrentDay_False() {
		assertFalse(myPM.removeJob(myJobStartsPriorToCurrentDay));
	}
	
	@Test
	public void removeJob_JobStartsMoreThanMinDaysAway_True() {
		assertTrue(myPM.removeJob(myJobStartsMoreThanMinDaysAway));
	}
	
	@Test
	public void removeJob_JobStartsExactlyMinDaysAway_True() {
		assertTrue(myPM.removeJob(myJobStartsExactlyMinDaysAway));
	}
}