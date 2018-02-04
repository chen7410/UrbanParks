import java.util.List;

public class Volunteer extends User {
	public final static int MAX_DAYS_TO_SIGN_UP = 2;
	
	private List<Job> myJobs;
	
	public Volunteer(String theUserName, String theFirstName, String theLastName) {
		super(theUserName, theFirstName, theLastName);
	}
	
	public void signup(final Job theJob) {
		
	}
	
	public boolean isAtLeastMinDays(final Job theJob) {
		return false;
	}
	
	public boolean isSameDayConflict(final Job theJob) {
		return false;
	}
	
	public void removeJob(final Job theJob) {
		myJobs.remove(theJob);
	}
}
