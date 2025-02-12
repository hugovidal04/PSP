//Prueba de lanzamiento de un mismo proceso en distintos directorios

package psp01_procesos;

import java.io.File;
import java.io.IOException;

public class PSP09_Directorio {

	  public static void main(String[] args) {

	    ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
	    pb.inheritIO();
	    
		//Primera ejecución
	    System.out.println(" ### EJECUCIÓN DE PROCESO SIN ESPECIFICAR DIRECTORIO ###");
	    System.out.println("     ***********************************************\n");	    
	    try {
	      pb.start().waitFor();
	    } catch (IOException | InterruptedException ex) {
	      ex.printStackTrace();
	    }
	    System.out.println("---------------");
	    System.err.printf("Directorio [user.dir]: %s\n", System.getProperties().get("user.dir"));
	    System.err.printf("Directorio [pb.directory()]: %s\n", pb.directory());
	    System.err.printf("Ejecución de %s en directorio: %s\n", pb.command(), pb.directory());
	    System.out.println("---------------\n\n");
	        
	    //Segunda ejecución cambiando directorio
	    System.out.println(" ### EJECUCIÓN DE PROCESO ESPECIFICANDO DIRECTORIO ###");
	    System.out.println("     *********************************************\n");
	    pb.directory(new File("C:\\windows"));
	    //System.err.printf("Ejecución de %s en directorio: %s\n", pb.command(), pb.directory());
	    try {
	      pb.start().waitFor();
	    } catch (IOException | InterruptedException ex) {
	      ex.printStackTrace();
	    }
	    System.out.println("---------------");
	    System.err.printf("Directorio [user.dir]: %s\n", System.getProperties().get("user.dir"));
	    System.err.printf("Directorio [pb.directory()]: %s\n", pb.directory());
	    System.err.printf("Ejecución de %s en directorio: %s\n", pb.command(), pb.directory());
	    System.out.println("---------------\n\n");
	  }
}
