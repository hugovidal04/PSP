//Resolución inversa de nombres de servidor
//Dado una IP, obtener nombre del servidor correspondiente

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PSP07_ResolucionInversa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la dirección IP (por ejemplo, 8.8.8.8): ");
        String direccionIP = scanner.nextLine();

        try {
            // Resolver el nombre del host a partir de la dirección IP
            InetAddress host = InetAddress.getByName(direccionIP);

            // Mostrar el nombre del host
            System.out.printf("Dirección IP: %s\nNombre del host: %s\n", direccionIP, host.getHostName());
        } catch (UnknownHostException e) {
            // Manejar errores si no se puede resolver la IP
            System.err.printf("No se pudo resolver el nombre del host para la dirección IP: %s\n", direccionIP);
        }

        scanner.close();
    }
}