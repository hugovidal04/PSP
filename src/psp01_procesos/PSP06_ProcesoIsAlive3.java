//Programa que lanza un proceso (leído por consola) y utiliza el método isAlive() para comprobar si se sigue ejecutando
//El programa comprueba cada 3 segundos si se halla en ejecución, hasta que no lo esté, y entonces debe terminar
//Tras cada comprobación de estado se muestra un mensaje por pantalla
//También se mostrará la hora de inicio del proceso, la hora de finalización y el tiempo que el proceso ha estado vivo

package psp01_procesos;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PSP06_ProcesoIsAlive3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String proceso = "";
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Introduzca proceso que desea lanzar: ");
		proceso = sc.nextLine();
			
		ProcessBuilder pb = new ProcessBuilder(proceso.split(" +")); //Expresión regular para una serie de espacios
		//ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ping", "120.0.0.111", "-n",  "5", "-w",  "3000"); //Proceso con duración de 15 segundos
		pb.inheritIO(); //El proceso hijo hereda los flujos de E/S del padre
		
		//Creamos formato para la hora
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		//Registramos hora de inicio del proceso y mostramos por consola
		LocalDateTime horaInicio = LocalDateTime.now();
		System.out.println("Hora de inicio: " + horaInicio.format(formato));
		
		try {
			Process p = pb.start();   //Iniciar el proceso
			System.err.println("PROCESO INICIADO.........");			
			
			 while (p.isAlive()) {
	                System.err.println("El proceso sigue en ejecución...");
	                Thread.sleep(3000); // Esperar 3 segundos antes de volver a comprobar
	            }

	            // Cuando el proceso termina
	            System.err.println("PROCESO FINALIZADO.......");
	            
	          //Registramos hora de finalización del proceso y mostramos por consola
	    		LocalDateTime horaFin = LocalDateTime.now();
	    		System.out.println("Hora de finalización: " + horaFin.format(formato));
	    		
	    	  //Calculamos tiempo total de ejecución del proceso
	    		Duration duracion = Duration.between(horaInicio, horaFin);
	    		long segTotal = duracion.getSeconds();
	    		System.out.println("El proceso ha estado vivo durante " + segTotal + " segundos");
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
