package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.JobMap;
import model.Staff;

public class StaffTest {

	private Staff myStaff;
	private JobMap myJobmap;
	
	private LocalDate myStartDateToday;
	private LocalDate myEndDateOneMonth;
	
	@Before
	public void setUp() throws Exception {
		myStaff = new Staff("tom", "Tom", "Lee");
		myJobmap = new JobMap();
		
		myStartDateToday = LocalDate.now();
		myEndDateOneMonth = LocalDate.now().plusMonths(1);
	}
	
	/**
	 * Tests to see if getJobsInPeriod method throws IllegalArgumentException
	 *  when a staff tries to get job period at start date is after end date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getJobsInPeriod_EndDateIsBeforeStartDateJob_IllegalArgumentException() {
		myStaff.getJobsInPeriod(myEndDateOneMonth, myStartDateToday, myJobmap);
	}
	
	/**
	 * Tests to see if getJobsInPeriod method throws IllegalArgumentException
	 *  when a staff pass in null start date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getJobsInPeriod_NullStartDate_IllegalArgumentException() {
		myStaff.getJobsInPeriod(null, myEndDateOneMonth, myJobmap);
	}
	
	/**
	 * Tests to see if getJobsInPeriod method throws IllegalArgumentException
	 *  when a staff pass in null end date.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getJobsInPeriod_NullEndDate_IllegalArgumentException() {
		myStaff.getJobsInPeriod(myStartDateToday, null, myJobmap);
	}
	
	/**
	 * Tests to see if getJobsInPeriod method throws IllegalArgumentException
	 *  when a staff pass in null job map.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void getJobsInPeriod_NullJobMap_IllegalArgumentException() {
		myStaff.getJobsInPeriod(myStartDateToday, myEndDateOneMonth, null);
	}
	

	/**
	 * Tests to see if setMaxJobAmount method throws IllegalArgumentException
	 *  when a staff pass zero to.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMaxJobAmount_SetMaxJobAmountToZero_IllegalArgumentException() {
		myStaff.setMaxJobAmount(0, myJobmap);
	}

	/**
	 * Tests to see if setMaxJobAmount method throws IllegalArgumentException
	 *  when a staff pass negative one.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMaxJobAmount_SetMaxJobAmountToNegativeNumber_IllegalArgumentException() {
		myStaff.setMaxJobAmount(-1, myJobmap);
	}
	
	/**
	 * Tests to see if setMaxJobAmount method throws IllegalArgumentException
	 *  when a staff pass null job map .
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setMaxJobAmount_PassinNullJobMap_IllegalArgumentException() {
		myStaff.setMaxJobAmount(3, null);
	}
	
}
