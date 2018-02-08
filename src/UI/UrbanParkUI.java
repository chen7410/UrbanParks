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

public class UrbanParkUI {

	private static Scanner myScanner = new Scanner(System.in);

	private static User myCurrentUser;

	private static UserMap myUsers;

	public static void main(final String[] theArgs) {
		load();
		int task = welcome();
		switch (task) {
		case 1:
			logIn();
			break;
		case 2:
			System.out.println(">>> Thank you for using Urban Parks.");
			myScanner.close();
		default:
			break;
		}

		if (myCurrentUser instanceof ParkManager) {
			basicParkManagerOptions();
		}
	}

	private static void load() {
		myUsers = new UserMap();
	}

	/**
	 * Prints out the welcome statements, prompts the user for next task, and
	 * returning task value.
	 * 
	 * @return The task value the user selected.
	 */
	private static int welcome() {
		System.out.println(">>> Welcome to Urban Parks.");
		System.out.println(">>> What would you like to do? (Please select a number)");

		String[] options = { "Log in", "Exit" };
		for (int i = 0; i < options.length; i++) {
			System.out.println("    " + (i + 1) + ". " + options[i]);
		}

		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();

		return task;
	}


	private static void logIn() {
		System.out.println(">>> Please enter your username:");

		System.out.println();
		System.out.print("> ");
		String username = myScanner.nextLine();
		System.out.println();
		myCurrentUser = myUsers.getUser(username);
		System.out.println(">>> Welcome " + myCurrentUser.getFirstName() + " " + myCurrentUser.getLastName()
				+ ". You are logged in as a " + myCurrentUser.getUserType());
	}

	private static void basicParkManagerOptions() {
		System.out.println(">>> What would you like to do? (Please select a number)");

		String[] options = { "Submit a new job", "View all your submitted jobs", "Log out" };
		for (int i = 0; i < options.length; i++) {
			System.out.println("    " + (i + 1) + ". " + options[i]);
		}

		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();
	}
}