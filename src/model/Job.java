package model;
import java.time.LocalDate;

public class Job {
	private int myJobID;
	private LocalDate myStartDate;
	private LocalDate myEndDate;
	private String myParkName;
	private ParkManager myPM;

	public Job(final LocalDate theStartDate, final LocalDate theEndDate, final String theParkName,
			final ParkManager thePM) {
		myStartDate = theStartDate;
		myEndDate = theEndDate;
		myParkName = theParkName;
		myPM = thePM;
		myJobID = generateJobID();
	}
	
	private int generateJobID() {
		return 0;
	}
	
	public int getJobID() {
		return myJobID;
	}

	public void setJobID(final int myJobID) {//don't need setter?
		this.myJobID = myJobID;
	}

	public LocalDate getStartDate() {
		return myStartDate;
	}

	public void setStartDate(final LocalDate myStartDate) {
		this.myStartDate = myStartDate;
	}

	public LocalDate getEndDate() {
		return myEndDate;
	}

	public void setEndDate(final LocalDate myEndDate) {
		this.myEndDate = myEndDate;
	}

	public String getParkName() {
		return myParkName;
	}

	public void setParkName(final String myParkName) {
		this.myParkName = myParkName;
	}

	public ParkManager getPM() {
		return myPM;
	}

	public void setPM(final ParkManager myPM) {
		this.myPM = myPM;
	}
}
