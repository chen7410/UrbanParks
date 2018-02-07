package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParkManager extends User {

	public static final int MAX_JOB_AMOUNT = 20;

	public static final int MAX_JOB_LENGTH = 3;

	public static final int MAX_END_DAY = 75;

	private List<Job> myJobs;

	public ParkManager(final String theUserName, final String theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		this.setMyUserType("Park Manager");
		myJobs = new ArrayList<Job>();
	}

	public void createJob(final Job theJob) {
		
	}

	/**
	 * Checks if there is room for one more job to be added.
	 * @param theJobList the list of jobs
	 * @return true if there's at least one more spot for a job.
	 */
	public boolean isLessThanMaxJobs(final JobMap theJobList) {
		return theJobList.size() < MAX_JOB_AMOUNT;
	}
	
	public boolean isJobWithinMaxDays(final Job theJob) {
		return false;
	}
	
	/**
	 * Tests if the job end date is less than or equal MAX_END_DAY days from now.
	 * @param theJob The job to check
	 * @return True if end date is MAX_END_DAY days or less from now.
	 */
	public boolean isJobEndsWithinMaxDays (final Job theJob) {
		return theJob.getEndDate().isBefore(LocalDate.now().plusDays(MAX_END_DAY + 1));
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
}
