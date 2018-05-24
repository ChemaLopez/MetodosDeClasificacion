package Clase;

import java.util.ArrayList;

public class Clase {

	
	private String nombreElemento;

	private double[][] matriz;

	private double[] centro;

	
	public Clase(ArrayList<Elemento> elementos, String nombre, double[] ds){
		
		this.setNombreElemento(nombre);
		this.centro=ds;
		matriz = new double[elementos.size()][elementos.get(0).getLista().length];
		
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
	
	
	public double[] getCentro(){
		return centro;
	}
	

	public String getCentroString(){
		String cadena="";
		
		for(double num : centro) {
			
			cadena+=num+" ";
		}
		return cadena;
	}
	public void setCentro(double[] centro) {
		
		this.centro= centro;
	}


	public String getNombreElemento() {
		return nombreElemento;
	}


	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}
	
	public double[][] getMatriz(){
		return matriz;
	}
	
}
