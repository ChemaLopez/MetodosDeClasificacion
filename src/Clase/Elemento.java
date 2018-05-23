package Clase;

import java.util.ArrayList;

public class Elemento {

	private  ArrayList<Double> lista;
	public String nombreElemento;

	
	public Elemento(ArrayList<Double> lista, String nombreElemento) {
		super();
		this.lista=lista;
		this.nombreElemento = nombreElemento;
	}
	
	
	public ArrayList<Double> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Double> lista) {
		this.lista = lista;
	}
	public String getNombreElemento() {
		return nombreElemento;
	}
	public void setNombreElemento(String nombreElemento) {
		this.nombreElemento = nombreElemento;
	}
	
	
}
