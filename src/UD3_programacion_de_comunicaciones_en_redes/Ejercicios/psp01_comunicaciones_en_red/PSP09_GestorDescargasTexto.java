//Descargar contenido de una URL y almacenar el contenido en un archivo local

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PSP09_GestorDescargasTexto {

    public void descargarArchivo(String urlDescargar, String nombreArchivo) {
        System.out.println("Descargando desde: " + urlDescargar);

        try (
            InputStream is = new URL(urlDescargar).openStream();		//Lee bytes desde la URL
            InputStreamReader reader = new InputStreamReader(is);       //Convierte los bytes en caracteres
            BufferedReader bReader = new BufferedReader(reader);		//Permite leer líneas completas de texto
            FileWriter escritorFichero = new FileWriter(nombreArchivo)	//Escribe caracteres en un archivo
        ) {
            String linea;
            while ((linea = bReader.readLine()) != null) {
                escritorFichero.write(linea);
                escritorFichero.write(System.lineSeparator()); // Añade un salto de línea
            }
            System.out.println("Archivo descargado y guardado como: " + nombreArchivo);

        } catch (MalformedURLException e) {
            System.err.println("URL mal escrita: " + urlDescargar);
        } catch (IOException e) {
            System.err.println("Error de lectura/escritura en: " + nombreArchivo);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	PSP09_GestorDescargasTexto gd = new PSP09_GestorDescargasTexto();
  	
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";

        // Ruta donde se guardará el archivo descargado
        String nombreArchivo = "F:\\textoPru.txt";

        // Descargar el archivo
        gd.descargarArchivo(url, nombreArchivo);
    }
}
