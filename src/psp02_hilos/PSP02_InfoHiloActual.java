/* *
 * Programa que obtiene el objeto Thread correspondiente al hilo actual de ejecución
 * y obtiene información acerca de él
 * */

package psp02_hilos;

public class PSP02_InfoHiloActual {

	public static void main(String[] args) {
		Thread t = Thread.currentThread(); //Devuelve el objeto Thread que represena al hilo de ejecución actualmente en ejecución
		
		System.out.println("Información del hilo actual");
		System.out.println("***************************");
		System.out.printf("Id...............: %d\n", t.getId());
		System.out.printf("Nombre...........: %s\n", t.getName());
		System.out.printf("Prioridad........: %d\n", t.getPriority());
		System.out.printf("Estado...........: %s\n", t.getState());
		System.out.printf("Grupo de hilos...: %s\n", t.getThreadGroup());
		System.out.printf("De tipo daemon...: %s\n", t.isDaemon() ? "Sí" : "No");
	}

}
