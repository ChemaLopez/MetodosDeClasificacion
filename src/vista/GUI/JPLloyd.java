package vista.GUI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Clase.Clase;
import Datos.Datos;
import algoritmos.Lloyd;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.IntToDoubleFunction;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Component;

public class JPLloyd extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPResultados panelResultados;


	private ArrayList<Clase> clases;

	private static JPLloyd instance;
	
	
	public static JPLloyd getInstance() {
		if(instance==null)
				instance= new JPLloyd();
		return instance;
	}
	
	/**
	 * Create the panel.
	 */
	public void initView() {
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
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel tituloParams = new  JLabel("Parametros:");
		tituloParams.setFont(new Font("Arial", Font.BOLD, 18));	
		tituloParams.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(tituloParams);
		informacion.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
			
		JLabel lblNewLabel = new JLabel("Tolerancia = 0.0000000001");
		panel.add(lblNewLabel);
		
		JLabel lblMax = new JLabel("Máximo de iteraciones = 10");
		panel.add(lblMax);
		
		JLabel razon = new JLabel("Razón de aprendizaje = 0.1");
		panel.add(razon);
		
		panel.setPreferredSize(informacion.getPreferredSize());
	
		
		
		panelResultados = new JPResultados();
		panelResultados.setBorder(new TitledBorder("Comprobación: "));
		 ((javax.swing.border.TitledBorder) panelResultados.getBorder()).
	        setTitleFont(new Font("Arial", Font.BOLD, 18));
		add(panelResultados);
		
		JButton btnComprobar = panelResultados.getButton();
		
		for( ActionListener al : btnComprobar.getActionListeners() ) {
			btnComprobar.removeActionListener( al );
	    }
		
		btnComprobar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] nombre_clases = {Datos.getClases().get(0), Datos.getClases().get(1)};
				double[][] datos_centros = Datos.getCentros();
				
				double[][] datos_entrenamiento = new double[Datos.getDatosClases().get(0).length + Datos.getDatosClases().get(1).length][Datos.getDatosClases().get(0)[0].length]; 
						
				int pos = 0;
				for(int i = 0; i < Datos.getDatosClases().get(0).length; i++){
					datos_entrenamiento[pos+i] = Datos.getDatosClases().get(0)[i];
				}				
				pos = Datos.getDatosClases().get(0).length;
				for(int i = 0; i < Datos.getDatosClases().get(1).length; i++){
					datos_entrenamiento[pos+i] = Datos.getDatosClases().get(1)[i];
				}
				
				double[][] datos_prueba = new double[Datos.getEjemplos().size()][Datos.getEjemplos().get(0).length];
				for(int i = 0; i < Datos.getEjemplos().size(); i++){
					datos_prueba[i] = Datos.getEjemplos().get(i);
				}

				IntToDoubleFunction funcion = (i) -> 0.1;
				double tolerancia = 0.0000000001;
				int max_iteraciones = 10;
				
				Lloyd lloyd = new Lloyd(datos_centros, nombre_clases, datos_entrenamiento, funcion, tolerancia, max_iteraciones);
				String s = "";
				int i = 1;
				for (double[] prueba : datos_prueba) {
					s += i + "º = " + lloyd.predecirClase(prueba);
					s+= "\n";
					i++;
				}
				
				panelResultados.setResultados(s);
			}
			
			
		});
		
		this.setVisible(true);
	}
	
	

	public ArrayList<Clase> getClases() {
		return clases;
	}

	public void setClases(ArrayList<Clase> clases) {
		this.clases = clases;
	}

	public void refresh(){
		panelResultados.clear();
	}

}
