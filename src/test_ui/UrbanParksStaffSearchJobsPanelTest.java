/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ui_staff.UrbanParksStaffSearchJobsPanel;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class UrbanParksStaffSearchJobsPanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		
		UrbanParksStaffSearchJobsPanel currentPanel = new UrbanParksStaffSearchJobsPanel(); 
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
