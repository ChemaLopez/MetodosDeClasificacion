package vista.GUI;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Clase.Clase;
import Clase.Elemento;
import Clase.LecturaElementos;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private static VentanaPrincipal instance;

	private static ArrayList<Clase> clases;



	public static void setEjemplos(ArrayList<Elemento> ejemplos) {
		VentanaPrincipal.ejemplos = ejemplos;
	}

	private HashMap<String,ArrayList<Elemento>> listaElementos;
	
	private JPanel panelDatos;
	private JPanel panelEjemplos;
	private JPanel panelAlgoritmo;
	private JPanel contenedor;
	private JLabel labelDatos;
	private JLabel labelEjemplos;

	private JMenuBar menu;
	private JMenuItem itemKMedias;
	private JMenuItem itemBayes;
	private JMenuItem itemLLoyd;
	private JMenuItem cargaEjemploArchivo;
	private JMenuItem cargarEjemploTexto;
	private JMenu cargaEjemplos;
	
	private JTextArea textoEjemplos;
	
	private static ArrayList<Elemento> ejemplos;

	
	
	public static VentanaPrincipal getInstance(){
		
		if(instance==null)
			instance= new VentanaPrincipal();
	
		return instance;
	}
	
	
	
	
public void initView(){
		ejemplos = new ArrayList<Elemento>();
	
		
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
	
	instance.setPreferredSize(new Dimension(1200,700));
	instance.setResizable(false);
	instance.setLocationRelativeTo(null);
	
	//panelDatos
	panelDatos = new JPanel();

	//DATOS
	labelDatos = new JLabel("Datos:");
	labelDatos.setFont(new Font("Arial", Font.BOLD, 18));

	panelDatos.add(labelDatos);
	
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
				
				cadena+=elem.getListaString() +'\n';
			}
			
			textArea.setText(cadena);
			
			textArea.setEditable(false);
			
			JScrollPane scroll2 = new JScrollPane (textArea, 
					   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll2.setBorder(new TitledBorder("Clase " + key.get(0).nombreElemento + ": "));
			
			panelTest.add(scroll2);
			
	
	}
	
	
	//panelejemplos
		panelEjemplos = new JPanel();

		//Ejemplo
		labelEjemplos = new JLabel("Ejemplos:");
		labelEjemplos.setFont(new Font("Arial", Font.BOLD, 18));

		panelEjemplos.add(labelEjemplos);
		
		panelEjemplos.setLayout(new BoxLayout(panelEjemplos, BoxLayout.Y_AXIS));

		panelEjemplos.setPreferredSize(new Dimension(180,600));
		
		JPanel panelTest2 = new JPanel();
		panelTest2.setLayout(new BoxLayout(panelTest2, BoxLayout.Y_AXIS));
		panelTest2.setPreferredSize(new Dimension(150, 500));
		JScrollPane scroll2 = new JScrollPane (panelTest2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelEjemplos.add(scroll2);
		textoEjemplos= new JTextArea();
		textoEjemplos.setText("");
		textoEjemplos.setEditable(false);
		JScrollPane scroll3 = new JScrollPane (textoEjemplos, 
						   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		panelTest2.add(scroll3);

	
	
	//panel algorimo va ir vacio para poner el panel del algoritmo
	
	JPKMedias.getInstance().setClases(clases);
	JPLloyd.getInstance().setClases(clases);

	JPKMedias.getInstance().initView();
	
	JPLloyd.getInstance().initView();
	JPLloyd.getInstance().setVisible(false);
	

	panelAlgoritmo = JPKMedias.getInstance();
	
	
	
	/// COSAS DEL MENU
	
	menu = new JMenuBar();
	itemBayes= new JMenuItem("Bayes");
	
	itemBayes.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cambiaBayes();
			
		}

		
	});
	itemKMedias = new JMenuItem("K-Medias Borroso");
	
	itemKMedias.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cambiaKmedias();
			
		}

		
	});
	itemLLoyd = new JMenuItem("LLoyd");
	
	itemLLoyd.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cambiaLLoyd();
			
		}

		
	});
	
	cargaEjemplos = new JMenu("Cargar ejemplo");

	cargarEjemploTexto= new JMenuItem("Introducir uno nuevo");
	cargarEjemploTexto.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cargarEjemploDesdeTexto();
		}
	});
	
	
	cargaEjemploArchivo = new JMenuItem("Cargar desde archivo");
	
	cargaEjemploArchivo.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cargarEjemploDesdeArchivo();
		}
	});

	cargaEjemplos.add(cargaEjemploArchivo);
	cargaEjemplos.add(cargarEjemploTexto);
	
	menu.add(cargaEjemplos);
	menu.add(itemKMedias);
	menu.add(itemBayes);
	menu.add(itemLLoyd);
	
	
	contenedor = new JPanel();
	contenedor.setLayout(new FlowLayout());
	contenedor.setPreferredSize(new Dimension(900, 600));
	contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
    contenedor.setComponentOrientation(
            ComponentOrientation.LEFT_TO_RIGHT);
	instance.setContentPane(contenedor);
	

	instance.setJMenuBar(menu);

	contenedor.add(panelDatos);
	contenedor.add(panelEjemplos);
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

