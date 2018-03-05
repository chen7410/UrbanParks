/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel that will be the home panel for staff. It 
 * will show the three options that a staff can perform. The 
 * options are search for jobs, change pending jobs and log out.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class UrbanParksStaffHomePanel extends Observable {
	
	private JPanel myPanel;
	
	/**
	 * Creates a new home panel for Urban Parks staff.
	 */
	public UrbanParksStaffHomePanel() {
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}
	
	private void setup() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		buttonsPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(buttonsPanel,
													BoxLayout.Y_AXIS);
		
		buttonsPanel.setLayout(buttonPanelLayout);
		
		
		// Buttons to go on the buttonPanel.
		JButton searchJobsButton = new JButton(
								new AbstractAction("Search Jobs") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("search jobs", 0));
			}
		});
		
		searchJobsButton.setPreferredSize(GUI.LONG_BUTTON_SIZE);
		searchJobsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel searchJobsButtonPanel = new JPanel();
		searchJobsButtonPanel.add(searchJobsButton);
		searchJobsButtonPanel.setBackground(
										GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		
		JButton pendingJobsSizeButton = new JButton(
				new AbstractAction("Change Max Pending Jobs") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal(
											"pending jobs size", 0));
			}
		});
		pendingJobsSizeButton.setPreferredSize(GUI.LONG_BUTTON_SIZE);
		pendingJobsSizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel pendingJobsSizeButtonPanel = new JPanel();
		pendingJobsSizeButtonPanel.add(pendingJobsSizeButton);
		pendingJobsSizeButtonPanel.setBackground(
										GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		
		JButton logOut = new JButton(new AbstractAction("Log Out") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("logout", 0));
			}
		});
		logOut.setPreferredSize(GUI.LONG_BUTTON_SIZE);
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel logOutButtonPanel = new JPanel();
		logOutButtonPanel.add(logOut);
		logOutButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel ivisiblePanel = new JPanel();
		ivisiblePanel.setPreferredSize(GUI.INVISBLE_PANEL_AREA);
		ivisiblePanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		// Adding the buttons onto the buttonsPanel.
		buttonsPanel.add(Box.createRigidArea(
									GUI.ABOVE_BUTTON_PADDING_AREA));
		
		buttonsPanel.add(searchJobsButtonPanel);
		buttonsPanel.add(Box.createRigidArea(
								GUI.BETWEEN_BUTTONS_PADDING_AREA));
		
		buttonsPanel.add(pendingJobsSizeButtonPanel);
		buttonsPanel.add(Box.createRigidArea(
								GUI.BETWEEN_BUTTONS_PADDING_AREA));
		
		buttonsPanel.add(logOutButtonPanel);
		buttonsPanel.add(ivisiblePanel);
		
		myPanel.add(buttonsPanel, BorderLayout.CENTER);
	}
	
	/**
	 * @return an instance of UrbanParksStaffHomePanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}