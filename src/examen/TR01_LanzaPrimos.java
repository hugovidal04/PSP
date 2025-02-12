package examen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TR01_LanzaPrimos {
	
	
	//Este método lanza un proceso y espera a su finalización
	public void lanzarBuscaPrimos_1(Integer n1, Integer n2) {
		String clase = "examen.TR01_BuscaPrimos";
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

			p.waitFor(); //Esperamos a que el proceso termine
			
		} catch (Exception ex) {
			System.err.println("Se ha producido un ERROR....");
			ex.printStackTrace();
		}
	}
	
	
	//Esta función lanza un proceso y lo devuelve como parámetro al programa que lo había llamado
	public Process lanzarBuscaPrimos_2(Integer n1, Integer n2) {
		String clase = "examen.TR01_BuscaPrimos";
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
		TR01_LanzaPrimos l = new TR01_LanzaPrimos();
		
		//Creamos formato para la hora
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		//METODO I
		System.out.println("Lanzamos procesos con Método I (secuencial)");
		System.out.println("*******************************************");
			
		//Registramos hora de inicio del proceso y mostramos por consola
		LocalDateTime horaInicio = LocalDateTime.now();
		System.err.println("Hora de inicio: " + horaInicio.format(formato));
				
		l.lanzarBuscaPrimos_1(1, 100000);
		l.lanzarBuscaPrimos_1(2, 500000);
		l.lanzarBuscaPrimos_1(3, 800000);
		l.lanzarBuscaPrimos_1(4, 200000);
		l.lanzarBuscaPrimos_1(5, 20000);
		l.lanzarBuscaPrimos_1(6, 500);
				
		//Registramos hora de finalización del proceso y mostramos por consola
		LocalDateTime horaFin = LocalDateTime.now();
		System.err.println("Hora de finalización: " + horaFin.format(formato));
		
	  //Calculamos tiempo total de ejecución del proceso
		Duration duracion = Duration.between(horaInicio, horaFin);
		long segTotal_1 = duracion.getSeconds();
		System.err.println("Tiempo total de proceso con Método I: " + segTotal_1 + " segundos");

		
		//METODO II
		System.out.println("\nLanzamos procesos con Método II (paralelo)");
		System.out.println("****************************************");
		
		//Registramos hora de inicio del proceso y mostramos por consola
		horaInicio = LocalDateTime.now();
		System.err.println("Hora de inicio: " + horaInicio.format(formato));
		
		//Lista para almacenar los procesos lanzados
		List<Process> procesos = new ArrayList<>();
		
		try {
			procesos.add(l.lanzarBuscaPrimos_2(1, 100000));
			procesos.add(l.lanzarBuscaPrimos_2(2, 200000));
			procesos.add(l.lanzarBuscaPrimos_2(3, 400000));
			procesos.add(l.lanzarBuscaPrimos_2(4, 200000));
			procesos.add(l.lanzarBuscaPrimos_2(5, 20000));
			procesos.add(l.lanzarBuscaPrimos_2(6, 500));
			
			for (Process p : procesos) {
				p.waitFor();
			}
		} catch (Exception e) {
            e.printStackTrace();
        }
				
		//Registramos hora de finalización del proceso y mostramos por consola
		horaFin = LocalDateTime.now();
		System.err.println("Hora de finalización: " + horaFin.format(formato));
		
	  //Calculamos tiempo total de ejecución del proceso
		duracion = Duration.between(horaInicio, horaFin);
		long segTotal_2 = duracion.getSeconds();
		System.err.println("Tiempo total de proceso con Método II: " + segTotal_2 + " segundos");
		
		
		//Calculamos porcentaxe
		float porcen = ((float)(segTotal_1 - segTotal_2) / segTotal_1) * 100f;
		System.out.printf("\nEl Método II es un %.2f%% más rápido.", porcen);
	}

}
