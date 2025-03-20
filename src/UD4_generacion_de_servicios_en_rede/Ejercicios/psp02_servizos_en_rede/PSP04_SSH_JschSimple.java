/* 
 * Programa que conecta por SSH a servidor público y ejecuta un comando de sistema (ls -l)
 * Se basa en la librería JSch
 */

package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import com.jcraft.jsch.*;

import java.io.InputStream;

public class PSP04_SSH_JschSimple {
    public static void main(String[] args) {
        String host = "test.rebex.net";  // Servidor SSH de prueba
        String usuario = "demo";         // Usuario
        String clave = "password";       // Contraseña
        int puerto = 22;                 // Puerto SSH

        try {
            // Crear sesión SSH
            JSch jsch = new JSch();
            Session session = jsch.getSession(usuario, host, puerto);
            session.setPassword(clave);
            session.setConfig("StrictHostKeyChecking", "no"); //Conexión sin verificar la clave del host (evita advertencia de autenticidad)

            System.out.println("Conectando al servidor SSH...");
            session.connect(); // Establecer conexión
            System.out.println("Conectado correctamente a " + host);

            // Abrir un canal para ejecutar comandos
            ChannelExec canal = (ChannelExec) session.openChannel("exec");
            canal.setCommand("ls -l"); // Comando a ejecutar en el servidor

            // Capturar la salida del comando
            canal.setInputStream(null);  //No se enviará entrada estándar al comando ejecutado
            canal.setErrStream(System.err);
            InputStream input = canal.getInputStream();

            canal.connect(); // Ejecutar el comando
            System.out.println("Ejecutando comando en el servidor...");

            // Leer y mostrar la salida del comando
            byte[] tmp = new byte[1024];
            while (true) {
                while (input.available() > 0) {  //Comprueba si hay datos disponibles en el flujo de entrada
                    int i = input.read(tmp, 0, 1024);  //Lee hasta 1024 bytes de la salida del comando
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i)); //Convierte los bytes en una cadena y la muestra en la consola
                }
                if (canal.isClosed()) {
                    break;
                }
                Thread.sleep(500);
            }

            // Cerrar conexiones
            canal.disconnect();   //Cierra el canal de ejecución de comandos
            session.disconnect(); //Cierra la conexión SSH
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
