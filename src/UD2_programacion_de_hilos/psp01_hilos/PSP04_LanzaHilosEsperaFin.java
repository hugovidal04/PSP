/* *
 * Programa que lanza dos hilos y realiza esperas aleatorias entre 10 y 500 ms.
 * El hilo principal espera a la terminación de los dos hilos lanzados
 * */

package UD2_programacion_de_hilos.psp02_hilos;

import java.util.Random;

class Hilo4 implements Runnable {
	private final String nombre;
	
	Hilo4(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
		Random r = new Random();
		
		for(int i = 0; i < 5; i++) {
			int pausa = 10 + r.nextInt(500 - 10); //Pausa entre 10 y 500 ms
			System.out.printf("Hilo: %s hace una pausa de %d ms.\n", this.nombre, pausa);
			try {
				Thread.sleep(pausa);
			} catch (InterruptedException e) {
				System.out.printf("Hilo %s interrumpido.\n", this.nombre);
			}
		}
		System.out.printf("Hilo %s terminado.\n", this.nombre);
	}
}


public class PSP04_LanzaHilosEsperaFin {

	public static void main(String[] args) {
		Thread h1 = new Thread(new Hilo4("H1"));
		Thread h2 = new Thread(new Hilo4("H2"));
		
		h1.start();
		h2.start();
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			System.out.println("Hilo principal interrumpido");
		}
		System.out.println("Hilo principal terminado");
	}
}
