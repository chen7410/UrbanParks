/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_park_manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Job;
import model.ParkManager;
import ui.GUI;

/**
 * A JPanal for Park Manager to submit a job and enter job detail.
 * 
 * @author  Group 7
 * @version February 28, 2018
 */
public class ParkManagerSubmitJobPanel {
	private final JPanel myPanel;
	private JPanel myCenterPanel;
	private final int textFieldSize = 45;
	private final int textAreaWidth = 20;
	private final int textAreaHeight =50;
	
	private final JTextField myParkNameTf;
	private final JTextField myLocationTf;
	private final JTextField myStartDateTf;
	private final JTextField myEndDateTf;
	private JTextArea myJobDescriptionTa;
	
	private final ParkManager myParkManager;
	private final DateTimeFormatter myDateFormatter;
	private Job mySubmittingJob;
	
	public ParkManagerSubmitJobPanel (final ParkManager theParkManager) {
		myPanel = new JPanel(new BorderLayout());
		myCenterPanel = new JPanel();
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myParkNameTf = new JTextField(textFieldSize);
		myLocationTf = new JTextField(textFieldSize);
		myStartDateTf = new JTextField(textFieldSize);
		myEndDateTf = new JTextField(textFieldSize);
		myJobDescriptionTa = new JTextArea(textAreaWidth, textAreaHeight);
		myDateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
		myParkManager = theParkManager;
		mySubmittingJob = null;
		setup();
	}
	
	/**
	 * Set up this ParkManagerSubmitJobPanel.
	 */
	private void setup() {
		JLabel topLabel= new JLabel("Please fill in the job detail");
		topLabel.setSize(GUI.JLABEL_SHORT_TEXT);
		JPanel topLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topLabelPanel.add(topLabel);
		
		JLabel parkName = new JLabel("Park name: ");
		JLabel location = new JLabel("Location: ");
		JLabel startDate = new JLabel("Start date: ");
		JLabel endDate = new JLabel("End date: ");
		JLabel jobDescription = new JLabel("Job description: ");
		
		//pairs label and text field
		JPanel parkNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		parkNamePanel.add(parkName);
		parkNamePanel.add(myParkNameTf);
		
		JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		locationPanel.add(location);
		locationPanel.add(myLocationTf);
		
		JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		startDatePanel.add(startDate);
		startDatePanel.add(myStartDateTf);
		
		JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		endDatePanel.add(endDate);
		endDatePanel.add(myEndDateTf);
		
		JPanel jobDescriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jobDescriptionPanel.add(jobDescription);
		jobDescriptionPanel.add(myJobDescriptionTa);
		
		BoxLayout box = new BoxLayout(myCenterPanel, BoxLayout.PAGE_AXIS);
		myCenterPanel.setLayout(box);
		myCenterPanel.add(startDatePanel);
		myCenterPanel.add(endDatePanel);
		myCenterPanel.add(parkNamePanel);
		myCenterPanel.add(locationPanel);
		myCenterPanel.add(jobDescriptionPanel);
		

		//Button
		JButton nextButton = makeNextButton();
		JButton backButton = makeBackButton();
		nextButton.setSize(GUI.BUTTON_SIZE);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//myCenterPanel.add(buttonPanel);
		
		myPanel.add(topLabelPanel, BorderLayout.NORTH);
		myPanel.add(myCenterPanel, BorderLayout.CENTER);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
//		myPanel.add(labelTextFielsPanel, BorderLayout.CENTER);		
	}
	
	private JButton makeBackButton() {
		JButton back = new JButton(new AbstractAction("Back"){

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		back.setPreferredSize(new Dimension(GUI.BUTTON_SIZE));
		return back;
	}

	private JButton makeNextButton() {
		JButton btn = new JButton(new AbstractAction("Next") {

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				if (myStartDateTf.getText().isEmpty() 
						|| myEndDateTf.getText().isEmpty() 
						|| myParkNameTf.getText().isEmpty()
						|| myLocationTf.getText().isEmpty()
						|| myJobDescriptionTa.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please fill in all the needed information!",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
				
				Job job = createCandidateJob(myStartDateTf.getText(),
						myEndDateTf.getText(), myParkNameTf.getText(),
						myParkManager, myLocationTf.getText(),
						myJobDescriptionTa.getText());
				
				if (!job.isJobWithinMaxDays()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Job length cannot be more than "
							+ Job.MAX_JOB_LENGTH + " days",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (!job.isJobEndsWithinMaxDays()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"You cannot submit a job that is more than "
							+ Job.MAX_END_DAY + " in the future",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				} else {
					mySubmittingJob = job;
					JOptionPane.showMessageDialog(new JFrame(),
							"Job created! Temporary popup message",
							"", JOptionPane.OK_OPTION);
				}
				
			}
			
		});
		btn.setPreferredSize(new Dimension(GUI.BUTTON_SIZE));
		return btn;
	}
	
	private Job createCandidateJob(final String theStartDate, 
			final String theEndDate, final String theParkName,
			final ParkManager thePM, final String theLocation, 
			final String theDescription) {
		
		Job job = new Job(LocalDate.parse(theStartDate, myDateFormatter),
				LocalDate.parse(theEndDate, myDateFormatter),
				theParkName, thePM, theLocation, theDescription);
		return job;
		
	}
	
	/**
	 * @return a ParkManagerSubmitJobPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
	
	
}
