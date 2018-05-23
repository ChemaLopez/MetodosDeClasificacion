package main;

import java.io.IOException;

import elemento.LecturaElementos;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LecturaElementos lectura = new LecturaElementos();
		
		
		try {
			lectura.leeArchivo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
