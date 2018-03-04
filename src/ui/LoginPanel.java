/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Observable;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
 * A JPanel that will be the first panel displayed to the 
 * users. It will display a welcome message and ask them to 
 * log in using their username. It also gives the users an 
 * option to exit the program.
 * 
 * @author  Group 7
 * @version March 5, 2018
 */
public class LoginPanel extends Observable {
	
	private static final int LOG_IN_BUTTON_PADDING = 25;
	private static final int SLOGAN_LABEL_PADDING = 50;
	private static final int SLOGAN_LABEL_SIZE = 20;
	private static final int USERNAME_TEXTBOX_WIDTH_SIZE = 15;
	private static final int WELCOME_LABEL_PADDING = 75;
	private static final int WELCOME_LABEL_SIZE = 40;
	
	
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
		centerPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		centerPanel.setBorder(BorderFactory.createTitledBorder("Log in"));
		BoxLayout layout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
		centerPanel.setLayout(layout);
		
		
		JLabel welcomeLabel = new JLabel("Welcome to Urban Parks");
		welcomeLabel.setFont(new Font(welcomeLabel.getName(),
									Font.PLAIN, WELCOME_LABEL_SIZE));
		
		// Writing the code to underline the welcome label.
		Font font = welcomeLabel.getFont();
		@SuppressWarnings("unchecked")
		Map<TextAttribute, Integer> attributes = (Map<TextAttribute,
									Integer>) font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE,
						TextAttribute.UNDERLINE_ON);
		welcomeLabel.setFont(font.deriveFont(attributes));
		
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JLabel slogan = new JLabel("Where you can sign up or create a"
									+ " volunteering job.",
											SwingConstants.CENTER);
		slogan.setFont(new Font(slogan.getName(), Font.PLAIN,
												SLOGAN_LABEL_SIZE));
		slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Adding the labels to the center panel with proper padding.
		centerPanel.add(Box.createRigidArea(new Dimension(0,
											WELCOME_LABEL_PADDING)));
		centerPanel.add(welcomeLabel);
		centerPanel.add(Box.createRigidArea(new Dimension(0,
											SLOGAN_LABEL_PADDING)));
		centerPanel.add(slogan);
		centerPanel.add(Box.createRigidArea(new Dimension(0,
											LOG_IN_BUTTON_PADDING)));
		
		
		JLabel enterUserNameLabel = new JLabel("Username: ");
		JTextField userName = new JTextField("Username",
										USERNAME_TEXTBOX_WIDTH_SIZE);
		userName.setForeground(Color.GRAY);
		userName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (userName.getText().isEmpty()) {
					userName.setText("Username");
					userName.setForeground(Color.GRAY);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (userName.getText().equals("Username")) {
					userName.setText("");
					userName.setForeground(Color.BLACK);
				}
			}
		});
		JButton logInButton = new JButton();
		Action logInAction = new AbstractAction("Log In") {
			
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
		exitButton.setPreferredSize(logInButton.getMinimumSize());
		exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		JPanel inputPanel = new JPanel(new FlowLayout());
		inputPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		inputPanel.add(enterUserNameLabel);
		inputPanel.add(userName);
		inputPanel.add(logInButton);
		inputPanel.add(exitButton);
		
		// Adding the inputPanel with all the content to the center panel.
		centerPanel.add(inputPanel);
		
		myPanel.add(centerPanel, BorderLayout.CENTER);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}