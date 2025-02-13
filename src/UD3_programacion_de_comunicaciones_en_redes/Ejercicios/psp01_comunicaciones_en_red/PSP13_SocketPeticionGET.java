package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PSP13_SocketPeticionGET {

    public static void main(String[] args) {
        // Definimos el host y el puerto de destino
        String destino = "jsonplaceholder.typicode.com";
        int puertoDestino = 80;

        // Ruta del recurso que queremos obtener
        String ruta = "/users";

        // Creamos el socket y la dirección de destino
        try (Socket socket = new Socket()) {
            InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

            // Establecemos un tiempo de espera de 5000 ms para la conexión
            System.out.println("Intentando conectar a " + destino + ":" + puertoDestino);
            socket.connect(direccion, 5000); // Intentamos conectarnos al servidor

            System.out.println("Conexión establecida con " + destino + ":" + puertoDestino);

            // Obtenemos los flujos de entrada y salida del socket
            try (InputStream is = socket.getInputStream();
                 OutputStream os = socket.getOutputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                // Enviamos una solicitud HTTP simple (GET request)
                String solicitud = "GET " + ruta + " HTTP/1.1\r\n" +
                                   "Host: " + destino + "\r\n" +
                                   "Connection: close\r\n\r\n";
                os.write(solicitud.getBytes());
                os.flush();
                System.out.println("Solicitud enviada al servidor...");

                // Leemos la respuesta del servidor y la mostramos por consola
                System.out.println("Respuesta del servidor:");
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }

            } catch (IOException e) {
                System.err.println("Error al manejar los flujos de datos: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }

        System.out.println("Programa finalizado.");
    }
}


