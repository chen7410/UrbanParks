package test_ui;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import model.Job;
import model.JobMap;
import model.User;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerSignUpDetailsPanel;

/**
 * Tests for VolunteerSignUpDetailsPanel.
 * 
 * @author Tuan Dinh
 * @version February 17, 2018
 */
public class VolunteerSignUpDetailsPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static Volunteer myVolunteer;
    private static VolunteerSignUpDetailsPanel myPanel;
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(User.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		myVolunteer = (Volunteer) myUsers.getUser("hasnah");
		List<Job> eligibleJobs = myJobs.getEligibleJobs(myVolunteer);
		myPanel = new VolunteerSignUpDetailsPanel(eligibleJobs.get(0));
		myFrame.add(myPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setResizable(false);
    	myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    	
    }
    
}
