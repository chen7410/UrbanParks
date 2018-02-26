package test_ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerViewAllUpCommingJobPanel;

/**
 * Tests for VolunteerSignUpPanel.
 * 
 * @author Minqing Chen
 * @version February 17, 2018
 */
public class VolunteerViewAllUpCommingJobPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static Volunteer myVolunteer;
    
    private static VolunteerViewAllUpCommingJobPanel myUpCommingJobPanelPanel;
    
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		myVolunteer = (Volunteer) myUsers.getUser("hasnah");
		myUpCommingJobPanelPanel = new VolunteerViewAllUpCommingJobPanel(
				myVolunteer.getJobList(myJobs));
		
		
		
		myFrame.add(myUpCommingJobPanelPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    	
    }
}
