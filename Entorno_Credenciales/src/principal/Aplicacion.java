package principal;

import java.awt.EventQueue;
import javax.swing.JFrame;

import jFrames.JFCredenciales;

public class Aplicacion {
	public static JFCredenciales window;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new JFCredenciales(1); 
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}