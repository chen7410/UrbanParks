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
 * @author Hasnah Said
 * @version February 18, 2018
 */

public class VolunteerSignUpConfirmationPanel extends Observable {

	private JPanel myPanel;
	private Job myJob;
	private List<Job> myJobs;

	public VolunteerSignUpConfirmationPanel(final Job theJob,
			final List<Job> theVolunteerJobList) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theVolunteerJobList;
		myJob = theJob;
		myJobs = theVolunteerJobList;
		setUp();
	}

	public void setUp() {
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		createButtons();
		createSignUpConfirmation();
	}

	/**
	 * 
	 */
	public void createButtons() {
		JPanel buttonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		
		
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

			/**
			 * Default serial number.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("signup", 0));
			}});
		signupAgainButton.setPreferredSize(new Dimension(170, 50));
		buttonPanel.add(signupAgainButton);
		
		//add button panel to myPanel
		buttonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	
	/**
	 * 
	 */
	public void createSignUpConfirmation() {
		JPanel jobConfirmationDetails = new JPanel(new GridLayout(0, 1));
		jobConfirmationDetails.setBackground(Color.WHITE);
		
		//confirmaiton message
		JLabel confirmationLabel = new JLabel(
				" You have successfully signed up for:");
		confirmationLabel.setFont(new Font(null, Font.BOLD, 30));
		jobConfirmationDetails.add(confirmationLabel);
		
		//display the new sign up job
		String detail = "    • " + myJob.getJobSummary();
		JLabel jobDetailSummary = new JLabel(detail, JLabel.LEFT);
		jobDetailSummary.setFont(new Font(null, Font.PLAIN, 15));
		jobDetailSummary.setPreferredSize(GUI.JLABEL_LONG_TEXT);
		jobConfirmationDetails.add(jobDetailSummary);

		JPanel jobScrollPanel = new JPanel(new GridLayout(0, 1));
		jobScrollPanel.setBackground(Color.WHITE);
		for (Job job: myJobs) {
			detail = "• " + job.getJobSummary();
			
			JLabel jobLabel = new JLabel(detail, JLabel.LEFT);
			jobLabel.setFont(new Font(null, Font.PLAIN, 15));
			jobLabel.setPreferredSize(GUI.JLABEL_LONG_TEXT);
			jobScrollPanel.add(jobLabel);
		}

		Border lower = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JScrollPane jobScrollPane = new JScrollPane(jobScrollPanel);
		jobScrollPane.setBackground(Color.WHITE);
		jobScrollPane.setBorder(BorderFactory.createTitledBorder(
				lower, "Here are your upcoming jobs: "));

		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.add(jobConfirmationDetails, BorderLayout.CENTER);
		eastPanel.add(jobScrollPane, BorderLayout.SOUTH);
		eastPanel.setBackground(Color.WHITE);

		JPanel northPanel = new JPanel(new BorderLayout());
		northPanel.add(eastPanel, BorderLayout.NORTH);
		northPanel.setBackground(Color.WHITE);

		myPanel.add(northPanel, BorderLayout.CENTER);
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}
