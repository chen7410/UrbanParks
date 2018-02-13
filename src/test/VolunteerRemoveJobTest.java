package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.ParkManager;
import model.Volunteer;

public class VolunteerRemoveJobTest {

	private LocalDate myJobStartDateIsToday;
	
	private LocalDate myJobStartDateIsYesterday;
	
	private LocalDate myJobStartDateIs20DaysAway;
	
	private Volunteer anyVolunteer;
	private ParkManager myParkManager;
	private Job myCancelJobIsYesterday;
	private Job myCancelJobIsToday;
	private Job myCancelJobIs20DaysAway;
	private Job myCancelJobIsExactMinDaysAway;
	
	@Before
	public void setUp() throws Exception {
		anyVolunteer = new Volunteer("hasnah", "Hasnah", "Said");
		myJobStartDateIsToday =
				LocalDate.now().plusDays(1);
		myJobStartDateIsYesterday = 
				LocalDate.now().plusDays(-1);
		myJobStartDateIs20DaysAway = 
				LocalDate.now().plusDays(20);
		myCancelJobIsYesterday = new Job(myJobStartDateIsYesterday, 
				myJobStartDateIsYesterday.plusDays(2), "Discover Park",
						myParkManager, "Seattle, WA",
						"Pick up leaves");
		myCancelJobIsToday = new Job(myJobStartDateIsToday,
				myJobStartDateIsToday, "Cherry Park",
						myParkManager, "Seattle, "
						+ "WA", "Pick up leaves");
		myCancelJobIs20DaysAway = new Job(myJobStartDateIs20DaysAway, 
				myJobStartDateIs20DaysAway, "Golden Park",
						myParkManager, "Seattle, "
						+ "WA", "Pick up leaves");
		myCancelJobIsExactMinDaysAway = new Job(LocalDate.now()
						.plusDays(anyVolunteer.getMinDaysInTheFuture()),
						LocalDate.now()
						.plusDays(anyVolunteer.getMinDaysInTheFuture()), "Kerry Park",
						myParkManager, "Seattle, WA",
						"Pick up leaves");
		anyVolunteer.signup(myCancelJobIs20DaysAway);
		anyVolunteer.signup(myCancelJobIsYesterday);
		anyVolunteer.signup(myCancelJobIsExactMinDaysAway);
		anyVolunteer.signup(myCancelJobIsToday);
		
	}

	/**
	 * Test volunteer unvolunteers for a job that starts
	 * on the current day
	 */
	@Test (expected = IllegalArgumentException.class)
	public void cancelJob_VolunteerCanelJobIsToday_IllegalArgumentException() {
		anyVolunteer.cancelJob(myCancelJobIsToday);
	}
	
	/**
	 * Volunteer unvolunteers for a multi-day job that starts 
	 * prior to the current day
	 */
	@Test (expected = IllegalArgumentException.class)
	public void cancelJob_VolunteerCanelJobIsPriorToCurrentDate_IllegalArgumentException() {
		anyVolunteer.cancelJob(myCancelJobIsYesterday);
	}
	
	/**
	 * Test Volunteer unvolunteers for a job that starts more than the 
	 * minimum number of days in the future
	 */
	@Test
	public void cancelJob_VolunteerCancelJobMoreThanMinDay_True() {
		assertTrue("Volunteer cancels job fail", 
				anyVolunteer.cancelJob(myCancelJobIs20DaysAway));
	}
	
	/**
	 * Volunteer unvolunteers for a job that starts exactly the minimum 
	 * number of days in the future
	 */
	@Test
	public void cancelJob_VolunteerCancelJobExactMinDay_True() {
		assertTrue("Volunteer cancels job fail", 
				anyVolunteer.cancelJob(myCancelJobIsExactMinDaysAway));
	}
	

}
