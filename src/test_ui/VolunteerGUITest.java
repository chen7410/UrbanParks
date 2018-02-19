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
import ui_volunteer.VolunteerSignUpConfirmationPanel;
import ui_volunteer.VolunteerSignUpDetailsPanel;
import ui_volunteer.VolunteerSignUpPanel;

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
		private JPanel myPanel;

		public GUI() {
			super("Urban Parks");
			init();
			createVolunteerSignUpPanel();
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
			myVolunteer = (Volunteer) myUsers.getUser("hasnah");
			
		}
		
		private void createVolunteerSignUpPanel() {
			List<Job> eligibleJobs = myJobs.getEligibleJobs(myVolunteer);
			VolunteerSignUpPanel p = new VolunteerSignUpPanel(eligibleJobs);
			myPanel = p.getPanel();
			p.addObserver(this);
			add(myPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void createVolunteerSignUpDetailsPanel(final int theJobID) {
			remove(myPanel);
			VolunteerSignUpDetailsPanel p = new VolunteerSignUpDetailsPanel(myJobs.getJob(theJobID));
			myPanel = p.getPanel();
			p.addObserver(this);
			add(myPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void createVolunteerSignUpConfirmationPanel(final int theJobID) {
			remove(myPanel);
			VolunteerSignUpConfirmationPanel p = new VolunteerSignUpConfirmationPanel(myJobs.getJob(theJobID));
			myPanel = p.getPanel();
			p.addObserver(this);
			add(myPanel, BorderLayout.CENTER);
			pack();
		}
		
		private void volunteerSignUpPanelActions(final ButtonSignal theSignal) {
			if (theSignal.getButtonName().toLowerCase().equals("view job details")) {
				createVolunteerSignUpDetailsPanel(theSignal.getJobID());
			} else {
				//go to home page
			}
		}
		
		private void volunteerSignUpDetailsPanelActions(final ButtonSignal theSignal) {
			if (theSignal.getButtonName().toLowerCase().equals("back")) {
				remove(myPanel);
				createVolunteerSignUpPanel();
			} else {
				myVolunteer.signup(myJobs.getJob(theSignal.getJobID()));
				createVolunteerSignUpConfirmationPanel(theSignal.getJobID());
			}
		}

		@Override
		public void update(final Observable theObservable, final Object theMessage) {
			ButtonSignal b = (ButtonSignal) theMessage;
			if (theObservable instanceof VolunteerSignUpPanel) {
				volunteerSignUpPanelActions(b);
			} else if (theObservable instanceof VolunteerSignUpDetailsPanel) {
				volunteerSignUpDetailsPanelActions(b);
			}
		}

	}
}
