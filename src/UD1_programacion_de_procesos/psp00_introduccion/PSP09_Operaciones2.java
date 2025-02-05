//Programa que evalúa los valores entre dos variables leídas por teclado y calcula:
//* Los múltiplos de 3
//* Imprime los múltiplos de 7
//* La suma de los impares
//* El total de números procesados

package UD1_programacion_de_procesos.psp00_introduccion;

import java.util.Scanner;

public class PSP09_Operaciones2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int ini, fin, multiplo3=0, sumaImpares=0, cont=0;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("OPERACIONES - 2");
		System.out.println("===============");
		
		//Leemos valores inicial y final
		System.out.print("Introduzca valor inicial: ");
		ini = sc.nextInt();
		System.out.print("Introduzca valor final: ");
		fin = sc.nextInt();
		
		//Procesamos valores
		for (int i = ini; i <= fin; i++) {
			if (i % 3 == 0) multiplo3++;
			if (i % 2 != 0) sumaImpares += i;
			if (i % 7 == 0) System.out.println("El número " + i + " es múltiplo de siete");
			cont++;
		}
		
		//Mostramos resultados
		System.out.println("Múltiplos de tres: " + multiplo3);
		System.out.println("La suma de los impares es: " + sumaImpares);
		System.out.println("Total de números procesados: " + cont);

		sc.close();
	}
}