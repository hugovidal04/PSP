/* *
 * Programa que lanza vario hilos que cooperan incrementando un contador hasta un valor determinado.
 * Ese valor se divide entre el número de hilos y el resultado es la cantidad de veces que ese hilo debe incrementarlo
 * El hilo principal crea y lanza todos los hilos y espera a que terminen de ejecutarse
 * */

package UD2_programacion_de_hilos.psp02_hilos;

class Contador {
	private int cuenta = 0;
	
	//public int getCuenta() { //Los resultados finales no son los esperados
	synchronized public int getCuenta() {
		return cuenta;
	}
	
	//public int incrementa() {  //Los resultados finales no son los esperados
	synchronized public int incrementa() {
		this.cuenta++;
		return cuenta;
	}
}


class Hilo05 implements Runnable {
	int numHilo, miParte, miCuenta = 0;
	private final Contador cont;
	
	public int getMiCuenta() {
		return miCuenta;
	}
	
	Hilo05(int numHilo, int miParte, Contador c) {
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = c;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < miParte; i++) {
			this.cont.incrementa();
			miCuenta++;
		}
		System.out.printf("Hilo %d terminado, cuenta: %d\n", numHilo, getMiCuenta());
	}
}


public class PSP05_HilosCooperantesNoSinc {
	private static final int NUM_HILOS = 10;
	private static final int CUENTA_TOTAL = 100000;

	public static void main(String[] args) {
		Contador c = new Contador();
		Thread[] hilos = new Thread[NUM_HILOS];
		for (int i = 0; i < NUM_HILOS; i++) {
			Thread th = new Thread(new Hilo05(i, CUENTA_TOTAL / NUM_HILOS, c));
			th.start();
			hilos[i] = th;
		}
		for (Thread h: hilos) {
			try {
				h.join();
			} catch (InterruptedException e) {
				
			}
		}
		System.out.printf("Cuenta global: %s\n", c.getCuenta());
	}

}
