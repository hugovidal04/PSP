//Programa que detecta mediante una función
//si el texto introducido es un palíndromo

package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP16_Palindromo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String txt = "";
		
		System.out.println("Palíndromo");
		System.out.println("==========");
		
		System.out.print("Introduzca Cadena: ");
		txt = sc.nextLine();
		if (esPalindromo(txt))
			System.out.println("La cadena ES un palíndromo");
		else
			System.out.println("La cadena NO ES un palíndromo");
		
		sc.close();
	}
	
	// Función Palíndromo
		private static boolean esPalindromo (String cad) {
			for (int i = 0; i < cad.length() / 2; i++) {
				if (cad.charAt(i) != cad.charAt(cad.length() - 1 - i)) {
					return false;
				}
			}
			return true;
		}

}
