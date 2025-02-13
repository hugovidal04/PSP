/*
 * Crea un programa en Java que simule un sistema de productor-consumidor.
 * Un productor genera números aleatorios y los coloca en un buffer compartido.
 * Los consumidores retiran los números del buffer para procesarlos.
 * Reglas:
 *    El buffer tiene una capacidad limitada (por ejemplo, 5 elementos).
 *    El productor debe esperar si el buffer está lleno.
 *    El consumidor debe esperar si el buffer está vacío.
 * El programa debe:
 *    Mostrar cómo los números son producidos y consumidos.
 *    Terminar después de que el productor haya generado un número fijo de elementos (por ejemplo, 20).
 */

package UD2_programacion_de_hilos.psp01_hilos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Buffer {
    private final Queue<Integer> buffer;
    private final int capacidad;

    public Buffer(int capacidad) {
        this.buffer = new LinkedList<>();
        this.capacidad = capacidad;
    }

    public synchronized void producir(int item) throws InterruptedException {
        while (buffer.size() == capacidad) {
            System.out.println("Buffer lleno. El productor espera...");
            wait(); // Espera hasta que haya espacio en el buffer
        }
        buffer.add(item);
        System.out.println("Producido: " + item + " | Elementos en el buffer: " + buffer.size());
        notifyAll(); // Notifica a los consumidores que hay un nuevo elemento
    }

    public synchronized int consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("Buffer vacío. El consumidor espera...");
            wait(); // Espera hasta que haya elementos en el buffer
        }
        int item = buffer.poll();
        System.out.println("Consumido: " + item + " | Elementos restantes en el buffer: " + buffer.size());
        notifyAll(); // Notifica al productor que hay espacio disponible
        return item;
    }
}

class Productor22 implements Runnable {
    private final Buffer buffer;
    private final int totalItems;

    public Productor22(Buffer buffer, int totalItems) {
        this.buffer = buffer;
        this.totalItems = totalItems;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < totalItems; i++) {
            try {
                int item = random.nextInt(100); // Genera un número aleatorio
                buffer.producir(item);
                Thread.sleep(250); // Simula el tiempo de producción
            } catch (InterruptedException e) {
                System.err.println("Productor interrumpido.");
            }
        }
    }
}

class Consumidor22 implements Runnable {
    private final Buffer buffer;

    public Consumidor22(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int item = buffer.consumir();
                Thread.sleep(500); // Simula el tiempo de consumo
            } catch (InterruptedException e) {
                System.err.println("Consumidor interrumpido.");
                break; // Salir del bucle si se interrumpe el hilo
            }
        }
    }
}

public class PSP22_ProductorConsumidor {
    public static void main(String[] args) throws InterruptedException {
        final int BUFFER_CAPACIDAD = 5; // Capacidad máxima del buffer
        final int TOTAL_ITEMS = 100; // Total de elementos a producir
        final int NUM_CONSUMIDORES = 2; // Número de consumidores

        Buffer buffer = new Buffer(BUFFER_CAPACIDAD);

        // Crear y arrancar el productor
        Thread productor = new Thread(new Productor22(buffer, TOTAL_ITEMS));
        productor.start();

        // Crear y arrancar consumidores
        Thread[] consumidores = new Thread[NUM_CONSUMIDORES];
        for (int i = 0; i < NUM_CONSUMIDORES; i++) {
            consumidores[i] = new Thread(new Consumidor22(buffer), "Consumidor-" + (i + 1));
            consumidores[i].start();
        }

        // Esperar a que el productor termine
        productor.join();

        // Interrumpir a los consumidores y esperar a que terminen
        for (Thread consumidor : consumidores) {
            consumidor.interrupt();
            consumidor.join();
        }

        System.out.println("FIN DEL PROGRAMA: Todos los elementos han sido procesados.");
    }
}
