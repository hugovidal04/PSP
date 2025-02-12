//Programa que crea un proceso según los parámetros indicados por consola

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.IOException;
import java.util.Scanner;

public class PSP03_Runtime2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String apli = "";
		Scanner sc = new Scanner (System.in);
		
		System.out.print("Introduzca aplicación del sistema a ejecutar: ");
		apli = sc.nextLine();

		try {
			Runtime.getRuntime().exec(apli);			
		} catch (IOException ex) {
			System.err.println("ERROR de E/S al ejecutar el comando");
			ex.printStackTrace();
		}

		sc.close();
	}

}
