/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel that will show all the jobs that a volunteer is 
 * eligible to sign up for. At the bottom of the panel, there's
 * an option to view the selected job details or return to the 
 * home panel.
 * 
 * @author  Group 7
 * @version March 5, 2018
 */
public class VolunteerSignUpPanel extends Observable {
 
	private List<Job> myEligibleJobs;
	private JPanel myPanel;
	
	private int mySelectedJobID;

	/**
	 * Creates a panel that will display all the jobs that
	 * the volunteer is eligible to sign up for.
	 * 
	 * @param theEligibleJobs a list of all the jobs that a volunteer 
	 *                        is eligible to sign up for. 
	 */
	public VolunteerSignUpPanel(final List<Job> theEligibleJobs) {
		myPanel = new JPanel(new BorderLayout());
		myEligibleJobs = theEligibleJobs;
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}

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
		int size = myEligibleJobs.size();
		for (int i = 0; i < size; i++) {

			JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			b.setBackground(Color.WHITE);
			group.add(b);
			radioPanel.add(b);
			//set mySelectedJobID to the first job ID
			if (i == 0) {
				b.setSelected(true);
				mySelectedJobID = myEligibleJobs.get(i).getJobID();
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
		myPanel.add(radioScrollPane, BorderLayout.CENTER);
		myPanel.add(buttonPanel, BorderLayout.SOUTH);
	}
	

	private JLabel makeTopJlabel() {
		JLabel topLabel = new JLabel("Sign Up A Job");
		topLabel.setSize(GUI.JLABEL_SHORT_TEXT);
		return topLabel;
	}
	

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
		if(myEligibleJobs.isEmpty()) {
			jobDetailButton.setEnabled(false);
		}
		return jobDetailButton;
	}

	private JRadioButton makeRadioButton(final Job theEligibleJob) {
		JRadioButton button = new JRadioButton(
				new AbstractAction(theEligibleJob.getJobSummary()) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				mySelectedJobID = theEligibleJob.getJobID();
			}
		});
		return button;
	}

	/**
	 * @return an instance of VolunteerSignUpPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}


}
