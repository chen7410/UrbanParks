/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

import model.User;

/**
 * A wrapper class for fire button signal between user interface..
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ButtonSignal {
	
	private String myButtonName;
	private int myJobID;
	private User myUser;
	
	
	public ButtonSignal(final String theButtonName, final int theJobID) {
		myButtonName = theButtonName;
		myJobID = theJobID;
	}
	
	public ButtonSignal(final String theButtonName, final User theUser) {
		myButtonName = theButtonName;
		myUser = theUser;
	}
	
	public String getButtonName() {
		return myButtonName;
	}
	
	public int getJobID() {
		return myJobID;
	}
	
	public User getUser() {
		return myUser;
	}
}