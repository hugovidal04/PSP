//Programa que lanza un proceso y utiliza el método isAlive() para comprobar si se sigue ejecutando
//El programa comprueba cada 3 segundos si se halla en ejecución, hasta que no lo esté, y entonces debe terminar
//Tras cada comprobación de estado se muestra un mensaje por pantalla

package psp01_procesos;

import java.io.IOException;

public class PSP06_ProcesoIsAlive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] comando = new String[] {"notepad"};
		
		ProcessBuilder pb = new ProcessBuilder(comando);
		//pb.inheritIO();
		
		try {
			Process p = pb.start();

			int i = 0;
			boolean fin = false;
			while(!fin) {
				System.out.printf("Verificación %d: ", i++);
				if(p.isAlive()) {
					System.out.println("El proceso está vivo");
					Thread.sleep(3000);
				} else {
					System.out.println("**** El proceso NO está vivo");
					fin = true;
				}
			}
		} catch (IOException e) {
			System.err.println("Error durante la ejecución del proceso");
			e.printStackTrace();
		} catch (InterruptedException ex) {
			System.err.println("Proceso interrumpido");
		}
		
	}

}
