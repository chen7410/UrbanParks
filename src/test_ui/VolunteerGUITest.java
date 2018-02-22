package test_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.Job;
import model.JobMap;
import model.User;
import model.UserMap;
import model.Volunteer;
import ui.ButtonSignal;
import ui.LoginPanel;
import ui_volunteer.VolunteerCancellationConfirmationPanel;
import ui_volunteer.VolunteerHomePanel;
import ui_volunteer.VolunteerSignUpConfirmationPanel;
import ui_volunteer.VolunteerSignUpDetailsPanel;
import ui_volunteer.VolunteerSignUpPanel;
import ui_volunteer.VolunteerSignedUpDetailsPanel;
import ui_volunteer.VolunteerViewAllUpCommingJobPanel;

public class VolunteerGUITest {

	/** The main window. */

	public static void main(final String[] theArgs) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (final UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (final IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (final InstantiationException ex) {
			ex.printStackTrace();
		} catch (final ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI();
			}
		});
	}

	private static class GUI extends JFrame implements Observer {

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
			myUsers.loadUserMap(User.USERS_DATA_FILE);
			myJobs.loadJobMap(JobMap.JOBS_DATA_FILE);
			createLoginPanel();
		}
		
		private void createLoginPanel() {
			LoginPanel loginPanel  = new LoginPanel(myUsers);
			myCurrentPanel = loginPanel.getPanel();
			loginPanel.addObserver(this);
			add(myCurrentPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void createVolunteerHomePanel() {
			remove(myCurrentPanel);
			VolunteerHomePanel homePanel = new VolunteerHomePanel(myVolunteer);
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
			VolunteerSignUpConfirmationPanel confirmationPanel = new VolunteerSignUpConfirmationPanel(myJobs.getJob(theJobID));
			myCurrentPanel = confirmationPanel.getPanel();
			confirmationPanel.addObserver(this);
			add(myCurrentPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void createVolunteerViewAllUpCommingJobPanel() {
			remove(myCurrentPanel);
			VolunteerViewAllUpCommingJobPanel upcomingPanel = new VolunteerViewAllUpCommingJobPanel(myVolunteer);
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
			VolunteerCancellationConfirmationPanel confirmationPanel = new VolunteerCancellationConfirmationPanel(myJobs.getJob(theJobID));
			myCurrentPanel = confirmationPanel.getPanel();
			confirmationPanel.addObserver(this);
			add(myCurrentPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void loginPanelAction(final ButtonSignal theSignal) {
			if(theSignal.getButtonName().toLowerCase().equals("login")) {
				User user = theSignal.getUser();
				if (user instanceof Volunteer) {
					myVolunteer = (Volunteer) user;
					createVolunteerHomePanel();
				}
			} else {
				//exit program
			}
		}
		
		private void volunteerHomePanelActions(final ButtonSignal theSignal) {
			if(theSignal.getButtonName().toLowerCase().equals("logout")) {
				remove(myCurrentPanel);
				createLoginPanel();
			} else if (theSignal.getButtonName().toLowerCase().equals("signup")) {
				createVolunteerSignUpPanel();
			} else if (theSignal.getButtonName().toLowerCase().equals("upcoming")){
				createVolunteerViewAllUpCommingJobPanel();
			}
		}
		
		private void volunteerSignUpPanelActions(final ButtonSignal theSignal) {
			if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
				createVolunteerSignUpDetailsPanel(theSignal.getJobID());
			} else {
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
				loginPanelAction(signal);
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
		
		private void parkManagerPanelsCases(final Observable theObservable, final Object theMessage) {
			
		}

		@Override
		public void update(final Observable theObservable, final Object theMessage) {
			volunteerPanelsCases(theObservable, theMessage);
			parkManagerPanelsCases(theObservable, theMessage);
		}

	}
}
