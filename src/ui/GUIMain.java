package ui;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
			@Override
			public void run() {
				new GUI();
			}
		});

	}
}
