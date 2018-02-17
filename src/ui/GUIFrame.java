package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GUIFrame {
	/** The size of all panels. */
	public static final Dimension PANEL_SIZE = new Dimension(800, 600);
	
	/** The size of all buttons. */
	public static final Dimension BUTTON_SIZE = new Dimension(150, 40);
	
	/** The gap between two button. */
	public static final int BUTTON_GAP_WIDTH = 100;
	
	/** The space added below a button. */
	public static final int BUTTON_GAP_HEIGHT = 50;
	
	/** The empty border of VolunteerSignUpPanel. */
	public static final Border VOLUNTEER_SIGNUP_PANEL_BORDER= new EmptyBorder(50, 100, 50, 100);
	
	/** The background color. */
    public static final Color VOLUNTEER_SIGNUP_PANEL_BGCOLOR = new Color(153, 217, 234);  

}
