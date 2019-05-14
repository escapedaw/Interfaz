package jPanels;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JPLista extends JPanel{
	
	public JPLista () {
		setBorder(new TitledBorder(null, "Numero de sala", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);				
		setSize(134, 256);
		setLocation(343, 21);
	}

}
