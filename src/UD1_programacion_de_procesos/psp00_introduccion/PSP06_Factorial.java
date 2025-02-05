//Cálculo del factorial de un número

package UD1_programacion_de_procesos.psp00_introduccion;

import java.util.Scanner;

public class PSP06_Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		long factorial = 1;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("CALCULO DE FACTORIAL");
		System.out.println("====================");
		
		System.out.print("Introduzca un número: ");
		num = sc.nextInt();
		
		for (int i = 2; i <= num; i++) {
			factorial *= i;
		}

		System.out.println("El factorial de " + num + " es: " + factorial);
		
		sc.close();
		
	}

}