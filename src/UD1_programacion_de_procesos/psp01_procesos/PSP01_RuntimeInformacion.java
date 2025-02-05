//Programa que muestra información de su instancia de la clase Runtime

package UD1_programacion_de_procesos.psp01_procesos;

public class PSP01_RuntimeInformacion {

	public static void main(String[] args) {
		System.out.println("INFORMACIÓN ");
		
		//Información de la clase Runtime
		System.out.println("Número de procesadores de la VM: " + Runtime.getRuntime().availableProcessors());
		System.out.println("Memoria disponible para la VM: " + Runtime.getRuntime().freeMemory());
		
		Runtime.getRuntime(); //Método estático
		System.out.println("Versión del JRE: " + Runtime.version());
	}
}