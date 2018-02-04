import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ParkManager extends User {

	public static final int MAX_JOB_AMOUNT = 20;

	public static final int MAX_JOB_LENGTH = 3;

	public static final int MAX_END_DAY = 75;

	private List<Job> myJobs;

	public ParkManager(final String theUserName, final String theFirstName, final String theLastName) {
		super(theUserName, theFirstName, theLastName);
		myJobs = new ArrayList<Job>();
	}

	public void createJob(final Job theJob) {
		if (isLessThanMaxJobs(theJob)) {
			myJobs.add(theJob);
		}
	}

	public boolean isLessThanMaxJobs(final Job theJob) {
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
	
	public boolean isJobWithinMaxDays(final Job theJob) {
		return false;
	}
	
	public boolean isJobEndsWithinMaxDays (final Job theJob) {
		return false;
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
	
}
