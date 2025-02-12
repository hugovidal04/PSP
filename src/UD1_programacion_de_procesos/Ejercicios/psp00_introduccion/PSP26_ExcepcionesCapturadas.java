package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP26_ExcepcionesCapturadas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Ejemplo de excepciones específicas capturadas");
			System.out.println("Introduzca como valor de denominador Intro o 0 para generar excepción");
			
			System.out.println();
			
			System.out.print("Introduzca dividendo: ");
			int dividendo = Integer.parseInt(sc.nextLine());
			
			System.out.print("Introduzca divisor: ");
			int divisor = Integer.parseInt(sc.nextLine());
			
			System.out.println("El resultado de la división es: " + dividendo/divisor);
			System.out.println();
		} catch (java.lang.ArithmeticException ex) {
			System.out.println("************ Se ha producido un ERROR aritmético (división por 0)!!!!!!");
			//System.out.println("Error: " + ex.getMessage());
		} catch (java.lang.NumberFormatException ex) {
			System.out.println("************ Se ha producido un ERROR de formato (valor null)!!!!!!");
			//System.out.println("Error: " + ex.getMessage());
		}

		sc.close();
	}
}
