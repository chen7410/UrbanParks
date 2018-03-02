/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanal for Urban parks staff to view all jobs between certain dates.
 * 
 * @author  Group 7
 * @version February 28, 2018
 */

public class UrbanParksStaffViewJobsPanel extends Observable {
	private List<Job> myAllUpCommingJobs;
	private JPanel myPanel;
	private LocalDate myStartDate;
	private LocalDate myEndDate;
	private int mySelectedJobID;

	/**
	 * @param theAllJobs all the jobs in the system.
	 */
	public UrbanParksStaffViewJobsPanel(final List<Job> theJobList, 
			final LocalDate theStartDate, final LocalDate theEndDate) {
		myPanel = new JPanel(new BorderLayout());
		myAllUpCommingJobs = theJobList;
		myStartDate = theStartDate;
		myEndDate = theEndDate;
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}

	/**
	 * Set up this UrbanParksStaffViewJobsPanel.
	 */
	private void setup() {
		JButton homeButton = makeHomeButton();
		JButton jobDetailButton = makeViewJobDetailButton();
		
		JLabel topLabel = makeTopJlabel();
		JPanel topLabelPanel = new JPanel();
		topLabelPanel.add(topLabel);
		
		//radio button panel
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
		radioPanel.setBackground(Color.WHITE);
		radioPanel.setBorder(GUI.VOLUNTEER_SIGNUP_PANEL_BORDER);
		
		//radio button group
		ButtonGroup group = new ButtonGroup();
		int size = myAllUpCommingJobs.size();
		//int size = 0;
		for (int i = 0; i < size; i++) {

			//JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			//System.out.println(b.toString());
			JRadioButton b = makeRadioButton(myAllUpCommingJobs.get(i));
			b.setBackground(Color.WHITE);
			group.add(b);
			radioPanel.add(b);
			radioPanel.add(Box.createRigidArea(GUI.RADIO_BUTTNON_RIGID_AREA));
			//set mySelectedJobID to the first job ID
			if (i == 0) {
				b.setSelected(true);
				mySelectedJobID = myAllUpCommingJobs.get(i).getJobID();
			}
		}
		
		//button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		buttonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		buttonPanel.add(homeButton);
		buttonPanel.add(jobDetailButton);

		//radio button scroll pane
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		JScrollPane radioScrollPane = new JScrollPane(radioPanel);
		radioScrollPane.setBorder(BorderFactory.createTitledBorder(
				loweredetched, "Select a job"));

		myPanel.add(topLabelPanel, BorderLayout.NORTH);
		
		if (size != 0) {
			myPanel.add(radioScrollPane, BorderLayout.CENTER);
		} else {
			radioPanel.add(new JLabel("No jobs between: " + 
					myStartDate.format(GUI.DATE_FORMATTER) + 
					" - " + myEndDate.format(GUI.DATE_FORMATTER)));
			myPanel.add(radioPanel, BorderLayout.CENTER);
		}
		
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Set up top label.
	 * @return return a label.
	 */
	private JLabel makeTopJlabel() {
		JLabel topLabel = new JLabel("Between: " + myStartDate + " - " + myEndDate);
		topLabel.setSize(GUI.JLABEL_SHORT_TEXT);
		return topLabel;
	}
	
	/**
	 * Set up Home button.
	 * Home button will fire a button signal which contains
	 * the name of this button and the selected job id the 
	 * and notify other observers.
	 * 
	 * @return the Home button.
	 */
	private JButton makeHomeButton() {
		JButton homeButton = new JButton(new AbstractAction("Home") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("Home", mySelectedJobID));
			}
			
		});
		homeButton.setPreferredSize(GUI.BUTTON_SIZE);
		return homeButton;
	}
	
	/**
	 * Set up the View job detail button.
	 * View job detail button will fire a button signal which contains 
	 * the selected job id and the name of this button and notify other observers.
	 * 
	 * @return the view job detail button.
	 */
	private JButton makeViewJobDetailButton() {
		JButton jobDetailButton = new JButton(new AbstractAction("View Job Details") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("view job details", mySelectedJobID));
			}
		});
		jobDetailButton.setPreferredSize(GUI.BUTTON_SIZE);
		if(myAllUpCommingJobs.isEmpty()) {
			jobDetailButton.setEnabled(false);
		}
		return jobDetailButton;
	}

	/**
	 * Set up a radio button.
	 * A radio button will change mySelectedJobID to the 
	 * current selected job' ID.
	 * 
	 * @param theEligibleJob 
	 * @return a radio button.
	 */
	private JRadioButton makeRadioButton(final Job theEligibleJob) {
		JRadioButton button = new JRadioButton(
				new AbstractAction(theEligibleJob.getJobSummary()) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				mySelectedJobID = theEligibleJob.getJobID();
				System.out.println("Radio button Selected Job ID " + theEligibleJob.getJobID());
			}
		});
		return button;
	}

	/**
	 * @return a UrbanParksStaffViewJobPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}
