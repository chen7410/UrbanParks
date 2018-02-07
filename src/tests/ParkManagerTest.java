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
 * @author Hasnah
 * @version 2/4/2018
 */
public class ParkManagerTest {
	
	/* Maximum length of a job is no more than 3 days */
	private final int MAX_JOB_LENGTH = 3;

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
	
	/** A job that takes 2 days */
	private Job jobTakesOneFewerThanMax;
	private LocalDate startOneDayFewer; 
	private LocalDate endOneDayFewer;

	/** A job that takes 3 days */
	private Job jobTakesTheMaxDays;
	private LocalDate startMaxDays;
	private LocalDate endMaxDays; 

	/** A job that takes 4 days */
	private Job jobTakesOneMoreThanMax;
	private LocalDate startOneDayMore;
	private LocalDate endOneDayMore;

	@Before
	public void setUp() throws Exception {
		myPM = new ParkManager("hasnahsaid", "Hasnah", "Said");
		int random = (int) (Math.random() * ParkManager.MAX_END_DAY);//0 <= random < ParkMangaer.MAX_END_DAY
		myJobEndsInFewerDaysThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(random), "Cal Anderson", myPM, "Seattle, WA");
		myJobEndInMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY), "Gas Works Park", myPM, "Seattle, WA");
		myJobEndsInOneMoreDayThanMaxDays = new Job(LocalDate.now(), LocalDate.now().plusDays(ParkManager.MAX_END_DAY + 1), "Cal Anderson", myPM, "Seattle, WA");
	
		/** A job that takes 2 days */
		startOneDayFewer = LocalDate.of(2018, 02, 01);
		endOneDayFewer = LocalDate.of(2018, 02, 03);
		jobTakesOneFewerThanMax = new Job(startOneDayFewer, endOneDayFewer, "Cal Anderson", myPM, "Seattle, WA");

		/** A job that takes 3 days */
		startMaxDays = LocalDate.of(2018, 02, 10);
		endMaxDays = LocalDate.of(2018, 02, 13);
		jobTakesTheMaxDays = new Job(startMaxDays, endMaxDays, "Gas Works Park", myPM, "Seattle, WA");

		/** A job that takes 4 days */
		startOneDayMore = LocalDate.of(2018, 02, 20);
		endOneDayMore = LocalDate.of(2018, 02, 24);
		jobTakesOneMoreThanMax = new Job(startOneDayMore, endOneDayMore, "Volunteer Park", myPM, "Seattle, WA");
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
					myPM.isJobWithinMaxDays(jobTakesOneFewerThanMax));
	}

	@Test 
	public void isJobWithinMaxDays_JobTakesExactlyMaxDays_True() {
		assertTrue("The job takes exactly the maximum days",
					myPM.isJobWithinMaxDays(jobTakesTheMaxDays));
	}

	@Test
	public void isJobWithinMaxDays_JobTakesOneMoreDayThanMax_False() {
		assertFalse("The job takes one day more than the maximum days.", 
					myPM.isJobWithinMaxDays(jobTakesOneMoreThanMax));
	}

	
}
