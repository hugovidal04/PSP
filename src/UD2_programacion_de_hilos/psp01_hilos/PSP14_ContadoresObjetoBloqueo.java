//Programa que lanza 10 hilos y entre todos, y a partes iguales, incremente cada uno de los dos contadores hasta alcanzar un valor de 100 000 000
//En un momento dado sólamente un hilo puede realizar una operación de incremento sdobre un contador
//Dos hilos distintos sí pueden incrementar cada uno de ellos, de forma concurrente, un contador distinto

package UD2_programacion_de_hilos.psp01_hilos;

class Contadores {

	  private long cont1 = 0;
	  private long cont2 = 0;
	  private final Object lock1 = new Object(); //Importante que sea de tipo final. Si se le asigna un nuevo valor quedan sin efecto los bloqueos
	  private final Object lock2 = new Object();

	  public void incrementar1() {
	    synchronized (lock1) { //Sólo un hilo puede acceder a este bloque de código. Evita que cont1 pueda ser modificado por varios hilos
	      cont1++;
	    }
	  }

	  public long getContador1() {
	    synchronized (lock1) { //Protege de accesos simultáneos por parte de varios hilos
	      return cont1;
	    }
	  }

	  public void incrementar2() {
	    synchronized (lock2) {
	      cont2++;
	    }
	  }

	  public long getContador2() {
	    synchronized (lock2) {
	      return cont2;
	    }
	  }

	}


class Hilo14 implements Runnable {

	  int numHilo, miParte, miCuenta = 0;
	  Contadores cont;

	  public int getMiCuenta() {
	    return miCuenta;
	  }

	  Hilo14(int numHilo, int miParte, Contadores c) {
	    this.numHilo = numHilo;
	    this.miParte = miParte;
	    this.cont = c;
	  }

	  @Override
	  public void run() {
	    for (int i = 0; i < miParte; i++) {
	      this.cont.incrementar1();
	      miCuenta++;
	    }
	    for (int i = 0; i < miParte; i++) {
	      this.cont.incrementar2();
	      miCuenta++;
	    }
	    System.out.printf("Hilo %d terminado, cuenta: %d\n", numHilo, getMiCuenta());
	  }
	}


public class PSP14_ContadoresObjetoBloqueo {

	  private static final int NUM_HILOS = 10;
	  private static final int CUENTA_TOTAL = 100000000;

	  public static void main(String[] args) {
	    Contadores c = new Contadores();
	    Thread[] hilos = new Thread[NUM_HILOS];
	    for (int i = 0; i < NUM_HILOS; i++) {
	      Thread th = new Thread(new Hilo14(i, CUENTA_TOTAL / NUM_HILOS, c));
	      th.start();
	      hilos[i] = th;
	    }
	    for (int i = 0; i < NUM_HILOS; i++) {
	      try {
	        hilos[i].join();
	      } catch (InterruptedException e) {
	        System.out.printf("INFO: Hilo %d interrumpido\n", i);
	      }
	    }
	    System.out.printf("Valor final contador 1: %s\n", c.getContador1());
	    System.out.printf("Valor final contador 2: %s\n", c.getContador2());
	  }

}
