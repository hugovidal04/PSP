//descarga de archicos binarios desde una URL
//Se establece la conexión HTTP con la clase HttpURLConnection

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PSP10_GestorDescargasBinario {

    public void descargarArchivo(String urlDescargar, String nombreArchivo) {
        System.out.println("Descargando desde: " + urlDescargar);

        try {
            // Crear conexión
            URL url = new URL(urlDescargar);  //Instanciamos objeto de la clase URL
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();  //Abre conexión con la url especificada
            conexion.setRequestMethod("GET");  //Especifica método GET (para solicitar datos del servidor)

            // Conectar y verificar el código de respuesta
            conexion.connect();
            int codigoRespuesta = conexion.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {						//Código 200 significa OK
                try (InputStream is = conexion.getInputStream(); 					//Creamos flujo de entrada con los datos enviados por el servidor
                     FileOutputStream fos = new FileOutputStream(nombreArchivo)) { 	//Flujo de salida para escribir en archivo local

                    byte[] buffer = new byte[1024];
                    int bytesLeidos;

                    // Leer y escribir en bloques de 1 KB
                    while ((bytesLeidos = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesLeidos);
                    }

                    System.out.println("Archivo descargado y guardado como: " + nombreArchivo);
                    System.out.println("**********************************");
                }
            } else {
                System.err.println("Error al descargar el archivo. Código de respuesta: " + codigoRespuesta);
            }

        } catch (IOException e) {
            System.err.println("Error durante la descarga: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	PSP10_GestorDescargasBinario gd = new PSP10_GestorDescargasBinario();
    	
    	
    	// Lista de URLs para descargar
        String[] urls = {
            "https://www.w3.org/TR/PNG/iso_8859-1.txt",
            "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
            "https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png"
        };

        // Carpeta de destino
        String carpetaDestino = "F:\\descargas\\";

        // Descargar cada archivo desde la lista de URLs
        for (String url : urls) {
            // Generar un nombre de archivo basado en la URL
            String nombreArchivo = carpetaDestino + url.substring(url.lastIndexOf("/") + 1);

            // Descargar el archivo
            gd.descargarArchivo(url, nombreArchivo);
        }

        System.out.println("**********************************");
        System.out.println("Todas las descargas han finalizado.");
    }
}
