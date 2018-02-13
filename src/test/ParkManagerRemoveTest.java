package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.ParkManager;

public class ParkManagerRemoveTest {
	
	private LocalDate myStartDateStartsLessThanMinDaysAway;
	private LocalDate myEndDateEndsLessThanMinDaysAway;
	
	private LocalDate myStartDateStartsExactlyMinDaysAway;
	private LocalDate myEndDateEndsExactlyMinDaysAway;
	
	private LocalDate myStartDateStartsMoreThanMinDaysAway;
	private LocalDate myEndDateEndsMoreThanMinDaysAway;
	
	private Job myJobStartsLessThanMinDaysAway;
	private Job myJobStartsExactlyMinDaysAway;
	private Job myJobStartsMoreThanMinDaysAway;
	
	private ParkManager myPM;
	
	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("brook", "Brook", "Negussie");
		
		myStartDateStartsLessThanMinDaysAway = LocalDate.now().plusDays(1);
		myEndDateEndsLessThanMinDaysAway = myStartDateStartsLessThanMinDaysAway;
		
		myStartDateStartsExactlyMinDaysAway = LocalDate.now().plusDays(1 + myPM.getMinDaysInTheFuture());
		myEndDateEndsExactlyMinDaysAway = myStartDateStartsExactlyMinDaysAway;
		
		myStartDateStartsMoreThanMinDaysAway = LocalDate.now().plusDays(3 + myPM.getMinDaysInTheFuture());
		myEndDateEndsMoreThanMinDaysAway = myStartDateStartsMoreThanMinDaysAway;
		
		myJobStartsLessThanMinDaysAway = new Job(myStartDateStartsLessThanMinDaysAway,
				myEndDateEndsLessThanMinDaysAway, "Discovery Park", myPM,
				"Seattle", "Pick up leaves"); 
		
		myJobStartsExactlyMinDaysAway = new Job(myStartDateStartsExactlyMinDaysAway, 
				myEndDateEndsExactlyMinDaysAway, "Seward Park", myPM,
				"Seattle", "Pick up leaves");
		
		myJobStartsMoreThanMinDaysAway = new Job(myStartDateStartsMoreThanMinDaysAway, 
				myEndDateEndsMoreThanMinDaysAway, "Volunteer Park", myPM,
				"Seattle", "Pick up leaves");
		
		myPM.createJob(myJobStartsLessThanMinDaysAway);
		myPM.createJob(myJobStartsExactlyMinDaysAway);
		myPM.createJob(myJobStartsMoreThanMinDaysAway);
	}
	
	@Test
	public void removeJob_JobStartsLessThanMinDaysAway_False() {
		assertFalse(myPM.removeJob(myJobStartsLessThanMinDaysAway));
	}
	
	@Test
	public void removeJob_JobStartsExactlyMinDaysAway_False() {
		assertFalse(myPM.removeJob(myJobStartsExactlyMinDaysAway));
	}
	
	@Test
	public void removeJob_JobStartsMoreThanMinDaysAway_False() {
		assertFalse(myPM.removeJob(myJobStartsMoreThanMinDaysAway));
	}
}