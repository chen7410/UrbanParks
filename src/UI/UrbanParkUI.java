/*
 * TCSS 360 - Winter 2018
<<<<<<< HEAD
 * Team: Group 7
=======
>>>>>>> afc6f0899884bc451c4c9f089486d0666aae7f02
 * Urban Parks Project
 */

package UI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
<<<<<<< HEAD
 * Allows for users to do all sorts of things ranging from signing up
 * for volunteering jobs to creating volunteering jobs, depending on
 * which type of user they are. There are two types of users:
 * Volunteers and Park Managers.
 * 
 * @author Group 7
 * @version February 12, 2018
=======
 * Allows for users to do all sorts of things ranging from signing up for
 * volunteering jobs to creating volunteering jobs, depending on which type of
 * user they are. There are two types of users: Volunteers and Park Managers.
 * 
 * @author Group 7
 * @version February 9, 2018
>>>>>>> afc6f0899884bc451c4c9f089486d0666aae7f02
 */
public class UrbanParkUI {

	private static final String INVALID_INPUT_MESSAGE = ">>> Please enter a valid option.\n";

	private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";

	private static final String SELECT_A_NUMBER_MESSAGE = "    (Please select a number)";

	private static final String USERS_DATA_FILE = "UsersInformations.ser";

	private static final String USER_INPUT_MESSAGE = "> ";

	private static final String WHAT_WOULD_LIKE_TO_DO_MESSAGE = ">>> What would you like to do?";

	private static final String YES_OR_NO_MESSAGE = "    (Please enter Yes or No)\n";

	private static User myCurrentUser;

	private static JobMap myJobs;

	private static DateTimeFormatter myDateFormatter;

	private static ParkManager myParkManager;

	/** Used to gathers input from the user. */
	private static Scanner myScanner;

	private static UserMap myUsers;

	private static Volunteer myVolunteer;

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

	/** Initializing all the fields and loading in necessary data. */
	private static void init() {
		myJobs = new JobMap();
		myUsers = new UserMap();

		myJobs.loadJobMap(JOBS_DATA_FILE);
		myUsers.loadUserMap(USERS_DATA_FILE);

		myScanner = new Scanner(System.in);
		myDateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
	}

	/**
<<<<<<< HEAD
	 * Prints out the welcome statements and prompts the user for next
	 * task.
=======
	 * Prints out the welcome statements and lets user choose what to
	 * do. If user puts in wrong input it will tell them to choose a 
	 * different input.
>>>>>>> afc6f0899884bc451c4c9f089486d0666aae7f02
	 * 
	 * @return false when the user want to exit the program.
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

	/**
<<<<<<< HEAD
	 * 
=======
	 * Lets user log in with a user name. If the user name doesn't 
	 * exist, let them try again. Will not exit unless they put in
	 * the right user name.
>>>>>>> afc6f0899884bc451c4c9f089486d0666aae7f02
	 */
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

	/**
	 * Shows Park Manager's menu. Will not exit unless they choose the
	 * exit option. Does not show submit job option when there are
	 * maximum amount of jobs in the system.
	 */
	private static void basicParkManagerOptions() {
		boolean isExit = false;
		while (!isExit) {
			welcomeUserMessage();
			System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
			System.out.println(SELECT_A_NUMBER_MESSAGE);
			String[] message = { ". Submit a new job",
					". View all your submitted jobs",
					". Log out" };
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
				if (myParkManager.isLessThanMaxJobs(myJobs) 
						&& selection == 1) {
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

	/**
	 * Lets park manager puts in all the necessary information to
	 * submit a new job. Checks for invalid dates: dates that have
	 * already passed, dates in wrong format, end dates that are
	 * before start date, jobs that last more than maximum amount
	 * of dates, and jobs that ends outside of maximum date range.
	 */
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
					if (jobStartDate.isBefore(LocalDate.now())) {
						System.out.println("\n>>> Invalid date. The date has already passed.\n");
					} else {
						isExit = true;
					}
				} catch (final DateTimeParseException theException) {
					System.out.println("\n>>> Invalid date. Please use" + " MM/DD/YY format.\n");
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
					System.out.println();
					if (jobEndDate.isBefore(LocalDate.now())) {
						System.out.println(">>> Invalid date. The date has already passed.\n");
					} else if (jobEndDate.isBefore(jobStartDate)) {
						System.out.println(">>> Invalid date. The end date cannot be before the start date.\n");
					} else {
						isExit = true;
					}
				} catch (final DateTimeParseException theException) {
					System.out.println("\n>>> Invalid date. Please use" + " MM/DD/YY format.\n");
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

	/**
	 * Lets user verify the job details and lets them choose if they
	 * want to submit the job.
	 * @param theJob 
	 * 			the job that gets shown to user.
	 */
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
				System.out.println("\n>>> Job has been submitted" + " successfully.");
				break;
			case "no":
				System.out.println("\n>>> Job has been cancelled" + " successfully.");
				isExit = true;
				break;
			default:
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			}
		}
	}