private void cambiaBayes() {
	// TODO Auto-generated method stub
	
	this.remove(panelAlgoritmo);
	
	panelAlgoritmo=JPBayes.getInstance();
	JPLloyd.getInstance().setVisible(false);

	JPKMedias.getInstance().setVisible(false);
	this.add(panelAlgoritmo);
	JPBayes.getInstance().setVisible(true);

	
	this.repaint();
	this.pack();

}

private void cambiaLLoyd() {
	// TODO Auto-generated method stub
	
	this.remove(panelAlgoritmo);
	
	panelAlgoritmo= JPLloyd.getInstance();
	JPBayes.getInstance().setVisible(false);

	JPKMedias.getInstance().setVisible(false);
	this.add(panelAlgoritmo);

	JPLloyd.getInstance().setVisible(true);
	
	this.repaint();

	
	

	
}
private void cambiaKmedias() {
	// TODO Auto-generated method stub
	
	this.remove(panelAlgoritmo);
	
	panelAlgoritmo= JPKMedias.getInstance();
	JPLloyd.getInstance().setVisible(false);
	JPBayes.getInstance().setVisible(false);
	this.add(panelAlgoritmo);
	JPKMedias.getInstance().setVisible(true);
	
	this.repaint();
}




private void cargarEjemploDesdeArchivo() {
	
	JFileChooser jf = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter( "*.txt", "text");
	jf.setFileFilter(filter);
	int resultado= jf.showDialog(instance, "Cargar");
	
	if(resultado==JFileChooser.APPROVE_OPTION) {
		String cadena = "";
		cadena = jf.getSelectedFile().getAbsolutePath();
		
		try {
			Elemento elem= LecturaElementos.leeEjemplo(cadena);
			cadena = textoEjemplos.getText();
			ejemplos.add(elem);
			cadena+=elem.getListaString()+"\n";
			textoEjemplos.setText(cadena);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
}

private void cargarEjemploDesdeTexto() {
	
	String texto = JOptionPane.showInputDialog("Introduzca el un elemento");
	
	if(texto!=null) {
		double[] lista=null;
		ArrayList<Double> numeros = new ArrayList<Double>();
		String op[] = texto.split(",");
		for (String elementData : op) {
		if (elementData.matches("[0-9]+.+[0-9]+"))
			numeros.add(Double.parseDouble(elementData));
		
		
	}
		lista = new double[numeros.size()];
		
		int i=0;
		for(double num : numeros) {
			lista[i]=num;
			++i;
		}
		Elemento elem = new Elemento(lista, null);
		ejemplos.add(elem);
		String cadena = textoEjemplos.getText();
		cadena+=elem.getListaString()+"\n";
		textoEjemplos.setText(cadena);
	}
		
	
	
}


public  ArrayList<Clase> getClases() {
	return clases;
}


public  String[] getClasesNombres() {
	String[] lista = new String[clases.size()];
	int i=0;
	for(Clase elem: clases) {
		lista[i]=elem.getNombreElemento();
	++i;
	}
	
	return lista;
}

public double[][] getCentros() {
	
	double[][] lista = new double[clases.size()][clases.get(0).getCentro().length];
	int i=0;
	for(Clase elem: clases) {
		lista[i]=elem.getCentro();
	++i;
	}
	
	return lista;
	
}

public  ArrayList<double[]> getEjemplos() {
	
	ArrayList<double[]> lista = new ArrayList<double[]>();
	
	for(Elemento elem: ejemplos)
		lista.add(elem.getLista());
	
	return lista;
}


}
