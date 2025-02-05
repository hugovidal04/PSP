//Resolución de nombres de servidor
//Dado un nombre de servidor, obtener la dirección IP correspondiente

package UD3_programacion_de_comunicaciones_en_redes.psp03_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PSP06_ResolucionDirecta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del servidor (por ejemplo, google.com): ");
        String nombreServidor = scanner.nextLine();

        try {
            // Resolver el nombre del servidor a una dirección IP
            InetAddress direccion = InetAddress.getByName(nombreServidor);

            // Mostrar la dirección IP
            System.out.printf("Servidor: %s\nDirección IP: %s\n", nombreServidor, direccion.getHostAddress());
        } catch (UnknownHostException e) {
            // Manejar errores si no se puede resolver el nombre
            System.err.printf("No se pudo resolver el nombre del servidor: %s\n", nombreServidor);
        }

        scanner.close();
    }
}
