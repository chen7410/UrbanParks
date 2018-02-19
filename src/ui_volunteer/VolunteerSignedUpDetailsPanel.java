package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Job;
import ui.ButtonSignal;
import ui.GUIFrame;

/**
 * Panel for when a volunteer views the details of a job they have
 * signed up for. It will notify Observers when a button is pressed.
 * 
 * @author Tuan Dinh
 * @version February 18, 2018
 */
public class VolunteerSignedUpDetailsPanel extends Observable {
	
	private JPanel myPanel;
	private List<Job> myEligibleJobs;
	private Job myJob;
	
	public VolunteerSignedUpDetailsPanel(final List<Job> theEligibleJobs) {
		myPanel = new JPanel(new BorderLayout());
		myEligibleJobs = theEligibleJobs;
		init();
		myPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
	private void init() {
		createButton();
		createJobDetails();
	}
	
	private void createButton() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				GUIFrame.BUTTON_GAP_WIDTH,
				GUIFrame.BUTTON_GAP_HEIGHT));
		JButton backButton = new JButton(new AbstractAction("Back") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				notifyObservers(new ButtonSignal("back", 0));
			}
		});
		backButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		JButton signupButton = new JButton(new AbstractAction("Remove") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				notifyObservers(new ButtonSignal("remove", 0));
				
			}
		});
		signupButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		buttonsPanel.add(backButton);
		buttonsPanel.add(signupButton);
		buttonsPanel.setBackground(GUIFrame.VOLUNTEER_SIGNUP_PANEL_BGCOLOR);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void createJobDetails() {
		JPanel jobDetailsPanel = new JPanel(new GridLayout(0, 1));
		JLabel l = new JLabel("Job Details");
		l.setFont(new Font(null, Font.BOLD, 30));
		jobDetailsPanel.add(l);
		myJob = myEligibleJobs.get(0);
		for (String detail : myJob.getJobDetailsList()) {
			String formattedDetail = "<html><span style=\"font-weight:bold;font-size:15px;\">" 
					+ detail.split(":")[0] + ": </span>"+ detail.split(":")[1] + "</html>";	
			JLabel label = new JLabel(formattedDetail, JLabel.LEFT);
			label.setPreferredSize(GUIFrame.JLABEL_LONG_TEXT);
			jobDetailsPanel.add(label);
		}
		JPanel cover0 = new JPanel(new BorderLayout());
		cover0.add(jobDetailsPanel, BorderLayout.EAST);
		JPanel cover1 = new JPanel(new BorderLayout());
		cover1.add(cover0, BorderLayout.NORTH);
		jobDetailsPanel.setBackground(Color.WHITE);
		cover0.setBackground(Color.WHITE);
		cover1.setBackground(Color.WHITE);
		myPanel.add(cover1);
	}
}
