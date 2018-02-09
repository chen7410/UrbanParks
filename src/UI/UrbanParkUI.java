/**
 * T CSS 360 - Winter 2018
 * Team: Group 7
 * Urban Parks Project
 */

package UI;

import java.util.Scanner;

public class UrbanParkUI {
	
	private static Scanner myScanner = new Scanner(System.in);
	
	public static void main(final String[] theArgs) {
		int taskUser = welcome();
		switch(taskUser) {
			case 1: logIn();
					break;
			
		}
		
		// Only if the user is a park manager.
		int taskPM = basicParkManagerOptions();
	}
	
	/**
	 * Prints out the welcome statements, prompts the user for next
	 * task, and returning task value.
	 * 
	 * @return The task value the user selected.
	 */
	private static int welcome() {
		System.out.println(">>> Welcome to Urban Parks.");
		System.out.println(">>> What would you like to do? (Please select a number)");
		
		String[] options = {"Log in", "Exit"};
		for (int i = 0; i < options.length; i++) {
			System.out.println("    " + (i + 1) + ". " + options[i]);
		}
		
		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();
		
		return task;
	}
	
	// *** ISSUE: Once the user inputs their username, the their data (First & Last name)"
	//																	+ " needs to be retrieved & added here.***"
	private static void logIn() {
		System.out.println(">>> Please enter your username:");
		
		System.out.println();
		System.out.print("> ");
		String username = myScanner.next();
		System.out.println();
		
		// This print statement below needs to be fixed.
		System.out.println(">>> Welcome " + username + ". You are logged in as a ...");
	}
	
	private static int basicParkManagerOptions() {
		System.out.println(">>> What would you like to do? (Please select a number)");
		
		String[] options = {"Submit a new job", "View all your submitted jobs", "Log out"};
		for (int i = 0; i < options.length; i++) {
			System.out.println("    " + (i + 1) + ". " + options[i]);
		}
		
		System.out.println();
		System.out.print("> ");
		int task = myScanner.nextInt();
		System.out.println();
		
		return task;
	}
}