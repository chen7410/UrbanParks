/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_park_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
 * A JPanel that will display a confirmation of the 
 * job that is being removed to the park manager. The
 * panel will have an option to return to home panel or 
 * to submit another job.
 * 
 * 
 * @author  Group 7
 * @version February 28, 2018
 */
public class ParkManagerUnsubmitConfirmationPanel extends Observable {
	
	private JPanel myPanel;
	private Job myJob;
	private List<Job> myJobs;
	
	
	/**
	 * Creates a new panel that confirms to the park manager
	 * the specified job that has been removed.
	 * 
	 * @param theJob                the job that is being removed.
	 * @param theParkManagerJobList the park manager's list of jobs.
	 */
	public ParkManagerUnsubmitConfirmationPanel(final Job theJob, 
			final List<Job> theParkManagerJobList) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theParkManagerJobList;
		myJob = theJob;
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
		
		
		JPanel jobConfirmationDetails = new JPanel(new GridLayout(0, 1));
		jobConfirmationDetails.setBackground(Color.WHITE);
		
		JLabel confirmationLabel = new JLabel(
				" You have successfully removed the follwoing job:");
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
	 * @return an instance of ParkManagerUnsubmitConfirmationPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}
