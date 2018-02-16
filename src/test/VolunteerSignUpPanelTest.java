package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Job;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui.VolunteerSignUpPanel;

/**
 * Tests for VolunteerSignUpPanel.
 * 
 * @author Minqing Chen
 * @version February 14, 2018
 */
public class VolunteerSignUpPanelTest {
	/** The main window. */
    private static JFrame myFrame;
    
    private static UserMap myUsers;
    private static JobMap myJobs;
    private static final String USERS_DATA_FILE = "UsersInformations.ser";
    private static final String JOBS_DATA_FILE = "UpcomingJobs.ser";
    
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize(); 
    
    private static Volunteer myVolunteer;
    
    private static VolunteerSignUpPanel myVolunteerSignUpPanel;
    
    
    public void setup() {
    	
    }
    
    public static void main(final String[] theArgs) {
    	myFrame = new JFrame("UrbanParks");
    	
    	myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(USERS_DATA_FILE);
		myJobs.loadJobMap(JOBS_DATA_FILE);
		
		myVolunteer = new Volunteer("matt", "Matthew", "Chen");
		System.out.println(myVolunteer.getJobList().size());
		//System.out.println(myJobs.getSortedJobsArray()[0].toString());
		myVolunteerSignUpPanel = new VolunteerSignUpPanel(myVolunteer, myJobs.getEligibleJobs(myVolunteer));
		
		
		//System.out.println(myVolunteerSignUpPanel.getComponentCount());
		
		myFrame.add(myVolunteerSignUpPanel.getPanel(), BorderLayout.CENTER);
		myFrame.pack();
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocation(SCREEN_SIZE.width / 2 - myFrame.getWidth() / 2, 
                            SCREEN_SIZE.height / 2 - myFrame.getHeight() / 2);
        myFrame.setVisible(true);
    	
    }
    
}
