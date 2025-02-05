package psp02_hilos;

import java.util.LinkedList;
import java.util.Random;

class Cola {
    private int MAX_ELEMENTOS;
    private LinkedList<Integer> cola;

    public Cola(int max) {
        cola = new LinkedList<>();
        this.MAX_ELEMENTOS = max;
    }

    public synchronized boolean estaVacia() {
        return cola.isEmpty();
    }

    public synchronized boolean estaLlena() {
        return cola.size() == MAX_ELEMENTOS;
    }

    public synchronized void encolar(int numero) {
        while (estaLlena()) {
            try {
                wait(); // Esperar a que haya espacio disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cola.addLast(numero);
        notifyAll(); // Notificar a los consumidores
    }

    public synchronized int desencolar() {
        while (estaVacia()) {
            try {
                wait(); // Esperar a que haya elementos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int numero = cola.removeFirst();
        notifyAll(); // Notificar a los productores
        return numero;
    }
}



class Productor implements Runnable {
    Cola colaCompartida;

    public Productor(Cola cola) {
        this.colaCompartida = cola;
    }

    public static int numAzar(int max) {
        Random generador = new Random();
        return generador.nextInt(max);
    }

    public void run() {
        while (true) {
            int num = numAzar(10);
            colaCompartida.encolar(num);
            System.out.println("Productor encoló el número: " + num);
        }
    }
}

class Consumidor implements Runnable {
    Cola colaCompartida;

    public Consumidor(Cola cola) {
        this.colaCompartida = cola;
    }

    public void run() {
        while (true) {
            int num = colaCompartida.desencolar();
            System.out.println("Consumidor recuperó el número: " + num);
        }
    }
}



public class PSP13_ProductoresConsumidores {
    public static void main(String[] args) throws InterruptedException {
        int MAX_PRODUCTORES = 5;
        int MAX_CONSUMIDORES = 7;
        int MAX_ELEMENTOS = 10;

        Thread[] hilosProductor = new Thread[MAX_PRODUCTORES];
        Thread[] hilosConsumidor = new Thread[MAX_CONSUMIDORES];

        Cola colaCompartida = new Cola(MAX_ELEMENTOS);

        for (int i = 0; i < MAX_PRODUCTORES; i++) {
            Productor productor = new Productor(colaCompartida);
            hilosProductor[i] = new Thread(productor);
            hilosProductor[i].start();
        }

        for (int i = 0; i < MAX_CONSUMIDORES; i++) {
            Consumidor consumidor = new Consumidor(colaCompartida);
            hilosConsumidor[i] = new Thread(consumidor);
            hilosConsumidor[i].start();
        }

        Thread.sleep(10000); // Permite que los hilos trabajen durante 10 segundos

        System.out.println("Finalizando programa...");
        System.exit(0); // Finaliza todos los hilos y el programa
    }
}
