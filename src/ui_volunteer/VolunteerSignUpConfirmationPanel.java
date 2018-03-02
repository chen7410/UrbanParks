/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * This panel is used to confirm a volunteer that they have signed up for a job
 * successfully. It will notify observers when a button is pressed.
 * 
 * @author Group 7
 * @version  March 5, 2018
 */

public class VolunteerSignUpConfirmationPanel extends Observable {

	private JPanel myPanel;
	private Job myJob;
	private List<Job> myJobs;
	
	/**
	 * Creates a new panel that will confirm signing up for
	 * a job to a volunteer.
	 * 
	 * @param theJob              that is being signed up for.
	 * @param theVolunteerJobList a list of all the volunteer's jobs.
	 */
	public VolunteerSignUpConfirmationPanel(final Job theJob,
			final List<Job> theVolunteerJobList) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theVolunteerJobList;
		myJob = theJob;
		myJobs = theVolunteerJobList;
		setUp();
	}

	private void setUp() {
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		createButtons();
		createSignUpConfirmation();
	}


	private void createButtons() {
		JPanel buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		
		JButton homeButton = new JButton(new AbstractAction("Home") {
			/**
			 * Serial version UID for object Serialization.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("home", 0));
			}
		});
		homeButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonPanel.add(homeButton);
		
		JButton signupAgainButton = new JButton(new AbstractAction("Sign up for another job") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("signup", 0));
			}});
		signupAgainButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonPanel.add(signupAgainButton);
		
		buttonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	
	private void createSignUpConfirmation() {
		
		JPanel jobConfirmationDetails = new JPanel(new GridLayout(0, 1));
		jobConfirmationDetails.setBackground(Color.WHITE);
		
		JLabel confirmationLabel = new JLabel(
				" You have successfully signed up for:");
		confirmationLabel.setFont(new Font(null, Font.BOLD, 20));
		jobConfirmationDetails.add(confirmationLabel);
		
		String detail = "      " + myJob.getJobSummary();
		JLabel jobDetailSummary = new JLabel(detail, JLabel.LEFT);
		jobDetailSummary.setFont(new Font(null, Font.PLAIN, 13));
		jobDetailSummary.setPreferredSize(GUI.JLABEL_LONG_TEXT);
		jobConfirmationDetails.add(jobDetailSummary);
	
		JPanel upcomingPanel = new JPanel();
		upcomingPanel.setLayout(new BoxLayout(upcomingPanel, BoxLayout.Y_AXIS));
		upcomingPanel.setBackground(Color.WHITE);
		upcomingPanel.setBorder(GUI.VOLUNTEER_SIGNUP_PANEL_BORDER);
		
		for (Job job: myJobs) {
			detail = "  " + job.getJobSummary();
			jobDetailSummary = new JLabel(detail, JLabel.LEFT);
			jobDetailSummary.setFont(new Font(null, Font.PLAIN, 13));
			jobDetailSummary.setPreferredSize(GUI.JLABEL_SHORT_TEXT);
			upcomingPanel.add(jobDetailSummary);
		}
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JScrollPane upcomingScrollPane = new JScrollPane(upcomingPanel);
		upcomingScrollPane.setBorder(BorderFactory.createTitledBorder(
				loweredetched, "Here are all your upcoming jobs: "));

		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.add(jobConfirmationDetails, BorderLayout.CENTER);
		eastPanel.setBackground(Color.WHITE);
	
		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(eastPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.WHITE);
	
		myPanel.add(northPanel, BorderLayout.NORTH);
		myPanel.add(upcomingScrollPane, BorderLayout.CENTER);
	}

	/**
	 * @return an instance of VolunteerSignUpConfirmationPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}
