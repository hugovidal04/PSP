package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.apache.commons.net.telnet.TelnetClient;

public class PSP03_TelnetEliza {

  private final static String servidor = "52.43.121.77";
  private final static int puerto = 9000;

  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("ERROR: indicar: nombre");
      return;
    }

    String nombre = args[0];

    String prompt = nombre + "> ";
    String marcadorRespuesta = "Elisa: ";

    TelnetClient telnet = new TelnetClient();

    try {
      telnet.connect(servidor, puerto);

      InputStream isDeElisa = telnet.getInputStream();
      OutputStream osAElisa = telnet.getOutputStream();

      try (InputStreamReader isr = new InputStreamReader(System.in); // No hace falta UTF-8
              BufferedReader br = new BufferedReader(isr);
              InputStreamReader isrDeElisa = new InputStreamReader(isDeElisa);
              BufferedReader brDeElisa = new BufferedReader(isrDeElisa);
              OutputStreamWriter oswAElisa = new OutputStreamWriter(osAElisa);
              BufferedWriter bwAElisa = new BufferedWriter(oswAElisa)) {
        
        String linea;
        do {
          
          String deElisa = brDeElisa.readLine();  ////Lee la respuesta de Eliza
          System.out.printf("%s%s\n", marcadorRespuesta, deElisa);   //Muestra la respuesta en la consola

          System.out.print(prompt);  //Solicita al usuario que escriba un mensaje

          linea = br.readLine();    //Lee la entrada del usuario  
          bwAElisa.write(linea);    //Envía el mensaje a Eliza
          bwAElisa.newLine();
          bwAElisa.flush();

        } while (linea != null && !linea.equals("."));  //Finaliza si el usuario escribe '.'

      }

    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

  }

}