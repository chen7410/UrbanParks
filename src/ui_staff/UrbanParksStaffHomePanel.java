/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.ButtonSignal;
import ui.GUI;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class UrbanParksStaffHomePanel extends Observable{
	
	private JPanel myPanel;
	
	public UrbanParksStaffHomePanel() {
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.GREEN);
		setup();
	}
	
	private void setup() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		buttonsPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
		buttonsPanel.setLayout(buttonPanelLayout);
		
		
		// Buttons to go on the buttonPanel.
		JButton searchJobsButton = new JButton(new AbstractAction("Search Jobs") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				setChanged();
//				notifyObservers(new ButtonSignal("search jobs", 0));
			}
		});
		searchJobsButton.setSize(GUI.BUTTON_SIZE);
		searchJobsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton pendingJobsSizeButton = new JButton(
				new AbstractAction("Change Number of Pending Jobs Size") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				setChanged();
//				notifyObservers(new ButtonSignal("pending jobs size", 0));
			}
		});
		pendingJobsSizeButton.setSize(GUI.BUTTON_SIZE);
		pendingJobsSizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
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
		logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		// Adding the buttons onto the buttonsPanel.
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 200)));
		buttonsPanel.add(searchJobsButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(pendingJobsSizeButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		buttonsPanel.add(logOut);
		
		myPanel.add(buttonsPanel, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}