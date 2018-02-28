/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
		
		// The main panel in the center.
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.GREEN);
		centerPanel.setBorder(BorderFactory.createTitledBorder("Urban Parks"));
		BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
		centerPanel.setLayout(layout);
		
		
		JLabel welcome = new JLabel("Welcome to Urban Parks");
		welcome.setSize(GUI.JLABEL_SHORT_TEXT);
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel slogan = new JLabel("Where you can sign up or create a"
									+ " volunteering job", SwingConstants.CENTER); 
		slogan.setSize(GUI.JLABEL_LONG_TEXT);
		slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Adding the labels to the center panel.
		centerPanel.add(welcome);
		centerPanel.add(slogan);
		
		
		
		JTextField userName = new JTextField("", 15);
		
		JButton logInButton = new JButton("LogIn");
		Action logInAction = new AbstractAction("LogIn") {
			
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
		};
		
		logInButton.setAction(logInAction);
		logInButton.setSize(GUI.BUTTON_SIZE);
		
		userName.addActionListener(logInAction);
		
		JButton exitButton = new JButton(new AbstractAction("Exit") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setSize(GUI.BUTTON_SIZE);
		exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel inputPanel = new JPanel(new FlowLayout());
		inputPanel.setBackground(Color.GREEN);
		inputPanel.add(userName);
		inputPanel.add(logInButton);
		inputPanel.add(exitButton);
		
		// Adding the inputPanel with all the content to the center panel.
		centerPanel.add(inputPanel);
		
		
//		JPanel welcomeTitle = new JPanel(new FlowLayout());
//		welcomeTitle.add(welcome);
		
//		JPanel sloganTitle = new JPanel(new FlowLayout());
//		sloganTitle.add(slogan);
		
//		myPanel.add(welcomeTitle, BorderLayout.NORTH);
//		myPanel.add(sloganTitle, BorderLayout.CENTER);
//		myPanel.add(inputPanel, BorderLayout.SOUTH);
		
		myPanel.add(centerPanel, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}