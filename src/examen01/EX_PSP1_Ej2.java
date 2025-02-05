/*
 * Una tienda online tiene un inventario limitado de un producto en oferta (por ejemplo, 100 unidades).
 * Los clientes acceden simultáneamente a la tienda para intentar comprar el producto.
 * Cada cliente (representado por un hilo) puede comprar entre 1 y 5 unidades del producto, dependiendo de su solicitud.
 * Simular este proceso mediante hilos en Java, cumpliendo las siguientes condiciones:
 *  -El inventario inicial es de 100 unidades.
 *  -Cada cliente debe verificar si hay suficientes productos disponibles antes de realizar la compra.
 *  -Si el inventario no tiene suficientes productos para satisfacer al cliente, este deberá abandonar la tienda sin comprar.
 *  
 * La aplicación debe mostrar:
 *  -Cada intento de compra (si fue exitoso o fallido).
 *  -El inventario restante después de cada compra.
 *  -Un mensaje final indicando cuántos clientes lograron comprar y cuántos no.
 */

package examen01;

import java.util.Random;

class Inventario {
    private int productos;
    private int exitos = 0; // Clientes exitosos
    private int fallos = 0; // Clientes que no pudieron comprar

    public Inventario(int productos) {
        this.productos = productos;
    }

    public synchronized boolean comprar(String cliente, int cantidad) {
        if (productos >= cantidad) {
            productos -= cantidad;
            exitos++;
            System.out.println(cliente + " intentó comprar " + cantidad + " unidades. Compra exitosa. Inventario restante: " + productos);
            return true;
        } else {
            fallos++;
            System.out.println(cliente + " intentó comprar " + cantidad + " unidades. No hay suficiente inventario. Abandona la tienda.");
            return false;
        }
    }

    public synchronized int getProductos() {
        return productos;
    }

    public synchronized int getExitos() {
        return exitos;
    }

    public synchronized int getFallos() {
        return fallos;
    }
}

class Cliente implements Runnable {
    private final String nombre;
    private final Inventario inventario;

    public Cliente(String nombre, Inventario inventario) {
        this.nombre = nombre;
        this.inventario = inventario;
    }

    @Override
    public void run() {
        Random random = new Random();
        int cantidad = random.nextInt(5) + 1; // Cantidad entre 1 y 5
        inventario.comprar(nombre, cantidad);
    }
}

public class EX_PSP1_Ej2 {
    public static void main(String[] args) throws InterruptedException {
        final int INVENTARIO_INICIAL = 50; // Inventario total inicial
        final int NUM_CLIENTES = 20; // Número total de clientes
        
        System.out.println("SIMULACIÓN TIENDA");
        System.out.println("*****************");

        Inventario inventario = new Inventario(INVENTARIO_INICIAL);
        Thread[] clientes = new Thread[NUM_CLIENTES];

        // Crear y arrancar los hilos de los clientes
        for (int i = 0; i < NUM_CLIENTES; i++) {
            clientes[i] = new Thread(new Cliente("Cliente-" + (i + 1), inventario));
            clientes[i].start();
        }

        // Esperar a que todos los clientes terminen
        for (Thread cliente : clientes) {
            cliente.join();
        }

        // Mostrar resumen
        System.out.println("\nFIN DE LA SIMULACIÓN:");
        System.out.println("Clientes que compraron: " + inventario.getExitos());
        System.out.println("Clientes que no pudieron comprar: " + inventario.getFallos());
        System.out.println("Unidades restantes en el inventario: " + inventario.getProductos());
    }
}