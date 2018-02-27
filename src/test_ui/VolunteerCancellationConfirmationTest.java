/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package test_ui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import model.Job;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerCancellationConfirmationPanel;

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
    private static Volunteer myVolunteer;
    private static VolunteerCancellationConfirmationPanel myPanel;
    
    
    public static void main(String[] args) {
    	myFrame = new JFrame("Job Cancellation Confirmation");
    	myJobs = new JobMap();
    	myUsers = new UserMap();
    	myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
    	myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
    	myVolunteer = (Volunteer) myUsers.getUser("hasnah");
    	List<Job> jobs = myJobs.getEligibleJobs(myVolunteer);
    	myJob = (Job) jobs.get(0);
    	myPanel = new VolunteerCancellationConfirmationPanel(myJob);
    	
    	myFrame.add(myPanel.getPanel(), BorderLayout.CENTER);
    	myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setResizable(false);
    	myFrame.setLocationRelativeTo(null);
    	myFrame.setVisible(true);
    }
}
