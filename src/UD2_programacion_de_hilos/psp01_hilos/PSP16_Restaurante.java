/*
 * En un restaurante hay un número limitado de mesas disponibles
 * Los clientes llegan al restaurante y deben esperar si no hay mesas libres
 * Una vez que un cliente encuentra una mesa, se queda en ella durante un tiempo aleatorio (simulando que está comiendo) y luego la libera
 * El programa debe simular esta situación usando hilos y garantizar que los clientes no esperen indefinidamente (sin inanición)
 * Cada cliente será representado por un hilo que intentará ocupar una mesa
 * El restaurante será un recurso compartido que gestione las mesas mediante métodos sincronizados
 * Si un cliente no encuentra una mesa libre, debe esperar hasta que se libere una
 * El programa debe ejecutar la simulación durante un tiempo definido
 */


package UD2_programacion_de_hilos.psp01_hilos;

import java.util.Random;

class Restaurante {
    private int mesasDisponibles;

    public Restaurante(int mesas) {
        this.mesasDisponibles = mesas;
    }

    // Método sincronizado para ocupar una mesa
    public synchronized void ocuparMesa(String cliente) throws InterruptedException {
        while (mesasDisponibles == 0) {
            System.out.println(cliente + " espera porque no hay mesas disponibles.");
            wait(); // Espera a que se libere una mesa
        }
        mesasDisponibles--; // Ocupa una mesa
        System.out.println(cliente + " ocupa una mesa. Mesas disponibles: " + mesasDisponibles);
    }

    // Método sincronizado para liberar una mesa
    public synchronized void liberarMesa(String cliente) {
        mesasDisponibles++; // Libera una mesa
        System.out.println(cliente + " libera una mesa. Mesas disponibles: " + mesasDisponibles);
        notifyAll(); // Notifica a los clientes que esperan
    }
}

class ClienteRestaurante implements Runnable {
    private String nombre;
    private Restaurante restaurante;

    public ClienteRestaurante(String nombre, Restaurante restaurante) {
        this.nombre = nombre;
        this.restaurante = restaurante;
    }

    @Override
    public void run() {
        try {
            restaurante.ocuparMesa(nombre); // Intenta ocupar una mesa
            int tiempoComiendo = new Random().nextInt(5000) + 1000; // Tiempo aleatorio entre 1 y 5 segundos
            Thread.sleep(tiempoComiendo); // Simula el tiempo comiendo
            restaurante.liberarMesa(nombre); // Libera la mesa
        } catch (InterruptedException e) {
            System.err.println(nombre + " fue interrumpido.");
        }
    }
}

public class PSP16_Restaurante {
    public static void main(String[] args) throws InterruptedException {
        int NUM_CLIENTES = 15; // Número de clientes
        int NUM_MESAS = 5; // Número de mesas disponibles en el restaurante

        Restaurante restaurante = new Restaurante(NUM_MESAS);

        Thread[] clientes = new Thread[NUM_CLIENTES];
        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Thread(new ClienteRestaurante("Cliente-" + i, restaurante));
            clientes[i].start();
        }

        // Simula el funcionamiento del restaurante durante 10 segundos
        Thread.sleep(10000);
        System.out.println("Fin de la simulación.");
        System.exit(0); // Termina el programa
    }
}

