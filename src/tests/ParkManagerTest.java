package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import model.Job;
import model.ParkManager;

/**
 * JUnit to test the business rule: 
 * As a Park Manager I want to submit a new job
 * No job can be specified whose end date is more than the maximum number of
 * days from the current date, default of 75 
 * The specified job ends one fewer than the maximum number of days from the current date
 * The specified job ends the maximum number of days from the current date
 * The specified job ends one more than the maximum number of days from the current date
 * 
 * @author Tuan
 * @version 2/4/2018
 */
public class ParkManagerTest {

	private ParkManager myPM;
	
	/**
	 * Job that ends in fewer days than the maximum end days from now.
	 */
	private Job myJobEndsInFewerDaysThanMaxDays;
	
	/**
	 * Job that ends in maximum end days from now.
	 */
	private Job myJobEndInMaxDays;
	
	/**
	 * Job that ends in one more day than maximum end days from now.
	 */
	private Job myJobEndsInOneMoreDayThanMaxDays;

	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("hasnahsaid", "Hasnah", "Said");
		int random = (int) (Math.random() * ParkManager.MAX_END_DAY);//0 <= random < ParkMangaer.MAX_END_DAY
		myJobEndsInFewerDaysThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(random), "Cal Anderson", myPM, "Seattle, WA");
		myJobEndInMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY), "Gas Works Park", myPM, "Seattle, WA");
		myJobEndsInOneMoreDayThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY + 1), "Cal Anderson", myPM, "Seattle, WA");
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
}
