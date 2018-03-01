package test_ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.JobMap;
import model.ParkManager;
import model.UserMap;
import model.Volunteer;
import ui_park_manager.ParkManagerViewAllUpCommingJobPanel;
import ui_volunteer.VolunteerViewAllUpCommingJobPanel;

/**
 * Tests for VolunteerSignUpPanel.
 * 
 * @author Minqing Chen
 * @version February 17, 2018
 */
public class ParkManagerViewAllUpCommingJobPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static ParkManager myParkManager;
    
    private static ParkManagerViewAllUpCommingJobPanel myUpCommingJobPanel;
    
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		myParkManager = (ParkManager) myUsers.getUser("brook");
		myUpCommingJobPanel = new ParkManagerViewAllUpCommingJobPanel(
				myParkManager.getJobList(myJobs));
		
		//System.out.println(myVolunteer.getJobList(myJobs).size());
		
		myFrame.setTitle("Urban Parks - Park Manager - " + myParkManager.getLastName());
		myFrame.add(myUpCommingJobPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    	
    }
}