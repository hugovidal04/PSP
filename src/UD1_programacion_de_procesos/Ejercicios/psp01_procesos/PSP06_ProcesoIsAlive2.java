//Programa que lanza un proceso y utiliza el método isAlive() para comprobar si se sigue ejecutando
//El programa comprueba cada 3 segundos si se halla en ejecución, hasta que no lo esté, y entonces debe terminar
//Tras cada comprobación de estado se muestra un mensaje por pantalla y pregunta si deseamos terminar con el proceso de forma manual

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.IOException;
import java.util.Scanner;

public class PSP06_ProcesoIsAlive2 {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad");
        Scanner sc = new Scanner(System.in);
        String op; 
        
        try {
            // Iniciar el proceso
            Process p = pb.start();

            // Bucle que comprueba si el proceso está vivo cada 3 segundos
            while (p.isAlive()) {
                System.out.println("El proceso sigue en ejecución...");
                
                System.out.print("Pulsar 'S' para finalizar el proceso manualmente o INTRO para continuar [PID=" + p.pid() + "]: ");
                op = sc.nextLine().toUpperCase();
                if (op.equals("S")) {
                	p.destroyForcibly();  //Necesitamos forzar el cierre del proceso en aplicaciones gráficas en Windows
                	System.out.println("El proceso ha sido eliminado de forma manual...");
                }
                
                Thread.sleep(3000); // Esperar 3 segundos antes de volver a comprobar
            }

            // Cuando el proceso termina
            System.out.println("El proceso ha finalizado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }       
        sc.close();
    }
}