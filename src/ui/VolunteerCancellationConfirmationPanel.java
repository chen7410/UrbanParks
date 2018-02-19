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
 * This panel is used to confirm a volunteer that they have cancelled
 * a job successfully. It will notify observers when a button is pressed.
 * 
 * @author Hasnah Said
 * @version February 18, 2018
 */
public class VolunteerCancellationConfirmationPanel {

	private JPanel myVolunteerCancellationPanel;
	private Job myJob;
	
	public VolunteerCancellationConfirmationPanel(final Job theJob) {
		myVolunteerCancellationPanel = new JPanel(new BorderLayout());
		myJob = theJob;
		setUp();
	}
	
	public void setUp() {
		myVolunteerCancellationPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myVolunteerCancellationPanel.setBackground(Color.WHITE);
		createButtons();
		createCancellationConfirmation();
	}
	
	public void createButtons() {
		JPanel buttonPanel = new JPanel (new FlowLayout(FlowLayout.CENTER, 
				GUIFrame.BUTTON_GAP_WIDTH, GUIFrame.BUTTON_GAP_HEIGHT));
		JButton viewUpcomingButton = new JButton (new AbstractAction("Upcoming Jobs") {

			/**
			 * Serial version UID for object Serialization.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		viewUpcomingButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		buttonPanel.add(viewUpcomingButton);
		
		JButton homeButton = new JButton(new AbstractAction("Home") {

			/**
			 * Serial version UID for object Serialization.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		homeButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		buttonPanel.add(homeButton);
		
		buttonPanel.setBackground(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BGCOLOR );
		myVolunteerCancellationPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void createCancellationConfirmation() {
		JPanel jobCancellationDetails = new JPanel(new GridLayout(0, 1));
		jobCancellationDetails.setBackground(Color.WHITE);
		
		JLabel confirmationLabel = new JLabel("You have canceled the following job:");
		confirmationLabel.setFont(new Font(null, Font.BOLD, 30));
		jobCancellationDetails.add(confirmationLabel);
		
		for (String detail: myJob.getJobDetailsList()) {
			String formattedDetail = "<html><span style=\"font-weight:bold;font-size:15px;\">" 
					+ detail.split(":")[0] + ": </span>"+ detail.split(":")[1] + "</html>";	
			JLabel detailLabel = new JLabel(formattedDetail, JLabel.LEFT);
			detailLabel.setPreferredSize(GUIFrame.JLABEL_LONG_TEXT);
			jobCancellationDetails.add(detailLabel);
		}
		
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.add(jobCancellationDetails, BorderLayout.EAST);
		eastPanel.setBackground(Color.WHITE);

		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(eastPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.WHITE);
		
		myVolunteerCancellationPanel.add(northPanel, BorderLayout.CENTER);
	}
	
	public JPanel getCancellationPanel() {
		return myVolunteerCancellationPanel;
	}
	
}
