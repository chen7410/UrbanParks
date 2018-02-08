/**
 * T CSS 360 - Winter 2018
 * Team: Group 7
 * Urban Parks Project
 */

package UI;

import java.util.Scanner;

import model.ParkManager;
import model.User;
import model.UserMap;
import model.Volunteer;

public class UrbanParkUI {
	private static final String myWelcomeMessage = ">>> Welcome to Urban Parks";
	private static final String myWhatToDo = ">>> What would you like to do?";
	private static final String mySelectNumber = "    (Please select a number)";
	private static final String myLogin = "        1. Log in";
	private static final String myExit = "        2. Exit";
	private static final String myLogOut = "        3. Log out";
	private static final String myEnterUsername = ">>> Please enter your username:";
	private static final String myLoginAs = "You are logged in as a ";
	private static final String myWelcome = ">>> Welcome ";
	//private static final String myConsoleChar = ">>>";
	private static final String mySignUpNewJob = "        1. Sign up for a new job";
	private static final String myViewUpCommingJob = "        2. View your upcoming jobs";
	private static final String myAllOpenVolunteerJob = ">>> Here are all the open volunteering jobs:";
	private static final String myReturnPreviousMessage = "        0. Return to previous menu";
	private static final String mySelectNumberViewJob = "    (Please select a number to view job details)";
	private static final String mySignUpJobMessage = "    Would you like to sign up for this job?";
	private static final String myYesOrNo = "    (Please enter Yes or No)";
	private static final String myAddJobSuccess = ">>> Job added successfully. Here are your upcoming jobs:";
	private static final String mySignUpAnotherJob = ">>> Would like to sign up for another job?";
	private static final String myUpcommingJob = ">>> Here are your upcoming jobs:";
	private static final String myLogOutMessage = ">>> You have logged out successfully.";
	private static final String myExitSystem = ">>> Thank you for using Urban Parks.";
	private static final String mySubmitNewJob = "        1. Submit a new job";
	private static final String myViewAllSubmittedJobs = "        2. View all your submitted jobs";

	private static Scanner myScanner = new Scanner(System.in);

	private static User myCurrentUser;

	private static UserMap myUsers;
		
	private static ParkManager myBrook;
	
	private static Volunteer myHasnah;
	
	private static ParkManager myMatthew;
	
	private static Volunteer myTuan;

	public static void main(final String[] theArgs) {
		load();
		int task = welcome();
		switch (task) {
		case 1:
			logIn();
			break;
		case 2:
			exitSystemMessage();
			myScanner.close();
		default:
			break;
		}

		if (myCurrentUser instanceof ParkManager) {
			basicParkManagerOptions();
		}
		
//		if (myCurrentUser instanceof Volunteer) {
//			basicVolunteerOptions();
//		} 
	}

	private static void load() {
		myUsers = new UserMap();
		
		myBrook = new ParkManager("brook", "Brook", "Negussie");
		myHasnah = new Volunteer("hasnah", "Hasnah", "Said");
		myMatthew = new ParkManager("matthew", "Minqin", "Chen");
		myTuan = new Volunteer("tuan", "Tuan", "Dinh");
		
		myUsers.addUser(myBrook);
		myUsers.addUser(myHasnah);
		myUsers.addUser(myMatthew);
		myUsers.addUser(myTuan);
		
	}

	/**
	 * Prints out the welcome statements, prompts the user for next task, and
	 * returning task value.
	 * 
	 * @return The task value the user selected.
	 */
	private static int welcome() {
		welcomeMessageMainMenu();
		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();

		return task;
	}


	private static void logIn() {
		enterUsername();
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.print("> ");		
		String username = scan.nextLine();
		
		System.out.println();
		myCurrentUser = myUsers.getUser(username);
		
		System.out.println(myWelcome + myCurrentUser.getFirstName() + " " + myCurrentUser.getLastName()
				+ ". " +myLoginAs + myCurrentUser.getUserType());
	}

	private static void basicParkManagerOptions() {
		
		parkManagerMenu();
		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();
	}
	
	/**
	 * print ">>> Welcome to Urban Parks." and main menu.
	 */
	private static void welcomeMessageMainMenu() {
		System.out.println(myWelcomeMessage + "\n" + myWhatToDo + "\n" + mySelectNumber + "\n" + myLogin
				 + "\n" + myExit);
	}
	
	/**
	 * print ">>> Please enter your username:".
	 */
	private static void enterUsername() {
		System.out.println(myEnterUsername);
	}
	
	/**
	 * print park manager
	 */
	
	private static void parkManagerMenu() {
		System.out.println(myWhatToDo + "\n" + mySelectNumber + "\n" + mySubmitNewJob + "\n" 
				+ myViewAllSubmittedJobs + "\n" + myLogOut);
	}
	
	/**
	 * print volunteer Menu.
	 */
	private static void volunteerMenu() {
		System.out.println(myWhatToDo + "\n" + mySelectNumber + "\n" + mySignUpNewJob  + "\n" + 
				myViewUpCommingJob  + "\n" + myLogOut);
	}
	
	/**
	 * print ">>> Here are all the open volunteering jobs:"e.
	 */
	private static void allOpenVolunteerJob() {
		System.out.println(myAllOpenVolunteerJob);
	}
	
	/**
	 * print "        0. Return to previous menu".
	 */
	private static void returnPreviousMessage() {
		System.out.println(myReturnPreviousMessage);
	}
	
	/**
	 * print "    (Please select a number to view job details)".
	 */
	private static void selectNumberViewJob() {
		System.out.println(mySelectNumberViewJob);
	}
	
	/**
	 * print "    Would you like to sign up for this job?".
	 */
	private static void wouldSignUpJob() {
		System.out.println(mySignUpJobMessage);
	}
	
	/**
	 * print "    (Please enter Yes or No)".
	 */
	private static void yesOrNo() {
		System.out.println(myYesOrNo);
	}
	
	/**
	 * print ">>> Job added successfully. Here are your upcoming jobs:"��
	 */
	private static void addJobSuccess() {
		System.out.println(myAddJobSuccess);
	}
	
	/**
	 * print ">>> Would like to sign up for another job?".
	 */
	private static void wouldSignUpAnotherJob() {
		System.out.println(mySignUpAnotherJob);
	}
	
	/**
	 * print ">>> Here are your upcoming jobs:".
	 */
	private static void upcommingJob() {
		System.out.println(myUpcommingJob);
	}
	
	/**
	 * print ">>> You have logged out successfully.".
	 */
	private static void logoutMessage() {
		System.out.println(myLogOutMessage);
	}
	
	/**
	 * print ">>> Thank you for using Urban Parks."
	 */
	private static void exitSystemMessage() {
		System.out.println(myExitSystem);
	}
}

