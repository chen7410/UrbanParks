/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_park_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import model.Job;
import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel thats will create a park manager home panel that will
 * show all the park manager's upcoming jobs on the left, and 
 * a list of options on the right. The options are: submit a 
 * new job, view all upcoming jobs and log out.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ParkManagerHomePanel extends Observable {
	
	private JPanel myPanel;
	private JobMap myJobMap;
	private int mySelectedJobID;
	private List<Job> myAllUpcomingJobs;
	private Dimension buttonSize;
	
    /**
     * Creates a new park manager home panel with a list of of jobs that belong
     * to the park manager and options to: submit a job, view all upcoming jobs
     * and log out.  
     *
     * @param theJobList a list of all the jobs that belongs to 
     * 					 a certain park manager. 
     */
	public ParkManagerHomePanel(final List<Job> theJobList, final JobMap theJobMap) {
		myAllUpcomingJobs = theJobList;
		myJobMap = theJobMap;
		myPanel = new JPanel(new BorderLayout());
		buttonSize = new Dimension(180, 40);
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	/**
	 * Setting up this ParkManagerHomePanel.
	 */
	private void setup() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		// Panel on the left side of the overall home panel.		
		JPanel upcomingJobsPanel = new JPanel(new BorderLayout());
		upcomingJobsPanel.setBackground(Color.WHITE);
		upcomingJobsPanel.setBorder(BorderFactory.createTitledBorder("Your Upcoming Jobs"));
		
		// Radio buttons panel
		JPanel radioButtonsPanel = new JPanel();
		radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.Y_AXIS));
		radioButtonsPanel.setBackground(Color.WHITE);
		radioButtonsPanel.setBorder(BorderFactory.createEmptyBorder());
		
		
		int upcomingJobSize = myAllUpcomingJobs.size();
		
		if (upcomingJobSize == 0) {
			/*
			 * This is for when the volunteer does not have any jobs
			 * signed up for.
			 */
			JLabel noCurrentJobsAvailableLabel = new JLabel("You do not have any upcoming jobs.", SwingConstants.CENTER);
			noCurrentJobsAvailableLabel.setFont(new Font(noCurrentJobsAvailableLabel.getName(), Font.PLAIN, 20));
			
			upcomingJobsPanel.add(noCurrentJobsAvailableLabel, BorderLayout.CENTER);
		} else {
			/*
			 * This case is for displaying the volunteer's upcoming
			 * jobs, up the UPCOMING_JOBS_MAX_NUM_DISPLAY value.
			 */
			ButtonGroup buttonGroup = new ButtonGroup();
			int index = 0;
			while (index < upcomingJobSize && index < GUI.UPCOMING_JOBS_MAX_NUM_DISPLAY) {
				// 
				JRadioButton radioButton = createRadioButton(myAllUpcomingJobs.get(index));
				radioButton.setBackground(Color.WHITE);
				buttonGroup.add(radioButton);
				radioButtonsPanel.add(radioButton);
				radioButtonsPanel.add(Box.createRigidArea(GUI.RADIO_BUTTNON_RIGID_AREA));
				if (index == 0) {
					radioButton.setSelected(true);
					// Selecting the first job as the default job to be viewed.
					mySelectedJobID = myAllUpcomingJobs.get(index).getJobID();
				}
				
				index++;
			}
			upcomingJobsPanel.add(radioButtonsPanel, BorderLayout.CENTER);
		}
		
		// The button to view the selected job from the radio buttons from above.
		JButton viewSelectedJobButton = new JButton(new AbstractAction("View Selected Job") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("view selected job", mySelectedJobID));
			}
		});
		viewSelectedJobButton.setPreferredSize(buttonSize);
		viewSelectedJobButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		// The panel which simple has the viewSelectedJobButton.
		JPanel viewSelectedJobPanel = new JPanel(new FlowLayout());
		viewSelectedJobPanel.add(viewSelectedJobButton);
		
		if (upcomingJobSize > 0) {
			upcomingJobsPanel.add(viewSelectedJobPanel, BorderLayout.SOUTH);
		}
		
		
		// Panel on the right side of the overall home panel.
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		buttonsPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
		buttonsPanel.setLayout(buttonPanelLayout);
		
		
		// Buttons to go on the buttonPanel.
		JButton signUpButton = new JButton("Submit A Job");
		signUpButton.addActionListener(new AbstractAction("Submit A Job") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("submit a job", 0));
			}
		});
		
		/*
		 * Checking if the number of Jobs in the whole system is not
		 * surpassing maximum number of job defined in the JobMap.
		 */
		if (myJobMap.isFull()) {
			signUpButton.setEnabled(false);
		} else {
			signUpButton.setEnabled(true);
		}
		
		signUpButton.setPreferredSize(buttonSize);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		JButton viewAllYourUpcommingJobsButton = new JButton(
				new AbstractAction("View All Upcomming Jobs") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("upcoming", 0));
			}
		});
		viewAllYourUpcommingJobsButton.setPreferredSize(buttonSize);
		viewAllYourUpcommingJobsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton logOut = new JButton(new AbstractAction("Log Out") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("logout", 0));
			}
		});
		logOut.setPreferredSize(buttonSize);
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		//current job quantity.
		JLabel jobAmount = new JLabel();
		JPanel topLabelPanel = new JPanel();
		topLabelPanel.add(jobAmount);

		jobAmount.setText("Max job allowed: " + 
				myJobMap.getMaxJobAmount() + 
				"             Currently: " + myJobMap.getPendingJobAmount());
		
		JPanel signUpButtonPanel = new JPanel();
		signUpButtonPanel.add(signUpButton);
		signUpButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel viewAllJobsButtonPanel = new JPanel();
		viewAllJobsButtonPanel.add(viewAllYourUpcommingJobsButton);
		viewAllJobsButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel logOutButtonPanel = new JPanel();
		logOutButtonPanel.add(logOut);
		logOutButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);

		// Adding the buttons onto the buttonsPanel.
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 200)));
		buttonsPanel.add(signUpButtonPanel);
		//buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(Box.createVerticalGlue());
		buttonsPanel.add(viewAllJobsButtonPanel);
		//buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(logOutButtonPanel);
		
		JPanel ivisiblePanel = new JPanel();
		//ivisiblePanel.add(Box.createRigidArea(new Dimension(0, 200)));
		ivisiblePanel.setPreferredSize(new Dimension(0, 200));
		ivisiblePanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(topLabelPanel, BorderLayout.NORTH);
		rightPanel.add(buttonsPanel, BorderLayout.CENTER);
		rightPanel.add(ivisiblePanel, 
				BorderLayout.SOUTH);

		// Adding panels to the SplitPane
		splitPane.setLeftComponent(upcomingJobsPanel);
		splitPane.setRightComponent(rightPanel);
		
		
		// Display specifics of the SplitPane
		splitPane.setResizeWeight(0.5);
		splitPane.setEnabled(false);

		// Adding SplitPane to main panel
		myPanel.add(splitPane, BorderLayout.CENTER);
	}
	
	/**
	 * Creates a radio button used to place a the different jobs
	 * which the Park Manager user has occurring at their park.
	 * 
	 * Pre-condition: The given job must have been initialized to a
	 * 					non-null value;
	 * @param theJob The given job to create a radio button for.
	 * @return A radio button.
	 */
	private JRadioButton createRadioButton(final Job theJob) {
		JRadioButton radioButton = new JRadioButton(
				new AbstractAction(theJob.getJobSummary()) {

			/** A generated serial version UID for object Serialization.*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mySelectedJobID = theJob.getJobID();
			}
		});
		return radioButton;
	}
	
	/**
	 * @return an instance of ParkManagerHomePanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}