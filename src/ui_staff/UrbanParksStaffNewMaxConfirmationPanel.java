package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Job;
import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

public class UrbanParksStaffNewMaxConfirmationPanel extends Observable {

	private JPanel myPanel;
	private JobMap myJobs;

	
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
		JPanel confirmationPanel = new JPanel();
		confirmationPanel.setBackground(Color.WHITE);
		
		JLabel confirmationLabel = new JLabel(
				" You Have Successfully Changed the Number of Maxiumum Jobs.");
		confirmationLabel.setFont(new Font(null, Font.BOLD, 20));
		confirmationPanel.add(confirmationLabel);
		
		JLabel newNumLabel = new JLabel("The new number of pending jobs is " + 
				myJobs.getMaxJobAmount());
		newNumLabel.setFont(new Font(null, Font.BOLD, 20));
		confirmationPanel.add(newNumLabel);
		
		
		myPanel.add(confirmationPanel, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
}
