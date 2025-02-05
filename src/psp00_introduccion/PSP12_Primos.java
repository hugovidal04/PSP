//Programa que lee por teclado un número natural
//y calcula los números primos existentes hasta dicho número

package psp00_introduccion;

import java.util.Scanner;

public class PSP12_Primos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int max;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("SERIE DE NÚMEROS PRIMOS");
		System.out.println("=======================");
		
		//Leemos número a evaluar
		System.out.print("Introduzca número máximo a evaluar: ");
		max = sc.nextInt();
		
		for (int i = 2; i <= max; i++) {
			if (esPrimo(i)) System.out.println(i);
		}
		
		sc.close();

	}
	
	
	//Declaramos la función esPrimo para evaluar si un número es primo o no
	private static boolean esPrimo(int n) {
		int cont=2;
		boolean aux=true;
				
		while (aux && cont < n) {
			if (n % cont == 0) {
				aux = false;
				break;}
			cont++;
		}
		return aux;
	}

}
