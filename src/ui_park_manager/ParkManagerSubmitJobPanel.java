/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_park_manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.Job;
import model.ParkManager;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanal that will allow a park manager to fill out 
 * information about the new job they want to submit. The 
 * panel will have text fields for the start date, end date, 
 * park name, park location and job description. 
 * The panel will have two buttons at the bottom: one to return home and 
 * one is Next and it's to move on to the next step of adding a job.
 * 
 * @author  Group 7
 * @version February 28, 2018
 */
public class ParkManagerSubmitJobPanel extends Observable {
	private final JPanel myPanel;
	private JPanel myCenterPanel;
	private final int textFieldSize = 35;
	private final int textAreaWidth = 20;
	private final int textAreaHeight =42;
	
	private final JTextField myParkNameTf;
	private final JTextField myLocationTf;
	private final JTextField myStartDateTf;
	private final JTextField myEndDateTf;
	private JTextArea myJobDescriptionTa;
	
	private final ParkManager myParkManager;
	
	/**
	 * Creates a new panel that allows the specified the park manager
	 * to submit a new job.
	 * 
	 * @param theParkManager the park manager that is 
	 *                       submitting a new job.
	 */
	public ParkManagerSubmitJobPanel (final ParkManager theParkManager) {
		myPanel = new JPanel(new BorderLayout());
		myCenterPanel = new JPanel();
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myParkNameTf = new JTextField(textFieldSize);
		myLocationTf = new JTextField(textFieldSize);
		myStartDateTf = new JTextField(textFieldSize);
		myEndDateTf = new JTextField(textFieldSize);
		myJobDescriptionTa = new JTextArea(textAreaWidth, textAreaHeight);
		myParkManager = theParkManager;
		setup();
	}
	
	/**
	 * Set up this ParkManagerSubmitJobPanel.
	 */
	private void setup() {
		JLabel topLabel= new JLabel("Fill in the job detail");
		topLabel.setSize(GUI.JLABEL_SHORT_TEXT);
		JPanel topLabelPanel = new JPanel();
		topLabelPanel.add(topLabel);
		
		JLabel parkName = new JLabel(" Park name: ");
		JLabel location = new JLabel("     Location: ");
		JLabel startDate = new JLabel("  Start date: ");
		JLabel endDate = new JLabel("    End date: ");
		JLabel jobDescription = new JLabel("Description: ");
		
		JLabel startDateFormat = new JLabel("MM/DD/YY");
		JLabel endDateFormat = new JLabel("MM/DD/YY");
		JLabel invGap1 = new JLabel("                    ");
		JLabel invGap2 = new JLabel("                    ");
		
		//pairs label and text field
		JPanel parkNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		parkNamePanel.add(parkName);
		parkNamePanel.add(myParkNameTf);
		parkNamePanel.add(invGap1);
		
		JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		locationPanel.add(location);
		locationPanel.add(myLocationTf);
		locationPanel.add(invGap2);
		
		JPanel startDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		startDatePanel.add(startDate);
		startDatePanel.add(myStartDateTf);
		startDatePanel.add(startDateFormat);
		
		JPanel endDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		endDatePanel.add(endDate);
		endDatePanel.add(myEndDateTf);
		endDatePanel.add(endDateFormat);
		
		//text area
		JPanel jobDescriptionPanel = new JPanel(new FlowLayout());
		JScrollPane textAreaScrollPane = new JScrollPane(myJobDescriptionTa,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		jobDescriptionPanel.add(jobDescription);
		jobDescriptionPanel.add(textAreaScrollPane);
		
		BoxLayout box = new BoxLayout(myCenterPanel, BoxLayout.Y_AXIS);
		myCenterPanel.setLayout(box);
		myCenterPanel.add(startDatePanel);
		myCenterPanel.add(endDatePanel);
		myCenterPanel.add(parkNamePanel);
		myCenterPanel.add(locationPanel);
		myCenterPanel.add(jobDescription);
		myCenterPanel.add(textAreaScrollPane);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		myCenterPanel.setBorder(BorderFactory.createTitledBorder(
				loweredetched, "Submit a job"));
		

		//Button
		JButton nextButton = makeNextButton();
		JButton backButton = makeBackButton();
		nextButton.setSize(GUI.BUTTON_SIZE);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);
		buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		myPanel.add(topLabelPanel, BorderLayout.NORTH);
		myPanel.add(myCenterPanel, BorderLayout.CENTER);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private JButton makeBackButton() {
		JButton back = new JButton(new AbstractAction("Home"){

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("home", 0));
			}
			
		});
		back.setPreferredSize(new Dimension(GUI.BUTTON_SIZE));
		return back;
	}

	private JButton makeNextButton() {
		JButton btn = new JButton(new AbstractAction("Next") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				if (myStartDateTf.getText().isEmpty() 
						|| myEndDateTf.getText().isEmpty() 
						|| myParkNameTf.getText().isEmpty()
						|| myLocationTf.getText().isEmpty()
						|| myJobDescriptionTa.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please fill in all the information!",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
				else {
				LocalDate startDate = null;
				LocalDate endDate = null;
				//check format
				try {
					startDate = LocalDate.parse(myStartDateTf.getText(),
							GUI.DATE_FORMATTER);
					endDate = LocalDate.parse(myEndDateTf.getText(),
							GUI.DATE_FORMATTER);


			 
					//check job
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
					} else if (job.isPassed()) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Invalid date. The date"
											+ " has already passed.",
								"Invalid input", JOptionPane.ERROR_MESSAGE);
					} else if (startDate.isAfter(endDate)) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Start date cannot be after end date.",
								"Invalid input", JOptionPane.ERROR_MESSAGE);
					} 
					else {
						//send a job to observers
						setChanged();
						notifyObservers(new ButtonSignal("next", job));
					}
				} catch (final DateTimeParseException theException) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please enter a valid date format MM/DD/YY",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
				
			}}
		});
		btn.setPreferredSize(new Dimension(GUI.BUTTON_SIZE));
		return btn;
	}
	
	/**
	 * Create a candidate job without any checking.
	 * The candidate job must be no more than Job.MAX_END_DAY in future
	 * and no more than Job.MAX_JOB_LENGTH.
	 * @param theStartDate
	 * @param theEndDate
	 * @param theParkName
	 * @param thePM
	 * @param theLocation
	 * @param theDescription
	 * @return a candidate Job.
	 */
	private Job createCandidateJob(final String theStartDate, 
			final String theEndDate, final String theParkName,
			final ParkManager thePM, final String theLocation, 
			final String theDescription) {
		
		Job job = new Job(LocalDate.parse(theStartDate, GUI.DATE_FORMATTER),
				LocalDate.parse(theEndDate, GUI.DATE_FORMATTER),
				theParkName, thePM, theLocation, theDescription);
		return job;
	}
	
	/**
	 * @return an instance of ParkManagerSubmitJobPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
	
	
}
