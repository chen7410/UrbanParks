/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_volunteer;

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
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel that will be the volunteer's home panel. It will show a
 * list of all their upcoming jobs on the left. On the right side, it
 * will show the options that a volunteer can choose from. The options
 * are: sign up, view all upcoming jobs, and log out.
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class VolunteerHomePanel extends Observable {
	
	private JPanel myPanel;
	private int mySelectedJobID;
	private List<Job> myAllUpcomingJobs;

	/**
	 * Creates a new volunteer home panel.
	 * 
	 * @param theJobList all the jobs that belongs to the volunteer.
	 */
	public VolunteerHomePanel(final List<Job> theJobList) {
		myAllUpcomingJobs = theJobList;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	private void setup() {
		JSplitPane splitPane = new JSplitPane(
									JSplitPane.HORIZONTAL_SPLIT);

		// Panel on the left side of the overall home panel.		
		JPanel upcomingJobsPanel = new JPanel(new BorderLayout());
		upcomingJobsPanel.setBackground(Color.WHITE);
		upcomingJobsPanel.setBorder(BorderFactory
						.createTitledBorder("Your Upcoming Jobs"));
		
		// Radio buttons panel
		JPanel radioButtonsPanel = new JPanel();
		radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel,
									BoxLayout.Y_AXIS));
		radioButtonsPanel.setBackground(Color.WHITE);
		radioButtonsPanel.setBorder(BorderFactory.createEmptyBorder());
		
		
		int upcomingJobSize = myAllUpcomingJobs.size();
		
		if (upcomingJobSize == 0) {
			/*
			 * This is for when the volunteer does not have any jobs
			 * signed up for.
			 */
			JLabel noCurrentJobsAvailableLabel = new JLabel(
								"You do not have any upcoming jobs.",
								SwingConstants.CENTER);
			noCurrentJobsAvailableLabel.setFont(new Font(
								noCurrentJobsAvailableLabel.getName(),
								Font.PLAIN, 12));
			
			upcomingJobsPanel.add(noCurrentJobsAvailableLabel,
									BorderLayout.CENTER);
		} else {
			/*
			 * This case is for displaying the volunteer's upcoming
			 * jobs, up the UPCOMING_JOBS_MAX_NUM_DISPLAY value.
			 */
			ButtonGroup buttonGroup = new ButtonGroup();
			int index = 0;
			while (index < upcomingJobSize && index <
							GUI.UPCOMING_JOBS_MAX_NUM_DISPLAY) {
				// 
				JRadioButton radioButton = createRadioButton(
									myAllUpcomingJobs.get(index));
				radioButton.setBackground(Color.WHITE);
				buttonGroup.add(radioButton);
				radioButtonsPanel.add(radioButton);
				radioButtonsPanel.add(Box.createRigidArea(
									GUI.RADIO_BUTTNON_RIGID_AREA));
				if (index == 0) {
					radioButton.setSelected(true);
					/*
					 * Selecting the first job as the default job
					 * to be viewed.
					 */
					mySelectedJobID = myAllUpcomingJobs.get(index)
														.getJobID();
				}
				
				index++;
			}
			upcomingJobsPanel.add(radioButtonsPanel,
									BorderLayout.CENTER);
		}
		
		/*
		 * The button to view the selected job from the radio
		 * buttons from above.
		 */
		JButton viewSelectedJobButton = new JButton(
							new AbstractAction("View Selected Job") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("view job details",
													mySelectedJobID));	
			}
		});
		viewSelectedJobButton.setPreferredSize(GUI.BUTTON_SIZE);
		viewSelectedJobButton.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		// The panel which simple has the viewSelectedJobButton.
		JPanel viewSelectedJobPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, 0, GUI.BUTTON_GAP_HEIGHT));
		viewSelectedJobPanel.add(viewSelectedJobButton);
		viewSelectedJobPanel.setBackground(
									GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		if (upcomingJobSize > 0) {
			upcomingJobsPanel.add(viewSelectedJobPanel,
									BorderLayout.SOUTH);
		}
		
		
		// Panel on the right side of the overall home panel.
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		buttonsPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(buttonsPanel,
													BoxLayout.Y_AXIS);
		buttonsPanel.setLayout(buttonPanelLayout);
		
		
		// Buttons to go on the buttonPanel.
		JButton signUpButton = new JButton(new AbstractAction(
														"Sign Up") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("signup", 0));
			}
		});
		signUpButton.setPreferredSize(GUI.BUTTON_SIZE);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton viewAllYourUpcommingJobsButton = new JButton(
				new AbstractAction("View Upcomming Jobs") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("upcoming", 0));
			}
		});
		viewAllYourUpcommingJobsButton.setPreferredSize(GUI.BUTTON_SIZE);
		viewAllYourUpcommingJobsButton.setAlignmentX(
										Component.CENTER_ALIGNMENT);
		
		
		JButton logOut = new JButton(new AbstractAction("Log Out") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("logout", 0));
			}
		});
		logOut.setPreferredSize(GUI.BUTTON_SIZE);
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel signUpButtonPanel = new JPanel();
		signUpButtonPanel.add(signUpButton);
		signUpButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel viewAllJobsButtonPanel = new JPanel();
		viewAllJobsButtonPanel.add(viewAllYourUpcommingJobsButton);
		viewAllJobsButtonPanel.setBackground(
										GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel logOutButtonPanel = new JPanel();
		logOutButtonPanel.add(logOut);
		logOutButtonPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel ivisiblePanel = new JPanel();
		ivisiblePanel.setPreferredSize(new Dimension(0, 200));
		ivisiblePanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		
		// Adding the buttons onto the buttonsPanel.
		buttonsPanel.add(Box.createRigidArea(
									GUI.ABOVE_BUTTON_PADDING_AREA));
		buttonsPanel.add(signUpButtonPanel);
		buttonsPanel.add(Box.createRigidArea(
								GUI.BETWEEN_BUTTONS_PADDING_AREA));
		buttonsPanel.add(viewAllJobsButtonPanel);
		buttonsPanel.add(Box.createRigidArea(
								GUI.BETWEEN_BUTTONS_PADDING_AREA));
		buttonsPanel.add(logOutButtonPanel);
		buttonsPanel.add(ivisiblePanel);
		
		
		// Adding panels to the SplitPane
		splitPane.setLeftComponent(upcomingJobsPanel);
		splitPane.setRightComponent(buttonsPanel);
		
		
		// Display specifics of the SplitPane
		splitPane.setResizeWeight(0.5);
		splitPane.setEnabled(false);
		
		
		// Adding SplitPane to main panel
		myPanel.add(splitPane, BorderLayout.CENTER);
	}
	
	public JRadioButton createRadioButton(final Job theJob) {
		JRadioButton radioButton = new JRadioButton(
				new AbstractAction(theJob.getJobSummary()) {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mySelectedJobID = theJob.getJobID();
			}
		});
		return radioButton;
	}
	
	/**
	 * @return an instance of VolunteerHomePanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}
}