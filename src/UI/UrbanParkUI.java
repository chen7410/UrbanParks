package UI;

import java.util.Scanner;

public class UrbanParkUI {
	
	private static Scanner myScanner = new Scanner(System.in);
	
	public static void main(final String[] theArgs) {
		int task = welcome();
		
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
}