/*
 * Programa en Java que conecta a una URL y obtiene su contenido, guardándolo en el archivo 'pagina.html'
 */
package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

//import java.io.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;					//Flujo de datos que se recibe de la URL
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Scanner;

public class PSP13_URL_ObtenerContenidoArchivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer la URL por consola
        System.out.print("Introduce una URL (ejemplo: https://www.wikipedia.org): ");
        String urlStr = scanner.nextLine().trim();
        scanner.close(); // Cerramos el scanner para liberar recursos

        try {
            URL url = new URL(urlStr);

            // Abrir la conexión y flujo de entrada
            try (InputStream is = url.openConnection().getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr);
                 BufferedWriter writer = new BufferedWriter(new FileWriter("pagina.html"))) {

                System.out.printf("Contenido de %s\n", urlStr);
                System.out.println("-----------------------------\n");

                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);  // Mostrar en consola
                    writer.write(linea);        // Guardar en archivo
                    writer.newLine();
                }

                System.out.println("\n-----------------------------");
                System.out.println("El contenido se ha guardado en 'pagina.html'.");

            } catch (IOException ex) {
                System.err.println("Error de E/S obteniendo contenido de la URL.");
                ex.printStackTrace();
            }
        } catch (MalformedURLException ex) {
            System.err.println("URL mal formada: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
