/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.ParkManager;
import model.Volunteer;
/**
 * JUnit tests for volunteer.
 * 
 * @author Minqing Chen
 * @version February 19, 2018
 */
public class VolunteerRemoveJobTest {

	private LocalDate myJobStartDateIsToday;
	private LocalDate myJobStartDateIsYesterday;
	private LocalDate myJobStartDateIsMinDay;
	private LocalDate myJobStartDateIsMoreThanMinDay;

	
	
	
	private Volunteer anyVolunteer;
	private ParkManager myParkManager;
	private Job myCancelJobIsYesterdayMultiDayLength;
	private Job myCancelJobIsToday;
	private Job myCancelJobIsMoreThanMinDay;
	private Job myCancelJobIsExactMinDay;
	
	@Before
	public void setUp() throws Exception {
		anyVolunteer = new Volunteer("hasnah", "Hasnah", "Said");
		myParkManager = new ParkManager("tom", "Tom", "Hanks");
		myJobStartDateIsToday =
				LocalDate.now();
		myJobStartDateIsYesterday = 
				LocalDate.now().plusDays(-1);
		myJobStartDateIsMinDay = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP);
		myJobStartDateIsMoreThanMinDay = 
				LocalDate.now().plusDays(Job.MIN_DAYS_TO_SIGN_UP + 1);
		
		myCancelJobIsYesterdayMultiDayLength = new Job(myJobStartDateIsYesterday, 
				myJobStartDateIsYesterday.plusDays(2), "Discover Park",
						myParkManager, "Seattle, WA",
						"Pick up leaves");
		myCancelJobIsToday = new Job(myJobStartDateIsToday,
				myJobStartDateIsToday, "Cherry Park",
						myParkManager, "Seattle, "
						+ "WA", "Pick up trash");
		myCancelJobIsMoreThanMinDay = new Job(myJobStartDateIsMoreThanMinDay, 
				myJobStartDateIsMoreThanMinDay, "Golden Park",
						myParkManager, "Seattle, "
						+ "WA", "Replace old chair");
		myCancelJobIsExactMinDay = new Job(LocalDate.now()
						.plusDays(Job.MIN_DAYS_TO_SIGN_UP),
						LocalDate.now()
						.plusDays(Job.MIN_DAYS_TO_SIGN_UP), "Kerry Park",
						myParkManager, "Seattle, WA",
						"Park mointoring");
		
		anyVolunteer.signup(myCancelJobIsMoreThanMinDay);
		anyVolunteer.signup(myCancelJobIsYesterdayMultiDayLength);
		anyVolunteer.signup(myCancelJobIsExactMinDay);
		anyVolunteer.signup(myCancelJobIsToday);
	}
	
	/**
	 * Test volunteer unvolunteers for a job that starts on the current day.
	 */
	@Test
	public void cancelJob_CancelJobStartDateToday_False() {
		assertFalse("cancel job fail " + myCancelJobIsToday.getStartDate(),
				anyVolunteer.cancelJob(myCancelJobIsToday));
	}
	
	/**
	 * Test volunteer unvolunteers for a multi-day job that 
	 * starts prior to the current day.

	 */
	@Test
	public void cancelJob_CancelJogStartDateYesterDayMultiDayLength_False() {
		assertFalse("cancel job fail " + myCancelJobIsYesterdayMultiDayLength.getStartDate(),
				anyVolunteer.cancelJob(myCancelJobIsYesterdayMultiDayLength));
	}
	/**
	 * Test volunteer unvolunteers for a job that starts more 
	 * than the minimum number of days in the future.
	 */
	@Test
	public void cancelJob_CancelJogStartDateMoreThanMinDay_True() {
		assertTrue("cancel job fail " + myCancelJobIsMoreThanMinDay.getStartDate(),
				anyVolunteer.cancelJob(myCancelJobIsMoreThanMinDay));
	}
	
	/**
	 * Test volunteer unvolunteers for a job that starts 
	 * exactly the minumum number of days in the future.
	 */
	@Test
	public void cancelJob_CancelJogStartDateIsExactMinDay_True() {
		assertTrue("cancel job fail " + myCancelJobIsExactMinDay.getStartDate(),
				anyVolunteer.cancelJob(myCancelJobIsExactMinDay));
	}
}
