package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Clase.Clase;
import Clase.Elemento;
import Clase.LecturaElementos;
import vista.GUI.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LecturaElementos lectura = new LecturaElementos();
		
		
		try {
			lectura.leeArchivo();
			HashMap<String,ArrayList<Elemento>> listaElementos = lectura.getListaElementos();
			
			ArrayList<Clase> clases = new ArrayList<>();
			lectura.leeCentros();
			ArrayList<double[]> centros =lectura.getCentros();
			
			int i =0;
			
			for(ArrayList<Elemento> key : listaElementos.values()){
					clases.add(new Clase(key,key.get(0).nombreElemento, centros.get(i)));
					++i;

			}

			
			
			
			
			VentanaPrincipal view = VentanaPrincipal.getInstance();
			
			view.setListaElementos(listaElementos);
			view.setClases(clases);
			
			view.initView();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
