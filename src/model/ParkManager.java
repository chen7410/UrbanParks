package model;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ParkManager extends User {

	private static final long serialVersionUID = 1L;

	private static final int MAX_JOB_AMOUNT = 20;

	private static final int MAX_JOB_LENGTH = 3;

	private static final int MAX_END_DAY = 75;
	
	/**
	 * List of job IDs this Park manager manages.
	 */
	private List<Integer> myJobs;

	public ParkManager(final String theUserName, final String theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		this.setUserType("Park Manager");
		myJobs = new ArrayList<>();
	}

	public void createJob(final Job theJob) {
		myJobs.add(theJob.getJobID());
	}

	public boolean isLessThanMaxJobs(final JobMap theJobList) {
		return theJobList.size() < MAX_JOB_AMOUNT;
	}
	
	/**
	 * Check if the job is not more than the max days, default is 3.
	 * 
	 * @param theJob calculate the length of theJob
	 * @return true if theJob is within 3 days
	 * 		   false if theJob is more than 3 days
	 */
	public boolean isJobWithinMaxDays(final Job theJob) {
		boolean withinMaxDays = true;

		LocalDate jobStartDate = theJob.getStartDate();
		LocalDate jobEndDate = theJob.getEndDate();

		Period diff = Period.between(jobStartDate, jobEndDate);
		int daysDifference = diff.getDays();

		if (daysDifference > MAX_JOB_LENGTH) {
			return false;
		}

		return withinMaxDays;

	}

	/**
	 * Test if the job end date is less than or equal MAX_END_DAY days from now.
	 * @param theJob The job to check
	 * @return True if end date is MAX_END_DAY days or less from now.
	 */
	public boolean isJobEndsWithinMaxDays (final Job theJob) {
		return theJob.getEndDate().isBefore(LocalDate.now().plusDays(MAX_END_DAY + 1));
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
	public int getMaxJobAmount() {
		return MAX_JOB_AMOUNT;
	}
	
	public int getMaxJobLength() {
		return MAX_JOB_LENGTH;
	}
	
	public int getMaxEndDay() {
		return MAX_END_DAY;
	}
	
	public List<Integer> getJobList() {
		return myJobs;
	}
	
}
