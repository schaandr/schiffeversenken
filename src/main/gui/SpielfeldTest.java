package main.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class SpielfeldTest {

	private JFrame frame;
	private Spielfeld spielfeld;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpielfeldTest window = new SpielfeldTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public SpielfeldTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Schiffe versenken");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("<html><h1>Schiffe versenken</h1></html>");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		spielfeld = new Spielfeld();
		SpielfeldTableModel stm  = new SpielfeldTableModel();
		
				
		
		spielfeld.setModel(stm);
	
		frame.getContentPane().add(spielfeld);
		
	}

	
}
