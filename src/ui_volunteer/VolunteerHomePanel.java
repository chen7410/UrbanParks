/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class VolunteerHomePanel extends Observable {
	
	private JPanel myPanel;
	private List<Job> myAllUpCommingJobs;
	
	public VolunteerHomePanel(final List<Job> theJobList) {
		myAllUpCommingJobs = theJobList;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	private void setup() {
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
		JPanel logOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		logOutPanel.add(logOut);
		
		
		
		JPanel centerSidePanel = new JPanel();
		
		JPanel yourUpcommingJobsPanel = new JPanel();
		yourUpcommingJobsPanel.setLayout((new BoxLayout(yourUpcommingJobsPanel, BoxLayout.Y_AXIS)));
		yourUpcommingJobsPanel.setBackground(Color.WHITE);
		
		JButton viewJobDetailsButton = new JButton(new AbstractAction("View job details") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Display VolunteerSignUpPanel.
				
			}
		});
		viewJobDetailsButton.setSize(GUI.BUTTON_SIZE);
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		int index = 0;
		while (index < myAllUpCommingJobs.size() && index < 5) {
			
			JRadioButton radioButton = new JRadioButton(new
					AbstractAction(myAllUpCommingJobs.get(index).getJobSummary()) {

				/** */
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO
					
				}
				
			});
			buttonGroup.add(radioButton);
			yourUpcommingJobsPanel.add(radioButton);
			
			index++;
		}
		
		JLabel upcommingJobs = new JLabel("Your upcomming Jobs");
		yourUpcommingJobsPanel.add(upcommingJobs);

		centerSidePanel.add(yourUpcommingJobsPanel, BorderLayout.CENTER);
		centerSidePanel.add(viewJobDetailsButton, BorderLayout.SOUTH);
		

		
		JPanel generalButtons = new JPanel();
		generalButtons.setLayout(new BoxLayout(generalButtons, BoxLayout.Y_AXIS));
		
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
		
		generalButtons.add(signUpButton);
		generalButtons.add(viewAllYourUpcommingJobsButton);
		
		JPanel emptyCenterPanel = new JPanel();
		emptyCenterPanel.setMaximumSize(new Dimension(0, 0));
		
		myPanel.add(logOutPanel, BorderLayout.NORTH);
		myPanel.add(emptyCenterPanel, BorderLayout.CENTER);
		myPanel.add(yourUpcommingJobsPanel, BorderLayout.WEST);
		myPanel.add(generalButtons, BorderLayout.EAST);
		
		
		
		
		
		
		// double width = GUIFrame.PANEL_SIZE.getWidth();
		
		// generalButtons.setMinimumSize(GUIFrame.PANEL_SIZE);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}