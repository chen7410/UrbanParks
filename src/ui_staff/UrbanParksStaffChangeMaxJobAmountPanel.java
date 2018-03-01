package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

public class UrbanParksStaffChangeMaxJobAmountPanel extends Observable {
	
	private JPanel myPanel;
	private JobMap myJobs;
	private int myCurrentNumOfPendingJobs;
	
	public UrbanParksStaffChangeMaxJobAmountPanel(final JobMap theJobs) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theJobs;
		myCurrentNumOfPendingJobs = myJobs.getMaxJobAmount();
		setUp();
	}
	
	private void setUp() {
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		createButtons();
		createChangeNumberPanel();
	}
	

	private void createButtons() {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				GUI.BUTTON_GAP_WIDTH,
				GUI.BUTTON_GAP_HEIGHT));
		JButton submitChangeButton = new JButton(new AbstractAction("Submit Changes") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: Observer?
				setChanged();
				notifyObservers(new ButtonSignal("submit change", 0));
			}
		});
		
		submitChangeButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonsPanel.add(submitChangeButton);
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	
	private void createChangeNumberPanel() {
		JPanel changeNumberPanel = new JPanel(new GridLayout(0, 1));
		changeNumberPanel.setBackground(Color.WHITE);
		
		
		JLabel currentMaxLabel = new JLabel("Current number of maximum pending jobs: "
				+ myCurrentNumOfPendingJobs);
		currentMaxLabel.setFont(new Font(null, Font.BOLD, 20));
		changeNumberPanel.add(currentMaxLabel);
		
		JLabel headerLabel = new JLabel("Please enter the new number in the box below.");
		headerLabel.setFont(new Font(null, Font.BOLD, 20));
		changeNumberPanel.add(headerLabel);
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.WHITE);
		JLabel label = new JLabel("New Number: ");
		textPanel.add(label);
		
		//TODO: ActionListener?
		JTextField textField = new JTextField(20);
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				int newNumber = Integer.parseInt(str);
				myJobs.setMaxJobAmount(newNumber);	
			}
		});
		
		textPanel.add(textField);

		
		changeNumberPanel.add(textPanel, BorderLayout.WEST);
		
		myPanel.add(changeNumberPanel, BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return myPanel;
	}
}
