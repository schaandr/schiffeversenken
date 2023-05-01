package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class SchiffeVersenkenGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static SchiffeVersenkenGUI schvergui = null;

	public SchiffeVersenkenGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Horizontales Panel
		JPanel content = new JPanel(new BorderLayout());
		this.setContentPane(content);
		
	}
	
	
	public static void main(String[] args) {
		schvergui = new SchiffeVersenkenGUI();
		schvergui.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

	}

}
