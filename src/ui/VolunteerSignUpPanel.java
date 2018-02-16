/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Job;
import model.Volunteer;
/**
 * A JPanal that showing all jobs a volunteer can sign up.
 * @author Group 7
 * @version February 14, 2018
 */
public class VolunteerSignUpPanel {

	/**
     * A generated serial version UID for object Serialization.
     */
	private static final long serialVersionUID = 1L;
	
	/**The size of the panel.*/
	private static final Dimension PANEL_SIZE = new Dimension(800, 600);
	
	
	private Volunteer myVolunteer;
	private List<Job> myEligibleJobs;
	private JPanel myVolunteerSignUpPanel;
	
	/**
	 * 
	 * @param theVolunteer 
	 * @param theAllJobs all the jobs in the system.
	 */
	public VolunteerSignUpPanel(final Volunteer theVolunteer, final List<Job> theEligibleJobs) {
		myVolunteerSignUpPanel = new JPanel();
		myVolunteer = theVolunteer;
		myEligibleJobs = theEligibleJobs;
		myVolunteerSignUpPanel.setPreferredSize(PANEL_SIZE);
		myVolunteerSignUpPanel.setLayout(new BorderLayout());
		
		setup();
		
		
		myVolunteerSignUpPanel.setBackground(Color.BLACK);
		//this.add(p2, BorderLayout.EAST);
		
	}
	
	private void setup() {
		JButton homeButton = new JButton("Home");
		JButton jobDetailButton = new JButton("View Job Detail");
		ButtonGroup group = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		int size = myEligibleJobs.size();
		for (int i = 0; i < size; i++) {
			
			JRadioButton b = makeRadioButton(myEligibleJobs.get(i));
			//System.out.println(b.toString());
			group.add(b);
			radioPanel.add(b);
		}
		radioPanel.setOpaque(true);
		radioPanel.setBackground(Color.WHITE);
		myVolunteerSignUpPanel.add(radioPanel, BorderLayout.CENTER);
		myVolunteerSignUpPanel.add(homeButton, BorderLayout.SOUTH);
		myVolunteerSignUpPanel.add(jobDetailButton, BorderLayout.NORTH);
	}
	
	
	private JRadioButton makeRadioButton(final Job theJob) {
		StringBuilder sb = new StringBuilder();
		sb.append(theJob.getParkName());
		sb.append(" ");
		sb.append(theJob.getStartDate().getMonthValue());
		sb.append("/");
		sb.append(theJob.getStartDate().getDayOfMonth());
		sb.append(" - ");
		sb.append(theJob.getEndDate().getMonthValue());
		sb.append("/");
		sb.append(theJob.getEndDate().getDayOfMonth());
		//System.out.println(sb.toString());
		return new JRadioButton(sb.toString());
	}
	
	public JPanel getPanel() {
		return myVolunteerSignUpPanel;
	}
	

}
