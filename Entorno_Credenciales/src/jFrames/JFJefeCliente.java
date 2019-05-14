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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bbdd.BD_CliEmpJef;
import bbdd.BD_Conector;
import bbdd.BD_ReservaVisita;
import jPanels.JPBotones;
import jPanels.JPLista;
import modelos.Cliente;
import modelos.Sala;
import javax.swing.JScrollBar;

public class JFJefeCliente extends JFrame{
	private JPLista pnlLista;
	private JPBotones pnlBoton;
	private BD_CliEmpJef bdCliEmpJef = new BD_CliEmpJef();
	JList<Sala> list;
	
	public JFJefeCliente () {
		setResizable(false);
		BD_Conector.BD_Ini("escapedaw");
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFJefeCliente.class.getResource("/imagenes/Logo_escape.jpg")));
		setBounds(100, 100, 550, 481);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		initialize();	
	}
	
	private void initialize () {
		// Panel lista nSala			
		pnlLista=new JPLista();	
		pnlLista.setBounds(20, 21, 497, 335);
		pnlLista.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		DefaultListModel modelo=new DefaultListModel();
		Vector<Cliente> v=bdCliEmpJef.mostrarClientes();
		if (v.size() == 0)
			JOptionPane.showMessageDialog(null, "Actualmente no hay ningún cliente en la base de datos", "AVISO",JOptionPane.INFORMATION_MESSAGE);
		for(Cliente cl: v ) 
			modelo.addElement(cl);
		
		list = new JList<Sala>();
		list.setModel(modelo);
		list.setBounds(20, 21, 452, 288);
		pnlLista.add(list);
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(pnlLista);
		pnlLista.setVisible(true);
		
		// Panel de los botones
		pnlBoton=new JPBotones();
		pnlBoton.setBorder(null);
		pnlBoton.setBounds(188, 369, 165, 55);
		
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