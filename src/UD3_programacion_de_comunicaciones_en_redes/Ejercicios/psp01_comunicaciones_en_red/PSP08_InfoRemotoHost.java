//Programa que solicita nombres de host o sus ip's y muestra información acerca de ellos

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PSP08_InfoRemotoHost {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String entrada;

        System.out.println("Introduce un nombre de dominio o una dirección IP (deja en blanco para salir):");

        while (true) {
            System.out.print("Entrada: ");
            entrada = scanner.nextLine().trim();

            // Salir si la entrada es una cadena vacía
            if (entrada.isEmpty()) {
                System.out.println("Fin del programa.");
                break;
            }

            try {
                // Intentar resolver la entrada a un objeto InetAddress
                InetAddress hostRemoto = InetAddress.getByName(entrada);

                // Mostrar información del host remoto
                System.out.printf("Nombre del host: %s\n", hostRemoto.getHostName());
                System.out.printf("Dirección IP: %s\n", hostRemoto.getHostAddress());
                System.out.printf("Es loopback: %s\n", hostRemoto.isLoopbackAddress() ? "sí" : "no");
                System.out.printf("Es dirección local: %s\n", hostRemoto.isSiteLocalAddress() ? "sí" : "no");
                System.out.printf("Tipo de dirección: %s\n",
                        (hostRemoto.getAddress().length == 4) ? "IPv4" : "IPv6");
                System.out.println("-----------------------------------------");
            } catch (UnknownHostException e) {
                // Manejo del error si no se puede resolver el host
                System.err.printf("No se pudo resolver el nombre del host o la dirección IP: %s\n", entrada);
            }
        }

        scanner.close(); // Cerrar el escáner
    }
}
