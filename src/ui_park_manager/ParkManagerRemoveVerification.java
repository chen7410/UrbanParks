/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
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

/**
 * A JPanel that will display a verification to the park manager
 * when a job is removed from the system. The panel will have two 
 * buttons at the bottom, one to return to home and one to confrim the 
 * removal. 
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class ParkManagerRemoveVerification extends Observable {
	private JPanel myPanel;
	private Job myJob;
	
    /**
     * Creates a new park manager panel that confirms the removal of
     * the specified job from the system.
     *
     * @param theJob the job that is being unsumbitted by the 
     * 				 park manager. 
     */
	public ParkManagerRemoveVerification(final Job theJob) {
		myPanel = new JPanel(new BorderLayout());
		myJob = theJob;
		init();
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
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
		
		
		JButton removeButton = new JButton(new AbstractAction("Remove") {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(new ButtonSignal("remove", myJob));
			}
		});
		if (!myJob.isJobRemovable()) {
			removeButton.setEnabled(false);
		}
		removeButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonsPanel.add(backButton);
		buttonsPanel.add(removeButton);
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
	
	/**
	 * @return an instance of ParkManagerRemoveVerification.
	 */
	public JPanel getPanel() {
		return myPanel;
	}


}
