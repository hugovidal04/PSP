//Diseña un programa en Java que simule el funcionamiento de varios temporizadores que funcionan en paralelo. Cada temporizador cuenta hasta un número
//predefinido, incrementando su cuenta cada segundo.
//Una vez que un temporizador llega a su límite, se detiene y muestra un mensaje indicando que ha terminado.
//
//El programa debe cumplir los siguientes requisitos:
//
//    Configurar cuántos temporizadores queremos (por ejemplo, 3).
//    Asignar un límite de conteo diferente a cada temporizador.
//    Mostrar en pantalla el progreso de cada temporizador en tiempo real.
//    Usar hilos para que todos los temporizadores cuenten al mismo tiempo.

package psp02_hilos;

class Temporizador implements Runnable {
    private final int id;
    private final int limite;

    public Temporizador(int id, int limite) {
        this.id = id;
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int i = 1; i <= limite; i++) {
            System.out.println("Temporizador " + id + " -> " + i + " segundos");
            try {
                Thread.sleep(1000); // Pausa de 1 segundo
            } catch (InterruptedException e) {
                System.err.println("Temporizador " + id + " fue interrumpido.");
                return;
            }
        }
        System.out.println("Temporizador " + id + " ha terminado.");
    }
}

public class PSP20_Temporizadores {

	public static void main(String[] args) {
        int[] limites = {5, 7, 10}; // Límite para cada temporizador

        for (int i = 0; i < limites.length; i++) {
            Thread temporizador = new Thread(new Temporizador(i + 1, limites[i]));
            temporizador.start();
        }

	}

}
