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
	
	public ButtonSignal(final String theButtonName, final Object theObject) {
		myButtonName = theButtonName;
		myObject = theObject;
	}
	
	public String getButtonName() {
		return myButtonName;
	}
	
	public int getJobID() {
		return (int) myObject;
	}
	
	public User getUser() {
		return (User) myObject;
	}
	
	public Job getJob() {
		return (Job) myObject;
	}
	
	public LocalDate getStartDate() {
		return (LocalDate) ((Object[])myObject)[0];
	}
	
	public LocalDate getEndDate() {
		return (LocalDate) ((Object[])myObject)[1];
	}
}