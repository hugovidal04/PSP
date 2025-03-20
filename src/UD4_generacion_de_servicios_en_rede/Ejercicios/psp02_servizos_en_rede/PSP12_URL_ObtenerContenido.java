/*
 *  Programa en Java que conecta a una URL y obtiene su contenido, mostrándolo por consola
 */

package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.net.URL;						//Representa una dirección web y permite abrir conexiones con ella
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.MalformedURLException;		//Se lanza si la URL no tiene un formato válido
import java.io.IOException;
import java.io.InputStream;					//Flujo de datos que se recibe de la URL

public class PSP12_URL_ObtenerContenido {

//  static String urlStr = "https://www.w3c.org";
//  static String urlStr = "https://es.wikipedia.org";
//  static String urlStr = "http://localhost";
//  static String urlStr = "https://www.rae.es";  
//  static String urlStr = "https://www.google.com";
//  static String urlStr = "https://www.oracle.com";
   static String urlStr = "https://www.wikipedia.org";

  public static void main(String[] args) {

    try {
      URL url = new URL(urlStr);	//Intentamos crear un objeto URL con la dirección almacenada en urlStr (si no es válida se lanza MalformedURLException)
      try (InputStream is = url.openConnection().getInputStream();		//Para permitir leer los datos recibidos
              InputStreamReader isr = new InputStreamReader(is);		//Convertimos flujo bytes en un Reader (Flujo de caracteres)
              BufferedReader br = new BufferedReader(isr)) {			//Mejoramos eficiencia al leer línea por línea
        System.out.printf("Contenidos de %s\n", urlStr);
        System.out.println("-----------------------------\n");
        String linea;
        while ((linea = br.readLine()) != null) {
          System.out.println(linea);
        }
        System.out.println("-----------------------------\n");
      } catch (IOException ex) {
        System.out.printf("Error de E/S obteniendo contenidos de URL.\n");
        ex.printStackTrace();
      }
    } catch (MalformedURLException ex) {
      System.out.printf("URL mal formada: %s.\n", urlStr);
      ex.printStackTrace();
    }
  }
}