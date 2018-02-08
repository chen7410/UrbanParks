package UI;

/**
 * This class represents UrbanPark user interface and program main method.
 * @author Group 7
 *
 */
public class UrbanParkUI {
	
	//all fields make public temporary for testing purpose. 
	public String myWelcomeMessage = ">>> Welcome to Urban Parks.";
	public String myWhatToDo = ">>> What would you like to do?";
	public String mySelectNymber = "    (Please select a number)";
	public String myLogin = "        1. Log in";
	public String myExit = "        2. Exit";
	public String myLogOut = "        3. Log out";
	public String myEnterUsername = ">>> Please enter your username:";
	public String myLoginAs = "You are logged in as a ";
	public String myWelcome = "Welcome ";
	//public String myConsoleChar = ">>>";
	public String mySignUpNewJob = "        1. Sign up for a new job";
	public String myViewUpCommingJob = "        2. View your upcoming jobs";
	public String myAllOpenVolunteerJob = ">>> Here are all the open volunteering jobs:";
	public String myReturnPreviousMessage = "        0. Return to previous menu";
	public String mySelectNumberViewJob = "    (Please select a number to view job details)";
	public String mySignUpJobMessage = "    Would you like to sign up for this job?";
	public String myYesOrNo = "    (Please enter Yes or No)";
	public String myAddJobSuccess = ">>> Job added successfully. Here are your upcoming jobs:";
	public String mySignUpAnotherJob = ">>> Would like to sign up for another job?";
	public String myUpcommingJob = ">>> Here are your upcoming jobs:";
	public String myLogOutMessage = ">>> You have logged out successfully.";
	public String myExitSystem = ">>> Thank you for using Urban Parks.";
	
	/**
	 * print ">>> Welcome to Urban Parks." and main menu.
	 */
	public void welcomeMessageMainMenu() {
		//System.out.println();
		// + "\n" + 
		System.out.println(myWelcomeMessage + "\n" + myWhatToDo + "\n" + mySelectNymber + "\n" + myLogin
				 + "\n" + myExit);
	}
	
	/**
	 * print ">>> Please enter your username:".
	 */
	public void enterUsername() {
		System.out.println(myEnterUsername);
	}
	
	/**
	 * print volunteer Menu.
	 */
	public void volunteerMenu() {
		System.out.println(myWhatToDo + "\n" + mySelectNymber + "\n" + mySignUpNewJob  + "\n" + 
				myViewUpCommingJob  + "\n" + myLogOut);
	}
	
	/**
	 * print ">>> Here are all the open volunteering jobs:"e.
	 */
	public void allOpenVolunteerJob() {
		System.out.println(myAllOpenVolunteerJob);
	}
	
	/**
	 * print "        0. Return to previous menu".
	 */
	public void returnPreviousMessage() {
		System.out.println(myReturnPreviousMessage);
	}
	
	/**
	 * print "    (Please select a number to view job details)".
	 */
	public void selectNumberViewJob() {
		System.out.println(mySelectNumberViewJob);
	}
	
	/**
	 * print "    Would you like to sign up for this job?".
	 */
	public void wouldSignUpJob() {
		System.out.println(mySignUpJobMessage);
	}
	
	/**
	 * print "    (Please enter Yes or No)".
	 */
	public void yesOrNo() {
		System.out.println(myYesOrNo);
	}
	
	/**
	 * print ">>> Job added successfully. Here are your upcoming jobs:"¡£
	 */
	public void addJobSuccess() {
		System.out.println(myAddJobSuccess);
	}
	
	/**
	 * print ">>> Would like to sign up for another job?".
	 */
	public void wouldSignUpAnotherJob() {
		System.out.println(mySignUpAnotherJob);
	}
	
	/**
	 * print ">>> Here are your upcoming jobs:".
	 */
	public void upcommingJob() {
		System.out.println(myUpcommingJob);
	}
	
	/**
	 * print ">>> You have logged out successfully.".
	 */
	public void logoutMessage() {
		System.out.println(myLogOutMessage);
	}
	
	/**
	 * print ">>> Thank you for using Urban Parks."
	 */
	public void exitSystemMessage() {
		System.out.println(myExitSystem);
	}
}
