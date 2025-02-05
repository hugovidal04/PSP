//Obtener el stream asociado a la salida estándar de un determinado proceso 
//y mostrarla por pantalla numerando cada una de las líneas obtenidas

package UD1_programacion_de_procesos.psp01_procesos;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class PSP12_capturaSalidaProceso {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    String[] comando = new String[] {"cmd", "/c", "dir", "C:\\"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
	    try {
	      Process p = pb.start();
	      // La siguiente es una alternativa a ProcessBuilder
	      // Process p = Runtime.getRuntime().exec(new String[] {"dir", "/Q", "/S", "/A",  nomDir});
	      p.waitFor(); //Esperamos a que el proceso termine
	      try (InputStream is = p.getInputStream();
	              InputStreamReader isr = new InputStreamReader(is);
	              BufferedReader br = new BufferedReader(isr)) {

	        int numLin = 1;
	        String linea = null;
	        while ((linea = br.readLine()) != null) {
	          System.out.printf("%d: %s\n", numLin++, linea);
	        }
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (InterruptedException ex) {
	    }
	}
}
