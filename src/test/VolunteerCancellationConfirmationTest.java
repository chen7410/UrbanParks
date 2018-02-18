/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import model.Job;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui.VolunteerCancellationConfirmationPanel;

/**
 * VolunteerCancellationConfirmation Test.
 * 
 * @author Hasnah Said
 * @version February 17, 2017
 * 
 */
public class VolunteerCancellationConfirmationTest {

	private static JFrame myFrame;
	private static UserMap myUsers;
	private static JobMap myJobs;
	private static Job myJob;
	private static final String USERS_DATA_FILE = "UsersInformations.ser";
    private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
    private static Volunteer myVolunteer;
    private static VolunteerCancellationConfirmationPanel myPanel;
    
    
    public static void main(String[] args) {
    	myFrame = new JFrame("Job Cancellation Confirmation");
    	myJobs = new JobMap();
    	myUsers = new UserMap();
    	myUsers.loadUserMap(USERS_DATA_FILE);
    	myJobs.loadJobMap(JOBS_DATA_FILE);
    	myVolunteer = (Volunteer) myUsers.getUser("hasnah");
    	List<Job> jobs = myJobs.getEligibleJobs(myVolunteer);
    	myJob = (Job) jobs.get(0);
    	myPanel = new VolunteerCancellationConfirmationPanel(myJob);
    	
    	myFrame.add(myPanel.getCancellationPanel(), BorderLayout.CENTER);
    	myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setResizable(false);
    	myFrame.setLocationRelativeTo(null);
    	myFrame.setVisible(true);
    }
}
