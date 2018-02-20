/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
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
import ui.GUIFrame;
/**
 * A JPanal that showing all jobs a volunteer can sign up.
 * @author Minqing Chen
 * @version February 17, 2018
 */
public class VolunteerSignUpPanel extends Observable {
 
	private List<Job> myEligibleJobs;
	private JPanel myPanel;
	
	/**The job ID of the corresponding job of radio button. */
	private int mySelectedJobID;

	/**
	 * @param theAllJobs all eligible jobs of a volunteer.
	 */
	public VolunteerSignUpPanel(final List<Job> theEligibleJobs) {
		myPanel = new JPanel(new BorderLayout());
		myEligibleJobs = theEligibleJobs;
		myPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}

	/**
	 * Set up this VolunteerSignUpPanel.
	 */
	private void setup() {
		JButton homeButton = makeActionButton("Home");
		JButton jobDetailButton = makeActionButton("View Job Details");
		
		JLabel topLabel = makeTopJlabel();
		JPanel topLabelPanel = new JPanel();
		topLabelPanel.add(topLabel);
		
		//radio button panel
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.setBackground(Color.WHITE);
		radioPanel.setBorder(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BORDER);
		
		//radio button group
		ButtonGroup group = new ButtonGroup();
		int size = myEligibleJobs.size();
		for (int i = 0; i < size; i++) {

			//JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			//System.out.println(b.toString());
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
				FlowLayout.CENTER, GUIFrame.BUTTON_GAP_WIDTH, GUIFrame.BUTTON_GAP_HEIGHT));
		buttonPanel.setBackground(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BGCOLOR);
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
	
	/**
	 * Set up top label.
	 * @return return a label.
	 */
	private JLabel makeTopJlabel() {
		JLabel topLabel = new JLabel("Sign Up A Job");
		topLabel.setSize(GUIFrame.JLABEL_SHORT_TEXT);
		return topLabel;
	}

	/**
	 * Set up an action button.
	 * Action button will fire a button signal which contains
	 * the name of this button and the selected job id the 
	 * and notify other observers.
	 * 
	 * @return the a button.
	 */
	private JButton makeActionButton(final String theButtonName) {
		JButton homeButton = new JButton(new AbstractAction(theButtonName) {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal(theButtonName, mySelectedJobID));
			}
			
		});
		homeButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		return homeButton;
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
			}
		});
		return button;
	}

	/**
	 * @return a VolunteerSignUpPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}


}
