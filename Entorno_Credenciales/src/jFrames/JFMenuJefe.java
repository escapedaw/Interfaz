package jFrames;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import bbdd.BD_CliEmpJef;
import bbdd.BD_Conector;
import bbdd.BD_PistaSala;
import bbdd.BD_ReservaVisita;
import jPanels.JPBotones;
import jPanels.JPMenuJefe;
import modelos.Cliente;
import modelos.Reserva;
import modelos.Visita;
import principal.Aplicacion;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class JFMenuJefe extends JFrame{
	private JPBotones pnlBoton;
	private BD_CliEmpJef bdCliEmpJef = new BD_CliEmpJef();
	private BD_PistaSala bdPisSal = new BD_PistaSala();
	private BD_ReservaVisita bdResVis = new BD_ReservaVisita();
	
	public JFMenuJefe() {
		setFont(new Font("Calibri Light", Font.BOLD, 15));
		setBackground(new Color(240, 240, 240));
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFCredenciales.class.getResource("/imagenes/Logo_escape.jpg")));
		setResizable(false);
		BD_Conector.BD_Ini("escapedaw");
		setTitle("MEN\u00DA JEFE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 363, 348);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize();	
	}
	
	private void initialize () {
		pnlBoton = new JPBotones ();
		pnlBoton.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		pnlBoton.setBounds(22, 190, 311, 47);
		
		JButton btnCredenciales = new JButton("1. Gestionar credenciales");
		btnCredenciales.setHorizontalAlignment(SwingConstants.LEFT);
		btnCredenciales.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnCredenciales.setBounds(80, 20, 190, 25);
		btnCredenciales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeCredenciales_click();
			}
		});
		getContentPane().add(btnCredenciales);
		
		// Boton gestionar empleados
		JButton btnEmpleados = new JButton("2. Gestionar empleados");
		btnEmpleados.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmpleados.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnEmpleados.setBounds(80, 56, 190, 25);
		btnEmpleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeEmpleados_click();
			}
		});
		getContentPane().add(btnEmpleados);
		
		// Boton gestionar salas
		JButton btnSalas = new JButton("3. Gestionar salas");
		btnSalas.setHorizontalAlignment(SwingConstants.LEFT);
		btnSalas.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnSalas.setBounds(80, 92, 190, 25);
		btnSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeSala_click();
			}
		});
		getContentPane().add(btnSalas);
		
		// Boton gestionar clientes
		JButton btnVerClientes = new JButton("4. Ver clientes");
		btnVerClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerClientes.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnVerClientes.setBounds(80, 128, 190, 25);
		btnVerClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeCliente_click();
			}
		});
		getContentPane().add(btnVerClientes);
		
		// Boton ver reservas
		JButton btnVerReservas = new JButton("5. Ver reservas");
		btnVerReservas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerReservas.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnVerReservas.setBounds(80, 164, 190, 25);
		btnVerReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeReserva_click();
			}
		});
		getContentPane().add(btnVerReservas);
		
		
		// Boton ver visitas
		JButton btnVerVisitas = new JButton("6. Ver visitas");
		btnVerVisitas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerVisitas.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnVerVisitas.setBounds(80, 200, 190, 25);
		btnVerVisitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeVisita_click();
			}
		});
		getContentPane().add(btnVerVisitas);
		
		
		// Boton ver facturacion
		JButton btnVerFacturacion = new JButton("7. Ver facturaci\u00F3n");
		btnVerFacturacion.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerFacturacion.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnVerFacturacion.setBounds(80, 236, 190, 25);
		btnVerFacturacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuJefeFacturacion_click();
			}
		});
		getContentPane().add(btnVerFacturacion);
		
		// Boton desconectar
		JButton btnDesconectar = new JButton("8. Desconectar");
		btnDesconectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Aplicacion.window.setVisible(true);
			}
		});
		btnDesconectar.setHorizontalAlignment(SwingConstants.LEFT);
		btnDesconectar.setFont(new Font("Calibri Light", Font.BOLD, 12));
		btnDesconectar.setBounds(80, 272, 190, 25);
		getContentPane().add(btnDesconectar);
	}
	
	private void menuJefeCredenciales_click() {
		// Ventana JFJefeCredenciales
		try {
			JFJefeCredenciales window = new JFJefeCredenciales(); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeEmpleados_click() {
		// Ventana JFJefeEmpleados
		try {
			JFJefeSalas window = new JFJefeSalas(); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeSala_click() {
		// Ventana JFJefeEmpleados
		try {
			JFJefeSalas window = new JFJefeSalas(); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeCliente_click () {
	// Ventana JFJefeCliente
		try {
			JFJefeCliente window = new JFJefeCliente(); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeReserva_click () {
		// Ventana JFJefeCliente
		try {
			JFJefeReserva window = new JFJefeReserva(); 
			if (window.isCerrar())
				window.dispose();
			else
				window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeVisita_click () {
		// Ventana JFJefeCliente
		try {
			JFJefeVisita window = new JFJefeVisita(); 
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void menuJefeFacturacion_click () {
		// Ventana JFJefeCliente
	}
}
