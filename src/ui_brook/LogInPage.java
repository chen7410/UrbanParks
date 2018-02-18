/**
 * 
 */
package ui_brook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Group 7
 *
 */
public class LogInPage extends JPanel {

	private static final Color BACKGROUND_COLOR = Color.white;
	
	private static final Dimension DEFAULT_SIZE = new Dimension(200, 400);
	
	public LogInPage() {
		super();
		setLayout(new BorderLayout());
		
		JLabel welcome = new JLabel("Welcome to Urban Parks");
		
		JTextField userName = new JTextField("User name", 10);
		JButton logIn = new JButton("Log in");
		JButton exit = new JButton("Exit");
		
		JPanel welcomePanel = new JPanel();
		welcomePanel.add(welcome);
		add(welcomePanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		
		JPanel inputPanel = new JPanel();
		inputPanel.add(userName);
		inputPanel.add(logIn);
		inputPanel.add(exit);
		
//		JLabel description = new JLabel("Where you can sign up or create a volunteering job.");
//		
//		centerPanel.add(description);
		centerPanel.add(inputPanel);
		
		JPanel exitPanel = new JPanel();
		exitPanel.add(exit);
		
		
		add(centerPanel, BorderLayout.CENTER);
		//add(exitPanel, BorderLayout.SOUTH);
		
		
		
//		add(welcome, BorderLayout.NORTH);
//		add(userName, BorderLayout.WEST);
//		add(logIn, BorderLayout.EAST);
//		add(exit, BorderLayout.SOUTH);
		
		setPreferredSize(DEFAULT_SIZE);
		setBackground(BACKGROUND_COLOR);
	}
}