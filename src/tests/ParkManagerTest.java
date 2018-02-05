package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.*;

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
class ParkManagerTest {

	private ParkManager myPM;
	
	/**
	 * Job that ends on fewer day than the maximum end days from now.
	 */
	private Job myJobEndsFewerDaysThanMaxDays;
	
	/**
	 * Job that ends on maximum end days from now.
	 */
	private Job myJobEndOnMaxDay;
	
	/**
	 * Job that ends on one more than maximum end days from now.
	 */
	private Job myJobEndOneMoreDayThanMaxDays;

	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("hasnahsaid", "Hasnah", "Said");
		int random = (int) (Math.random() * ParkManager.MAX_END_DAY);//0 <= random < ParkMangaer.MAX_END_DAY
		myJobEndsFewerDaysThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(random), "Cal Anderson", myPM);
		myJobEndOnMaxDay = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY), "Gas Works Park", myPM);
		myJobEndOneMoreDayThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY + 1), "Cal Anderson", myPM);
	}

	@Test
	public void isJobEndsWithinMaxDays_JobEndsFewerDaysThanMaxDays_True() {
		assertTrue("This is true", myPM.isJobEndsWithinMaxDays(myJobEndsFewerDaysThanMaxDays));
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsOnMaxDay_True() {
		assertTrue("This is true 1", myPM.isJobEndsWithinMaxDays(myJobEndOnMaxDay));
	}
	
	@Test
	public void isJobEndsWithinMaxDays_JobEndsOneMoreDayThanMaxDays_False() {
		assertFalse("This is true 1", myPM.isJobEndsWithinMaxDays(myJobEndOneMoreDayThanMaxDays));
	}
}
