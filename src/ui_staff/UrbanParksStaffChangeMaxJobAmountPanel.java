package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

/**
 * 
 * 
 * @author Group 7
 * @version March 5, 2018
 */
public class UrbanParksStaffChangeMaxJobAmountPanel extends Observable {
	
	private JPanel myPanel;
	private JobMap myJobs;
	
	public UrbanParksStaffChangeMaxJobAmountPanel(final JobMap theJobs) {
		myPanel = new JPanel(new BorderLayout());
		myJobs = theJobs;
		setUp();
	}
	
	private void setUp() {
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		createPanel();
	}
	

	private void createPanel() {

	
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
		centerPanel.setLayout(layout);
		
		JLabel currentNumberLabel = new JLabel("Current number of maximum pending jobs: "
				 + myJobs.getMaxJobAmount());
		currentNumberLabel.setFont(new Font(null, Font.PLAIN, 25));
		currentNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(Color.WHITE);
		
		JLabel newNumberLabel = new JLabel("Enter the new maximum number: ");
		newNumberLabel.setFont(new Font(null, Font.BOLD, 15));
		JTextField newNumberTextField = new JTextField("", 20);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,
				GUI.BUTTON_GAP_WIDTH,
				GUI.BUTTON_GAP_HEIGHT));
		JButton submitChangeButton = new JButton(new AbstractAction("Submit Change") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = newNumberTextField.getText();
				if (str.length() > 0) {
					int newNumber = Integer.parseInt(str);
					myJobs.setMaxJobAmount(newNumber);
					setChanged();
					notifyObservers(new ButtonSignal("submit change", 0));
					
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please enter a valid number.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		submitChangeButton.setPreferredSize(GUI.BUTTON_SIZE);
		buttonsPanel.add(submitChangeButton);
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);

		
		textFieldPanel.add(newNumberLabel);
		textFieldPanel.add(newNumberTextField);
		
		centerPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		centerPanel.add(currentNumberLabel);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		centerPanel.add(textFieldPanel);

		myPanel.add(centerPanel);
		
	}

	public JPanel getPanel() {
		return myPanel;
	}
}
