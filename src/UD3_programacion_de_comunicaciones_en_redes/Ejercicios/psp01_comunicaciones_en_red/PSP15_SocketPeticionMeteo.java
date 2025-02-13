package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PSP15_SocketPeticionMeteo {

    public static void main(String[] args) {
        // Servidor y puerto de Open-Meteo API
        String servidor = "api.open-meteo.com";
        int puerto = 80;

        // Ruta de la solicitud (en este caso, para obtener datos de A Estrada)
        String ruta = "/v1/forecast?latitude=42.6891&longitude=-8.4884&current=temperature_2m,precipitation,rain&hourly=temperature_2m&forecast_days=1";

        try (Socket socket = new Socket()) {
            // Conectar al servidor con un timeout de 5 segundos
            InetSocketAddress direccion = new InetSocketAddress(servidor, puerto);
            System.out.println("Conectando a " + servidor + " en el puerto " + puerto);
            socket.connect(direccion, 5000);
            System.out.println("Conexión establecida con " + servidor);

            // Obtener flujos de entrada y salida
            try (OutputStream os = socket.getOutputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                // Construir la solicitud HTTP GET
                String solicitud = "GET " + ruta + " HTTP/1.1\r\n" +
                                   "Host: " + servidor + "\r\n" +
                                   "Connection: close\r\n\r\n";

                // Enviar la solicitud
                os.write(solicitud.getBytes());
                os.flush();
                System.out.println("Solicitud enviada al servidor...");

                // Leer la respuesta del servidor
                System.out.println("Respuesta del servidor:");
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al conectar o comunicarse con el servidor: " + e.getMessage());
        }

        System.out.println("Programa finalizado.");
    }
}
