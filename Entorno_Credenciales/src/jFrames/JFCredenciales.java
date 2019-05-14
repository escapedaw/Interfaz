package jFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import bbdd.BD_CliEmpJef;
import bbdd.BD_Conector;
import bbdd.BD_Credenciales;
import jPanels.JPBotones;
import jPanels.JPCredenciales;
import modelos.Credencial;

import java.awt.Toolkit;
import java.awt.Window.Type;

public class JFCredenciales extends JFrame{
	private JPCredenciales pnlCredencial;
	private JPBotones pnlBoton;
	private BD_Credenciales bdCre = new BD_Credenciales ();
	
	public JFCredenciales(int pos) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFCredenciales.class.getResource("/imagenes/Logo_escape.jpg")));
		setResizable(false);
		BD_Conector.BD_Ini("escapedaw");
		if (pos == 1) {
			setTitle("Iniciar sesión");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		if (pos == 2) {
			setTitle("Crear usuario");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		if (pos == 3) {
			setTitle("Modificar contraseña");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		if (pos == 4) {
			setTitle("Eliminar usuario");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		setBounds(100, 100, 363, 288);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize(pos);	
	}		
	// Pintar paneles
	private void initialize (int pos) {
		try {
			pnlCredencial = new JPCredenciales (pos);
			pnlCredencial.setSize(311, 155);
			pnlCredencial.setLocation(22, 21);
			pnlCredencial.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));

		}
		catch (Exception e) { 
			JOptionPane.showMessageDialog(null, "En estos momentos no se puede acceder a la base de datos", "AVISO",JOptionPane.INFORMATION_MESSAGE);
		}
		
		getContentPane().add(pnlCredencial);
		pnlCredencial.setLayout(null);
		
		// Panel de los botones
		pnlBoton=new JPBotones();
		pnlBoton.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		pnlBoton.setBounds(22, 190, 311, 47);
		
		// Boton ACEPTAR
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAceptar.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnAceptar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			aceptar_click(pos);
		}
		});
		pnlBoton.add(btnAceptar);
		
		// Boton CANCELAR
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnCancelar.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			cancelar_click();
			}
		});
		pnlBoton.add(btnCancelar);
		
		getContentPane().add(pnlBoton);
		pnlBoton.setVisible(true);
	}
	
	public void aceptar_click(int pos) {
		if (!pnlCredencial.validarCajas())
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "ERROR",JOptionPane.ERROR_MESSAGE);
		else {
			if (pos == 1) {
				String rol=bdCre.conectarse(pnlCredencial.getUsuario(), pnlCredencial.getPasswd());
				switch(rol) {
				 case "J":
					 JOptionPane.showMessageDialog(null, "Sesión iniciada como jefe", "INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
					 pnlCredencial.limpiarPanel();
					 try {
						JFMenuJefe window = new JFMenuJefe(); 
						window.setVisible(true);
						this.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				 case "E":
					 JOptionPane.showMessageDialog(null, "Sesión iniciada como empleado", "INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
						break;
				 case "C":
					 JOptionPane.showMessageDialog(null, "Sesión iniciada como cliente", "INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
						break;
				 default:
					 JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			
			if (pos == 2) {
				String dni = bdCre.buscarId(pnlCredencial.getUsuario(), "empleados");
				if (dni == null)
					JOptionPane.showMessageDialog(null, "Fallo técnico, póngase en contacto con el administrador", "ERROR",JOptionPane.ERROR_MESSAGE);
				else if (dni.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null, "No se ha encontrado ningún usuario con este DNI", "AVISO",JOptionPane.INFORMATION_MESSAGE);
				else {
					int c = bdCre.anadir_Usuario(new Credencial(dni, pnlCredencial.getPasswd(), 'E'));
					switch (c) {
					case 0:
						JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
						break;
					case -1:
						JOptionPane.showMessageDialog(null, "Problemas técnicos, póngase en contacto con el administrador", "ERROR",JOptionPane.ERROR_MESSAGE);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Credencial creado correctamente", "CONFIRMACIÓN",JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
			}
			
			if (pos == 3) {
				int c = bdCre.cambiar_clave(pnlCredencial.getUsuario(), pnlCredencial.getPasswd());
				switch (c) {
				case 0:
					JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "Problemas técnicos, póngase en contacto con el administrador", "ERROR",JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Credencial creado correctamente", "CONFIRMACIÓN",JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			
			if (pos == 4) {
				int c = bdCre.eliminar_Usuario(pnlCredencial.getUsuario());
				switch (c) {
				case 0:
					JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "Problemas técnicos, póngase en contacto con el administrador", "ERROR",JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "CONFIRMACIÓN",JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}

		}
	}
	
	public void cancelar_click() {
		pnlCredencial.limpiarPanel();
	}
}
