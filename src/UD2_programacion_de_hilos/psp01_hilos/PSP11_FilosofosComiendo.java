/*
 * En una mesa hay procesos que simulan el comportamiento de unos filósofos que intentan comer de un plato.
 * Cada filósofo tiene un cubierto a su izquierda y uno a su derecha y para poder comer tiene que conseguir los dos.
 * Si lo consigue, mostrará un mensaje en pantalla que indique «Filosofo 2 comiendo».
 * Despues de comer, soltará los cubiertos y esperará al azar un tiempo entre 1000 y 5000 milisegundos,
 * indicando por pantalla «El filósofo 2 está pensando».
 * En general todos los objetos de la clase Filósofo está en un bucle infinito dedicándose a comer y a pensar.
 * Simular este problema en un programa Java que muestre el progreso de todos sin caer en problemas de sincronización ni de inanición.
 */

package UD2_programacion_de_hilos.psp01_hilos;

import java.util.Random;

class GestorPalillos {
    boolean palilloLibre[];
    
    public GestorPalillos(int numPalillos){
            palilloLibre = new  boolean[numPalillos];
            for (int i=0; i < numPalillos; i++){
                    palilloLibre[i] = true;
            } //Fin del for
    } //Fin del constructor
    
    public synchronized boolean intentarCogerPalillos(int pos1, int pos2) {
            boolean seConsigue=false;
            if ((palilloLibre[pos1]) && (palilloLibre[pos2]) ) {
                    palilloLibre[pos1]=false;
                    palilloLibre[pos2]=false;
                    seConsigue=true;
            } //Fin del if
            return seConsigue;
    }

    public void liberarPalillos(int pos1, int pos2){
            palilloLibre[pos1]=true;
            palilloLibre[pos2]=true;
    }
}

class Filosofo implements Runnable{
    GestorPalillos gestorPalillos;
    int posPalilloIzq, posPalilloDer;
    
    public Filosofo(GestorPalillos g, int pIzq, int pDer){
            this.gestorPalillos = g;
            this.posPalilloDer = pDer;
            this.posPalilloIzq = pIzq;
    }
    
    public void run() {
            while (true){
                    boolean palillosCogidos;
                    palillosCogidos = this.gestorPalillos.intentarCogerPalillos(posPalilloIzq, posPalilloDer);
                    if (palillosCogidos){
                            comer();
                            this.gestorPalillos.liberarPalillos(posPalilloIzq, posPalilloDer);
                            dormir();
                    } //Fin del if
            } //Fin del while true
    } //Fin del run()

    private void esperarTiempoAzar() {
    	final int MAX_TIEMPO = 5000;
            Random generador=new Random();
            int msAzar=generador.nextInt(MAX_TIEMPO);
            try {
                    Thread.sleep(msAzar);
            } catch (InterruptedException ex) {
                    System.out.println("Falló la espera");
            }
    }
    
    private void comer() {
            System.out.println("Filosofo " + Thread.currentThread().getName() + " comiendo");
            esperarTiempoAzar();
    }
      
    private void dormir(){
            System.out.println("Filosofo " + Thread.currentThread().getName() + " durmiendo (zzzzzz)");
            esperarTiempoAzar();
    }
}

public class PSP11_FilosofosComiendo {

	public static void main(String[] args) {
		int NUM_PROCESOS = 5;
        Filosofo filosofos[] = new Filosofo[NUM_PROCESOS];
        GestorPalillos gestorPalillos;
        gestorPalillos = new GestorPalillos(NUM_PROCESOS);
        Thread hilos[] = new Thread[NUM_PROCESOS];
        
        for (int i = 1; i < NUM_PROCESOS; i++){
                filosofos[i] = new Filosofo(gestorPalillos, i, i-1);
                hilos[i] = new Thread(filosofos[i]);
                hilos[i].start();
        }
        filosofos[0] = new Filosofo(gestorPalillos, 0, NUM_PROCESOS - 1);
        hilos[0] = new Thread(filosofos[0]);
        hilos[0].start();
	}
}
