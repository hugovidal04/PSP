/*
 * Programa que crea varios hilos, cada uno de los cuales cuenta desde 1 hasta un valor determinado
 */

package UD2_programacion_de_hilos.psp01_hilos;

//Clase Contador que implementa Runnable
class Contador06 implements Runnable {
 private final String nombre;
 private final int hasta;

 // Constructor para inicializar el nombre y el límite de conteo
 public Contador06(String nombre, int hasta) {
     this.nombre = nombre;
     this.hasta = hasta;
 }

 @Override
 public void run() {
     for (int i = 1; i <= hasta; i++) {
         System.out.printf("%s: %d\n", nombre, i);
         try {
             Thread.sleep(500); // Pausa de medio segundo entre cada conteo
         } catch (InterruptedException e) {
             System.err.printf("El hilo %s fue interrumpido\n", nombre);
         }
     }
     System.out.printf("%s ha terminado de contar hasta %d\n", nombre, hasta);
 }
}

public class PSP06_Contador {

	public static void main(String[] args) {
		// Crear tres instancias de la clase Contador con diferentes límites de conteo
        Thread contador1 = new Thread(new Contador06("Contador1", 5));
        Thread contador2 = new Thread(new Contador06("Contador2", 3));
        Thread contador3 = new Thread(new Contador06("Contador3", 4));

        // Iniciar los hilos
        contador1.start();
        contador2.start();
        contador3.start();

        System.out.println("Hilos iniciados. Contando en paralelo...\n");

	}

}
