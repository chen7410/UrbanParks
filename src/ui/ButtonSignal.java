/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

import java.time.LocalDate;

import model.Job;
import model.User;

/**
 * A wrapper class for fire button signal between user interface..
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ButtonSignal {
	
	private String myButtonName;
	private Object myObject;
	
	/**
	 * Creates a new button signal with the specified name 
	 * and the specified object type. 
	 * 
	 * @param theButtonName the name of the button
	 * @param theObject
	 */
	public ButtonSignal(final String theButtonName, final Object theObject) {
		myButtonName = theButtonName;
		myObject = theObject;
	}
	
	/**
	 * @return the name of the button.
	 */
	public String getButtonName() {
		return myButtonName;
	}
	
	/**
	 * @return the job's ID. 
	 */
	public int getJobID() {
		return (int) myObject;
	}
	
	/**
	 * @return the user type.
	 */
	public User getUser() {
		return (User) myObject;
	}
	
	/**
	 * @return the job of the user.
	 */
	public Job getJob() {
		return (Job) myObject;
	}
	
	/**
	 * @return the start date of the job.
	 */
	public LocalDate getStartDate() {
		return (LocalDate) ((Object[])myObject)[0];
	}
	
	/**
	 * @return the end date of the job.
	 */
	public LocalDate getEndDate() {
		return (LocalDate) ((Object[])myObject)[1];
	}
}