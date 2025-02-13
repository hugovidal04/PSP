package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;
	
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PSP18_ServidorEcho {

    public static void main(String[] args) {
        final int PUERTO = 12345; // Puerto donde escuchará el servidor

        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor en espera de conexiones en el puerto " + PUERTO);

            while (true) {
                // Aceptar la conexión de un cliente
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

                // Procesar los mensajes del cliente
                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                     PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        if (mensaje.isEmpty()) {
                            System.out.println("El cliente ha terminado la conexión.");
                            break; // Finalizamos el bucle cuando el cliente envía una cadena vacía
                        }

                        // Procesar y responder al mensaje
                        String respuesta = "El cliente " + clienteSocket.getInetAddress() + " ha enviado la cadena: " + mensaje;
                        System.out.println(respuesta);
                        salida.println("Mensaje recibido correctamente por el servidor");
                    }
                }

                // Cerrar conexión con el cliente
                clienteSocket.close();
                System.out.println("Conexión con el cliente cerrada.");
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
