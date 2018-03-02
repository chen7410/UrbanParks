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
	
	private LocalDate myNullDate;
	private LocalDate myToday;
	private LocalDate myYesterday;
	
	private String myNullString;
	private String myEmptyString;
	private String myParkName;
	private String myParkLocation;
	private String myJobDescription;
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
		
		myNullDate = null;
		myToday = LocalDate.now();
		myYesterday = LocalDate.now().plusDays(-1);
		myNullString = null;
		myEmptyString = "";
		myParkName = "Medina Park";
		myParkLocation = "Medina";
		myJobDescription = "Clean up waterbody";
	}
	
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when a job end day is current date and start date is tomorrow.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_EndDateIsBeforeStartDateJob_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isJobWithinDates(myToday, myYesterday);
	}
	
	/**
	 * Tests to see if isJobWithinDates method throws IllegalArgumentException
	 *  when pass in a null end date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isJobWithinDates_NullEndtDateJob_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isJobWithinDates(myToday, myNullDate);
	}
	
	/**
	 * Tests to see if isJobWithinDates method throws IllegalArgumentException
	 *  when pass in a null start date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isJobWithinDates_NullStartDateJob_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isJobWithinDates(myNullDate, myToday);
	}
	
	/**
	 * Tests to see if isJobWithinDates method throws IllegalArgumentException
	 *  when pass in a null end date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isJobWithinDates_NullEndDateJob_IllegalArgumentException() {
		Job job = new Job(myToday, myNullDate, myParkName, myPM, myParkLocation, myJobDescription);
	}
	
	
	/**
	 * Tests to see if isAtLeastMinDays method throws IllegalArgumentException
	 *  when pass in a start == null.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isJobWithinDates_PassInNegativeNumber_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isJobWithinDates(null, myToday);
	}
	
	/**
	 * Tests to see if  isSameDayConflict method throws IllegalArgumentException
	 *  when pass in a job == null.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isSameDayConflict_PassInNegativeNumber_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isSameDayConflict(null);
	}
	
	/**
	 * Tests to see if isAtLeastMinDays method throws IllegalArgumentException
	 *  when pass in a negative integer.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void isAtLeastMinDays_PassInNegativeNumber_IllegalArgumentException() {
		myJobStartsOnCurrentDay.isAtLeastMinDays(-1);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a empty job description.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructEmptyDescriptionJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myParkName, myPM, myEmptyString, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null job description.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullDescriptionJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myParkName, myPM, myNullString, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a empty park location.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructEmptyParkLocationJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myParkName, myPM, myEmptyString, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null park location.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullLocationJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myParkName, myPM, myNullString, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a empty park name.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructEmptyParkNameJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myEmptyString, myPM, myParkLocation, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null park name.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullParkNameJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myNullString, myPM, myParkLocation, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null park manager.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullParkManagerJob_IllegalArgumentException() {
		Job job = new Job(myToday, myToday, myParkName, null, myParkLocation, myJobDescription);
	}
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null end date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullEndDateJob_IllegalArgumentException() {
		Job job = new Job(myToday, myNullDate, myParkName, myPM, myParkLocation, myJobDescription);
	}
	
	
	/**
	 * Tests to see if a job throws IllegalArgumentException
	 *  when pass in a null start date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void JobConstructor_ConstructNullStartDateJob_IllegalArgumentException() {
		Job job = new Job(myNullDate, myToday, myParkName, myPM, myParkLocation, myJobDescription);
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