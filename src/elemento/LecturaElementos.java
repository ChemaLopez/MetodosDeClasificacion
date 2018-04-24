package elemento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LecturaElementos {

	private HashMap<String,ArrayList<Elemento>> listaElementos;
	
	
	
	public LecturaElementos(){
		setListaElementos(new HashMap<String,ArrayList<Elemento>>());
		
	}



	public void leeArchivo() throws IOException{
		
		String currentDir = new File("").getAbsolutePath();

		File newFile = new File(currentDir, "Iris2Clases");

		String cadena;
		FileReader lector = new FileReader(newFile);
		BufferedReader buffer = new BufferedReader(lector);
		
			// LEER EL ARCHIVO HASTA EL FINAL DE ARCHIVO
			while ((cadena = buffer.readLine()) != null) {
				// PARSEAR CADA LINEA
				ArrayList<Double> numeros = new ArrayList<Double>();
				String op[] = cadena.split(",");
				for (String elementData : op) {
					if (elementData.matches("[0-9]+") && elementData.length() > 2) {
						numeros.add(Double.parseDouble(elementData));
					} else {
						 Elemento elem= new Elemento(numeros, elementData);
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
	
	
	
	
	public HashMap<String,ArrayList<Elemento>> getListaElementos() {
		return listaElementos;
	}



	public void setListaElementos(HashMap<String,ArrayList<Elemento>> listaElementos) {
		this.listaElementos = listaElementos;
	}
	
	
}
