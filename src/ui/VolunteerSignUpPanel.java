/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import model.Job;
import model.Volunteer;
/**
 * A JPanal that showing all jobs a volunteer can sign up.
 * @author Group 7
 * @version February 14, 2018
 */
public class VolunteerSignUpPanel extends Observable {

	/**
	 * A generated serial version UID for object Serialization.
	 */
	private static final long serialVersionUID = 1L;


	private Volunteer myVolunteer;
	private List<Job> myEligibleJobs;
	private JPanel myVolunteerSignUpPanel;

	/**
	 * 
	 * @param theVolunteer 
	 * @param theAllJobs all the jobs in the system.
	 */
	public VolunteerSignUpPanel(final Volunteer theVolunteer, final List<Job> theEligibleJobs) {
		myVolunteerSignUpPanel = new JPanel(new BorderLayout());
		myVolunteer = theVolunteer;
		myEligibleJobs = theEligibleJobs;
		myVolunteerSignUpPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myVolunteerSignUpPanel.setBackground(Color.WHITE);
		setup();
	}

	private void setup() {
		JButton homeButton = new JButton("Home");
		homeButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		JButton jobDetailButton = new JButton("View Job Detail");
		jobDetailButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		ButtonGroup group = new ButtonGroup();

		//radio button panel
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.setBackground(Color.WHITE);
		radioPanel.setBorder(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BORDER);
		int size = myEligibleJobs.size();
		for (int i = 0; i < size; i++) {

			//JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			//System.out.println(b.toString());
			JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			b.setBackground(Color.WHITE);
			group.add(b);
			radioPanel.add(b);
		}

		//button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUIFrame.BUTTON_GAP_WIDTH, GUIFrame.BUTTON_GAP_HEIGHT));
		buttonPanel.setBackground(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BGCOLOR );
		buttonPanel.add(homeButton);
		buttonPanel.add(jobDetailButton);

		//radio button scroll pane
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JScrollPane radioScrollPane = new JScrollPane(radioPanel);
		radioScrollPane.setBorder(BorderFactory.createTitledBorder(
				loweredetched, "Select a job"));

		myVolunteerSignUpPanel.add(radioScrollPane, BorderLayout.CENTER);
		myVolunteerSignUpPanel.add(buttonPanel, BorderLayout.SOUTH);
	}


	private JRadioButton makeRadioButton(final Job theJob) {
		JRadioButton button = new JRadioButton(theJob.getJobSummary());
		
		return button;
	}

	public JPanel getPanel() {
		return myVolunteerSignUpPanel;
	}


}
