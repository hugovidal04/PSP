//Juego consistente en acertar un número generado aleatoriamente

package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP04_AcertarNumero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Definimos constante que establece el valor máximo del número aleatorio
		final int MAX = 10000;
		
		//Definimos variables
		boolean encontrado = false;
		int num, intentos = 0;
		
		//Generamos n�mero aleatorio entre 1 y MAX
		int azar = (int) (Math.random() * MAX);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Intenta acertar un número entre 1 y " + MAX);
				
		do {
			System.out.print("Introduzca número: ");
			num = sc.nextInt();
			intentos++;
			if (num == azar) 
				encontrado = true;
			 else if (num < azar) 
				System.out.println("Te has quedado corto");
			 else System.out.println("Te has pasado");
		} while (!encontrado);
		
		System.out.println("Enhorabuena..!!!!!");
		System.out.println("Has encontrado el número secreto tras " + intentos + " intentos");
		
		sc.close();
	}
}