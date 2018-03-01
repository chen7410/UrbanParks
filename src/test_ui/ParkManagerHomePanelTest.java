/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.JobMap;
import model.ParkManager;
import model.UserMap;
import ui_park_manager.ParkManagerHomePanel;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class ParkManagerHomePanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		UserMap users  = new UserMap();
		JobMap jobs = new JobMap();
		users.loadUserMap(UserMap.USERS_DATA_FILE);
		jobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		ParkManager currentParkManager = (ParkManager) users.getUser("brook");
		ParkManagerHomePanel currentPanel = new ParkManagerHomePanel(currentParkManager.getJobList(jobs)); 
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}