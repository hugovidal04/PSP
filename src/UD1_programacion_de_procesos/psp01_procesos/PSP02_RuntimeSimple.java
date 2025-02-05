//Programa que crea un proceso según los parámetros indicados

package UD1_programacion_de_procesos.psp01_procesos;

import java.io.IOException;

public class PSP02_RuntimeSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 1) {
			System.err.println("ERROR: Especificar comando a ejecutar");
			System.exit(1);
		}
		
		try {
			Runtime.getRuntime().exec(args); //Lanzamos nuevo proceso
		} catch (IOException ex) {
			System.err.println("ERROR de E/S al ejecutar el comando");
			ex.printStackTrace();
		}
	}
}
