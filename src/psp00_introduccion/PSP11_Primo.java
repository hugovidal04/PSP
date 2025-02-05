//Programa que determina si un valor leído por teclado es un número primo

package psp00_introduccion;

import java.util.Scanner;

public class PSP11_Primo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num, cont=2;
		boolean esPrimo=true;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("PRIMOS");
		System.out.println("======");
		
		//Leemos número a evaluar
		System.out.print("Introduzca número: ");
		num = sc.nextInt();
				
		while (esPrimo && cont < num) {
			if (num % cont == 0) {
				esPrimo = false;
				break;}
			cont++;
		}
		
		if (esPrimo) System.out.println("El número " + num + " es primo");
		else System.out.println("El número " + num + " NO es primo");
		
		sc.close();
	}

}