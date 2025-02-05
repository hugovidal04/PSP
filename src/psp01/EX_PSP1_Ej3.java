package psp01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Cine {
    private final int numAsientos;
    private final int capacidadCola;
    private final Queue<Espectador> colaEspera;
    private final boolean[] asientos;

    public Cine(int numAsientos, int capacidadCola) {
        this.numAsientos = numAsientos;
        this.capacidadCola = capacidadCola;
        this.colaEspera = new LinkedList<>();
        this.asientos = new boolean[numAsientos];
    }

    public synchronized boolean asignarAsiento(Espectador espectador) {
        for (int i = 0; i < numAsientos; i++) {
            if (!asientos[i]) { 
                asientos[i] = true;
                System.out.println("Espectador " + espectador.getId() + " asignado al asiento " + i);
                return true;
            }
        }
        return false; 
    }

    public synchronized void liberarBahia(int idAsiento) {
        asientos[idAsiento] = false;
        System.out.println("Asiento " + idAsiento + " ahora está libre.");
    }

    public synchronized boolean agregarColaEspera(Espectador auto) {
        if (colaEspera.size() < capacidadCola) {
            colaEspera.add(auto);
            System.out.println("Espectador " + auto.getId() + " agregado a la cola de espera.");
            return true;
        }
        return false; 
    }

    public synchronized Espectador obtenerDeColaEspera() {
        return colaEspera.poll();
    }
}

class Espectador {
    private final int id;

    public Espectador(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class GeneradorEspectadores implements Runnable {
    private final Cine cine;
    private final int tiempoLlegadaMax;
    private int idAuto = 1;

    public GeneradorEspectadores(Cine cine, int tiempoLlegadaMax) {
        this.cine = cine;
        this.tiempoLlegadaMax = tiempoLlegadaMax;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            Espectador espectador = new Espectador(idAuto++);
            if (!cine.asignarAsiento(espectador)) {
                if (!cine.agregarColaEspera(espectador)) {
                    System.out.println("Espectador " + espectador.getId() + " se marcha, no hay espacio.");
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

public class EX_PSP1_Ej3 {

	public static void main(String[] args) {
        final int NUM_ASIENTOS = 3;
        final int CAPACIDAD_COLA = 5;
        final int TIEMPO_LLEGADA_MAX = 2000;

        Cine cine = new Cine(NUM_ASIENTOS, CAPACIDAD_COLA);
        new Thread(new GeneradorEspectadores(cine, TIEMPO_LLEGADA_MAX)).start();

	}

}