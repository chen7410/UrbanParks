/**
 * 
 */
package ui_brook;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Group 7
 *
 */
public class GUI {
	
	public GUI() {
		setup();
	}
	
	private void setup() {
		final JFrame guiFrame = new JFrame("Urban Parks");
		JPanel mainPanel = new JPanel();
		
		
		guiFrame.add(new LogInPage());
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setSize(600, 400);
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setResizable(false);
		guiFrame.setVisible(true);
	}
 }