package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VolunteerSignUpDetailsPanel extends Observable implements Observer{
	
	JPanel myPanel;
	JPanel myButtons;
	public VolunteerSignUpDetailsPanel() {
		myPanel = new JPanel(new BorderLayout());
		init();
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
	private void init() {
		createButton();
	}
	
	private void createButton() {
		myButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 50));
		JButton backButton = new JButton(new AbstractAction() {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton signupButton = new JButton(new AbstractAction() {
			
			/**
		     * A generated serial version UID for object Serialization.
		     */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		myButtons.add(backButton);
		myButtons.add(signupButton);
		myPanel.add(myButtons, BorderLayout.SOUTH);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
