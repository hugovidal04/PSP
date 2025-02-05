/*
 * Un hilo debe generar un número al azar entre 1 y 100 que deben adivinar otros 10 hilos
 * Si un hilo acierta el número, debe terminar su ejecución inmediatamente.
 * El resto de hilos deben terminar su ejecución en cuanto propongan un número y se les avise de otro hilo ya ha acertado el número*/

package psp02_programacion_de_hilos;

import java.util.Random;

class NumeroOculto {

	  private final int numOculto;
	  private boolean numAdivinado = false;

	  public NumeroOculto() {
	    Random r = new Random();
	    this.numOculto = r.nextInt(101);
	    System.out.printf("[[Creado número oculto: %d]]\n", this.numOculto);
	  }

	  synchronized public int propuestaNumero(int num) {
	    int result = 0;   // Por defecto: no adivinado
	    if (numAdivinado) {
	      result = -1;
	    } else if (num == this.numOculto) {
	      numAdivinado = true;
	      result = 1;
	    }
	    return result;
	  }

	}


class HiloConcursante implements Runnable {

	  private final NumeroOculto numOculto;

	  private final String id;
	  private Random r = new Random();

	  HiloConcursante(NumeroOculto numOculto, String id) {
	    this.numOculto = numOculto;
	    this.id = id;
	  }

	  @Override
	  public void run() {

	    System.out.printf(">> Hilo %s comienza.\n", this.id);

	    boolean juegoTerminado = false;
	    while (!juegoTerminado) {

	      int num = r.nextInt(101);
	      System.out.printf("Hilo %s propone número %d.\n", this.id, num);

	      int resultado = numOculto.propuestaNumero(num);
	      if (resultado == -1) { //Otro hilo ha adivinado el número
	        juegoTerminado = true;
	        System.out.printf("Hilo %s: otro hilo ha acertado.\n", this.id);
	      } else if (resultado == 1) { //El Hilo ha acertado el número oculto
	        juegoTerminado = true;
	        System.out.printf("¡¡¡ Hilo %s acierta con el número: %d !!!\n", this.id, num);
	      }

	    }
	    System.out.printf("## Hilo %s termina.\n", this.id);
	  }

	}




public class PSP07_AdivinarNumero {
	  private final static int NUM_HILOS_CONCURSANTES = 10;

	  public static void main(String[] args) {
	    final NumeroOculto numOculto = new NumeroOculto();
	    Thread[] hilosConcursantes = new Thread[NUM_HILOS_CONCURSANTES];
	    
	    for (int i = 0; i < NUM_HILOS_CONCURSANTES; i++) {
	      hilosConcursantes[i] = new Thread(new HiloConcursante(numOculto, ""+i));
	    }
	    for(Thread t: hilosConcursantes) {
	      t.start();
	    }
	  }

}


























