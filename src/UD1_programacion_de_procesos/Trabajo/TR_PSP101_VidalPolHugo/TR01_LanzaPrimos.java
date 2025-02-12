package UD1_programacion_de_procesos.Trabajo.TR_PSP101_VidalPolHugo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TR01_LanzaPrimos {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que ingrese el número de rangos
        System.out.print("Ingrese la cantidad de rangos a procesar: ");
        int cantidadRangos = scanner.nextInt();

        int[][] rangos = new int[cantidadRangos][2];

        // Solicitar los rangos al usuario
        for (int i = 0; i < cantidadRangos; i++) {
            System.out.printf("Ingrese el rango %d (valor inicial y final separados por espacio): ", i + 1);
            rangos[i][0] = scanner.nextInt(); // Valor inicial
            rangos[i][1] = scanner.nextInt(); // Valor final
        }

        ejecutarSecuencial(rangos);
        ejecutarParalelo(rangos);

        scanner.close();
    }

    private static void ejecutarSecuencial(int[][] rangos) {
        System.out.println("\nLanzamos procesos con Método I (secuencial)");
        long inicio = System.currentTimeMillis();

        for (int[] rango : rangos) {
            TR01_BuscaPrimos tarea = new TR01_BuscaPrimos(rango[0], rango[1]);
            tarea.run();
            System.out.printf("La cantidad de primos entre: %d y %d es: %d%n", rango[0], rango[1], tarea.getTotalPrimos());
        }

        long fin = System.currentTimeMillis();
        System.out.printf("Tiempo total de proceso con Método I: %d segundos%n", (fin - inicio) / 1000);
    }

    private static void ejecutarParalelo(int[][] rangos) throws InterruptedException {
        System.out.println("\nLanzamos procesos con Método II (paralelo)");
        long inicio = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(rangos.length);
        List<TR01_BuscaPrimos> tareas = new ArrayList<>();

        for (int[] rango : rangos) {
            TR01_BuscaPrimos tarea = new TR01_BuscaPrimos(rango[0], rango[1]);
            tareas.add(tarea);
            executor.execute(tarea);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        for (int i = 0; i < rangos.length; i++) {
            System.out.printf("La cantidad de primos entre: %d y %d es: %d%n", rangos[i][0], rangos[i][1], tareas.get(i).getTotalPrimos());
        }

        long fin = System.currentTimeMillis();
        System.out.printf("Tiempo total de proceso con Método II: %d segundos%n", (fin - inicio) / 1000);

        // Cálculo del porcentaje de mejora
        double tiempoSecuencial = (fin - inicio) / 1000.0;
        double tiempoParalelo = (System.currentTimeMillis() - inicio) / 1000.0;
        double mejora = ((tiempoSecuencial - tiempoParalelo) / tiempoSecuencial) * 100;
        System.out.printf("El Método II es un %.2f%% más rápido.%n", mejora);
    }
}