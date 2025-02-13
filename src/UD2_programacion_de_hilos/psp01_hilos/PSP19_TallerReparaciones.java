//En un taller de reparación de automóviles, hay un número limitado de bahías de servicio donde los autos pueden ser atendidos.
//También hay una lista de espera para autos que llegan cuando todas las bahías están ocupadas.
//El taller tiene varios mecánicos que trabajan en los autos de forma simultánea.
//
//El funcionamiento es el siguiente:
//
//    Si un auto llega y hay una bahía de servicio libre, se asigna inmediatamente a un mecánico para su reparación.
//    Si todas las bahías están ocupadas, el auto pasa a la lista de espera (cola de espera).
//    Si no hay espacio en la cola de espera, el auto se va del taller sin ser atendido.
//    Cuando un mecánico termina de reparar un auto, toma el siguiente auto de la lista de espera (si hay alguno) o espera hasta que llegue otro auto.
//
//Simular el comportamiento del taller usando un programa en Java que cumpla los siguientes requisitos:
//
//    Los autos llegan al taller de forma continua y aleatoria.
//    Cada reparación tiene una duración aleatoria dentro de un rango definido.
//    Debe ser posible configurar:
//        El número de bahías de servicio.
//        El número de mecánicos.
//        La capacidad máxima de la cola de espera.
//    El programa debe mostrar:
//        Cuándo un auto llega al taller y si se atiende o se marcha por falta de espacio.
//        Qué mecánico está atendiendo a qué auto.
//        El estado de las bahías y la cola de espera en tiempo real.

package UD2_programacion_de_hilos.psp02_hilos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Taller {
    private final int numBahias;
    private final int capacidadCola;
    private final Queue<Auto> colaEspera;
    private final boolean[] bahias;

    public Taller(int numBahias, int capacidadCola) {
        this.numBahias = numBahias;
        this.capacidadCola = capacidadCola;
        this.colaEspera = new LinkedList<>();
        this.bahias = new boolean[numBahias];
    }

    // Intenta asignar una bahía libre a un auto
    public synchronized boolean asignarBahia(Auto auto) {
        for (int i = 0; i < numBahias; i++) {
            if (!bahias[i]) { // Si la bahía está libre
                bahias[i] = true;
                System.out.println("Auto " + auto.getId() + " asignado a la bahía " + i);
                return true;
            }
        }
        return false; // No hay bahías disponibles
    }

    // Libera una bahía después de que termine la reparación
    public synchronized void liberarBahia(int idBahia) {
        bahias[idBahia] = false;
        System.out.println("Bahía " + idBahia + " ahora está libre.");
    }

    // Agrega un auto a la cola de espera
    public synchronized boolean agregarColaEspera(Auto auto) {
        if (colaEspera.size() < capacidadCola) {
            colaEspera.add(auto);
            System.out.println("Auto " + auto.getId() + " agregado a la cola de espera.");
            return true;
        }
        return false; // La cola está llena
    }

    // Obtiene el siguiente auto de la cola de espera
    public synchronized Auto obtenerDeColaEspera() {
        return colaEspera.poll();
    }
}

class Auto {
    private final int id;

    public Auto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class Mecanico implements Runnable {
    private final int id;
    private final Taller taller;

    public Mecanico(int id, Taller taller) {
        this.id = id;
        this.taller = taller;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            Auto auto = taller.obtenerDeColaEspera();
            if (auto != null) {
                System.out.println("Mecánico " + id + " reparando auto " + auto.getId());
                int tiempoReparacion = 1000 + random.nextInt(4000); // Tiempo aleatorio entre 1 y 5 segundos
                try {
                    Thread.sleep(tiempoReparacion);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Mecánico " + id + " terminó con el auto " + auto.getId());
                taller.liberarBahia(id);
            } else {
                try {
                    Thread.sleep(500); // Espera un poco antes de volver a revisar la cola
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class GeneradorAutos implements Runnable {
    private final Taller taller;
    private final int tiempoLlegadaMax;
    private int idAuto = 1;

    public GeneradorAutos(Taller taller, int tiempoLlegadaMax) {
        this.taller = taller;
        this.tiempoLlegadaMax = tiempoLlegadaMax;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            Auto auto = new Auto(idAuto++);
            if (!taller.asignarBahia(auto)) {
                if (!taller.agregarColaEspera(auto)) {
                    System.out.println("Auto " + auto.getId() + " se marcha, no hay espacio.");
                }
            }

            try {
                Thread.sleep(random.nextInt(tiempoLlegadaMax));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class PSP19_TallerReparaciones {

	public static void main(String[] args) {
        final int NUM_BAHIAS = 3;
        final int CAPACIDAD_COLA = 5;
        final int NUM_MECANICOS = 3;
        final int TIEMPO_LLEGADA_MAX = 2000;

        Taller taller = new Taller(NUM_BAHIAS, CAPACIDAD_COLA);

        // Crear y lanzar los hilos de los mecánicos
        for (int i = 0; i < NUM_MECANICOS; i++) {
            new Thread(new Mecanico(i, taller)).start();
        }

        // Crear y lanzar el generador de autos
        new Thread(new GeneradorAutos(taller, TIEMPO_LLEGADA_MAX)).start();

	}

}
