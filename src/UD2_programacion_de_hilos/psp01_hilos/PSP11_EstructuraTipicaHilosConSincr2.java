package UD2_programacion_de_hilos.psp01_hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Contador11 {
	private AtomicInteger cuenta = new AtomicInteger(0); //Contador seguro para hilos
	
	public void incrementar() {
		cuenta.incrementAndGet(); //Método propio de AtomicInteger
	}
	
	public synchronized int getCuenta() {
		return cuenta.get(); //Método propio de AtomicInteger
	}
}

class TareaCompleja11 implements Runnable {
	private Contador11 contador;
	private int[] operacionesPorHilo;
	private int numeroHilo;
	
	public TareaCompleja11(Contador11 contador, int[] operacionesPorHilo, int numeroHilo) {
		this.contador = contador;
		this.operacionesPorHilo = operacionesPorHilo;
		this.numeroHilo = numeroHilo;
	}
	
	@Override
	public void run() {
		//Hacemos algo...
		Random generador = new Random();
		int numAzar = (1 + generador.nextInt(5) * 100);
		int operacionesRealizadas = 0;
		
		for (int i = 0; i < numAzar; i++) {
			int a = i * 3;
			contador.incrementar();
			operacionesRealizadas++;
		}
		
		operacionesPorHilo[numeroHilo] = operacionesRealizadas;
		
		Thread hiloActual = Thread.currentThread();
		String miNombre = hiloActual.getName();
		System.out.println("Finalizado el hilo: " + miNombre + " con " + operacionesRealizadas + " operaciones");
	}
}


public class PSP11_EstructuraTipicaHilosConSincr2 {

	public static void main(String[] args) {
		final int NUM_HILOS = 100;
		Thread[] hilosAsociados = new Thread[NUM_HILOS];
		
		int[] operacionesPorHilo = new int[NUM_HILOS];
		
		Contador11 contadorCompartido = new Contador11();
		
		for (int i = 0; i < NUM_HILOS; i++) {
			TareaCompleja11 t = new TareaCompleja11(contadorCompartido, operacionesPorHilo, i);
			Thread hilo = new Thread(t);
			hilo.setName("Hilo-" + i);
			hilo.start();
			hilosAsociados[i] = hilo;
		}
		
		//Después de crear lo hilos esperamos a que acaben
		
		for (int i = 0; i < NUM_HILOS; i++) {
			Thread hilo = hilosAsociados[i];
			try {
				hilo.join();
			} catch (InterruptedException e) {
				System.out.println("Algún hilo acabó antes de tiempo");
			}
		}
		
		//Sumamos todas las operaciones por hilo almacenadas en el array
		int totalOperaciones = 0;
		for (int operaciones : operacionesPorHilo) {
			totalOperaciones += operaciones;
		}		
		
		System.out.println("El hilo principal ha terminado");
		System.out.printf("Total de operaciones (suma de operaciones individuales): %d\n", totalOperaciones);
		System.out.printf("Total de operaciones (contador compartido): %d\n", contadorCompartido.getCuenta());
	}

}