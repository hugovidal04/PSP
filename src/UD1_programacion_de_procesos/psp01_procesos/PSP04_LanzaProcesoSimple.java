//Programa que lanza un nuevo proceso según los parámetros indicados

package UD1_programacion_de_procesos.psp01_procesos;

import java.io.IOException;
import java.util.Arrays;

public class PSP04_LanzaProcesoSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length <= 0) {
			System.out.println("Debe indicarse comando a ejecutar");
			System.exit(1);
		}
		
		ProcessBuilder pb = new ProcessBuilder(args);
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo", "Hola Mundo");
		pb.inheritIO(); //El proceso hijo hereda los flujos de E/S del padre
		
		try {
			Process p = pb.start();   //Iniciar el proceso
			int codRet = p.waitFor(); //Esperar a que el proceso termine
			System.out.println("La ejecución de " + Arrays.toString(args)
				+ " devuelve " + codRet
				+ " " + (codRet == 0 ? "(ejecución correcta)" : "(ERROR)") 
			);
		}
		catch (IOException e) {
			System.err.println("Error durante la ejecución del proceso");
			System.err.println("Información detallada");
			System.err.println("---------------------");
			e.printStackTrace();
			System.err.println("---------------------");
			System.exit(2);
		}
		catch (InterruptedException e){
			System.err.println("Proceso interrumpido");
			System.exit(3);
		}
	}
}
