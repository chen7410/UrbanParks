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
import java.awt.GridLayout;
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
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class ParkManagerHomePanel extends Observable {
	
	private JPanel myPanel;
	private int mySelectedJobID;
	private List<Job> myAllUpcomingJobs;
	
	public ParkManagerHomePanel(final List<Job> theJobList) {
		myAllUpcomingJobs = theJobList;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	private void setup() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		// Panel on the left side of the overall home panel.		
		JPanel upcomingJobsPanel = new JPanel(new BorderLayout());
		upcomingJobsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		upcomingJobsPanel.setBorder(BorderFactory.createTitledBorder("Your Upcoming Jobs"));
		
		// Radio buttons panel
		JPanel radioButtonsPanel = new JPanel(new GridLayout(0, 1));
		radioButtonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
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
				notifyObservers(new ButtonSignal("view job details", mySelectedJobID));	
			}
		});
		viewSelectedJobButton.setSize(GUI.BUTTON_SIZE);
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
		JButton signUpButton = new JButton(new AbstractAction("SignUp") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("signup", 0));
			}
		});
		signUpButton.setSize(GUI.BUTTON_SIZE);
		signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton viewAllYourUpcommingJobsButton = new JButton(
				new AbstractAction("View all your upcomming jobs") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("upcoming", 0));
			}
		});
		viewAllYourUpcommingJobsButton.setSize(GUI.BUTTON_SIZE);
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
		logOut.setSize(GUI.BUTTON_SIZE);
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		// Adding the buttons onto the buttonsPanel.
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 200)));
		buttonsPanel.add(signUpButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(viewAllYourUpcommingJobsButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(logOut);
		
		
		
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
	
	public JPanel getPanel() {
		return myPanel;
	}

}