package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
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

	private static final String WHAT_WOULD_LIKE_TO_DO_MESSAGE = ">>> What would you like to do?";

	private static final String SELECT_A_NUMBER_MESSAGE = "    (Please select a number)";

	private static final String USER_INPUT_MESSAGE = "> ";

	private static final String YES_OR_NO_MESSAGE = "    (Please enter Yes or No)\n";

	private static final String INVALID_INPUT_MESSAGE = ">>> Please enter a valid option.\n";

	private static Scanner myScanner;

	private static User myCurrentUser;

	private static Volunteer myVolunteer;

	private static ParkManager myParkManager;

	private static UserMap myUsers;

	private static JobMap myJobs;

	private static DateTimeFormatter myDateFormatter;

	public static void main(final String[] theArgs) {
		init();
		while (welcome()) {
			if (myCurrentUser instanceof ParkManager) {
				myParkManager = (ParkManager) myCurrentUser;
				basicParkManagerOptions();
			} else if (myCurrentUser instanceof Volunteer) {
				myVolunteer = (Volunteer) myCurrentUser;
				basicVolunteerOptions();
			}
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
	 * Prints out the welcome statements, prompts the user for next task
	 * 
	 * @return false when the user want to exit the program
	 */
	private static boolean welcome() {
		boolean isExit = false;
		boolean isExitProgram = true;
		while (!isExit) {
			System.out.println(">>> Welcome to Urban Parks");
			System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
			System.out.println(SELECT_A_NUMBER_MESSAGE);
			System.out.println("        1. Log in");
			System.out.println("        2. Exit\n");
			int selection = 0;
			while (!isExit) {
				System.out.print(USER_INPUT_MESSAGE);
				try {
					selection = myScanner.nextInt();
					myScanner.nextLine();
				} catch (final InputMismatchException theException) {
					myScanner = new Scanner(System.in);
				}
				System.out.println();
				switch (selection) {
				case 1:
					logIn();
					isExit = true;
					break;
				case 2:
					isExit = true;
					exitSystemMessage();
					myScanner.close();
					isExitProgram = false;
					break;
				default:
					System.out.println(INVALID_INPUT_MESSAGE);
					break;
				}
			}

		}
		return isExitProgram;
	}

	private static void logIn() {
		System.out.println(">>> Please enter your username:\n");
		while (true) {
			System.out.print(USER_INPUT_MESSAGE);
			String username = myScanner.nextLine();
			System.out.println();
			myCurrentUser = myUsers.getUser(username);
			if (myCurrentUser == null) {
				System.out.println(">>> Your username does not exist.");
				System.out.println(">>> Please enter a different username:\n");
			} else {
				break;
			}
		}

	}

	private static void welcomeUserMessage() {
		System.out.println(">>> Welcome " + myCurrentUser.getFirstName() + " " + myCurrentUser.getLastName() + ". "
				+ "You are logged in as a " + myCurrentUser.getUserType() + '.');
	}

	private static void basicParkManagerOptions() {
		boolean isExit = false;
		while (!isExit) {
			welcomeUserMessage();
			System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
			System.out.println(SELECT_A_NUMBER_MESSAGE);
			String[] message = { ". Submit a new job", ". View all your submitted jobs", ". Log out" };
			int index = 0;
			int choiceModifier = 0;
			if (!myParkManager.isLessThanMaxJobs(myJobs)) {
				index = 1;
				choiceModifier = 1;
			}
			for (int i = 1; index < message.length; i++, index++) {
				System.out.println("        " + i + message[index]);
			}
			while (true) {
				System.out.print(USER_INPUT_MESSAGE);
				int selection = 0;
				try {
					selection = myScanner.nextInt();
					myScanner.nextLine();
				} catch (final InputMismatchException theException) {
					myScanner = new Scanner(System.in);
				}
				System.out.println();
				if (myParkManager.isLessThanMaxJobs(myJobs) && selection == 1) {
					submitNewJob();
					break;
				} else if (selection + choiceModifier == 2) {
					printParkManagerSubmittedJobs(true);
					break;
				} else if (selection + choiceModifier == 3) {
					isExit = true;
					break;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			}
		}
	}

	private static void submitNewJob() {
		boolean isFillOutJob = true;
		while (isFillOutJob) {
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

			LocalDate jobStartDate = null;
			boolean isExit = false;
			while (!isExit) {
				try {
					System.out.print(USER_INPUT_MESSAGE);
					jobStartDate = LocalDate.parse(myScanner.nextLine(), myDateFormatter);
					isExit = true;
				} catch (final DateTimeParseException theException) {
					System.out.println("\n>>> Invalid date. Please use MM/DD/YY format.\n");
				}
			}

			System.out.println();

			System.out.println(">>> Enter job end date (MM/DD/YY):\n");

			LocalDate jobEndDate = null;

			isExit = false;
			while (!isExit) {
				try {
					System.out.print(USER_INPUT_MESSAGE);
					jobEndDate = LocalDate.parse(myScanner.nextLine(), myDateFormatter);
					isExit = true;
				} catch (final DateTimeParseException theException) {
					System.out.println("\n>>> Invalid date. Please use MM/DD/YY format.\n");
				}
			}
			System.out.println();

			System.out.println(">>> Enter job description:\n");
			System.out.print(USER_INPUT_MESSAGE);
			String jobDescription = myScanner.nextLine();
			System.out.println();

			Job job = new Job(jobStartDate, jobEndDate, parkName, myParkManager, jobLocation, jobDescription);
			System.out.println(job);
			System.out.println();

			if (!myParkManager.isJobEndsWithinMaxDays(job) || !myParkManager.isJobWithinMaxDays(job)) {
				System.out.println("We cannot submit this job:");
				if (!myParkManager.isJobWithinMaxDays(job)) {
					System.out.println("Your job takes more than " + myParkManager.getMaxJobLength() + " days.");
				}
				if (!myParkManager.isJobEndsWithinMaxDays(job)) {
					System.out.println("Your job ends more than " + myParkManager.getMaxEndDay() + " days from today.");
				}
				System.out.println();
			} else {
				jobDetailsVerification(job);
				printParkManagerSubmittedJobs(false);
			}

			System.out.println(">>> Would you like to submit another job?");
			System.out.println(YES_OR_NO_MESSAGE);

			isExit = false;
			while (!isExit) {
				System.out.print(USER_INPUT_MESSAGE);
				switch (myScanner.nextLine().toLowerCase()) {
				case "yes":
					System.out.println();
					isExit = true;
					break;
				case "no":
					System.out.println();
					isExit = true;
					isFillOutJob = false;
					break;
				default:
					System.out.println(INVALID_INPUT_MESSAGE);
					break;
				}
			}
		}

	}

	private static void jobDetailsVerification(final Job theJob) {
		boolean isExit = false;
		System.out.println(">>> Would like to submit this job?");
		System.out.println(YES_OR_NO_MESSAGE);
		while (!isExit) {
			System.out.print(USER_INPUT_MESSAGE);
			switch (myScanner.nextLine().toLowerCase()) {
			case "yes":
				isExit = true;
				myJobs.addJob(theJob);
				myParkManager.createJob(theJob);
				myJobs.storeJobMap(JOBS_DATA_FILE);
				myUsers.storeUserMap(USERS_DATA_FILE);
				System.out.println("\n>>> Job has been submitted successfully.");
				break;
			case "no":
				System.out.println("\n>>> Job has been cancelled successfully.");
				isExit = true;
				break;
			default:
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			}
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
				System.out.println(" - " + job.getEndDate().format(myDateFormatter));
			}
			if (jobIDList.size() == 0) {
				System.out.println("    You have not submitted any jobs.");
			}
			System.out.println();
			if (isAbleToViewDetails) {
				System.out.println("    0. Return to previous menu");
				System.out.println("    (Please select a number to view job details)\n");
				while (true) {
					System.out.print(USER_INPUT_MESSAGE);
					int selection = -1;
					try {
						selection = myScanner.nextInt();
						myScanner.nextLine();
					} catch (final InputMismatchException theException) {
						myScanner = new Scanner(System.in);
					}

					System.out.println();
					if (selection > 0 && selection <= jobIDList.size()) {
						System.out.println(myJobs.getJob(jobIDList.get(selection - 1)));
						System.out.println(">>> Do you want to view another job details?");
						System.out.println(YES_OR_NO_MESSAGE);
						boolean isExit = false;
						while (!isExit) {
							System.out.print(USER_INPUT_MESSAGE);
							switch (myScanner.nextLine().toLowerCase()) {
							case "yes":
								isExit = true;
								System.out.println();
								break;
							case "no":
								isAbleToViewDetails = false;
								isExit = true;
								System.out.println();
								break;
							default:
								System.out.println();
								System.out.println(INVALID_INPUT_MESSAGE);
								break;
							}
						}
						break;
					} else if (selection == 0) {
						isAbleToViewDetails = false;
						break;
					} else {
						System.out.println(INVALID_INPUT_MESSAGE);
					}
				}

			}
		} while (isAbleToViewDetails);
	}

	private static void basicVolunteerOptions() {
		welcomeUserMessage();
		System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
		System.out.println(SELECT_A_NUMBER_MESSAGE);
		System.out.println("        1. Sign up for a new job");
		System.out.println("        2. View your upcoming jobs");
		System.out.println("        3. Log out");
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
		Job[] jobList = myJobs.getJobsArray();
		for (int i = 0; i < jobList.length; i++) {
			if (myVolunteer.isAtLeastMinDays(jobList[i]) || isSameDayConflictCheck(jobList[i])) {
				System.out.println("        " + (i + 1) + ". " + jobList[i].getParkName()  
						+ ": " + jobList[i].getStartDate() +  " - " + jobList[i].getEndDate());
			}
		}
		
		System.out.println("\n        0. Return to previous menu");
		System.out.println("        (Please select a number to view job details)\n");
		System.out.print(USER_INPUT_MESSAGE);
		
		//TODO: check invalid input
		int selection = myScanner.nextInt();
		System.out.println(jobList[selection - 1]);
		
		
		System.out.println(">>> Would you like to sign up for this job?");
		System.out.println(YES_OR_NO_MESSAGE + '\n');
		System.out.print(USER_INPUT_MESSAGE);
		
		Scanner scan = new Scanner(System.in);
		
		switch (scan.nextLine().toLowerCase()) {
		case "yes":
			System.out.println("yes");
//			addJob();
			break;
		case "no":
			System.out.println("no");
			break;
		default:
			System.out.println("Invalid input");
			break;
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
