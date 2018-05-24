package Clase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturaElementos {

	private HashMap<String,ArrayList<Elemento>> listaElementos;
	
	private ArrayList<double []> centros;

	public LecturaElementos(){
		setListaElementos(new HashMap<String,ArrayList<Elemento>>());
		centros= new ArrayList<double []>();
	}



	public void leeArchivo() throws IOException{
		
		String currentDir = new File("").getAbsolutePath();

		File newFile = new File(currentDir, "Iris2Clases.txt");

		String cadena;
		FileReader lector = new FileReader(newFile);
		BufferedReader buffer = new BufferedReader(lector);
		
			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				ArrayList<Double> numeros = new ArrayList<Double>();
				String op[] = cadena.split(",");
				for (String elementData : op) {
					
					if (elementData.matches("[0-9]+.+[0-9]+")) {
						numeros.add(Double.parseDouble(elementData));
					} else {
						 double[] listacentros = new double[numeros.size()];
						
						int i=0;
						for(double num : numeros) {
							listacentros[i]=num;
							++i;
						}
						 Elemento elem= new Elemento(listacentros, elementData);
						 if(listaElementos.containsKey(elementData)){
							 listaElementos.get(elementData).add(elem);
						 }else{
							 listaElementos.put(elementData, new ArrayList<Elemento>());
							 listaElementos.get(elementData).add(elem);
						 }
					}
				}

			}
		
		buffer.close();

		
	}
	
	

	public void leeCentros() throws IOException{
		
		String currentDir = new File("").getAbsolutePath();

		File newFile = new File(currentDir, "Centros.txt");

		String cadena;
		FileReader lector = new FileReader(newFile);
		BufferedReader buffer = new BufferedReader(lector);
		
			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				ArrayList<Double> numeros = new ArrayList<Double>();
				String op[] = cadena.split(",");
				for (String elementData : op) {
					
				numeros.add(Double.parseDouble(elementData));
					
					
				}
				
				double[] listacentros = new double[numeros.size()];
			
				int i=0;
				for(double num : numeros) {
					listacentros[i]=num;
					++i;
				}
					
				centros.add(listacentros);
				

			}
		
		buffer.close();

		
	}
	
	
	
public static Elemento leeEjemplo(String archivo) throws IOException{
		

		File newFile = new File(archivo);

		String cadena;
		FileReader lector = new FileReader(newFile);
		
		double[] listacentros=null;
		Elemento elem=null;
		BufferedReader buffer = new BufferedReader(lector);

			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				ArrayList<Double> numeros = new ArrayList<Double>();
				String op[] = cadena.split(",");
				for (String elementData : op) {
				if (elementData.matches("[0-9]+.+[0-9]+"))
					numeros.add(Double.parseDouble(elementData));
				else {
					listacentros = new double[numeros.size()];
					
					int i=0;
					for(double num : numeros) {
						listacentros[i]=num;
						++i;
					}
					elem = new Elemento(listacentros, elementData);
				}
					
					
				}
				
			
					
				

			}
		
		buffer.close();

		return elem;
		
	}
	
	
	
	public HashMap<String,ArrayList<Elemento>> getListaElementos() {
		return listaElementos;
	}



	public void setListaElementos(HashMap<String,ArrayList<Elemento>> listaElementos) {
		this.listaElementos = listaElementos;
	}
	
	
	public ArrayList<double[]> getCentros() {
		return centros;
	}



	
	
}
