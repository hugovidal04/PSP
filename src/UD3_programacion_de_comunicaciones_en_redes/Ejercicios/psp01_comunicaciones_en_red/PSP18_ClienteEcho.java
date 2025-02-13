package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PSP18_ClienteEcho {

    public static void main(String[] args) {
        final String HOST = "localhost"; // Dirección del servidor
        final int PUERTO = 12345; // Puerto donde escucha el servidor

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            String mensaje;
            do {
                System.out.print("Introduce un mensaje (cadena vacía para salir): ");
                mensaje = teclado.readLine();

                if (mensaje.isEmpty()) {
                    System.out.println("Saliendo del cliente...");
                    break;
                }

                // Enviar el mensaje al servidor
                salida.println(mensaje);

                // Leer la respuesta del servidor
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);

            } while (!mensaje.isEmpty());

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
