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
 * @author Tuan
 * @author Hasnah
 * @version 2/4/2018
 */
public class ParkManagerTest {

	private ParkManager myPM;

	private Job myJobEndsInFewerDaysThanMaxDays;
	
	private Job myJobEndInMaxDays;
	
	private Job myJobEndsInOneMoreDayThanMaxDays;
	
	private Job myJobTakesOneDayLessThanMax;
	
	private LocalDate myJobOneLessDayStartDate; 
	
	private LocalDate myJobOneLessDayEndDate;

	private Job myJobTakesExactlyMaxDays;
	
	private LocalDate myJobExactlyMaxStartDate;
	
	private LocalDate myJobExactlyMaxEndDate; 

	private Job myJobTakesOneDayMoreThanMax;
	
	private LocalDate myJobOneMoreDayStartDate;
	
	private LocalDate myJobOneMoreDayEndDate;

	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("hasnahsaid", "Hasnah", "Said");
		int random = (int) (Math.random() * myPM.getMaxEndDay() );//0 <= random < ParkMangaer.MAX_END_DAY
		myJobEndsInFewerDaysThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(random), "Cal Anderson", myPM, "Seattle, WA", "Pick up leaves");
		myJobEndInMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(myPM.getMaxEndDay()), "Gas Works Park", myPM, "Seattle, WA", "Pick up leaves");
		myJobEndsInOneMoreDayThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(myPM.getMaxEndDay() + 1), "Cal Anderson", myPM, "Seattle, WA", "Pick up leaves");
	
		// A job that takes 2 days
		 myJobOneLessDayStartDate = LocalDate.of(2018, 02, 01);
		 myJobOneLessDayEndDate = LocalDate.of(2018, 02, 03);
		 myJobTakesOneDayLessThanMax = new Job(myJobOneLessDayStartDate, myJobOneLessDayEndDate, "Cal Anderson", myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes 3 days 
		 myJobExactlyMaxStartDate = LocalDate.of(2018, 02, 10);
		 myJobExactlyMaxEndDate = LocalDate.of(2018, 02, 13);
		 myJobTakesExactlyMaxDays = new Job(myJobExactlyMaxStartDate, myJobExactlyMaxEndDate, "Gas Works Park", myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes 4 days
		myJobOneMoreDayStartDate = LocalDate.of(2018, 02, 20);
		myJobOneMoreDayEndDate = LocalDate.of(2018, 02, 24);
		myJobTakesOneDayMoreThanMax = new Job(myJobOneMoreDayStartDate, myJobOneMoreDayEndDate, "Volunteer Park", myPM, "Seattle, WA", "Pick up leaves");
		// A job that takes one day less than max
		myJobOneLessDayStartDate = LocalDate.of(2018, 02, 01);
		myJobOneLessDayEndDate = LocalDate.of(2018, 02, 03);
		myJobTakesOneDayLessThanMax = new Job(myJobOneLessDayStartDate, myJobOneLessDayEndDate, "Cal Anderson", myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes exactly max days
		myJobExactlyMaxStartDate = LocalDate.of(2018, 02, 10);
		myJobExactlyMaxEndDate = LocalDate.of(2018, 02, 13);
		myJobTakesExactlyMaxDays = new Job(myJobExactlyMaxStartDate, myJobExactlyMaxEndDate, "Gas Works Park", myPM, "Seattle, WA", "Pick up leaves");

		// A job that takes one day more than max
		myJobOneMoreDayStartDate = LocalDate.of(2018, 02, 20);
		myJobOneMoreDayEndDate = LocalDate.of(2018, 02, 24);
		myJobTakesOneDayMoreThanMax = new Job(myJobOneMoreDayStartDate, myJobOneMoreDayEndDate, "Volunteer Park", myPM, "Seattle, WA", "Pick up leaves");
	}

	@Test
	public void isJobEndsWithinMaxDays_JobEndsInFewerDaysThanMaxDays_True() {
		assertTrue("End date: " + myJobEndsInFewerDaysThanMaxDays.getEndDate(), myPM.isJobEndsWithinMaxDays(myJobEndsInFewerDaysThanMaxDays));
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsInMaxDays_True() {
		assertTrue("End date: " + myJobEndInMaxDays.getEndDate(), myPM.isJobEndsWithinMaxDays(myJobEndInMaxDays));
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsInOneMoreDayThanMaxDays_False() {
		assertFalse("End date: " + myJobEndsInOneMoreDayThanMaxDays
				.getEndDate(), myPM.isJobEndsWithinMaxDays(myJobEndsInOneMoreDayThanMaxDays));
	}

	@Test
	public void isJobWithinMaxDays_JobTakesOneFewerDayThanMax_True() {
		assertTrue("The job takes one day less than the maximum days.", 
					myPM.isJobWithinMaxDays(myJobTakesOneDayLessThanMax));
	}

	@Test 
	public void isJobWithinMaxDays_JobTakesExactlyMaxDays_True() {
		assertTrue("The job takes exactly the maximum days",
					myPM.isJobWithinMaxDays(myJobTakesExactlyMaxDays));
	}

	@Test
	public void isJobWithinMaxDays_JobTakesOneMoreDayThanMax_False() {
		assertFalse("The job takes one day more than the maximum days.", 
					myPM.isJobWithinMaxDays(myJobTakesOneDayMoreThanMax));
	}

	
}
