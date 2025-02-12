//Operaciones simples con array

package psp00_introduccion;

import java.util.Scanner;

public class PSP21_ValoresArray2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int MAX = 20;
		final int ALEATORIO_MAX = 100;
		final int ALEATORIO_MIN = 1000;
		final int INTER_MIN = 150;
		final int INTER_MAX = 400;
		
		int vector[];
		vector = new int[MAX];
		Scanner sc = new Scanner(System.in);
		int min, max;
		double acum;
		int v_inter = 0;

		//Inicializamos ARRAY con valores		
		System.out.println("Introducimos valores aleatorios en un ARRAY de enteros");
		System.out.println("******************************************************");
		
		for (int i = 0; i < vector.length; i++) {
			//System.out.print("Introduce el valor de la posición " + i + ": ");
			//vector[i] = sc.nextInt();
			//vector[i] = (int)Math.floor(Math.random()* ALEATORIO +1);
			vector[i] = (int) Math.floor(Math.random() * (ALEATORIO_MAX - ALEATORIO_MIN + 1) + ALEATORIO_MIN);
			
		}

		System.out.println("");
		System.out.println("");
		
		//Mostramos por pantalla los valores del ARRAY
		System.out.println("Valores del ARRAY de enteros");
		System.out.println("****************************");
		
		for (int i = 0; i < vector.length; i++) {
			System.out.println("O valor de la posición " + i + " es: " + vector[i]);
		}
		
		//Procesamos información del ARRAY
		min = vector[0];
		max = vector[0];
		acum = 0;
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] < min) min = vector[i];
			if (vector[i] > max) max = vector[i];
			acum += vector[i];
			if (vector[i] >= INTER_MIN && vector[i] <= INTER_MAX) v_inter++;
		}
		
		//Mostramos resultados por pantalla
		System.out.println();
		System.out.println();
		System.out.println("Introducimos valores aleatorios en un  ARRAY de enteros");
		System.out.println("*******************************************************");
		System.out.println("Valor máximo: " + max);
		System.out.println("Valor mínimo: " + min);
		System.out.println("Media: " + acum/MAX);
		System.out.println("Valores entre " + INTER_MIN + " - " + INTER_MAX + ": " + v_inter);
		
		sc.close();		
	}

}
