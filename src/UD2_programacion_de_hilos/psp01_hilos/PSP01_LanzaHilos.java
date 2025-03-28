package UD2_programacion_de_hilos.psp01_hilos;

class Hilo implements Runnable {
	private final String nombre;
	Hilo(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
		System.out.printf("Hilo %s terminado.\n", this.nombre);
	}
}

public class PSP01_LanzaHilos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread h1 = new Thread(new Hilo("H1"));
		Thread h2 = new Thread(new Hilo("H2"));
		
		h1.start();
		h2.start();
		
		System.out.println("Hilo principal terminado");
	}

}
