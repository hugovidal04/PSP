//Programa que establece un tiempo máximo para la ejecución de un comando

package UD1_programacion_de_procesos.psp01_procesos;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PSP07_comandoTimeOut {
	
	public static int MAX_TIEMPO = 10000; //milisegundos

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Definimos comando a ejecutar
		String[] comando = new String[] {"ping", "120.0.0.111", "-n", "5", "-w", "3000"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		System.out.printf("Se ejecuta comando: %s\n", Arrays.toString(comando));
		
		pb.inheritIO(); //Heredamos flujo de E/S del proceso padre
		
		try {
			Process p = pb.start();   //Iniciar el proceso
			
			if (!p.waitFor(MAX_TIEMPO, TimeUnit.MILLISECONDS)) {
				p.destroy();
				System.err.printf("AVISO: no se ha terminado en %d ms. Forzamos su finalización\n", MAX_TIEMPO);
			} else System.err.printf("El proceso ha terminado de forma natural antes de los %d ms\n", MAX_TIEMPO);
			
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
