package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui.VolunteerSignUpPanel;
import ui.VolunteerViewAllUpCommingJobPanel;

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
    private static final String USERS_DATA_FILE = "UsersInformations.ser";
    private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
    
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize(); 
    
    private static Volunteer myVolunteer;
    
    private static VolunteerViewAllUpCommingJobPanel myUpCommingJobPanelPanel;
    
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(USERS_DATA_FILE);
		myJobs.loadJobMap(JOBS_DATA_FILE);
		
		myVolunteer = (Volunteer) myUsers.getUser("hasnah");
		myUpCommingJobPanelPanel = new VolunteerViewAllUpCommingJobPanel(
				myJobs.getEligibleJobs(myVolunteer));
		
		
		
		myFrame.add(myUpCommingJobPanelPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2, 
                            SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        myFrame.setVisible(true);
    	
    }
}
