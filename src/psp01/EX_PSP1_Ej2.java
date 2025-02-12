package psp01;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


class Tienda implements Runnable {
    private static final int MAX_INVENTARIO = 50; // Inventario inicial de la tienda 
    private static final int MAX_CLIENTES = 20; 
    private static AtomicInteger unidades = new AtomicInteger(MAX_INVENTARIO);
    private static AtomicInteger cliente = new AtomicInteger(MAX_CLIENTES);
    private static int numeroUnidadesCompra;
    private static boolean numeroGenerado = false;

    public synchronized static int getNumeroUnidadesCompra() {
        while (!numeroGenerado) {
            try {
                Tienda.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numeroGenerado = false;
        return numeroUnidadesCompra;
    }
    
    public static boolean actualizarInventario(int cantidad) {
        int inventarioActual = unidades.get();
        if (cantidad < 0 && inventarioActual + cantidad < 0) {
            return false;
        }
        unidades.addAndGet(cantidad);
        return true;
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
        	numeroUnidadesCompra = rand.nextInt(5);
            System.out.println("Cliente: " + cliente.get());
            System.out.println("Número generado: " + numeroUnidadesCompra);
            numeroGenerado = true;
            synchronized (Tienda.class) {
                Tienda.class.notifyAll();
            }

            try {
                Thread.sleep(200); //Tiempo de espera entre apuestas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class main {
	private static final int NUMERO_CLIENTES = 20;


	public static void main(String[] args) {
		// Crear y lanzar el hilo de la banca
        Thread tiendaThread = new Thread(new Tienda(), "Tienda");
        tiendaThread.start();
        
	}
}
