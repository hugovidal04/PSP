//Programa que ejecuta el comando findstr para seleccionar las líneas de un fichero que contenga 
//un determinado texto y que las escriba en otro fichero

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.File;
import java.io.IOException;

public class PSP10_SeleccionaLineas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		final String path = "C:\\Users\\JOSEMI\\eclipse-workspace\\tmp\\";
		final String fichEntrada = "ArchivoBase.txt";
		final String fichSalida = "ArchivoResultado.txt";
		final String patron = "dos";
		
	      String nomFichEntrada = path + fichEntrada;
	      String nomFichSalida = path + fichSalida;

	      System.out.printf("Buscando patrón \"%s\" en fichero %s, salida a fichero %s\n",
	              patron, fichEntrada, fichSalida);

	      ProcessBuilder pb = new ProcessBuilder("findstr", patron);
	      pb.redirectInput(new File(nomFichEntrada));
	      pb.redirectOutput(new File(nomFichSalida));

	      try {
	        Process p = pb.start();
	        System.out.println("Proceso con id=" + p.pid() +  " realizando tarea encomendada...");
	      } catch (IOException e) {
	        System.out.println("ERROR: de E/S");
	        e.printStackTrace();
	      }

	}

}
