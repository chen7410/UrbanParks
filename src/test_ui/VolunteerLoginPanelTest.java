/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.User;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerLoginPanel;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class VolunteerLoginPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		UserMap users = new UserMap();
		Volunteer anyVolunteer = new Volunteer("matthew", "Minqing", "Chen");
		users.loadUserMap(User.USERS_DATA_FILE);
		VolunteerLoginPanel currentPanel = new VolunteerLoginPanel(users.getAllUserNames());
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}