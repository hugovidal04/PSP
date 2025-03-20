package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PSP02_TelnetInteractivo {

    public static void main(String[] args) {
        String servidor = "telehack.com"; // Servidor Telnet interactivo
        int puerto = 23; // Puerto estándar Telnet
        
        TelnetClient telnet = new TelnetClient();

        try {
            // Conectar al servidor Telnet
            System.out.println("Conectando a " + servidor + " en el puerto " + puerto + "...");
            telnet.connect(servidor, puerto);
            System.out.println("Conectado correctamente.\n");

            // Obtener flujos de entrada/salida
            try (InputStream entrada = telnet.getInputStream();
                 OutputStream salida = telnet.getOutputStream();
                 PrintWriter writer = new PrintWriter(salida, true);
                 Scanner scanner = new Scanner(System.in)) {

                // Hilo para leer la respuesta del servidor
                Thread lector = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int dato;
                            while ((dato = entrada.read()) != -1) {
                                System.out.print((char) dato);
                            }
                        } catch (Exception e) {
                            System.out.println("Conexión cerrada.");
                        }
                    }
                });

                lector.start(); // Iniciar el hilo de lectura

                // Leer comandos del usuario y enviarlos al servidor
                String comando;
                while (true) {
                    System.out.print("\nIngrese un comando Telnet (o 'exit' para salir): ");
                    comando = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(comando)) {
                        break; // Salir del bucle
                    }
                    writer.println(comando); // Enviar comando
                    writer.flush();
                }

                System.out.println("Cerrando conexión...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
