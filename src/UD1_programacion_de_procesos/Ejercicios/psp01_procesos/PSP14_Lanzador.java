//Programa que lanza varios procesos (clase java) simultáneamente

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PSP14_Lanzador {
	
	public void lanzarSumador(Integer n1, Integer n2) {
		String clase = "psp01_procesos.PSP14_Sumador";
		ProcessBuilder pb;
		
		try {
			//pb = new ProcessBuilder("java", clase, n1.toString(), n2.toString());  //Error: no encuentra .class
			pb = new ProcessBuilder("java", "-cp", "bin", clase, n1.toString(), n2.toString()); //Especificamos el classpath (carpeta bin)
			pb.inheritIO();
			Process p = pb.start();
			
			//Leemos la salida del proceso
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}

			//p.waitFor(); //Esperamos a que el proceso termine
			
		} catch (Exception ex) {
			System.err.println("Se ha producido un ERROR....");
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PSP14_Lanzador l = new PSP14_Lanzador();
		
		System.out.println("Lanzamos procesos");
		System.out.println("*****************");
		
		//Creamos formato para la hora
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		//Registramos hora de inicio del proceso y mostramos por consola
		LocalDateTime horaInicio = LocalDateTime.now();
		System.err.println("\nHora de inicio: " + horaInicio.format(formato));
		
		
		l.lanzarSumador(1, 100000000);
		l.lanzarSumador(2, 500);
		l.lanzarSumador(3, 1000000);
		l.lanzarSumador(4, 100000000);
		l.lanzarSumador(5, 90000000);
		l.lanzarSumador(6, 80000000);
		
		
		
		l.lanzarSumador(7, 70000000);
		l.lanzarSumador(8, 100000000);
//		l.lanzarSumador(8, 2147483647);
		
		
		//Registramos hora de finalización del proceso y mostramos por consola
		LocalDateTime horaFin = LocalDateTime.now();
		System.err.println("\n\nHora de finalización: " + horaFin.format(formato));
		
	  //Calculamos tiempo total de ejecución del proceso
		Duration duracion = Duration.between(horaInicio, horaFin);
		long segTotal = duracion.getSeconds();
		System.out.println("El proceso ha estado vivo durante " + segTotal + " segundos");
				
				
	}
}
