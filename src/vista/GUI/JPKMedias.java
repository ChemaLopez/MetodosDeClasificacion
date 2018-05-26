package vista.GUI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Clase.Clase;
import algoritmos.KMeans;
import util.MatrizToVectorVector;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Component;

public class JPKMedias extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private JPResultados panelResultados;
	
	private ArrayList<Clase> clases;
	
	
	private static JPKMedias  instance;
	
	public static JPKMedias getInstance() {
		
		if(instance==null)
			instance = new JPKMedias();
		
		return instance;
	}
	
	/**
	 * Create the panel.
	 */
	public void initView(){
		

		//INFORMACION
		setPreferredSize(new Dimension(600, 600));

		JPanel informacion = new JPanel();
		JLabel titulo = new  JLabel("Informacion:");
		titulo.setFont(new Font("Arial", Font.BOLD, 18));
		informacion.add(titulo);
		informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS));
		add(informacion);

		//CENTRO DE LAS CLASES 
		JPanel centros = new JPanel();
		
		centros.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel tituloCentros = new  JLabel("Centros:");
		tituloCentros.setFont(new Font("Arial", Font.BOLD, 18));		
		centros.setLayout(new BoxLayout(centros, BoxLayout.Y_AXIS));
		centros.add(tituloCentros);
		
		informacion.add(centros);
		
		JPanel c1 = new JPanel();
		c1.setBorder(new EmptyBorder(10, 10, 10, 10));
		centros.add(c1);
		c1.setLayout(new BoxLayout(c1, BoxLayout.Y_AXIS));
		
		//CLASES
		for(Clase clase : clases) {
			JTextArea textField = new JTextArea();
			JLabel label = new JLabel(clase.getNombreElemento());
			textField.setEditable(false);
			textField.setAlignmentX(Component.LEFT_ALIGNMENT);
			textField.setText(clase.getCentroString());
			c1.add(label);
			c1.add(textField);
			textField.setMaximumSize( 
				     new Dimension(200, textField.getPreferredSize().height) );
			textField.setPreferredSize( 
				     new Dimension(200, textField.getPreferredSize().height) );
		}
		informacion.add(c1);
		
		//PARAMETROS DE CONFIGURACION
		JPanel panel = new JPanel();
		JLabel tituloParams = new  JLabel("Parametros:");
		tituloParams.setFont(new Font("Arial", Font.BOLD, 18));	
		tituloParams.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(tituloParams);
		informacion.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
		JLabel lblNewLabel = new JLabel("Tolerancia = 0.01");
		panel.add(lblNewLabel);
		lblNewLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblPesoExponencial = new JLabel("Peso exponencial = 2");
		panel.add(lblPesoExponencial);
		lblPesoExponencial.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setPreferredSize(informacion.getPreferredSize());

	
		
		panelResultados = new JPResultados();
		panelResultados.setPreferredSize(new Dimension(180,600));
		add(panelResultados);
		
		JButton btnComprobar = panelResultados.getButton();
		
		for( ActionListener al : btnComprobar.getActionListeners() ) {
			btnComprobar.removeActionListener( al );
	    }
		
		btnComprobar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] nombre_clases = VentanaPrincipal.getInstance().getClasesNombres();
				
				double[][] datos_centros = VentanaPrincipal.getInstance().getCentros();
				
						
				ArrayList<Clase>  listaClases = VentanaPrincipal.getInstance().getClases();
				int filas=0;
				for(Clase clase : listaClases) {
					
					filas+=clase.getMatriz().length;
				}
				
				double[][] datos_entrenamiento = new double[filas][listaClases.get(0).getMatriz()[0].length]; 

				int pos = 0;
				
				for(Clase clase: listaClases) {
					//AÑADIR LOS ELEMENTOS DE LA CLASE
					double[][] matrizDeClase = clase.getMatriz();
					for(double[] array: matrizDeClase) {
						datos_entrenamiento[pos]=array;
						++pos;
					}
					
				}
				
				
				//EJEMPLOS
				ArrayList<double[]> ejemplos = VentanaPrincipal.getInstance().getEjemplos();
				double[][] datos_prueba = new double[ejemplos.size()][ejemplos.get(0).length];
				pos =0;
				for(double [] lista :ejemplos) {
					datos_prueba[pos]=lista;
					pos++;
				}
				

				double tolerancia = 0.01,
						peso_exponencial = 2;

				KMeans kmeans = new KMeans(datos_centros, nombre_clases, datos_entrenamiento, tolerancia, peso_exponencial);
				
				String s = "";
				for (Vector<Double> ejemplo : MatrizToVectorVector.metodoCutre(datos_prueba)) {
					s += kmeans.comprobarPunto(ejemplo);
					s += "\n";
				}
				
				panelResultados.setResultados(s);
			}
			
			
		});
		
		this.setVisible(true);

	}
	
	public void refresh(){
		panelResultados.clear();
	}

	public ArrayList<Clase> getClases() {
		return clases;
	}

	public void setClases(ArrayList<Clase> clases) {
		this.clases = clases;
	}


}
