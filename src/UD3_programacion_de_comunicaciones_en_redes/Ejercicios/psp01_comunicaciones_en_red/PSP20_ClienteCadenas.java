package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PSP20_ClienteCadenas {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 12345;

        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            // Pedir al usuario las cadenas y la operación
            System.out.print("Introduce la primera cadena: ");
            String cadena1 = scanner.nextLine();

            System.out.print("Introduce la segunda cadena: ");
            String cadena2 = scanner.nextLine();

            System.out.print("Introduce la operación (Alfa, Mezclar, Mayus): ");
            String operacion = scanner.nextLine();

            // Enviar los datos al servidor en formato "cadena1,cadena2,operacion"
            String mensaje = cadena1 + "," + cadena2 + "," + operacion;
            salida.println(mensaje);

            // Recibir la respuesta del servidor
            String respuesta = entrada.readLine();

            // Mostrar resultado
            System.out.println("Resultado recibido:\n" + respuesta);

        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}
