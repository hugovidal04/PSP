/*
 * Simulación bancaria
 * Un banco necesita controlar el acceso a cuentas bancarias y para ello desea hacer un programa de prueba en Java que permita lanzar procesos 
 * que ingresen y retiren dinero a la vez y comprobar así si el resultado final es el esperado.
 * 
 * Se parte de una cuenta con 100 euros y se pueden tener procesos que ingresen 100 euros, 50 o 20. 
 * También se pueden tener procesos que retiran 100, 50 o 20 euros euros. Se desean tener los siguientes procesos:
 *  - 40 procesos que ingresan 100
 *  - 20 procesos que ingresan 50
 *  - 60 que ingresen 20.
 *  De la misma manera se desean lo siguientes procesos que retiran cantidades.
 *   - 40 procesos que retiran 100
 *   - 20 procesos que retiran 50
 *   - 60 que retiran 20.
 *   
 *   Se desea comprobar que tras la ejecución la cuenta tiene exactamente 100 euros, que era la cantidad de la que se disponía al principio. 
 */

package UD2_programacion_de_hilos.psp02_hilos;

class Cuenta {

    private int saldo;
    private int saldoInicial;

    public Cuenta(int saldo){
        this.saldoInicial = saldo;
        this.saldo = saldo;
    }

    public synchronized void hacerMovimiento(int cantidad) {
        // Mostrar el movimiento antes de aplicarlo
        System.out.println("Movimiento: " + (cantidad > 0 ? "Ingreso" : "Retiro") + " de " + Math.abs(cantidad) + ". Saldo antes: " + saldo);
        this.saldo = this.saldo + cantidad;
        // Mostrar el saldo después de aplicar el movimiento
        System.out.println("Saldo después del movimiento: " + saldo);
    }

    public boolean esSimulacionCorrecta() {
        return this.saldo == this.saldoInicial;
    }

    public int getSaldo() {
        return this.saldo;
    }
}

class HiloCliente implements Runnable {
    Cuenta cuenta;
    int cantidad;

    public HiloCliente(Cuenta cuenta, int cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        /* Forzamos la maquinaria: repetimos
        la operación muchísimas veces para
        intentar verificar si la simulación es
        correcta */
        for (int i = 0; i < 5; i++) {
            cuenta.hacerMovimiento(cantidad);
        }
    }
}

public class PSP17_CuentaBancaria {
    public static void main(String[] args) throws InterruptedException {
        Cuenta cuenta = new Cuenta(100);

        final int NUM_OPS_CON_100 = 40;
        final int NUM_OPS_CON_50  = 60;
        final int NUM_OPS_CON_20  = 20;
        
        //Mensaje inicial
        System.out.println("SALDO INICIAL: " + cuenta.getSaldo());   
 
        Thread[] hilosIngresan100 = new Thread[NUM_OPS_CON_100];
        Thread[] hilosRetiran100  = new Thread[NUM_OPS_CON_100];
        Thread[] hilosIngresan50  = new Thread[NUM_OPS_CON_50];
        Thread[] hilosRetiran50   = new Thread[NUM_OPS_CON_50];
        Thread[] hilosIngresan20  = new Thread[NUM_OPS_CON_20];
        Thread[] hilosRetiran20   = new Thread[NUM_OPS_CON_20];

        /* Arrancamos todos los hilos*/
        for (int i = 0; i < NUM_OPS_CON_100; i++) {
            HiloCliente ingresa = new HiloCliente(cuenta, 100);
            HiloCliente retira  = new HiloCliente(cuenta, -100);

            hilosIngresan100[i] = new Thread(ingresa);
            hilosRetiran100[i]  = new Thread(retira);

            hilosIngresan100[i].start();
            hilosRetiran100[i].start();
        }

        for (int i = 0; i < NUM_OPS_CON_50; i++) {
            HiloCliente ingresa = new HiloCliente(cuenta, 50);
            HiloCliente retira  = new HiloCliente(cuenta, -50);

            hilosIngresan50[i] = new Thread(ingresa);
            hilosRetiran50[i]  = new Thread(retira);

            hilosIngresan50[i].start();
            hilosRetiran50[i].start();
        }

        for (int i = 0; i < NUM_OPS_CON_20; i++) {
            HiloCliente ingresa = new HiloCliente(cuenta, 20);
            HiloCliente retira  = new HiloCliente(cuenta, -20);

            hilosIngresan20[i] = new Thread(ingresa);
            hilosRetiran20[i]  = new Thread(retira);

            hilosIngresan20[i].start();
            hilosRetiran20[i].start();
        }

        /* En este punto todos los hilos están arrancados,
        ahora toca esperarlos */

        for (int i = 0; i < NUM_OPS_CON_100; i++) {
            hilosIngresan100[i].join();
            hilosRetiran100[i].join();
        }

        for (int i = 0; i < NUM_OPS_CON_50; i++) {
            hilosIngresan50[i].join();
            hilosRetiran50[i].join();
        }

        for (int i = 0; i < NUM_OPS_CON_20; i++) {
            hilosIngresan20[i].join();
            hilosRetiran20[i].join();
        }

        if (cuenta.esSimulacionCorrecta()) {
            System.out.println("La simulación fue correcta");
        } else {
            System.out.println("La simulación falló");
            System.out.println("La cuenta tiene: " + cuenta.getSaldo());
            System.out.println("Revise sus synchronized");
        }
    }
}
