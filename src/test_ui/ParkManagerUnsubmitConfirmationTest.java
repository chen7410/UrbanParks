package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.JobMap;
import model.ParkManager;
import model.UserMap;
import ui_park_manager.ParkManagerUnsubmitConfirmationPanel;

/**
 * Test for ParkMangaer Confirmation
 * @author Hasnah Said
 * @version March 1, 2018
 */

public class ParkManagerUnsubmitConfirmationTest {

	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static ParkManager myParkManager;
    
    private static ParkManagerUnsubmitConfirmationPanel myConfirmationPanel;
    
    public static void main (final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		myParkManager = (ParkManager) myUsers.getUser("brook");
		myConfirmationPanel = new ParkManagerUnsubmitConfirmationPanel(
				myParkManager.getSortedJobList(myJobs).get(0), myParkManager.getSortedJobList(myJobs));
    	
		myFrame.setTitle("Urban Parks - Park Manager - " + myParkManager.getLastName());
		myFrame.add(myConfirmationPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
    
}
