package jPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class JPCredenciales extends JPanel{
	private JTextField txtUsuario;
	private JPasswordField txtPasswd;
	public JPCredenciales(int pos) {
		setLayout(null);
		if (pos == 1 || pos ==3) {
			JLabel lblIdent = new JLabel("Identificaci\u00F3n del usuario:");
			lblIdent.setBounds(58, 21, 200, 20);
			lblIdent.setFont(new Font("Calibri Light", Font.BOLD, 16));
			add(lblIdent);
		}
		 if (pos == 2) {
			JLabel lblIdent = new JLabel("DNI del usuario:");
			lblIdent.setBounds(58, 21, 200, 20);
			lblIdent.setFont(new Font("Calibri Light", Font.BOLD, 16));
			add(lblIdent);
		 }
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(58, 52, 200, 20);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPasswd = new JLabel("Contrase\u00F1a:");
		lblPasswd.setFont(new Font("Calibri Light", Font.BOLD, 16));
		lblPasswd.setBounds(58, 83, 110, 20);
		add(lblPasswd);
		
		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(58, 112, 200, 20);
		add(txtPasswd);
	}
	
	public boolean validarCajas() {
		if (txtUsuario.getText().equals("") )
			if (txtPasswd.getText().equals("")) // Al ser passwd da fallo?
				return false;
		return true;
	}
	
	public String getUsuario () {
		return txtUsuario.getText();
	}
	
	public String getPasswd () {
		return txtPasswd.getText();
	}
	
	public void limpiarPanel() {		
		txtUsuario.setText("");		
		txtPasswd.setText("");		
	}
}
