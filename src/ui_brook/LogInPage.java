/**
 * 
 */
package ui_brook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Group 7
 *
 */
public class LogInPage extends JPanel {

	private static final Color BACKGROUND_COLOR = Color.white;
	
	private static final Dimension DEFAULT_SIZE = new Dimension(200, 400);
	
	private JLabel welcome;
	
	public LogInPage() {
		super();
		
		setLayout(new BorderLayout());
		
//		JPanel north = new JPanel();
		welcome = new JLabel("Welcome to Urban Parks");
//		north.setLayout(new BorderLayout());
//		north.add(welcome);
		
		// add(north, BorderLayout.NORTH);
		
		add(welcome, BorderLayout.CENTER);
		setPreferredSize(DEFAULT_SIZE);
		setBackground(BACKGROUND_COLOR);
	}
}