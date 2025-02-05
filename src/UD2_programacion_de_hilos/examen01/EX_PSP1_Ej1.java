package UD2_programacion_de_hilos.examen01;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EX_PSP1_Ej1 {
	
	//Esta función lanza un proceso y lo devuelve como parámetro al programa que lo había llamado
	public Process calcularSuma(Integer n1, Integer n2) {
		String clase = "psp01.EX_PSP1_Ej1_Sumador";
		ProcessBuilder pb;

		try {
			//pb = new ProcessBuilder("java", clase, n1.toString(), n2.toString());  //Error: no encuentra .class
			pb = new ProcessBuilder("java", "-cp", "bin", clase, n1.toString(), n2.toString()); //Especificamos el classpath (carpeta bin)
			pb.inheritIO();
			return pb.start();
			
		} catch (Exception ex) {
			System.err.println("Se ha producido un ERROR....");
			ex.printStackTrace();
			return null;
		}
	}
	
	

	public static void main(String[] args) {
		EX_PSP1_Ej1 l = new EX_PSP1_Ej1();
		
		//Creamos formato para la hora
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");


		
		//Lanzamos procesos
		System.out.println("\nLanzamos procesos en paralelo");
		System.out.println("*********************************");
		
		//Registramos hora de inicio del proceso y mostramos por consola
		LocalDateTime horaInicio = LocalDateTime.now();
		System.err.println("Hora de inicio: " + horaInicio.format(formato));
		
		//Lista para almacenar los procesos lanzados
		List<Process> procesos = new ArrayList<>();
		Random random = new Random();
		
		try {
			procesos.add(l.calcularSuma(1, random.nextInt(9001) + 1000));
			procesos.add(l.calcularSuma(2, random.nextInt(9001) + 1000));
			procesos.add(l.calcularSuma(3, random.nextInt(9001) + 1000));
			procesos.add(l.calcularSuma(4, random.nextInt(9001) + 1000));
			procesos.add(l.calcularSuma(5, random.nextInt(9001) + 1000));
			
			for (Process p : procesos) {
				p.waitFor();
			}
		} catch (Exception e) {
            e.printStackTrace();
        }
				
		//Registramos hora de finalización del proceso y mostramos por consola
		LocalDateTime horaFin = LocalDateTime.now();
		System.err.println("\n\nHora de finalización: " + horaFin.format(formato));
		
	    //Calculamos tiempo total de ejecución del proceso
		Duration duracion = Duration.between(horaInicio, horaFin);
		long segTotal = duracion.getSeconds();
		System.err.println("\nTiempo total de proceso: " + segTotal + " segundos");
	}

}