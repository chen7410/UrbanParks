package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.JobMap;
import model.ParkManager;
import model.UserMap;
import ui_park_manager.ParkManagerViewAllUpCommingJobPanel;
import ui_staff.StaffViewJobPanel;

public class UrbanParksStaffViewJobPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static ParkManager myParkManager;
    
    private static StaffViewJobPanel myStaffViewJobPanel;
    
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		myParkManager = (ParkManager) myUsers.getUser("brook");
		myStaffViewJobPanel = new StaffViewJobPanel(
				myParkManager.getJobList(myJobs));
		
		//System.out.println(myVolunteer.getJobList(myJobs).size());
		
		myFrame.setTitle("Urban Parks - Staff - " + "Someone");
		myFrame.add(myStaffViewJobPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    	
    }
}
