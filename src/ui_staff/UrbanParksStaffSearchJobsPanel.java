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
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.AbstractAction;
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

import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * A JPanel that lets Urban Parks Staff search the system
 * for jobs in a given period. This panel will have two text
 * fields that are for the start date of the period and the 
 * end date of the period.
 * 
 * @author  Group 7
 * @version March 5, 2018
 */
public class UrbanParksStaffSearchJobsPanel extends Observable {
	
	private JPanel myPanel;
	
	/**
	 * Creates a panel that will allow Urban Parks staff to search for
	 * jobs within a given period.
	 */
	public UrbanParksStaffSearchJobsPanel() {
		myPanel = new JPanel(new BorderLayout());
		myPanel.setPreferredSize(GUI.PANEL_SIZE);
		myPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		setup();
	}
	
	private void setup() {
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createSoftBevelBorder(1));
		BoxLayout buttonPanelLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(buttonPanelLayout);
		
		JLabel searchJobsLabel = new JLabel("Search Jobs");
		searchJobsLabel.setFont(new Font(searchJobsLabel.getName(), Font.PLAIN, 40));
		
		// Writing the code to underline the welcome label.
		Font font = searchJobsLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		searchJobsLabel.setFont(font.deriveFont(attributes));
		
		searchJobsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel startDatePanel = new JPanel(new FlowLayout());
		startDatePanel.setMaximumSize(new Dimension(800, 0));
		startDatePanel.setBackground(Color.WHITE);
		
		JPanel endDatePanel = new JPanel(new FlowLayout());
		endDatePanel.setPreferredSize(new Dimension(800, 0));
		endDatePanel.setBackground(Color.WHITE);
		
		JLabel startDateLabel = new JLabel("Start date (MM/DD/YY): ", SwingConstants.CENTER);
		JTextField startDateInputField = new JTextField("", 10);
		
		JLabel endDateLabel = new JLabel("  End date (MM/DD/YY): ", SwingConstants.CENTER);
		JTextField endDateInputField = new JTextField("", 10);
		
		
		// Panel for buttons at the south quadrant of the frame.
		JPanel buttonsPanel = new JPanel(new FlowLayout(
				FlowLayout.CENTER, GUI.BUTTON_GAP_WIDTH, GUI.BUTTON_GAP_HEIGHT));
		buttonsPanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		
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
		homeButton.setPreferredSize(GUI.BUTTON_SIZE);
		homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton searchButton = new JButton(new AbstractAction("Search") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (startDateInputField.getText().isEmpty() || endDateInputField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Please enter a valid date.",
							"Invalid input", JOptionPane.ERROR_MESSAGE);
				} else {
					
					DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
					
					LocalDate localStartDate = null;
					LocalDate localEndDate = null;
					
					try {
						localStartDate = LocalDate.parse(startDateInputField.getText(), myDateFormatter);
						localEndDate = LocalDate.parse(endDateInputField.getText(), myDateFormatter);
						
						
						
						
						// TODO: check if the start date is before or the same day as the end date
						
						Object[] localDateArray = new LocalDate[2];
						localDateArray[0] = localStartDate;
						localDateArray[1] = localEndDate;
						
						setChanged();
						notifyObservers(new ButtonSignal("submit", localDateArray));
						
					} catch (final DateTimeParseException theException) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Invalid date. Please "
								+ "enter the dates using"
								+ " MM/DD/YY format.",
								"Invalid input", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
//		searchButton.setSize(GUI.BUTTON_SIZE);
		searchButton.setPreferredSize(GUI.BUTTON_SIZE);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		buttonsPanel.add(homeButton);
		buttonsPanel.add(searchButton);

		
		startDatePanel.add(startDateLabel);
		startDatePanel.add(startDateInputField);
		
		endDatePanel.add(endDateLabel);
		endDatePanel.add(endDateInputField);
		
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		mainPanel.add(searchJobsLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		mainPanel.add(startDatePanel);
		mainPanel.add(endDatePanel);
		
		myPanel.add(mainPanel, BorderLayout.CENTER);
		myPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * @return an instance of UrbanParksStaffSearchJobsPanel.
	 */
	public JPanel getPanel() {
		return myPanel;
	}	
}