package vista.GUI;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import algoritmos.Bayes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class JPBayes extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPResultados panelResultados;
	
	private static JPBayes instance;
	/**
	 * Create the panel.
	 */
	
	public  static JPBayes getInstance() {
		
		if(instance ==null) {
			instance= new JPBayes();
		}
		
		return instance;
	}
	
	public JPBayes() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	
		setPreferredSize(new Dimension(600, 600));

		panelResultados = new JPResultados();
		add(panelResultados);
		
		JButton btnComprobar = panelResultados.getButton();
		
		for( ActionListener al : btnComprobar.getActionListeners() ) {
			btnComprobar.removeActionListener( al );
	    }
		
		btnComprobar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Bayes bayes = new Bayes();
				
				
				for (int i = 0; i < VentanaPrincipal.getInstance().getClases().size(); i++) {
					bayes.aprenderClase(VentanaPrincipal.getInstance().getClases().get(i).getMatriz(), VentanaPrincipal.getInstance().getClases().get(i).getNombreElemento());
				}
				
				String s = "";
				for (double[] ejemplo : VentanaPrincipal.getInstance().getEjemplos()) {
					s += bayes.predecirClase(ejemplo);
					s += "\n";
				}
				
				panelResultados.setResultados(s);
			}
			
			
		});
		
	}
	
	public void refresh(){
		panelResultados.clear();
	}

}
