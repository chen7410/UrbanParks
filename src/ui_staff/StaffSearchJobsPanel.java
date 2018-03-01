/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */

package ui_staff;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Observable;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ui.ButtonSignal;
import ui.GUI;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class StaffSearchJobsPanel extends Observable {
	
	private JPanel myPanel;
	
	public StaffSearchJobsPanel() {
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
		
		JPanel startDatePanel = new JPanel(new FlowLayout());
		JPanel endDatePanel = new JPanel(new FlowLayout());
		
		JLabel startDateLabel = new JLabel("Start date (MM/dd/yyyy): ", SwingConstants.CENTER);
		JTextField startDateInputField = new JTextField("", 11);
		
		JLabel endDateLabel = new JLabel("Start date (MM/dd/yyyy): ", SwingConstants.CENTER);
		JTextField endDateInputField = new JTextField("", 11);
		
		
		// Panel for buttons at the south quadrant of the frame.
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		
		JButton homeButton = new JButton(
				new AbstractAction("Home") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("home", 0));
			}
		});
		homeButton.setSize(GUI.BUTTON_SIZE);
		homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = dateFormat.parse(startDateInputField.getText());
			Date endDate = dateFormat.parse(endDateInputField.getText());
			
			JButton submitButton = createRadioButton(startDate, endDate);
			
			buttonsPanel.add(homeButton);
			buttonsPanel.add(submitButton);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startDatePanel.add(startDateLabel);
		startDatePanel.add(startDateInputField);
		
		endDatePanel.add(endDateLabel);
		endDatePanel.add(endDateInputField);
		
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 75)));
		mainPanel.add(searchJobsLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		mainPanel.add(startDatePanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		mainPanel.add(startDatePanel);
		
		myPanel.add(mainPanel, BorderLayout.CENTER);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public JButton createRadioButton(final Date theStartDate, final Date theEndDate) {
		JButton button = new JButton(
				new AbstractAction("Submit") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO: something 
			}
		});
		return button;
	}
	
	public JPanel getPanel() {
		return myPanel;
	}	
}