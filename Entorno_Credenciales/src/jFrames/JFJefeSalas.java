package jFrames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bbdd.BD_Conector;
import bbdd.BD_PistaSala;
import modelos.Sala;

import jPanels.JPBotones;
import jPanels.JPLista;
import jPanels.JPJefeSalas;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import java.awt.Toolkit;

public class JFJefeSalas extends JFrame {
	private JPJefeSalas pnlSala;
	private JPBotones pnlBotones;
	private JPLista pnlLista;
	private BD_PistaSala bbdd = new BD_PistaSala ();
	JList<Sala> list;
	
	public JFJefeSalas () {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFJefeSalas.class.getResource("/imagenes/Logo_escape.jpg")));
		BD_Conector.BD_Ini("escapedaw");
			setTitle("Salas");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 518, 398);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize();	
	}
	
	private void initialize () {
		// Pinta los paneles en la ventana
		try {
			pnlSala = new JPJefeSalas (bbdd.mostrarIdentEmple(), bbdd.mostrarIdentJefe(),Sala.calcularNumeroSala("NSALA", "salas", "SA"));
			pnlSala.setSize(311, 258);
			pnlSala.setLocation(22, 21);
			pnlSala.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
				

		}
			catch (Exception e) { // Modificar, es el acceso a bbdd
			JOptionPane.showMessageDialog(null, "En estos momentos no se puede acceder a la base de datos", "AVISO",JOptionPane.INFORMATION_MESSAGE);
		}
		getContentPane().add(pnlSala); // Esto sirve para añadir el panel al JFrame
		

		// Panel lista nSala			
		pnlLista=new JPLista();	
		pnlLista.setBounds(343, 21, 134, 258);
		pnlLista.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		DefaultListModel modelo=new DefaultListModel();
		Vector<Sala> v=bbdd.listarSalas();
		for(Sala s: v ) {
			modelo.addElement(s);
		}

		list = new JList<Sala>();
		list.setModel(modelo);
		list.setBounds(20, 21, 92, 214);
		pnlLista.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {	
							if (list.getSelectedIndex()!=-1) {
							pnlSala.setDatos(list.getSelectedValue());
							pnlSala.setInHabilitado(false);
							}
							
					}
			});
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(pnlLista);
		pnlLista.setVisible(true);
		
		// Panel de los botones
		pnlBotones=new JPBotones();
		pnlBotones.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		pnlBotones.setBounds(22, 290, 455, 48);
		
		// Boton CREAR
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCrear.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				crear_click(modelo);
			}
		});
		pnlBotones.add(btnCrear);
		
		// Boton MODIFICAR
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnModificar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				modificar_click();
			}
		});
		
		pnlBotones.add(btnModificar);
		
		
		// Boton ELIMINAR
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar_click (modelo);
			}
		});
		btnEliminar.setFont(new Font("Calibri Light", Font.BOLD, 14));
		pnlBotones.add(btnEliminar);
		
		
		// Boton CANCELAR
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelar_click();
				list.setSelectedIndex(-1);
				
				
			}
		});
		btnCancelar.setFont(new Font("Calibri Light", Font.BOLD, 14));
		pnlBotones.add(btnCancelar);
		getContentPane().add(pnlBotones);
		
		pnlBotones.setVisible(true);
	}	
	
	public void crear_click (DefaultListModel modelo) {
		if (!pnlSala.validarCajas())
			JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "ERROR",JOptionPane.ERROR_MESSAGE);
		else {
			Sala sa=pnlSala.getSala(); // Meto los datos en un objeto sala
			int f=bbdd.crearSala(sa); 	
			switch (f) {
				case 0:
					JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "Problemas técnicos", "ERROR",JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(null, "Sala añadida correctamente", "CONFIRMACIÓN",JOptionPane.INFORMATION_MESSAGE);							
					pnlSala.setNSala(Sala.calcularNumeroSala("NSALA", "salas", "SA"));
					modelo.addElement(sa);
					list.setModel(modelo);
					
			}
			pnlSala.limpiarPanel();
		}
	}
	
	public void modificar_click() {
		Sala sa = pnlSala.getSala();
		int f = bbdd.modificarPrecio(sa);
		list.getSelectedValue().setPrecio(pnlSala.getPrecio());
		//pnlSala.limpiarPanel();
		switch (f) {
			case 0:
				JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
				break;
			case -1:
				JOptionPane.showMessageDialog(null, "Problemas técnicos", "ERROR",JOptionPane.ERROR_MESSAGE);
				break;
			default:
				JOptionPane.showMessageDialog(null, "PRECIO ACTUALIZADO CORRECTAMENTE", "AVISO",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void eliminar_click (DefaultListModel modelo) {
		Sala sa=pnlSala.getSala();
		//Deberia ir aqui porque si la borro, no existe ya
		
		
				
		int f=bbdd.borrarSala(sa); 	
		switch (f) {
			case 0:
				JOptionPane.showMessageDialog(null, "En estos momentos no podemos atender su solicitud", "AVISO",JOptionPane.INFORMATION_MESSAGE);
				break;
			case -1:
				JOptionPane.showMessageDialog(null, "Problemas técnicos", "ERROR",JOptionPane.ERROR_MESSAGE);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Sala eliminada correctamente", "CONFIRMACIÓN",JOptionPane.INFORMATION_MESSAGE);	
				int i=list.getSelectedIndex();
				modelo.remove(i);
				list.setSelectedIndex(-1);
				
		}
		pnlSala.limpiarPanel();
	}
	
	public void cancelar_click() {
		pnlSala.setNSala(Sala.calcularNumeroSala("NSALA", "salas", "SA"));
		pnlSala.limpiarPanel();
		pnlSala.setInHabilitado(true);
		pnlSala.setInHabilitadoNSala();
	}
}
