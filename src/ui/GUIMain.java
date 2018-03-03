/*
 * TCSS 360 - Winter 2018
 * Urban Parks Project
 */
package ui;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This is the main class that will be the 
 * driver for Urban Parks program.
 * 
 * @author  Group 7
 * @version March 5, 2018
 *
 */
public class GUIMain {

	public static void main(String[] args) {		
		
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
			/**
			 * Creates a new GUI for Urban Parks.
			 */
			@Override
			public void run() {
				new GUI();
			}
		});

	}
}
