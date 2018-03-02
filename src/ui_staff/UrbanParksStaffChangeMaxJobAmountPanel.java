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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.JobMap;
import ui.ButtonSignal;
import ui.GUI;

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
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
		centerPanel.setLayout(layout);
		
		JLabel currentNumberLabel = new JLabel("Current number of maximum pending jobs: "
				 + myJobs.getMaxJobAmount());
		currentNumberLabel.setFont(new Font(null, Font.PLAIN, 30));
		currentNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(Color.WHITE);
		
		JLabel newNumberLabel = new JLabel("Enter new maximum number: ");
		newNumberLabel.setFont(new Font(null, Font.PLAIN, 20));

		JTextField newNumberTextField = new JTextField("", 20);
		
		newNumberTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = newNumberTextField.getText();
				int newNumber = Integer.parseInt(str);
				myJobs.setMaxJobAmount(newNumber);	
			}	
		});
		
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
