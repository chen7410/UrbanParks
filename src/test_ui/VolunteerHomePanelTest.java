/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test_ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.JobMap;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerHomePanel;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class VolunteerHomePanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		UserMap users = new UserMap();
		JobMap jobs = new JobMap();
		users.loadUserMap(UserMap.USERS_DATA_FILE);
		jobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		Volunteer currentVolunteer = (Volunteer) users.getUser("tuan");
		VolunteerHomePanel currentPanel = new VolunteerHomePanel(currentVolunteer.getSortedJobList(jobs));
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}