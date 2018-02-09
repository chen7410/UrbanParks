package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Job;
import model.JobMap;
import model.ParkManager;
import model.User;
import model.UserMap;
import model.Volunteer;

/**
 * T CSS 360 - Winter 2018 Team: Group 7 Urban Parks Project
 */
public class UrbanParkUI {
	
	private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
	
	private static final String USERS_DATA_FILE = "UsersInformations.ser";
	
	private static final String myLogOut = "        3. Log out";

	private static final String WHAT_WOULD_LIKE_TO_DO_MESSAGE = ">>> What would you like to do?";
	
	private static final String SELECT_A_NUMBER_MESSAGE = "    (Please select a number)";
	
	private static final String USER_INPUT_MESSAGE = "> ";
	
	private static final String YES_OR_NO_MESSAGE = "    (Please enter Yes or No)";
	
	private static Scanner myScanner;

	private static User myCurrentUser;

	private static Volunteer myVolunteer;

	private static ParkManager myParkManager;
	
	private static UserMap myUsers;

	private static JobMap myJobs;

	private static DateTimeFormatter myDateFormatter;
	
	public static void main(final String[] theArgs) {
		init();
		welcome();
		if (myCurrentUser instanceof ParkManager) {
			myParkManager = (ParkManager) myCurrentUser;
			basicParkManagerOptions();
		} else if (myCurrentUser instanceof Volunteer) {
			myVolunteer = (Volunteer) myCurrentUser;
			basicVolunteerOptions();
		}
	}

	private static void init() {
		myJobs = new JobMap();
		myUsers = new UserMap();
				
		myJobs.loadJobMap(JOBS_DATA_FILE);
		myUsers.loadUserMap(USERS_DATA_FILE);
		
		myScanner = new Scanner(System.in);
		myDateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
	}


	/**
	 * Prints out the welcome statements, prompts the user for next task, and
	 * returning task value.
	 * 
	 * @return The task value the user selected.
	 */
	private static void welcome() {
		System.out.println(">>> Welcome to Urban Parks");
		System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
		System.out.println(SELECT_A_NUMBER_MESSAGE);
		System.out.println("        1. Log in");
		System.out.println("        2. Exit\n");
		System.out.print(USER_INPUT_MESSAGE);
		int selection = myScanner.nextInt();
		myScanner.nextLine();
		System.out.println();
		switch (selection) {
		case 1:
			logIn();
			break;
		case 2:
			exitSystemMessage();
			myScanner.close();
			break;
		default:
			// TODO: have the main menu display again??
			System.out.println("Please enter a valid option");
		}
	}

	private static void logIn() {
		System.out.println(">>> Please enter your username:\n");
		System.out.print(USER_INPUT_MESSAGE);
		String username = myScanner.nextLine();
		System.out.println();
		myCurrentUser = myUsers.getUser(username);
	}
	
	private static void welcomeUserMessage() {
		System.out.println(">>> Welcome " + myCurrentUser.getFirstName() + " " + myCurrentUser.getLastName() + ". "
				+ "You are logged in as a " + myCurrentUser.getUserType() + '.');
	}

	private static void basicParkManagerOptions() {
		boolean isExit = false;
		while(!isExit){
			welcomeUserMessage();
			System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
			System.out.println(SELECT_A_NUMBER_MESSAGE);
			System.out.println("        1. Submit a new job");
			System.out.println("        2. View all your submitted jobs");
			System.out.println(myLogOut + '\n');
			System.out.print(USER_INPUT_MESSAGE);
			int selection = myScanner.nextInt();
			myScanner.nextLine();
			System.out.println();
			switch (selection) {
			case 1:
				submitNewJob();
				break;
			case 2:
				printParkManagerSubmittedJobs(true);
				break;
			default:
				isExit = true;
				break;
			}
		}
	}

