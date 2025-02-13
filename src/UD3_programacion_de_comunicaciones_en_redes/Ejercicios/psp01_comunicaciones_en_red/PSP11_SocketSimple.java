//Creación de socket básico

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.IOException;         // Manejo de excepciones de entrada/salida
import java.io.InputStream;         // Para leer datos del socket
import java.io.OutputStream;        // Para escribir datos en el socket
import java.net.InetSocketAddress;  // Dirección IP y puerto
import java.net.Socket;             // Socket para la conexión

public class PSP11_SocketSimple {
 public static void main(String[] args) {
     // Definimos el nombre del host al que queremos conectarnos
     String destino = "www.google.com";

     // Puerto de destino (80 es el puerto estándar para HTTP)
     int puertoDestino = 80;

     // Creamos un socket, que será el punto de conexión con el servidor
     Socket socket = new Socket();

     // Definimos la dirección de destino como un objeto InetSocketAddress
     // Contiene el nombre del host y el puerto
     InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

     try {
         // Intentamos establecer la conexión con el servidor
         socket.connect(direccion);

         // Si llegamos aquí, la conexión se ha establecido correctamente
         System.out.println("Conexión establecida con " + destino + ":" + puertoDestino);

         // Obtenemos los flujos de entrada y salida del socket
         InputStream is = socket.getInputStream(); // Flujo para leer datos del servidor
         OutputStream os = socket.getOutputStream(); // Flujo para enviar datos al servidor

         // Operaciones de petición/envío de información al servidor

     } catch (IOException e) {
         System.out.println(
             "No se pudo establecer la conexión o hubo un fallo al leer datos."
         );
         e.printStackTrace();
     }
 }
}
