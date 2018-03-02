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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Job;
import ui.ButtonSignal;
import ui.GUI;

/**
 * 
 * 
 * @author Brook Negussie
 * @version March 5, 2018
 */
public class UrbanParksStaffSearchJobsPanel extends Observable {
	
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
		searchJobsLabel.setFont(new Font(searchJobsLabel.getName(), Font.PLAIN, 40));
		
		// Writing the code to underline the welcome label.
		Font font = searchJobsLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		searchJobsLabel.setFont(font.deriveFont(attributes));
		
		searchJobsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel startDatePanel = new JPanel(new FlowLayout());
		startDatePanel.setMaximumSize(new Dimension(800, 0));
		startDatePanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JPanel endDatePanel = new JPanel(new FlowLayout());
		endDatePanel.setPreferredSize(new Dimension(800, 0));
		endDatePanel.setBackground(GUI.VOLUNTEER_PANELS_BGCOLOR);
		
		JLabel startDateLabel = new JLabel("Start date (MM/dd/yyyy): ", SwingConstants.CENTER);
		JTextField startDateInputField = new JTextField("", 10);
		
		JLabel endDateLabel = new JLabel("End date (MM/dd/yyyy): ", SwingConstants.CENTER);
		JTextField endDateInputField = new JTextField("", 10);
		
		
		// Panel for buttons at the south quadrant of the frame.
		JPanel buttonsPanel = new JPanel(new FlowLayout());
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
		homeButton.setSize(GUI.BUTTON_SIZE);
		homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		JButton submitButton = new JButton("Submit A Button");
		submitButton.setEnabled(false);
		
		
		if (!startDateInputField.getText().isEmpty() && !endDateInputField.getText().isEmpty()) {
			submitButton.setEnabled(true);
			
			/*
			 * Parsing the different values from the start date input
			 * using a Scanner.
			 */
			Scanner startDateScanner = new Scanner(startDateInputField.getText());
			startDateScanner.useDelimiter("/");
			
			String startMonth = startDateScanner.next();
			int startMonthInt = Integer.parseInt(startMonth);
			String startDay = startDateScanner.next();
			int startDayInt = Integer.parseInt(startDay);
			String startYear = startDateScanner.next();
			int startYearInt = Integer.parseInt(startYear);
			
			@SuppressWarnings("deprecation")
			Date startDate = new Date(startYearInt, startMonthInt, startDayInt);
			
			startDateScanner.close();
			
			
			/*
			 * Parsing the different values from the end date input
			 * using a Scanner.
			 */
			Scanner endDateScanner = new Scanner(startDateInputField.getText());
			startDateScanner.useDelimiter("/");
			
			String endMonth = endDateScanner.next();
			int endMonthInt = Integer.parseInt(endMonth);
			String endDay = endDateScanner.next();
			int endDayInt = Integer.parseInt(endDay);
			String endYear = endDateScanner.next();
			int endYearInt = Integer.parseInt(endYear);
			
			@SuppressWarnings("deprecation")
			Date endDate = new Date(startYearInt, startMonthInt, startDayInt);
			
			endDateScanner.close();
			
			
			LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			
			submitButton = createSubmitButton(localStartDate, localEndDate);
		}

		
		buttonsPanel.add(homeButton);
		buttonsPanel.add(submitButton);

		
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
	
	public JButton createSubmitButton(final LocalDate theLocalStartDate, final LocalDate theLocalEndDate) {
		JButton button = new JButton(
				new AbstractAction("Submit") {

			/** */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setChanged();
				notifyObservers(new ButtonSignal("submit", 0));
			}
		});
		return button;
	}
	
	public JPanel getPanel() {
		return myPanel;
	}	
}