	private static void submitNewJob() {
		System.out.println(">>> Please fill out the job details.");
		System.out.println(">>> Enter park name:\n");
		System.out.print(USER_INPUT_MESSAGE);
		String parkName = myScanner.nextLine();
		System.out.println();
		
		System.out.println(">>> Enter job location:\n");
		System.out.print(USER_INPUT_MESSAGE);
		String jobLocation = myScanner.nextLine();
		System.out.println();
	
		System.out.println(">>> Enter job start date(MM/DD/YY):\n");
		System.out.print(USER_INPUT_MESSAGE);
		LocalDate jobStartDate = LocalDate.parse(myScanner.nextLine(), myDateFormatter);
		System.out.println();
		
		System.out.println(">>> Enter job end date (MM/DD/YY):\n");
		System.out.print(USER_INPUT_MESSAGE);
		LocalDate jobEndDate = LocalDate.parse(myScanner.nextLine(), myDateFormatter);
		System.out.println();
		
		System.out.println(">>> Enter job description:\n");
		System.out.print(USER_INPUT_MESSAGE);
		String jobDescription = myScanner.nextLine();
		System.out.println();
		
		Job job = new Job(jobStartDate, jobEndDate, parkName, myParkManager, jobLocation, jobDescription);
		System.out.println(job);
		System.out.println();
		
		if (myParkManager.isJobEndsWithinMaxDays(job) && myParkManager.isJobWithinMaxDays(job)) {
			jobDetailsVerification(job);
			printParkManagerSubmittedJobs(false);
		}
		
		System.out.println(">>> Would you like to submit another job?");
		System.out.println(YES_OR_NO_MESSAGE + '\n');
		System.out.print(USER_INPUT_MESSAGE);
		switch (myScanner.nextLine().toLowerCase()) {
		case "yes":
			submitNewJob();
			break;
		case "no":
			break;
		default:
			System.out.println("Invalid input");
			break;
		}
	}
	
	private static void jobDetailsVerification(final Job theJob) {
		System.out.println(">>> Would like to submit this job?");
		System.out.println(YES_OR_NO_MESSAGE + '\n');
		System.out.print(USER_INPUT_MESSAGE);
		switch(myScanner.nextLine().toLowerCase()) {
		case "yes" :
			
			myJobs.addJob(theJob);
			myParkManager.createJob(theJob);
			
			myJobs.storeJobMap(JOBS_DATA_FILE);
			myUsers.storeUserMap(USERS_DATA_FILE);
			System.out.println("\n>>> Job has been submitted successfully.");
			break;
		default:
			break;
		}
	}
	
	
	
	private static void printParkManagerSubmittedJobs(boolean isAbleToViewDetails) {
		do {
			System.out.println(">>> Here are your submitted jobs:");
			List<Integer> jobIDList = myParkManager.getJobList();
			for (int i = 1; i <= jobIDList.size(); i++) {
				Job job = myJobs.getJob(jobIDList.get(i - 1));
				System.out.print("    " + i + ". ");
				System.out.print(job.getParkName());
				System.out.print(": " + job.getStartDate().format(myDateFormatter));
				System.out.println(" - " + job.getEndDate().format(myDateFormatter) + '\n');
			}
			
			if(isAbleToViewDetails) {
				System.out.println("    0. Return to previous menu");
				System.out.println("    (Please select a number to view job details)\n");
				System.out.print(USER_INPUT_MESSAGE);
				int selection = myScanner.nextInt();
				myScanner.nextLine();
				if (selection > 0 && selection <= jobIDList.size()) {
					System.out.println(myJobs.getJob(jobIDList.get(selection - 1)));
					System.out.println(">>> Do you want to view another job details?");
					System.out.println(YES_OR_NO_MESSAGE);
					System.out.print(USER_INPUT_MESSAGE);
					switch(myScanner.nextLine().toLowerCase()) {
					case "no":
						isAbleToViewDetails = false;
					}
				} else if (selection == 0) {
					isAbleToViewDetails = false;
				} else {
					System.out.println("Invalid input");
				}
			}
		} while(isAbleToViewDetails);
		
	}

	private static void basicVolunteerOptions() {
		welcomeUserMessage();
		System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
		System.out.println(SELECT_A_NUMBER_MESSAGE);
		System.out.println("        1. Sign up for a new job");
		System.out.println("        2. View your upcoming jobs");
		System.out.println(myLogOut);
		System.out.println();
		System.out.print(USER_INPUT_MESSAGE);
		int selection = myScanner.nextInt();
		myScanner.nextLine();
		switch (selection) {
		case 1:
			signUpForNewJob();
			break;
		default:
			break;
		}
	}

	private static void signUpForNewJob() {
		System.out.println(">>> Here are all the open volunteering jobs:");
		//myJobs.displayJobs();
		
		Job[] jobList = myJobs.getJobsArray();
		for (Job job : jobList) {
			if (myVolunteer.isAtLeastMinDays(job) || isSameDayConflictCheck(job)) {
				System.out.println(job.toString());
			}
		}

	}

	private static boolean isSameDayConflictCheck(final Job theCandidateJob) {
		boolean result = false;
		for (int jobID : myVolunteer.getJobList()) {
			result = result || myVolunteer.isSameDayConflict(theCandidateJob, myJobs.getJob(jobID));
		}
		return result;
	}

	private static void exitSystemMessage() {
		System.out.println(">>> Thank you for using Urban Parks.");
		myJobs.storeJobMap(JOBS_DATA_FILE);
		myUsers.storeUserMap(USERS_DATA_FILE);
	}
}
