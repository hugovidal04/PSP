//Conexión con un servidor mediante socket y petición de GET

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class PSP12_SocketSimple2 {

    public static void main(String[] args) {
        // Definimos el nombre del host y el puerto de destino
        String destino = "www.example.com";
        int puertoDestino = 80;

        // Creamos el socket y la dirección de destino
        try (Socket socket = new Socket()) {
            InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

            // Establecemos un tiempo de espera de 5000 ms para la conexión
            System.out.println("Intentando conectar a " + destino + ":" + puertoDestino);
            socket.connect(direccion, 5000); // Intentamos conectarnos al servidor

            System.out.println("Conexión establecida con " + destino + ":" + puertoDestino);

            // Obtenemos los flujos de entrada y salida del socket
            try (InputStream is = socket.getInputStream();								//Flujo de entrada para leer los datos que el servidor envía al cliente
                 OutputStream os = socket.getOutputStream();							//Flujo de salida para enviar datos desde el cliente al servidor
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {	//Leer datos del flujo de entrada (InputStream) línea por línea

                // Enviamos una solicitud HTTP simple (GET request)
                String solicitud = "GET / HTTP/1.1\r\nHost: " + destino + "\r\n\r\n";	//Solicitamos página raíz (/)
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

        } catch (SocketTimeoutException e) {
        	System.err.println("Superado tiempo de espera para establecer conexión: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }

        System.out.println("Programa finalizado.");
    }
}
