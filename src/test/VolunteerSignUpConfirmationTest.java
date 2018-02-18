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
import ui.VolunteerSignUpConfirmationPanel;

/**
 * VolunteerSignUpConfirmation Test.
 * 
 * @author Hasnah Said
 * @version February 17, 2017
 * 
 */
public class VolunteerSignUpConfirmationTest {
	
	
	private static JFrame myFrame;
	private static UserMap myUsers;
	private static JobMap myJobs;
    private static Job myJob;
	private static final String USERS_DATA_FILE = "UsersInformations.ser";
    private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
    private static Volunteer myVolunteer;
    private static VolunteerSignUpConfirmationPanel myPanel;

    
    public static void main(String[] args) {
    	
    	myFrame = new JFrame("Job Sign-up Confirmation");
    	myJobs = new JobMap();
    	myUsers = new UserMap();
    	myUsers.loadUserMap(USERS_DATA_FILE);
    	myJobs.loadJobMap(JOBS_DATA_FILE);
    	myVolunteer = (Volunteer) myUsers.getUser("hasnah");
    	List<Job> jobs = myJobs.getEligibleJobs(myVolunteer);
    	myJob = (Job) jobs.get(0);
    	myPanel = new VolunteerSignUpConfirmationPanel(myJob);
    	
    	myFrame.add(myPanel.getConfirmationPanel(), BorderLayout.CENTER);
    	myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setResizable(false);
    	myFrame.setLocationRelativeTo(null);
    	myFrame.setVisible(true);
    }

}
