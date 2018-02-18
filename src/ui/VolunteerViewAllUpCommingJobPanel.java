package ui;

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

public class VolunteerViewAllUpCommingJobPanel extends Observable{
	/**
	 * A generated serial version UID for object Serialization.
	 */
	private static final long serialVersionUID = 1L;
 
	private List<Job> myAllUpCommingJobs;
	private JPanel myVolunteerViewAllUpCommingJobPanel;
	
	/**The job ID of the corresponding job of radio button. */
	private int mySelectedJobID;

	/**
	 * @param theAllJobs all the jobs in the system.
	 */
	public VolunteerViewAllUpCommingJobPanel(final List<Job> theAllUpCommingJobs) {
		myVolunteerViewAllUpCommingJobPanel = new JPanel(new BorderLayout());
		myAllUpCommingJobs = theAllUpCommingJobs;
		myVolunteerViewAllUpCommingJobPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myVolunteerViewAllUpCommingJobPanel.setBackground(Color.WHITE);
		setup();
	}

	/**
	 * Set up this VolunteerSignUpPanel.
	 */
	private void setup() {
		JButton homeButton = makeHomeButton();
		JButton jobDetailButton = makeViewJobDetailButton();
		
		JLabel topLabel = new JLabel("All Upcomming Jobs");
		JPanel topLabelPanel = new JPanel();
		topLabelPanel.add(topLabel);
		
		//radio button panel
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.setBackground(Color.WHITE);
		radioPanel.setBorder(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BORDER);
		
		//radio button group
		ButtonGroup group = new ButtonGroup();
		int size = myAllUpCommingJobs.size();
		for (int i = 0; i < size; i++) {

			//JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			//System.out.println(b.toString());
			JRadioButton b = makeRadioButton(myAllUpCommingJobs.get(i));
			b.setBackground(Color.WHITE);
			group.add(b);
			radioPanel.add(b);
			//set mySelectedJobID to the first job ID
			if (i == 0) {
				b.setSelected(true);
				mySelectedJobID = myAllUpCommingJobs.get(i).getJobID();
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

		myVolunteerViewAllUpCommingJobPanel.add(topLabelPanel, BorderLayout.NORTH);
		myVolunteerViewAllUpCommingJobPanel.add(radioScrollPane, BorderLayout.CENTER);
		myVolunteerViewAllUpCommingJobPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	private JLabel makeTopJlabel() {
		JLabel topLabel = new JLabel("All Upcomming Jobs");
		topLabel.setSize(GUIFrame.JLABEL_SHORT_TEXT);
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
				System.out.println("Home Selected Job ID " + mySelectedJobID);
			}
			
		});
		homeButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
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
		JButton jobDetailButton = new JButton(new AbstractAction("View Job Detail") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theEvent) {
				setChanged();
				notifyObservers(new ButtonSignal("View Job Detail", mySelectedJobID));
				System.out.println("View Job Detail Selected Job ID " + mySelectedJobID);
			}
		});
		jobDetailButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
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
	 * @return a VolunteerSignUpPanel.
	 */
	public JPanel getPanel() {
		return myVolunteerViewAllUpCommingJobPanel;
	}


}
