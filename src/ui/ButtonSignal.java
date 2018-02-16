/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

/**
 * A wrapper class for fire button signal between user interface..
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ButtonSignal {
	
	private String myButtonName;
	private int myJobID;
	
	
	public ButtonSignal(final String theButtonName, final int theJobID) {
		myButtonName = theButtonName;
		myJobID = theJobID;
	}
	
	public String getButtonName() {
		return myButtonName;
	}
	
	public int getJobID() {
		return myJobID;
	}
}