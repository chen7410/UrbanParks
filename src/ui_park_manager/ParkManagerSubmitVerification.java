package ui_park_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Job;
import ui.ButtonSignal;
import ui.GUI;

public class ParkManagerSubmitVerification extends Observable {
	private JPanel myPanel;
	private Job myJob;
	
	public ParkManagerSubmitVerification(final Job theJob) {
		myPanel = new JPanel(new BorderLayout());
		myJob = theJob;
		init();
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
	public String getPanelName() {
		return "Submit A Job";
	}
	
	private void init() {
		createButton();
		createJobDetails();
	}
	
	private void createButton() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				GUI.BUTTON_GAP_WIDTH,
				GUI.BUTTON_GAP_HEIGHT));
		JButton backButton = new JButton(new AbstractAction("Back") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("back", 0));
			}
		});
		backButton.setPreferredSize(GUI.BUTTON_SIZE);
		
		
		JButton signupButton = new JButton(new AbstractAction("Submit") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("submit", myJob));
			}
		});
		signupButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonsPanel.add(backButton);
		buttonsPanel.add(signupButton);
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private void createJobDetails() {
		JPanel jobDetailsPanel = new JPanel(new GridLayout(0, 1));
		JLabel l = new JLabel("Job Details");
		l.setFont(new Font(null, Font.BOLD, 30));
		jobDetailsPanel.add(l);
		for (String detail : myJob.getJobDetailsList()) {
			String formattedDetail = "<html><span style=\"font-weight:bold;font-size:15px;\">" 
					+ detail.split(":")[0] + ": </span>"+ detail.split(":")[1] + "</html>";	
			JLabel label = new JLabel(formattedDetail, JLabel.LEFT);
			label.setPreferredSize(GUI.JLABEL_LONG_TEXT);
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