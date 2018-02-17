package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Job;

public class VolunteerSignUpDetailsPanel extends Observable implements Observer{
	
	JPanel myPanel;
	JPanel myButtonsPanel;
	JPanel myJobDetailsPanel;
	List<Job> myEligibleJobs;
	public VolunteerSignUpDetailsPanel(final List<Job> theEligibleJobs) {
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
		myButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				GUIFrame.BUTTON_GAP_WIDTH,
				GUIFrame.BUTTON_GAP_HEIGHT));
		JButton backButton = new JButton(new AbstractAction("Back") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		backButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		JButton signupButton = new JButton(new AbstractAction("Sign up") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		signupButton.setPreferredSize(GUIFrame.BUTTON_SIZE);
		myButtonsPanel.add(backButton);
		myButtonsPanel.add(signupButton);
		myPanel.add(myButtonsPanel, BorderLayout.SOUTH);
	}
	
	private void createJobDetails() {
		myJobDetailsPanel = new JPanel(new GridLayout(0, 1));
		Job j = myEligibleJobs.get(0);
		for (String detail : j.getJobDetailsList()) {
			JLabel label = new JLabel(detail, JLabel.LEFT);
			myJobDetailsPanel.add(label);
		}
		
		
		myPanel.add(myJobDetailsPanel);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
