//Exemplo simple de declaración, escritura e lectura dun array

package psp00_introduccion;

import java.util.Scanner;

public class PSP19_ArraySimple {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int MAX = 4; 
		int cantidades[];
		Scanner sc = new Scanner(System.in);
		cantidades = new int[MAX];
		
		System.out.println("Leemos un Array de enteros");
		System.out.println("**************************");
		
		for (int i = 0; i < cantidades.length; i++) {
			System.out.print("Introduce el valor de la posición " + i + ": ");
	        cantidades[i] = sc.nextInt();
		}
		
		System.out.println(""); System.out.println("");
		
		System.out.println("Escribimos un Array de enteros");
		System.out.println("*****************************");
		
		for (int i = 0; i < cantidades.length; i++) {
			System.out.println("El valor de la posición " + i + " es: " + cantidades[i]);
		}
		
		sc.close();

	}

}
