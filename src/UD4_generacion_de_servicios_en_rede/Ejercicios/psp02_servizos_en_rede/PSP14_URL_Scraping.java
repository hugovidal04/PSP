package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class PSP14_URL_Scraping {
    public static void main(String[] args) {
        try {
            // Define la URL que quieres hacer scraping
            String url = "https://www.microsiervos.com"; // Reemplaza por la URL que quieras scrapear
            URL website = new URL(url);

            // Conexión a la página web usando Jsoup
            Document document = Jsoup.connect(website.toString()).get();  //Obtenemos el HTML de la página

            // Selecciona los elementos que quieres extraer. En este caso, todos los enlaces <a>
            Elements links = document.select("a");	//Seleccionamos elemento del HTML

            // Imprime cada enlace encontrado
            for (Element link : links) {
                System.out.println("Texto del enlace: " + link.text());
                System.out.println("URL del enlace: " + link.attr("href"));
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Hubo un error al intentar conectarse a la URL: " + e.getMessage());
        }
    }
}
