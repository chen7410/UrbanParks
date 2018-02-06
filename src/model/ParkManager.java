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

	public boolean isLessThanMaxJobs(final Job theJob) {
		return false;
	}
	
	public boolean isJobWithinMaxDays(final Job theJob) {
		return false;
	}
	
	public boolean isJobEndsWithinMaxDays (final Job theJob) {
		if (theJob.getEndDate().isBefore(LocalDate.now().plusDays(MAX_END_DAY + 1))) {
			return true;
		}
		return false;
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
}
