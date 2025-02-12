//Ejemplo de código con excepciones no capturadas

package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP24_ExcepcionNoCapturada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Ejemplo de excepción no capturada");
		System.out.println("Introduzca como valor de denominador Intro o 0 para generar excepción");
		
		System.out.println();
		
		System.out.print("Introduzca dividendo: ");
		int dividendo = Integer.parseInt(sc.nextLine());
		
		System.out.print("Introduzca divisor: ");
		int divisor = Integer.parseInt(sc.nextLine());
		
		System.out.println("El resultado de la división es: " + dividendo/divisor);
		System.out.println();
		
		sc.close();
	}

}
