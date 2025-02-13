package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PSP16_SocketApache {

    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor Apache
        int puerto = 80; // Puerto HTTP estándar
        //String recurso = "/servidor/trigonometría.php"; 
        String recurso = "/server-status";

        try (Socket socket = new Socket()) {
            // Conectar al servidor Apache en localhost:80
            socket.connect(new InetSocketAddress(host, puerto), 5000);
            System.out.println("Conectado a " + host + ":" + puerto);

            // Flujos de entrada y salida
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Enviar petición HTTP GET al servidor
            String peticion = "GET " + recurso + " HTTP/1.1\r\n" +
                              "Host: " + host + "\r\n" +
                              "Connection: close\r\n\r\n";
            os.write(peticion.getBytes());
            os.flush();

            // Leer y mostrar la respuesta del servidor
            String linea;
            System.out.println("\nRespuesta del servidor:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
}
