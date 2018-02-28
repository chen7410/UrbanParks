package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Job;
import model.JobMap;
import model.User;
import model.UserMap;
import model.Volunteer;
import ui_volunteer.VolunteerCancellationConfirmationPanel;
import ui_volunteer.VolunteerHomePanel;
import ui_volunteer.VolunteerSignUpConfirmationPanel;
import ui_volunteer.VolunteerSignUpDetailsPanel;
import ui_volunteer.VolunteerSignUpPanel;
import ui_volunteer.VolunteerSignedUpDetailsPanel;
import ui_volunteer.VolunteerViewAllUpCommingJobPanel;

public class GUI extends JFrame implements Observer {
	/** The size of all panels. */
	public static final Dimension PANEL_SIZE = new Dimension(800, 600);

	/** The size of all buttons. */
	public static final Dimension BUTTON_SIZE = new Dimension(150, 40);

	/** The size for label with short text */
	public static final Dimension JLABEL_SHORT_TEXT = new Dimension(750, 30);

	/** The size for label with long text that needs to be wrapped */
	public static final Dimension JLABEL_LONG_TEXT = new Dimension(750, 55);

	/** The gap between two button. */
	public static final int BUTTON_GAP_WIDTH = 100;

	/** The space added below a button. */
	public static final int BUTTON_GAP_HEIGHT = 50;
	
	/**
	 * The number of jobs which will be displayed by the Upcoming
	 * Jobs list for both the Park Managers and the Volunteers.
	 * */
	public static final int UPCOMING_JOBS_MAX_NUM_DISPLAY = 5;

	/** The empty border of VolunteerSignUpPanel. */
	public static final Border VOLUNTEER_SIGNUP_PANEL_BORDER = new EmptyBorder(50, 100, 50, 100);

	/** The background color. */
	public static final Color VOLUNTEER_PANELS_BGCOLOR = new Color(153, 217, 234);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserMap myUsers;
	private JobMap myJobs;
	private Volunteer myVolunteer;
	private JPanel myCurrentPanel;

	public GUI() {
		super("Urban Parks");
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void init() {
		myJobs = new JobMap();
		myUsers = new UserMap();
		myUsers.loadUserMap(UserMap.USERS_DATA_FILE);
		myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
		createLoginPanel();
	}

	private void createLoginPanel() {
		LoginPanel loginPanel = new LoginPanel(myUsers);
		myCurrentPanel = loginPanel.getPanel();
		loginPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerHomePanel() {
		remove(myCurrentPanel);
		VolunteerHomePanel homePanel = new VolunteerHomePanel(myVolunteer.getJobList(myJobs));
		myCurrentPanel = homePanel.getPanel();
		homePanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerSignUpPanel() {
		remove(myCurrentPanel);
		List<Job> eligibleJobs = myJobs.getEligibleJobs(myVolunteer);
		VolunteerSignUpPanel signUpPanel = new VolunteerSignUpPanel(eligibleJobs);
		myCurrentPanel = signUpPanel.getPanel();
		signUpPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerSignUpDetailsPanel(final int theJobID) {
		remove(myCurrentPanel);
		VolunteerSignUpDetailsPanel detailsPanel = new VolunteerSignUpDetailsPanel(myJobs.getJob(theJobID));
		myCurrentPanel = detailsPanel.getPanel();
		detailsPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerSignUpConfirmationPanel(final int theJobID) {
		remove(myCurrentPanel);
		VolunteerSignUpConfirmationPanel confirmationPanel = new VolunteerSignUpConfirmationPanel(
				myJobs.getJob(theJobID));
		myCurrentPanel = confirmationPanel.getPanel();
		confirmationPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerViewAllUpCommingJobPanel() {
		remove(myCurrentPanel);
		VolunteerViewAllUpCommingJobPanel upcomingPanel = new VolunteerViewAllUpCommingJobPanel(myVolunteer.getJobList(myJobs));
		myCurrentPanel = upcomingPanel.getPanel();
		upcomingPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerSignedUpDetailsPanel(final int theJobID) {
		remove(myCurrentPanel);
		VolunteerSignedUpDetailsPanel detailsPanel = new VolunteerSignedUpDetailsPanel(myJobs.getJob(theJobID));
		myCurrentPanel = detailsPanel.getPanel();
		detailsPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void createVolunteerCancellationConfirmationPanel(final int theJobID) {
		remove(myCurrentPanel);
		VolunteerCancellationConfirmationPanel confirmationPanel = new VolunteerCancellationConfirmationPanel(
				myJobs.getJob(theJobID));
		myCurrentPanel = confirmationPanel.getPanel();
		confirmationPanel.addObserver(this);
		add(myCurrentPanel, BorderLayout.CENTER);
		pack();
	}

	private void loginPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("login")) {
			User user = theSignal.getUser();
			if (user instanceof Volunteer) {
				myVolunteer = (Volunteer) user;
				createVolunteerHomePanel();
			}
		}
	}

	private void volunteerHomePanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("logout")) {
			remove(myCurrentPanel);
			createLoginPanel();
		} else if (theSignal.getButtonName().toLowerCase().equals("signup")) {
			createVolunteerSignUpPanel();
		} else if (theSignal.getButtonName().toLowerCase().equals("upcoming")) {
			createVolunteerViewAllUpCommingJobPanel();
		}
	}

	private void volunteerSignUpPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
			createVolunteerSignUpDetailsPanel(theSignal.getJobID());
		} else if(theSignal.getButtonName().toLowerCase().equals("home")){
			createVolunteerHomePanel();
		}
	}

	private void volunteerSignUpDetailsPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("back")) {
			createVolunteerSignUpPanel();
		} else {
			myVolunteer.signup(myJobs.getJob(theSignal.getJobID()));
			createVolunteerSignUpConfirmationPanel(theSignal.getJobID());
		}
	}

	private void volunteerSignUpConfirmationPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("home")) {
			createVolunteerHomePanel();
		} else {
			createVolunteerViewAllUpCommingJobPanel();
		}
	}

	private void volunteerViewAllUpCommingJobPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
			createVolunteerSignedUpDetailsPanel(theSignal.getJobID());
		} else if (theSignal.getButtonName().toLowerCase().equals("home")) {
			createVolunteerHomePanel();
		}
	}

	private void volunteerSignedUpDetailsPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("remove")) {
			myVolunteer.cancelJob(myJobs.getJob(theSignal.getJobID()));
			createVolunteerCancellationConfirmationPanel(theSignal.getJobID());
		} else if (theSignal.getButtonName().toLowerCase().equals("back")) {
			createVolunteerViewAllUpCommingJobPanel();
		}
	}

	private void volunteerCancellationConfirmationPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("home")) {
			createVolunteerHomePanel();
		} else if (theSignal.getButtonName().toLowerCase().equals("upcoming")) {
			createVolunteerViewAllUpCommingJobPanel();
		}
	}

	private void volunteerPanelsCases(final Observable theObservable, final Object theMessage) {
		ButtonSignal signal = (ButtonSignal) theMessage;
		if (theObservable instanceof LoginPanel) {
			loginPanelActions(signal);
		} else if (theObservable instanceof VolunteerHomePanel) {
			volunteerHomePanelActions(signal);
		} else if (theObservable instanceof VolunteerSignUpPanel) {
			volunteerSignUpPanelActions(signal);
		} else if (theObservable instanceof VolunteerSignUpDetailsPanel) {
			volunteerSignUpDetailsPanelActions(signal);
		} else if (theObservable instanceof VolunteerSignUpConfirmationPanel) {
			volunteerSignUpConfirmationPanelActions(signal);
		} else if (theObservable instanceof VolunteerViewAllUpCommingJobPanel) {
			volunteerViewAllUpCommingJobPanelActions(signal);
		} else if (theObservable instanceof VolunteerSignedUpDetailsPanel) {
			volunteerSignedUpDetailsPanelActions(signal);
		} else if (theObservable instanceof VolunteerCancellationConfirmationPanel) {
			volunteerCancellationConfirmationPanelActions(signal);
		}
	}

	
	
	/**************************Park Manager*******************************/
	
	/*
	private void ParkManagerSignUpPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
			createVolunteerSignUpDetailsPanel(theSignal.getJobID());
		} else if(theSignal.getButtonName().toLowerCase().equals("home")){
			createVolunteerHomePanel();
		}
	}
	*/
	
	private void ParkManagerViewAllUpCommingJobPanelActions(final ButtonSignal theSignal) {
		if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
			//createVolunteerSignedUpDetailsPanel(theSignal.getJobID());
			System.out.println("create Park manager job detail panel.");
		} else if (theSignal.getButtonName().toLowerCase().equals("home")) {
			//createVolunteerHomePanel();
			System.out.println("back to park manager home.");
		}
	}
	
	
	
	
	
	
	
	
	private void parkManagerPanelsCases(final Observable theObservable, final Object theMessage) {

	}

	@Override
	public void update(final Observable theObservable, final Object theMessage) {
		volunteerPanelsCases(theObservable, theMessage);
		parkManagerPanelsCases(theObservable, theMessage);
	}

}
