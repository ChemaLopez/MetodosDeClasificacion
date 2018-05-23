package vista.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Clase.Clase;
import Clase.Elemento;
import Datos.Datos;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private static VentanaPrincipal instance;

	private ArrayList<Clase> clases;
	private HashMap<String,ArrayList<Elemento>> listaElementos;
	
	private JPanel panelDatos;
	private JPanel panelAlgoritmo;
	private JPanel contenedor;
	private JLabel labelDatos;
	
	private JMenuBar menu;
	private JMenu itemKMedias;
	private JMenu itemBayes;
	private JMenu itemLLoyd;
	
	
	public static VentanaPrincipal getInstance(){
		
		if(instance==null)
			instance= new VentanaPrincipal();
	
		return instance;
	}
	
	
	
	
public void initView(){
		
	
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		WindowAdapter exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Quieres salir de la aplicacion?", 
		             "Confirmacion de salida", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
	this.addWindowListener(exitListener);
	
	instance.setPreferredSize(new Dimension(900,700));
	instance.setResizable(false);
	instance.setLocationRelativeTo(null);
	
	//panelDatos
	panelDatos = new JPanel();

	//DATOS
	panelDatos.setBorder(new TitledBorder("Datos: "));
	panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

	panelDatos.setPreferredSize(new Dimension(180,600));
	JPanel panelTest = new JPanel();
	panelTest.setLayout(new BoxLayout(panelTest, BoxLayout.Y_AXIS));
	panelTest.setPreferredSize(new Dimension(150, 600));
	JScrollPane scroll = new JScrollPane (panelTest,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	panelDatos.add(scroll);
	for(ArrayList<Elemento> key : listaElementos.values()){
		
		
			JTextArea textArea = new JTextArea();
			String cadena ="";
			
			for(Elemento elem: key) {
				
				cadena+=elem.getLista().toString() +'\n';
			}
			
			textArea.setText(cadena);
			
			textArea.setEditable(false);
			
			JScrollPane scroll2 = new JScrollPane (textArea, 
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll2.setBorder(new TitledBorder("Clase " + key.get(0).nombreElemento + ": "));
			
			panelTest.add(scroll2);
			
	
	}
	
	
	
	//panel algorimo va ir vacio para poner el panel del algoritmo
	panelAlgoritmo = new JPanel();
	
	
	
	
	
	/// COSAS DEL MENU
	
	menu = new JMenuBar();
	itemBayes= new JMenu("Bayes");
	itemKMedias = new JMenu("K-Medias Borroso");
	itemLLoyd = new JMenu("LLoyd");
	
	
	menu.add(itemKMedias);
	menu.add(itemBayes);
	menu.add(itemLLoyd);
	
	
	contenedor = new JPanel();
	contenedor.setPreferredSize(new Dimension(900, 600));
	contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));

	instance.setContentPane(contenedor);
	

	instance.setJMenuBar(menu);
	
	contenedor.add(panelDatos);
	contenedor.add(panelAlgoritmo);
	
	instance.pack();
	
	instance.setVisible(true);

}

public void setClases(ArrayList<Clase> clases) {
	
	this.clases=clases;
	
	
}


public void setListaElementos(HashMap<String,ArrayList<Elemento>> listaElementos) {
	this.listaElementos = listaElementos;
}



}
