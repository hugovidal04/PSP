package psp00_introduccion;

import java.util.Scanner;

public class PSP25_ExcepcionCapturada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Ejemplo de excepción capturada de forma genérica");
			System.out.println("Introduzca como valor de denominador Intro o 0 para generar excepción");
			
			System.out.println();
			
			System.out.print("Introduzca dividendo: ");
			int dividendo = Integer.parseInt(sc.nextLine());
			
			System.out.print("Introduzca divisor: ");
			int divisor = Integer.parseInt(sc.nextLine());
			
			System.out.println("El resultado de la división es: " + dividendo/divisor);
			System.out.println();
		} catch (java.lang.Throwable ex) {
			System.out.println("************ Se ha producido un ERROR de algún tipo!!!!!!");
		}
		sc.close();
	}

}
