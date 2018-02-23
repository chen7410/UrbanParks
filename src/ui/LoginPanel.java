/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.User;
import model.UserMap;

/**
 * 
 * 
 * @author Brook Negussie
 * @version February 21, 2018
 */
public class LoginPanel extends Observable {
	
	private JPanel myPanel;
	private UserMap myUsers;
	
	public LoginPanel(final UserMap theUserMap) {
		myUsers = theUserMap;
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(Color.WHITE);
		setup();
	}
	
	private void setup() {
		JLabel welcome = new JLabel("Welcome to Urban Parks");
		welcome.setSize(GUI.JLABEL_SHORT_TEXT);
		JLabel slogan = new JLabel("Where you can sign up or create a"
									+ " volunteering job"); 
		slogan.setSize(GUI.JLABEL_LONG_TEXT);
		JTextField userName = new JTextField("User name", 15);
		JButton logInButton = new JButton(new AbstractAction("LogIn") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent theArg) {
				String givenUserName = userName.getText();
				User user = myUsers.getUser(givenUserName);
				if (myUsers.getUser(givenUserName) != null) {
					setChanged();
					notifyObservers(new ButtonSignal("login", user));
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please enter a valid user name.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		logInButton.setSize(GUI.BUTTON_SIZE);
		
		JButton exitButton = new JButton(new AbstractAction("Exit") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setSize(GUI.BUTTON_SIZE);
		
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