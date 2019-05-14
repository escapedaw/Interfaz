package jPanels;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import bbdd.BD_PistaSala;
import modelos.Empleado;
import modelos.Jefe;
import modelos.Sala;
import javax.swing.JTable;;

public class JPJefeSalas extends JPanel {
	private JTextField txtnSala;	
	private JTextField txtnPersonas;
	private JTextField txtPrecio;
	private JComboBox cmbidEmp;
	private JComboBox cmbidJefe;
	private JTextField txtTipo;
	private JTextField txtDificultad;
	
	public JPJefeSalas (Vector<String> identEmple, Vector<String> identJefe,String numeroSala) {
		
		setBorder(new TitledBorder(null, "Datos Salas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout (null);
		setBounds(22, 21, 311, 258);
		initialize(identEmple, identJefe,numeroSala);
	}
	
	

	private void initialize(Vector<String> identEmple, Vector<String> identJefe,String numeroSala) {
		// Si se genera solo, como se hace?
		JLabel lblnSala = new JLabel("Numero de sala");
		lblnSala.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblnSala.setBounds(21, 30, 114, 14);
		add(lblnSala);
		
		txtnSala = new JTextField();
		txtnSala.setColumns(10);
		txtnSala.setBounds(199, 25, 92, 25);
		txtnSala.setText(numeroSala);
		txtnSala.setEnabled(false);
		add(txtnSala);
		
		JLabel lblidEmple = new JLabel("Identificacion del empleado");
		lblidEmple.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblidEmple.setBounds(21, 62, 165, 14);
		add(lblidEmple);
		
		cmbidEmp = new JComboBox(identEmple);
		cmbidEmp.setBounds(199, 57, 92, 22);
		add(cmbidEmp);
		
		JLabel lblidJefe = new JLabel("Identificacion del jefe");
		lblidJefe.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblidJefe.setBounds(21, 92, 156, 14);
		add(lblidJefe);
		
		cmbidJefe = new JComboBox(identJefe);
		cmbidJefe.setBounds(199, 88, 92, 22);
		add(cmbidJefe);
				
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblTipo.setBounds(21, 152, 156, 14);
		add(lblTipo);
		
		JLabel lblDificultad = new JLabel("Dificultad");
		lblDificultad.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblDificultad.setBounds(21, 182, 68, 14);
		add(lblDificultad);
		
		JLabel lblnPersonas = new JLabel("Numero de personas");
		lblnPersonas.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblnPersonas.setBounds(21, 212, 165, 14);
		add(lblnPersonas);
		
		txtnPersonas = new JTextField();
		txtnPersonas.setColumns(10);
		txtnPersonas.setBounds(199, 207, 92, 25);
		add(txtnPersonas);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Calibri Light", Font.BOLD, 14));
		lblPrecio.setBounds(21, 122, 68, 14);
		add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(199, 117, 92, 25);
		add(txtPrecio);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(199, 147, 92, 25);
		add(txtTipo);
		txtTipo.setColumns(10);
		
		txtDificultad = new JTextField();
		txtDificultad.setColumns(10);
		txtDificultad.setBounds(199, 177, 92, 25);
		add(txtDificultad);		
	}
	
	public void setNSala(String n) {
		txtnSala.setText(n);
	}
	
	public boolean validarCajas() {
		if (txtnPersonas.getText().equals("") )
			return false;
		return true;
	}
	
	public Sala getSala() {
		Sala sa = new Sala(txtnSala.getText(), cmbidEmp.getSelectedItem().toString(),cmbidJefe.getSelectedItem().toString(),txtTipo.getText(), txtDificultad.getText(), Integer.parseInt(txtnPersonas.getText()), Integer.parseInt(txtPrecio.getText()));
		return sa;
	}
	
	public int getPrecio() {
		return Integer.parseInt(txtPrecio.getText());
	}
	public void setDatos (Sala sa) {
		txtnSala.setText(sa.getNsala());
		int p=buscar(cmbidEmp,sa.getIdEmple());
		cmbidEmp.setSelectedIndex(p);
		p=buscar(cmbidJefe,sa.getIdJefe());
		cmbidJefe.setSelectedIndex(p);
		txtTipo.setText(sa.getTipo());		
		txtDificultad.setText(sa.getDificultad());		
		txtnPersonas.setText(Integer.toString(sa.getNPersonas()));		
		txtPrecio.setText(Integer.toString(sa.getPrecio()));
	}
	
	/* Buscar usando un JCombo:
	 * Primero crear un ComboBoxModel al que le añades el getModel() del JCombo (en este caso, pasado por parametro).
	 * Recorres el modelo y, para comparar, conviertes el getElementA(i) a un toString() y ya comparas.
	 * Devuelves la posicion de dicho elemento
	 */
	private int buscar(JComboBox l, String id) {
		ComboBoxModel modelo=l.getModel();
		for (int i=0;i<modelo.getSize();i++) {
			if ( modelo.getElementAt(i).toString().equals(id))
				return i;
		}
		return -1;
	}
	
	public void setInHabilitado(boolean valor) {
		txtnSala.setEnabled(valor);
		cmbidEmp.setEnabled(valor);
		txtTipo.setEnabled(valor);
		txtDificultad.setEnabled(valor);
		txtnPersonas.setEnabled(valor);
		cmbidJefe.setEnabled(valor);
	}
	
	public void setInHabilitadoPrecio() {
		txtPrecio.setEnabled(false);
	}
	
	public void setInHabilitadoNSala() {
		txtnSala.setEnabled(false);
	}
	
	public void limpiarPanel() {		
		txtTipo.setText("");		
		txtDificultad.setText("");		
		txtnPersonas.setText("");		
		txtPrecio.setText("");
	}
}