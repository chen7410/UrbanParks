package test_ui;

import java.time.LocalDate;
import java.util.List;

import model.Job;
import model.JobMap;
import model.UserMap;

public class addUserIgnoreThisClass {
	private static UserMap myUsers;
	private static JobMap myJobs;
	private static LocalDate myStartDate = LocalDate.now().plusMonths(-1);
	private static LocalDate myEndtDate = LocalDate.now().plusMonths(1);
	
	public static void main(String[] args) {
		myUsers = new UserMap();
		myJobs = new JobMap();
		myUsers.loadUserMap(myUsers.USERS_DATA_FILE);
		myJobs.loadJobMap(myJobs.JOBS_DATA_FILE);
		//Staff tom = new Staff("tom", "Tom", "Hanks");
		//myUsers.addUser(tom);
		System.out.println(myUsers.getUser("tom"));
		System.out.println(myUsers.getAllUserNames().toString());
		
		//System.out.println(myJobs.getJobsInPeriod(myStartDate, myEndtDate));
		List<Job> joblist = myJobs.getJobsInPeriod(myStartDate, myEndtDate);
		
		for (Job j : joblist) {
			System.out.println(j.toString());
		}

		
	}

}
