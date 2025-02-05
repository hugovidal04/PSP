package psp02_hilos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class HiloAdivinador implements Runnable {
    private final int id;
    private final int numeroCorrecto;
    private final ArrayList<Resultado> resultados;
    private final long inicio;
    private final int max;
    private static final Object lock = new Object();

    public HiloAdivinador(int id, int numeroCorrecto, ArrayList<Resultado> resultados, long inicio, int max) {
        this.id = id;
        this.numeroCorrecto = numeroCorrecto;
        this.resultados = resultados;
        this.inicio = inicio;
        this.max = max;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int intento = random.nextInt(max) + 1; // Número aleatorio entre 1 y 100
            if (intento == numeroCorrecto) {
                long tiempo = System.currentTimeMillis() - inicio;
                synchronized (lock) {
                    // Verificamos si ya está registrado este hilo para evitar duplicados
                    if (resultados.stream().noneMatch(r -> r.getId() == id)) {
                        resultados.add(new Resultado(id, tiempo));
                        System.out.println("Hilo " + id + " acertó el número " + numeroCorrecto + " en " + tiempo + " ms.");
                    }
                }
                break; // El hilo termina cuando acierta
            }
        }
    }
}

class Resultado {
    private final int id;
    private final long tiempo;

    public Resultado(int id, long tiempo) {
        this.id = id;
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public long getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Hilo " + id + " acertó en " + tiempo + " ms.";
    }
}


public class PSP21_AdivinarNumero2 {

	public static void main(String[] args) throws InterruptedException {
        final int MAX = 100000000; // Rango del número aleatorio
        final int n = 10;    // Número de hilos

        Random random = new Random();
        int numeroCorrecto = random.nextInt(MAX) + 1; // Número entre 1 y MAX
        System.out.println("Número generado: " + numeroCorrecto);

        ArrayList<Resultado> resultados = new ArrayList<>();
        Thread[] hilos = new Thread[n];
        long inicio = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            hilos[i] = new Thread(new HiloAdivinador(i + 1, numeroCorrecto, resultados, inicio, MAX));
            hilos[i].start();
        }

        // Esperamos a que todos los hilos terminen
        for (int i = 0; i < n; i++) {
            hilos[i].join();
        }

        // Ordenar los resultados por tiempo de acierto
        Collections.sort(resultados, Comparator.comparingLong(Resultado::getTiempo));

        System.out.println("\nOrden de aciertos:");
        for (Resultado resultado : resultados) {
            System.out.println(resultado);
        }
	}

}
