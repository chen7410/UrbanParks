/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel that will allow Urban Parks staff that to 
 * change the maximum number of pending jobs that are in 
 * the system. This panel will show the current number of
 * jobs and will have a text field where the new number of 
 * job can be entered. 
 * 
 * @author  Group 7
 * @version March 5, 2018
 */
public class UrbanParksStaffNewMaxConfirmationPanel extends Observable {

	private JPanel myPanel;
	private JobMap myJobs;

	/**
	 * Creates a panel that will confirm the change of the maximum
	 * number of pending jobs a system can have at any given time.
	 * 
	 * @param theJobs a JobMap that has all the jobs in the system.
	 */
	public UrbanParksStaffNewMaxConfirmationPanel(final JobMap theJobs) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theJobs;
		setUp();
	}
	
	private void setUp() {
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		createButtons();
		createUnubmitConfirmation();
	}

	private void createButtons() {
		JPanel buttonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		JButton homeButton = new JButton(new AbstractAction("Home") {

			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("home", 0));
			}
		});
		homeButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonPanel.add(homeButton);
		buttonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void createUnubmitConfirmation() {
		
		JPanel successfulMessagePanel = new JPanel();
		successfulMessagePanel.setBackground(Color.WHITE);
		successfulMessagePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
		
		successfulMessagePanel.add(new JLabel("<html><font size = 5><strong>You Have Successfully "
				+ "Changed the Number of Maxiumum Jobs.<br> </strong><hr></font></html>"), gbc);
		
		
		JPanel newNumberPanel = new JPanel();
		newNumberPanel.setLayout(new GridBagLayout());
		newNumberPanel.setBackground(Color.WHITE);
		newNumberPanel.add(new JLabel("<html><font size = 5>The new number of maximum "
				+ "allowed jobs: " + myJobs.getMaxJobAmount() +"</font></html>"), gbc);
		JPanel labels = new JPanel(new GridBagLayout());
		labels.add(successfulMessagePanel, gbc);
		labels.add(newNumberPanel);
		labels.setBackground(Color.WHITE);

		myPanel.add(labels, BorderLayout.CENTER);
		
	}
	
	/**
	 * @return an instance of UrbanParksStaffNewMaxConfirmationPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
	
}
