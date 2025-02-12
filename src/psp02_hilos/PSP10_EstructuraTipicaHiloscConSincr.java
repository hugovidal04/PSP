/*
 * Estructura básica de un programa que genera hilos y se sincronizan entre ellos para evitar 'race condition'
 * En este caso se realizan operaciones de multiplicación (sin ninguna finalidad concreta)
 * Y el programa muestra finalmente el número de operaciones realizadas
 * */

package psp02_hilos;

import java.util.Random;

class TareaCompleja10 implements Runnable{
    private Contador10 contador; // Agregamos una referencia a Contador10
    
    public TareaCompleja10(Contador10 contador) {
        this.contador = contador; // Inicializamos el contador compartido
    }
    
    @Override
    public void run() {
        // Hacemos algo, por ejemplo un número indeterminado de multiplicaciones
        Random generador = new Random();
        int numAzar = (1 + generador.nextInt(5)) * 100;
        for (int i = 0; i < numAzar; i++) {
            int a = i * 3;
            contador.incrementar(); // Actualizamos el contador compartido
        }
        
        Thread hiloActual = Thread.currentThread();
        String miNombre = hiloActual.getName();
        System.out.println("Finalizado el hilo " + miNombre);
    }
}


class Contador10 {
    private int cuenta; // Cambiado a `private` para mantener la encapsulación

    public Contador10() {
        cuenta = 0;
    }
    
	//  //Sincronizamos este fragmento de código
	//  public synchronized void incrementar(){
	//          cuenta=cuenta+1;
	//  }
    
    // Aumentamos más el rendimiento sincronizando de este modo
    public void incrementar() {
        System.out.println("Otras cosas");
        synchronized(this){
                cuenta = cuenta + 1;
        }
        System.out.println("Más cosas...");
        synchronized(this){
                if (cuenta > 300){
                        System.out.println("Este hilo trabaja mucho");
                }
        }
    }
    
    // Método sincronizado para obtener el valor del contador
    public synchronized int getCuenta() {
        return cuenta;
    }
  
}


public class PSP10_EstructuraTipicaHiloscConSincr {

	public static void main(String[] args) {
		int NUM_HILOS=100;
        Thread[] hilosAsociados;
        
        // Crear una única instancia de Contador10 compartida entre todos los hilos
        Contador10 contadorCompartido = new Contador10();
        

        hilosAsociados=new Thread[NUM_HILOS];
        for (int i = 0; i < NUM_HILOS; i++){
                TareaCompleja10 t = new TareaCompleja10(contadorCompartido);
                Thread hilo = new Thread(t);
                hilo.setName("Hilo-" + i);
                hilo.start();
                hilosAsociados[i] = hilo;
        }

        /* Despues de crear todo, nos aseguramos
         * de esperar que todos los hilos acaben. */

        for (int i = 0; i < NUM_HILOS; i++){
                Thread hilo=hilosAsociados[i];
                try {
                        //Espera a que el hilo acabe
                        hilo.join();
                } catch (InterruptedException e) {
                        System.out.print("Algun hilo acabó ");
                        System.out.println(" antes de tiempo!");
                }
        }
        System.out.println("El principal ha terminado");
        System.out.printf("Total de multiplicaciones realizadas: %d\n", contadorCompartido.getCuenta());
	}

}
