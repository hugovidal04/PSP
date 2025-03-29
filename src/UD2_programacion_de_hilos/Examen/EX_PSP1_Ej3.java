package UD2_programacion_de_hilos.Examen;

import java.util.Random;

class Cine {
	private int asientosDisponibles;

    public Cine(int asientos) {
        this.asientosDisponibles = asientos;
    }

    // Método sincronizado para intentar entrar al cine
    public synchronized boolean intentarEntrar(String espectador) throws InterruptedException {
        if (asientosDisponibles > 0) {
            asientosDisponibles--; // Ocupa un asiento
            System.out.println(espectador + " ha entrado. Asientos disponibles: " + asientosDisponibles);
            return true; // Entró exitosamente
        }
        System.out.println(espectador + " espera porque no hay asientos disponibles.");
        return false; // No pudo entrar
    }

    // Método sincronizado para salir del cine
    public synchronized void salir(String espectador) {
        asientosDisponibles++; // Libera un asiento
        System.out.println(espectador + " ha salido. Asientos disponibles: " + asientosDisponibles);
        notifyAll(); // Notifica a los espectadores que esperan
    }
}

class Espectador implements Runnable {
    private String nombre;
    private Cine cine;

    public Espectador(String nombre, Cine cine) {
        this.nombre = nombre;
        this.cine = cine;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {
            try {
                // Decidir si el espectador intentará entrar
                boolean puedeEntrar = random.nextBoolean(); // 50% de probabilidad
                if (!puedeEntrar) {
                    int tiempoEspera = random.nextInt(2000) + 1000; // Tiempo aleatorio entre 1 y 2 segundos
                    System.out.println(nombre + " no puede entrar ahora. Esperando " + tiempoEspera + " ms antes de intentar de nuevo.");
                    Thread.sleep(tiempoEspera);
                    //continue; // Reintentar entrada
                }

                // Intentar ocupar un asiento
                if (cine.intentarEntrar(nombre)) {
                    int tiempoPelicula = random.nextInt(5000) + 1000; // Tiempo viendo la película (1-5 segundos)
                    Thread.sleep(tiempoPelicula); // Simula el tiempo viendo la película
                    cine.salir(nombre); // Sale del cine
                    //break; // Termina su ciclo
                } else {
                    Thread.sleep(1000); // Espera 1 segundo antes de intentar nuevamente
                }
            } catch (InterruptedException e) {
                System.err.println(nombre + " fue interrumpido.");
                break;
            }
        }
    }
}

public class EX_PSP1_Ej3 {
    public static void main(String[] args) throws InterruptedException {
    	int NUM_ESPECTADORES = 10; // Número de espectadores
        int NUM_ASIENTOS = 5; // Número de asientos en el cine

        Cine cine = new Cine(NUM_ASIENTOS);

        Thread[] espectadores = new Thread[NUM_ESPECTADORES];
        for (int i = 0; i < NUM_ESPECTADORES; i++) {
            espectadores[i] = new Thread(new Espectador("Espectador-" + i, cine));
            espectadores[i].start();
        }

        // Simula el funcionamiento del cine durante 15 segundos
        Thread.sleep(15000);
        System.out.println("Fin de la simulación.");
        System.exit(0); // Termina el programa
    }
}
