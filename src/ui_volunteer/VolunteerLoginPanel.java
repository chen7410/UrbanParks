/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_volunteer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.GUIFrame;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class VolunteerLoginPanel {
	
	private JPanel myPanel;
	private List<String> myUsers;
	
	public VolunteerLoginPanel(final List<String> theUserList) {
		myUsers = theUserList;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUIFrame.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}
	
	private void setup() {
		JLabel welcome = new JLabel("Welcome to Urban Parks");
		JLabel slogan = new JLabel("Where you can sign up or create a"
									+ " volunteering job"); 
		JTextField userName = new JTextField("User name", 15);
		JButton logInButton = new JButton(new AbstractAction("LogIn") {

			@Override
			public void actionPerformed(ActionEvent theArg) {
				String givenUserName = userName.getText();
				if (myUsers.contains(givenUserName)) {
					// Proceed to the next page.
				} else {
					System.out.println(myUsers.contains(givenUserName));
					JOptionPane.showMessageDialog(new JFrame(),
							"Please enter a valid user name.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton exitButton = new JButton(new AbstractAction("Exit") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JPanel input = new JPanel(new FlowLayout());
		input.add(userName);
		input.add(logInButton);
		input.add(exitButton);
		
		JPanel welcomeTitle = new JPanel(new FlowLayout());
		welcomeTitle.add(welcome);
		
		JPanel sloganTitle = new JPanel(new FlowLayout());
		sloganTitle.add(slogan);
		
		myPanel.add(welcomeTitle, BorderLayout.NORTH);
		myPanel.add(sloganTitle, BorderLayout.CENTER);
		myPanel.add(input, BorderLayout.SOUTH);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}