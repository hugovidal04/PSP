/* *
 * Programa que obtiene información acerca de las prioridades de los hilos
 * */

package UD2_programacion_de_hilos.psp02_hilos;

public class PSP03_InfoPrioridadHilos {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		
		System.out.println("Información prioridades");
		System.out.println("***********************");
		System.out.printf("Prioridad mínima............: %d\n", Thread.MIN_PRIORITY);
		System.out.printf("Prioridad máxima............: %d\n", Thread.MAX_PRIORITY);
		System.out.printf("Prioridad normal............: %d\n", Thread.NORM_PRIORITY);
		System.out.printf("Prioridad de hilo actual....: %d\n", t.getPriority());
	}

}
