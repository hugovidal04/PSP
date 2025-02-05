//Obtener el stream asociado a la salida estándar de un determinado proceso
//Leerlo línea a línea y mostrarlo por pantalla

package UD1_programacion_de_procesos.psp01_procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PSP11_lanzaProcesoCapturaSalida {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] comando = new String[] {"cmd", "/c", "dir", "C:\\"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			Process p = pb.start();   //Iniciar el proceso
			System.err.println("PROCESO INICIADO.........");			
			
			try (InputStream is = p.getInputStream();
				 InputStreamReader isr = new InputStreamReader(is);
				 BufferedReader br = new BufferedReader(isr)) {
				
				int codRet = p.waitFor(); //Esperamos a que el proceso termine y guardamos su código de retorno
				System.out.println("La ejecución de [" + Arrays.toString(comando)
						+ "] devuelve " + codRet
						+ " " + (codRet == 0 ? "(ejecución correcta)" : "(ERROR)") 
				);
				System.out.println("Salida del proceso");	
				System.out.println("------------------");
				String linea = null;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
				System.out.println("------------------");
			}
		}
		catch (IOException e) {
			System.err.println("Error durante la ejecución del proceso");
			System.err.println("Información detallada");
			System.err.println("---------------------");
			e.printStackTrace();
			System.err.println("---------------------");
			System.exit(2);
		}
		catch (InterruptedException e){
			System.err.println("Proceso interrumpido");
			System.exit(3);
		}

	}

}
