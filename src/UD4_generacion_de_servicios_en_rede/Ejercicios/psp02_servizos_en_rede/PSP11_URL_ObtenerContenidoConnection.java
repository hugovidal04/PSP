/*
 * programa en Java que permite introducir una URL por consola y obtiene información sobre la URL y la conexión HTTP, incluyendo:
 *   - Codificación
 *   - Longitud del contenido
 *   - Tipo MIME
 *   - Fecha de última modificación
 *   - Cabeceras HTTP.
 */

package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.IOException;			//Maneja posibles errores de entrada/salida al conectar con la URL
import java.net.URL;				//Representa una dirección web y permite abrir conexiones con ella
import java.net.URLConnection;		//Permite establecer una conexión con la URL y obtener información de la misma
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PSP11_URL_ObtenerContenidoConnection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Leer la URL por consola
        System.out.print("Introduce una URL (ejemplo: https://www.google.com): ");
        String urlStr = scanner.nextLine().trim();

        try {
            // Crear el objeto URL y abrir la conexión
            URL url = new URL(urlStr);						//Creamos objeto URL
            
            // Mostrar información de la URL
            System.out.println("\nInformación sobre la URL:");
            System.out.println("-------------------------------------------------");
            System.out.println("Protocolo: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Puerto: " + (url.getPort() == -1 ? "Predeterminado" : url.getPort()));
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + (url.getQuery() != null ? url.getQuery() : "Sin parámetros"));
            System.out.println("-------------------------------------------------\n");
            
            
            URLConnection urlConn = url.openConnection();	//Establece conexión con la URL (devuelve objeto URLConnection)
            urlConn.connect();								//Inicia conexión con servidor web
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Imprimir la información de la conexión
            System.out.printf("\nInformación de %s\n", urlStr);
            System.out.printf("-----------------------------\n");
            System.out.printf("Codificación: %s\n", urlConn.getContentEncoding());				//Devuelve codificación del contenido
            System.out.printf("Longitud (int): %d\n", urlConn.getContentLength());				//Longitud en bytes
            System.out.printf("Longitud (long): %d\n", urlConn.getContentLengthLong());			//Similar al anterior pero para archivos grandes
            System.out.printf("Tipo (MIME): %s\n", urlConn.getContentType());					//Obtiene el tipo MIME
            System.out.printf("Fecha (EPOCH): %d\n", urlConn.getDate());						//Fecha de respuesta del servidor en formato EPOCH (milisegundos desde 1970)
            System.out.printf("Fecha: %s\n", sdf.format(new Date(urlConn.getDate())));			//Lo mismo en formato más legible
            System.out.printf("Expira (EPOCH): %d\n", urlConn.getExpiration());					//Expiración del contenido de la caché
            System.out.printf("Expira: %s\n", sdf.format(new Date(urlConn.getExpiration())));	//Lo mismo en formato más legible

            // Obtener y mostrar todas las cabeceras HTTP
            System.out.printf("\nTodas las cabeceras para %s\n", urlStr);
            System.out.printf("-----------------------------\n");
            Map<String, List<String>> cabeceras = urlConn.getHeaderFields();				//Obtiene todas las cabeceras HTTP como un Map<String, List<String>>
            for (Map.Entry<String, List<String>> entry : cabeceras.entrySet()) {			//Recorremos map para obtener clave-valor
                System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error al conectar con la URL: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
