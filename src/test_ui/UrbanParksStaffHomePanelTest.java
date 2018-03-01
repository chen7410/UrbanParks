/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package test_ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ui_staff.UrbanParksStaffHomePanel;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class UrbanParksStaffHomePanelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("UrbanParks");
		
		UrbanParksStaffHomePanel currentPanel = new UrbanParksStaffHomePanel(); 
		
		frame.add(currentPanel.getPanel(), BorderLayout.CENTER);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}