package Clase;

import java.util.ArrayList;

public class Clase {

	
	public String nombreElemento;

	private double[][] matriz;

	
	public Clase(ArrayList<Elemento> elementos, String nombre){
		
		this.nombreElemento= nombre;
		
		matriz = new double[elementos.size()][elementos.get(0).getLista().size()];
		
		int i=0;
		
			for(Elemento elem : elementos) {
				int k=0;
				for(Double num : elem.getLista()) {
					matriz[i][k]=num;
					++k;
				}
				
				++i;
			}
		
		
		
	}
	
	
	
}
