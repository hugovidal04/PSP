//Programa que lanza un nuevo proceso en función de los parámetros leídos por pantalla

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.IOException;
import java.util.Scanner;

public class PSP05_LanzaProceso2 {

	public static void main(String[] args) {
		
	String proceso = "";
	Scanner sc = new Scanner (System.in);
	
	System.out.print("Introduzca proceso que desea lanzar: ");
	proceso = sc.nextLine();
		
	ProcessBuilder pb = new ProcessBuilder(proceso.split(" +")); //Expresión regular para una serie de espacios
	//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo", "Hola Mundo");
	pb.inheritIO(); //El proceso hijo hereda los flujos de E/S del padre
	
	try {
		Process p = pb.start();   //Iniciar el proceso
		
		//Información del proceso
		System.out.println("PID: " + p.pid());
		System.out.println("INFO: " + p.info());
		
		int codRet = p.waitFor(); //Esperar a que el proceso termine
		System.out.println("La ejecución de [" + proceso
			+ "] devuelve " + codRet
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
	
	sc.close();
}

}
