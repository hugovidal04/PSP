//Conexión simple usando telnet a un servidor que muestra imágenes en modo texto

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp02_servizos_en_rede;

import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class PSP01_TelnetBasico {
    
    public static void main(String[] args) {
        String servidor = "towel.blinkenlights.nl"; // Servidor Telnet (Star Wars en ASCII)
        int puerto = 23; // Puerto estándar de Telnet

        TelnetClient telnet = new TelnetClient();

        try {
            // Conectar al servidor Telnet
            System.out.println("Conectando a " + servidor + " en el puerto " + puerto + "...");
            telnet.connect(servidor, puerto);
            System.out.println("Conectado correctamente.\n");

            // Obtener flujos de entrada y salida
            final InputStream entrada = telnet.getInputStream();
            OutputStream salida = telnet.getOutputStream();
            PrintWriter writer = new PrintWriter(salida, true);

            // Hilo para leer la respuesta del servidor
            //   *Ejecuta la lectura del servidor en paralelo, sin bloquear la entrada del usuario.
            //   *Permite que el usuario escriba comandos mientras recibe datos del servidor.
            //   *Muestra en tiempo real la respuesta del servidor
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
            
            
         // Leer datos del servidor de forma secuencial (sin Thread)
         // Si lo hacemos de este modo no podemos interacturar con el servidor Telnet mientras se recibe la respuesta
//            int dato;
//            while ((dato = entrada.read()) != -1) {
//                System.out.print((char) dato);
//            }
//            System.out.println("\nIngrese un comando Telnet: ");
            
            
            

            // Leer comandos del usuario y enviarlos al servidor
            Scanner scanner = new Scanner(System.in);
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

            // Cerrar la conexión
            telnet.disconnect();
            scanner.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}