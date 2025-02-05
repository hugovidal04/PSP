//En una peluquería hay barberos y sillas para los clientes (siempre hay más sillas que clientes). Sin embargo,
//en esta peluquería no siempre hay trabajo por lo que los barberos duermen cuando no hay clientes a los que afeitar.
//Un cliente puede llegar a la barbería y encontrar alguna silla libre, en cuyo caso, el cliente se sienta y esperará que algún barbero le afeite.
//Puede ocurrir que el cliente llegue y no haya sillas libres, en cuyo caso se marcha.
//Simular el comportamiento de la barbería mediante un programa Java teniendo en cuenta que:
//    Se generan clientes continuamente, algunos encuentran silla y otros no. Los que no consigan silla desaparecen (terminan su ejecución)
//    Puede haber más sillas que barberos y al revés (poner constantes para poder cambiar fácilmente entre ejecuciones).
//    Se recuerda que no debe haber inanición, es decir ningún cliente debería quedarse en una silla esperando un tiempo infinito.

package psp02_programacion_de_hilos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Barberia {
    private final int numSillas;
    public final Queue<Cliente> sillasEspera;

    public Barberia(int numSillas) {
        this.numSillas = numSillas;
        this.sillasEspera = new LinkedList<>();
    }

    public synchronized boolean entrarCliente(Cliente cliente) {
        if (sillasEspera.size() < numSillas) {
            sillasEspera.add(cliente);
            System.out.println(cliente.getNombre() + " entra y se sienta en una silla de espera. Sillas ocupadas: " + sillasEspera.size());
            notifyAll(); // Despierta a algún barbero
            return true;
        } else {
            System.out.println(cliente.getNombre() + " encuentra la barbería llena y se marcha.");
            return false;
        }
    }

    public synchronized Cliente siguienteCliente() {
        while (sillasEspera.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " no tiene clientes y se duerme.");
                wait(); // Barbero espera a que lleguen clientes
            } catch (InterruptedException e) {
                System.err.println("Barbero interrumpido mientras dormía.");
            }
        }
        Cliente cliente = sillasEspera.poll();
        System.out.println(cliente.getNombre() + " es atendido por " + Thread.currentThread().getName() + ". Sillas ocupadas: " + sillasEspera.size());
        return cliente;
    }
}

class Cliente implements Runnable {
    private final String nombre;
    private final Barberia barberia;

    public Cliente(String nombre, Barberia barberia) {
        this.nombre = nombre;
        this.barberia = barberia;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        if (barberia.entrarCliente(this)) {
            // Cliente se queda en espera hasta ser atendido.
        } else {
            // Cliente se va porque no hay sillas.
        }
    }
}
  
class Barbero implements Runnable {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            Cliente cliente = barberia.siguienteCliente();
            afeitarCliente(cliente);
        }
    }

    private void afeitarCliente(Cliente cliente) {
        System.out.println(Thread.currentThread().getName() + " está afeitando a " + cliente.getNombre() + ".");
        try {
            Thread.sleep(2000); // Simula tiempo de afeitado
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " fue interrumpido mientras afeitaba.");
        }
        System.out.println(Thread.currentThread().getName() + " terminó de afeitar a " + cliente.getNombre() + ".");
    }
}


public class PSP18_BarberiaDormilona {

    public static void main(String[] args) throws InterruptedException {
    	final int NUM_SILLAS = 5; // Número de sillas de espera
        final int NUM_BARBEROS = 2; // Número de barberos
        final int NUM_CLIENTES = 20; // Total de clientes que llegan

        Barberia barberia = new Barberia(NUM_SILLAS);

        // Crear y arrancar los barberos
        for (int i = 0; i < NUM_BARBEROS; i++) {
            Thread barbero = new Thread(new Barbero(barberia), "Barbero-" + (i + 1));
            barbero.start();
        }

        // Generar clientes aleatoriamente
        Random random = new Random();
        for (int i = 0; i < NUM_CLIENTES; i++) {
            try {
                Thread.sleep(random.nextInt(1000)); // Tiempo aleatorio entre llegadas de clientes
            } catch (InterruptedException e) {
                System.err.println("Error en la generación de clientes.");
            }
            Thread cliente = new Thread(new Cliente("Cliente-" + (i + 1), barberia));
            cliente.start();
        }
    }
}
