/*
 * Programa que permite conectarse a un servidor SSH real 
 * y ejecutar múltiples comandos hasta que el usuario escriba exit
 * Se basa en la librería JSch
 */

package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import com.jcraft.jsch.*;

import java.io.InputStream;
//import java.io.PrintWriter;
import java.util.Scanner;

public class PSP05_SSH_JschInteractivo {
    public static void main(String[] args) {
        String host = "test.rebex.net";  // Servidor SSH de prueba
        String usuario = "demo";         // Usuario
        String clave = "password";       // Contraseña
        int puerto = 22;                 // Puerto SSH

        try {
            // Crear sesión SSH
            JSch jsch = new JSch();
            Session sesion = jsch.getSession(usuario, host, puerto);
            sesion.setPassword(clave);
            sesion.setConfig("StrictHostKeyChecking", "no");  //Evita la confirmación de huella digital SSH (no recomendado en producción)

            System.out.println("Conectando al servidor SSH...");
            sesion.connect(); // Establecer conexión
            System.out.println("Conectado correctamente a " + host);

            Scanner scanner = new Scanner(System.in);
            String comando;

            while (true) {
                System.out.print("\nIntroduce un comando SSH ('exit' para salir): ");
                comando = scanner.nextLine();
                
                if ("exit".equalsIgnoreCase(comando)) {
                    break; // Salir del bucle
                }

                ejecutarComando(sesion, comando);
            }

            // Cerrar la conexión
            sesion.disconnect();
            scanner.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejecutarComando(Session session, String comando) {
        try {
            // Abrir un canal para ejecutar comandos
            ChannelExec canal = (ChannelExec) session.openChannel("exec");
            canal.setCommand(comando);

            // Capturar la salida del comando
            canal.setInputStream(null);
            canal.setErrStream(System.err);
            InputStream input = canal.getInputStream();

            canal.connect(); // Ejecutar el comando
            System.out.println("Ejecutando: " + comando);

            // Leer y mostrar la salida del comando
            byte[] tmp = new byte[1024];
            while (true) {
                while (input.available() > 0) {
                    int i = input.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (canal.isClosed()) {
                    break;
                }
                Thread.sleep(500);
            }

            // Cerrar canal
            canal.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
