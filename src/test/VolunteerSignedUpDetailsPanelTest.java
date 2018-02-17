package test;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import model.Job;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui.VolunteerSignedUpDetailsPanel;

public class VolunteerSignedUpDetailsPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static final String USERS_DATA_FILE = "UsersInformations.ser";
    private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
    
    private static Volunteer myVolunteer;
    private static VolunteerSignedUpDetailsPanel myPanel;
    
    
    public void setup() {
    	
    }
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(USERS_DATA_FILE);
		myJobs.loadJobMap(JOBS_DATA_FILE);
		myVolunteer = (Volunteer) myUsers.getUser("hasnah");
		List<Job> eligibleJobs = myJobs.getEligibleJobs(myVolunteer);
		myPanel = new VolunteerSignedUpDetailsPanel(eligibleJobs);
		myFrame.add(myPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setResizable(false);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    	
    }
}
