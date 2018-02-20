/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Job;
import ui.GUIFrame;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class VolunteerHomePanel {
	
	private JPanel myPanel;
	private List<Job> myJobs;
	
	public VolunteerHomePanel(final List<Job> theJobs) {
		myJobs = theJobs;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	private void setup() {
		JButton logOut = new JButton(new AbstractAction("Log Out") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				// TODO We need to display the LoginPanel

			}
			
		});
		JPanel logOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		logOutPanel.add(logOut);
		
		JLabel upcommingJobs = new JLabel("Your upcomming Job");
		
		
		myPanel.add(logOutPanel, BorderLayout.NORTH);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}