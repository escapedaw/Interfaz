package jFrames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import bbdd.BD_Conector;
import bbdd.BD_ReservaVisita;
import jPanels.JPBotones;
import jPanels.JPLista;
import modelos.Reserva;
import modelos.Sala;
import modelos.Visita;

public class JFJefeVisita extends JFrame{
	private JPLista pnlLista;
	private JPBotones pnlBoton;
	private BD_ReservaVisita bdResVis = new BD_ReservaVisita();
	JList<Sala> list;
	
	public JFJefeVisita () {
		BD_Conector.BD_Ini("escapedaw");
		setTitle("Visitas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFJefeVisita.class.getResource("/imagenes/Logo_escape.jpg")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 810, 493);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize();	
	}
	
	private void initialize () {
		// Panel lista nSala			
		pnlLista=new JPLista();	
		pnlLista.setBounds(20, 21, 745, 333);
		pnlLista.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		DefaultListModel modelo=new DefaultListModel();
		Vector<Visita> vVis = bdResVis.mostrarVisitas();
		if (vVis == null)
			JOptionPane.showMessageDialog(null, "Fallo técnico, póngase en contacto con el administrador", "ERROR",JOptionPane.ERROR_MESSAGE);
		if (vVis.size() == 0)
			JOptionPane.showMessageDialog(null, "Actualmente no hay ninguna visita en la base de datos", "AVISO",JOptionPane.INFORMATION_MESSAGE);
		// Como hacer para que no se abra la ventana?
		for(Visita vi: vVis ) 
			modelo.addElement(vi);
		
		list = new JList<Sala>();
		list.setModel(modelo);
		list.setBounds(20, 21, 700, 288);
		pnlLista.add(list);
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(pnlLista);
		pnlLista.setVisible(true);
		
		// Panel de los botones
		pnlBoton=new JPBotones();
		pnlBoton.setBorder(null);
		pnlBoton.setBounds(292, 378, 165, 55);
		
		// Boton SALIR
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 20));
		btnSalir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				cancelar_click();
			}
		});
		pnlBoton.add(btnSalir);
				
		getContentPane().add(pnlBoton);
		pnlBoton.setVisible(true);
	}
	
	public void cancelar_click() {
		dispose();
	}
}
