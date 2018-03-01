/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_staff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ui.GUI;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class UrbanParksStaffSearchJobsPanel {
	
	private JPanel myPanel;
	
	public UrbanParksStaffSearchJobsPanel() {
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		setup();
	}
	
	private void setup() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		mainPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(buttonPanelLayout);
		
		JLabel searchJobsLabel = new JLabel("Search Jobs");
		searchJobsLabel.setFont(new Font(searchJobsLabel.getName(), Font.PLAIN, 25));
		
		// Writing the code to underline the welcome label.
		Font font = searchJobsLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		searchJobsLabel.setFont(font.deriveFont(attributes));
		
		searchJobsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel datesPanel = new JPanel(new FlowLayout());
		
		JLabel startDateLabel = new JLabel("Start date: ", SwingConstants.CENTER);
		
		// Adding a calendar to receive input for start date.
		
		
		
		datesPanel.add(startDateLabel);
		
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		mainPanel.add(searchJobsLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
	}
}