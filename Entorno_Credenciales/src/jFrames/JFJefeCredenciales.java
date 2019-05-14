package jFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bbdd.BD_Conector;
import bbdd.BD_Credenciales;
import jPanels.JPBotones;
import jPanels.JPCredenciales;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JFJefeCredenciales extends JFrame {
	private JPCredenciales pnlCredencial;
	private JPBotones pnlBoton;
	private BD_Credenciales bbddC = new BD_Credenciales ();
	
	public JFJefeCredenciales() {
		setFont(new Font("Calibri Light", Font.BOLD, 15));
		setBackground(new Color(240, 240, 240));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(JFCredenciales.class.getResource("/imagenes/Logo_escape.jpg")));
		setResizable(false);
		BD_Conector.BD_Ini("escapedaw");
		setTitle("MEN\u00DA CREDENCIALES");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 326, 221);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize();

	}

	private void initialize() {
		pnlBoton = new JPBotones ();
		pnlBoton.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		pnlBoton.setBounds(22, 190, 311, 47);
		// Crear credencial
		JButton btnCrearCredencial = new JButton("1. Crear credenciales empleado");
		btnCrearCredencial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				crearCredencial_click();
			}
		});
		btnCrearCredencial.setHorizontalAlignment(SwingConstants.LEFT);
		btnCrearCredencial.setBounds(50, 23, 215, 25);
		getContentPane().add(btnCrearCredencial);

		// Modificar contraseña
		JButton btnModPassw = new JButton("2. Modificar contrase\u00F1a");
		btnModPassw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				modificarContraseña_click();
			}
		});
		btnModPassw.setHorizontalAlignment(SwingConstants.LEFT);
		btnModPassw.setBounds(50, 61, 215, 25);
		getContentPane().add(btnModPassw);

		// Eliminar
		JButton btnEliminar = new JButton("3. Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eliminarCredencial_click();
			}
		});
		btnEliminar.setHorizontalAlignment(SwingConstants.LEFT);
		btnEliminar.setBounds(50, 99, 215, 25);
		getContentPane().add(btnEliminar);

		// Salir
		JButton btnSalir = new JButton("4. Salir");
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				salir_click();
			}
		});
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalir.setBounds(50, 137, 215, 25);
		getContentPane().add(btnSalir);
	}

	private void crearCredencial_click() {
		// Ventana JFJefeCliente
		try {
			JFCredenciales window = new JFCredenciales(2); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modificarContraseña_click() {
		try {
			JFCredenciales window = new JFCredenciales(3); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void eliminarCredencial_click() {
		try {
			JFCredenciales window = new JFCredenciales(4); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void salir_click() {
		dispose();
	}
}
