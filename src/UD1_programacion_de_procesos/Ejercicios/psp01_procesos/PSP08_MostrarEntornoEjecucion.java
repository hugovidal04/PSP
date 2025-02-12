//Programa que muestra el entorno de ejecución de un proceso con Map<String, String> environment()

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.util.Map;
import java.util.Map.Entry;

public class PSP08_MostrarEntornoEjecucion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    ProcessBuilder pb1 = new ProcessBuilder("cmd"); // Da igual el comando

	    Map<String, String> env = pb1.environment();

	    for (Entry<String, String> entrada : env.entrySet()) {
	      System.out.printf("%s: %s\n", entrada.getKey(), entrada.getValue());
	    }

	}

}
