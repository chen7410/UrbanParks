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
 * @author Group 7
 * @version March 5, 2018
 */
public class ParkManagerHomePanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		UserMap users  = new UserMap();
		JobMap jobMap = new JobMap();
		users.loadUserMap(UserMap.USERS_DATA_FILE);
		jobMap.loadJobMap(JobMap.JOBS_DATA_FILE);
		
		ParkManager currentParkManager = (ParkManager) users.getUser("matthew");
		ParkManagerHomePanel currentPanel = new ParkManagerHomePanel(currentParkManager.getSortedJobList(jobMap), jobMap); 
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}