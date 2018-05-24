package Clase;

import java.util.ArrayList;

public class Elemento {

	private double[] lista;
	public String nombreElemento;

	
	public Elemento(double[] lista, String nombreElemento) {
		super();
		this.lista=lista;
		this.nombreElemento = nombreElemento;
	}
	
	
	public double[] getLista() {
		return lista;
	}
	public void setLista(double[] lista) {
		this.lista = lista;
	}
	public String getNombreElemento() {
		return nombreElemento;
	}
	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}
	
	
	public String getListaString(){
		String cadena="";
		
		for(double num : lista) {
			
			cadena+=num+" ";
		}
		return cadena;
	}
	
}
