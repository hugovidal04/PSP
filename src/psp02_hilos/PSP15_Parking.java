/*
 * En una ciudad existe un parking con un número limitado de plazas. Los coches intentan entrar al parking,
 * pero si está lleno deben esperar a que una plaza quede libre. Una vez dentro del parking,
 * los coches permanecen un tiempo aleatorio (simulando que están estacionados) antes de salir y liberar su plaza.
 * 
 * Cada coche será representado por un hilo que intentará ocupar una plaza en el parking.
 * El parking será un recurso compartido que debe gestionar la entrada y salida de los coches utilizando sincronización.
 * Los coches que no puedan entrar inmediatamente al parking deben esperar.
 * El programa debe permitir que el parking funcione durante un tiempo determinado (por ejemplo, 10 segundos) antes de detenerse.
 */

package psp02_hilos;

import java.util.Random;

class Parking {
    private int plazasDisponibles;

    public Parking(int plazas) {
        this.plazasDisponibles = plazas;
    }

    // Método sincronizado para entrar al parking
    public synchronized void entrar(String coche) throws InterruptedException {
        while (plazasDisponibles == 0) {
            System.out.println(coche + " espera porque el parking está lleno.");
            wait(); // Espera a que una plaza quede libre
        }
        plazasDisponibles--; // Ocupa una plaza
        System.out.println(coche + " ha entrado. Plazas disponibles: " + plazasDisponibles);
    }

    // Método sincronizado para salir del parking
    public synchronized void salir(String coche) {
        plazasDisponibles++; // Libera una plaza
        System.out.println(coche + " ha salido. Plazas disponibles: " + plazasDisponibles);
        notifyAll(); // Notifica a los hilos que esperan
    }
}

class Coche implements Runnable {
    private String nombre;
    private Parking parking;

    public Coche(String nombre, Parking parking) {
        this.nombre = nombre;
        this.parking = parking;
    }

    @Override
    public void run() {
        try {
            parking.entrar(nombre); // Intenta entrar al parking
            int tiempoEstacionado = new Random().nextInt(5000) + 1000; // Tiempo aleatorio entre 1 y 5 segundos
            Thread.sleep(tiempoEstacionado); // Simula el tiempo estacionado
            parking.salir(nombre); // Sale del parking
        } catch (InterruptedException e) {
            System.err.println(nombre + " fue interrumpido.");
        }
    }
}

public class PSP15_Parking {
    public static void main(String[] args) throws InterruptedException {
        int NUM_COCHES = 10; // Número de coches
        int NUM_PLAZAS = 5; // Número de plazas en el parking

        Parking parking = new Parking(NUM_PLAZAS);

        Thread[] coches = new Thread[NUM_COCHES];
        for (int i = 0; i < NUM_COCHES; i++) {
            coches[i] = new Thread(new Coche("Coche-" + i, parking));
            coches[i].start();
        }

        // Simula el funcionamiento del parking durante 10 segundos
        Thread.sleep(10000);
        System.out.println("Fin de la simulación.");
        System.exit(0); // Termina el programa
    }
}