	/**
	 * Prints out a list of job summary a park manager has with the
	 * option to let them view job details.
	 * @param theIsAbleToViewDetails
	 * 						true if user can view job details.
	 */
	private static void printParkManagerSubmittedJobs(boolean theIsAbleToViewDetails) {
		do {
			System.out.println(">>> Here are your submitted jobs:");
			List<Integer> jobIDList = myParkManager.getJobList();
			for (int i = 1; i <= jobIDList.size(); i++) {
				System.out.print("    " + i + ". ");
				System.out.println(myJobs.getJob(jobIDList.get(i - 1)).getJobSummary());
			}
			if (jobIDList.size() == 0) {
				System.out.println("    You have not submitted any jobs.");
			}
			System.out.println();
			if (theIsAbleToViewDetails) {
				System.out.println("    0. Return to previous menu");
				System.out.println("    (Please select a number to" + " view job details)\n");
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
						System.out.println(">>> Do you want to view" + " another job details?");
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
								theIsAbleToViewDetails = false;
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
						theIsAbleToViewDetails = false;
						break;
					} else {
						System.out.println(INVALID_INPUT_MESSAGE);
					}
				}

			}
		} while (theIsAbleToViewDetails);
	}
	
	/**
	 * Shows Volunteer's menu. Will not exit unless they choose the
	 * exit option.
	 */
	private static void basicVolunteerOptions() {
		boolean isExitToMainMenu = false;
		while (!isExitToMainMenu) {
			welcomeUserMessage();
			System.out.println(WHAT_WOULD_LIKE_TO_DO_MESSAGE);
			System.out.println(SELECT_A_NUMBER_MESSAGE);
			System.out.println("        1. Sign up for a new job");
			System.out.println("        2. View your upcoming jobs");
			System.out.println("        3. Log out");
			System.out.println();
			int selection = 0;
			boolean isExit = false;
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
					signUpForNewJob();
					isExit = true;
					break;
				case 2:
					printVolunteerSignedUpJobs(true);
					isExit = true;
					break;
				case 3:
					isExit = true;
					isExitToMainMenu = true;
					break;
				default:
					System.out.println(INVALID_INPUT_MESSAGE);
					break;
				}
			}
		}
	}
	
	/**
	 * Shows the user the list of jobs that they can sign up for. Will
	 * hide jobs that starts within the minimum number of days and jobs
	 * that overlap what they already have.
	 */
	private static void signUpForNewJob() {
		boolean isExitToMainMenu = false;
		while (!isExitToMainMenu) {
			System.out.println(">>> Here are all the open volunteering jobs:");
			Job[] jobList = myJobs.getJobsArray();
			List<Job> validJobs = new ArrayList<>();
			for (int i = 0; i < jobList.length; i++) {
				if (myVolunteer.isAtLeastMinDays(jobList[i]) && !isSameDayConflictCheck(jobList[i])) {
					validJobs.add(jobList[i]);
				}
			}
			for (int i = 1; i <= validJobs.size(); i++) {
				System.out.println("        " + i + ". " + validJobs.get(i - 1).getJobSummary());
			}
			System.out.println("\n        0. Return to previous menu");
			System.out.println("        (Please select a number to" + " view job details)\n");
			int selection = -1;

			while (true) {
				try {
					System.out.print(USER_INPUT_MESSAGE);
					selection = myScanner.nextInt();
					myScanner.nextLine();
				} catch (final InputMismatchException theException) {
					myScanner = new Scanner(System.in);
				}
				System.out.println();
				if (selection == 0) {
					isExitToMainMenu = true;
					break;
				} else if (selection > 0 && selection <= validJobs.size()) {
					Job job = validJobs.get(selection - 1);
					jobSignUpVerification(job);
					printVolunteerSignedUpJobs(false);
					System.out.println("Would you like to sign up for" + " another job?");
					System.out.println(YES_OR_NO_MESSAGE);
					boolean isExit = false;
					while (!isExit) {
						System.out.print(USER_INPUT_MESSAGE);
						switch (myScanner.nextLine().toLowerCase()) {
						case "yes":
							isExit = true;
							break;
						case "no":
							isExitToMainMenu = true;
							isExit = true;
							break;
						default:
							break;
						}
					}
					break;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			}
		}
	}

	/**
	 * Asks user if they want to sign up for shown job.
	 * @param theJob
	 * 			the job being shown to user
	 */
	private static void jobSignUpVerification(final Job theJob) {
		System.out.println(theJob.toString());
		System.out.println(">>> Would you like to sign up for this job?");
		System.out.println(YES_OR_NO_MESSAGE);
		boolean isExit = false;
		while (!isExit) {
			System.out.print(USER_INPUT_MESSAGE);
			switch (myScanner.nextLine().toLowerCase()) {
			case "yes":
				myVolunteer.signup(theJob);
				myUsers.storeUserMap(USERS_DATA_FILE);
				System.out.println("\n>>> You have signed up for the" + " job successfully.");
				isExit = true;
				break;
			case "no":
				isExit = true;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Prints out a list of job summary a volunteer has with the
	 * option to let them view job details.
	 * @param theIsAbleToViewDetails
	 * 						true if user can view job details.
	 */
	private static void printVolunteerSignedUpJobs(boolean theIsAbleToViewDetails) {
		do {
			System.out.println(">>> Here are your upcoming jobs:");
			List<Integer> jobIDList = myVolunteer.getJobList();
			for (int i = 1; i <= jobIDList.size(); i++) {
				System.out.println("    " + i + ". " + myJobs.getJob(jobIDList.get(i - 1)).getJobSummary());
			}

			if (jobIDList.size() == 0) {
				System.out.println("    You have not signed up for" + " any jobs.");
			}

			if (theIsAbleToViewDetails) {
				System.out.println("\n    0. Return to previous menu");
				System.out.println("    (Please select a number to" + " view job details)\n");
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
						System.out.println(">>> Do you want to view" + " another job details?");
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
								theIsAbleToViewDetails = false;
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
						theIsAbleToViewDetails = false;
						break;
					} else {
						System.out.println(INVALID_INPUT_MESSAGE);
					}
				}

			}
		} while (theIsAbleToViewDetails);
	}

	/**
	 * Checks if the job conlicts with any job a volunteer current has.
	 * @param theCandidateJob
	 * 					the job to check
	 * @return true if there are conflicts, false otherwise
	 */
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