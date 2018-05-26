package vista.GUI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JPResultados extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JButton btnComprobar;
	private JLabel titulo;
	/**
	 * Create the panel.
	 */
	public JPResultados() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		titulo = new JLabel("Restultados: ");
		titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		titulo.setFont(  new Font("Arial", Font.BOLD, 18));
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane (textArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textArea.setRows(20);
		add(titulo);
		add(scroll);		
		
		JPanel panel = new JPanel();
		add(panel);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.setBackground(new Color(153, 204, 153));
		panel.add(btnComprobar);
	}
	
	public void setResultados(String results){
		textArea.setText(results);
		this.revalidate();
		this.repaint();
	}
	
	public JButton getButton(){
		return btnComprobar;
	}
	
	public void clear(){
		textArea.setText("");
	}

}
