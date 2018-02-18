/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Job;

/**
 * This panel is used to confirm a volunteer that they have signed up for
 * a job successfully. It will notify observers when a button is pressed.
 * 
 * @author Hasnah Said
 * @version February 18, 2018
 */

public class VolunteerSignUpConfirmationPanel {
	
	private JPanel myVolunteerConfirmationPanel;
	private Job myJob;
	
	public VolunteerSignUpConfirmationPanel(final Job theJob) {
		myVolunteerConfirmationPanel = new JPanel(new BorderLayout());
		myJob = theJob;
		setUp();
	}
	
	public void setUp() {
		myVolunteerConfirmationPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myVolunteerConfirmationPanel.setBackground(Color.WHITE);
		createButtons();
		createSignUpConfirmation();
	}
	
	public void createButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 
				GUIFrame.BUTTON_GAP_WIDTH, GUIFrame.BUTTON_GAP_HEIGHT));
		
		JButton viewUpcomingJobsButton = new JButton (
				new AbstractAction("Upcoming Jobs") {

					/**
					 * Serial version UID for object Serialization.
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent theEvent) {
						// TODO Auto-generated method stub	
					}
				});
		viewUpcomingJobsButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		buttonPanel.add(viewUpcomingJobsButton);

		JButton homeButton = new JButton(new AbstractAction("Home") {

			/**
			 * Serial version UID for object Serialization.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				// TODO Auto-generated method stub
			}
		});
		homeButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		buttonPanel.add(homeButton);
		
		buttonPanel.setBackground(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BGCOLOR );
		myVolunteerConfirmationPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void createSignUpConfirmation() {
		JPanel jobConfirmationDetails = new JPanel(new GridLayout(0, 1));
		jobConfirmationDetails.setBackground(Color.WHITE);
		
		JLabel confirmationLabel = new JLabel("You have signed up for the following job:");
		confirmationLabel.setFont(new Font(null, Font.BOLD, 30));
		jobConfirmationDetails.add(confirmationLabel);
		
		for (String detail: myJob.getJobDetailsList()) {
			String formattedDetail = "<html><span style=\"font-weight:bold;font-size:15px;\">" 
					+ detail.split(":")[0] + ": </span>"+ detail.split(":")[1] + "</html>";
			JLabel detailLabel = new JLabel(formattedDetail, JLabel.LEFT);
			detailLabel.setPreferredSize(GUIFrame.JLABEL_LONG_TEXT);
			jobConfirmationDetails.add(detailLabel);
		}
		
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.add(jobConfirmationDetails, BorderLayout.EAST);
		eastPanel.setBackground(Color.WHITE);

		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(eastPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.WHITE);
		
		myVolunteerConfirmationPanel.add(northPanel, BorderLayout.CENTER);
	}
	
	
	public JPanel getConfirmationPanel() {
		return myVolunteerConfirmationPanel;
	}
	
}