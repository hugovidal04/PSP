/*
 * Se desea simular los posibles beneficios de diversas estrategias de juego en un casino. La ruleta francesa es un juego en el que hay
 * una ruleta con 37 números (del 0 al 36). Cada 3000 milisegundos el croupier saca un número al azar y los diversos hilos apuestan para ver si ganan.
 * Todos los hilos empiezan con 1.000 euros y la banca (que controla la ruleta) con 50.000. Cuando los jugadores pierden dinero,
 * la banca incrementa su saldo.
 * 
 * Se puede jugar a un número concreto. Habrá 4 hilos que eligen números al azar del 1 al 36 (no el 0) y restarán 10 euros de su saldo para apostar 
 * a ese ese número. Si sale su número su saldo se incrementa en 360 euros (36 veces lo apostado).
 * Se puede jugar a par/impar. Habrá 4 hilos que eligen al azar si apuestan a que saldrá un número par o un número impar. 
 * Siempre restan 10 euros para apostar y si ganan incrementan su saldo en 20 euros.
 * Se puede jugar a la «martingala». Habrá 4 hilos que eligen números al azar. Elegirán un número y empezarán restando 10 euros de su saldo 
 * para apostar a ese número. Si ganan incrementan su saldo en 360 euros. Si pierden jugarán el doble de su apuesta anterior
 * (es decir, 20, luego 40, luego 80, y así sucesivamente)
 * 
 * La banca acepta todas las apuestas pero nunca paga más dinero del que tiene.
 * Si sale el 0, todo el mundo pierde y la banca se queda con todo el dinero.
 */

package psp02_programacion_de_hilos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


class Banca implements Runnable {
    private static final int MAX_BANCA = 1000; //Saldo inicial de la banca
    private static AtomicInteger saldo = new AtomicInteger(MAX_BANCA);
    private static int numeroRuleta;
    private static boolean numeroGenerado = false;

    public synchronized static int getNumeroRuleta() {
        while (!numeroGenerado) {
            try {
                Banca.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numeroGenerado = false;
        return numeroRuleta;
    }

    public static boolean actualizarSaldo(int cantidad) {
        int saldoActual = saldo.get();
        if (cantidad < 0 && saldoActual + cantidad < 0) {
            return false;
        }
        saldo.addAndGet(cantidad);
        return true;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            numeroRuleta = rand.nextInt(37);
            System.out.println("Saldo Banca: " + saldo.get());
            System.out.println("Número generado: " + numeroRuleta);
            numeroGenerado = true;
            synchronized (Banca.class) {
                Banca.class.notifyAll();
            }

            try {
                Thread.sleep(200); //Tiempo de espera entre apuestas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



abstract class Jugador implements Runnable {
    protected int saldo = 100;

    public Jugador() {
        super();
    }

    protected abstract void realizarApuesta(int numeroRuleta);

    @Override
    public void run() {
        while (saldo > 0) {
            int numeroRuleta = Banca.getNumeroRuleta();
            realizarApuesta(numeroRuleta);
        }
        System.out.println(Thread.currentThread().getName() + " ha quedado sin saldo.");
    }
}



class JugadorNumeroConcreto extends Jugador {
    @Override
    protected void realizarApuesta(int numeroRuleta) {
        int apuesta = 10;
        int numeroElegido = new Random().nextInt(36) + 1;
        saldo -= apuesta;
        if (numeroRuleta == numeroElegido) {
            saldo += 360;
        }
        Banca.actualizarSaldo(apuesta);
    }
}



class JugadorParImpar extends Jugador {
    @Override
    protected void realizarApuesta(int numeroRuleta) {
        int apuesta = 10;
        boolean apostarPar = new Random().nextBoolean();
        saldo -= apuesta;
        if ((numeroRuleta != 0) && (numeroRuleta % 2 == 0) == apostarPar) {
            saldo += 20;
        }
        Banca.actualizarSaldo(apuesta);
    }
}



class JugadorMartingala extends Jugador {
    private int apuesta = 10;
    private int numeroElegido;

    public JugadorMartingala() {
        super();
        numeroElegido = new Random().nextInt(36) + 1;
    }

    @Override
    protected void realizarApuesta(int numeroRuleta) {
        saldo -= apuesta;
        if (numeroRuleta == numeroElegido) {
            saldo += 360;
            apuesta = 10; // Restablecer apuesta
        } else {
            apuesta *= 2; // Duplicar la apuesta
        }
        Banca.actualizarSaldo(apuesta);
    }
}










public class PSP12_CasinoRoyal {
	private static final int NUMERO_JUGADORES = 4;

	public static void main(String[] args) {
        // Crear y lanzar el hilo de la banca
        Thread bancaThread = new Thread(new Banca(), "Banca");
        bancaThread.start();

        // Crear y lanzar los hilos de los jugadores de número concreto
        for (int i = 0; i < NUMERO_JUGADORES; i++) {
            Thread jugadorNumero = new Thread(new JugadorNumeroConcreto(), "Jugador Número " + (i + 1));
            jugadorNumero.start();
        }

        // Crear y lanzar los hilos de los jugadores de par/impar
        for (int i = 0; i < NUMERO_JUGADORES; i++) {
            Thread jugadorParImpar = new Thread(new JugadorParImpar(), "Jugador Par/Impar " + (i + 1));
            jugadorParImpar.start();
        }

        // Crear y lanzar los hilos de los jugadores de martingala
        for (int i = 0; i < NUMERO_JUGADORES; i++) {
            Thread jugadorMartingala = new Thread(new JugadorMartingala(), "Jugador Martingala " + (i + 1));
            jugadorMartingala.start();
        }
	}
}
