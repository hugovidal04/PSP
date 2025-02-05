//Operacións simples con array

package psp00_introduccion;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PSP20_ValoresArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int MAX = 4; 
		int [] cantidades;
		Scanner sc = new Scanner(System.in);
		cantidades = new int[MAX];
		int vmax;
		int vmin;
		int acum = 0;
		float media;
		DecimalFormat formato = new DecimalFormat("0.00");
		
		System.out.println("Leemos un Array de enteros");
		System.out.println("**************************");
		
		for (int i = 0; i < cantidades.length; i++) {
			System.out.print("Introduce el valor de la posición " + i + ": ");
	        cantidades[i] = sc.nextInt();
		}
		
		System.out.println(""); System.out.println("");
		
		System.out.println("Procesamos Array");
		System.out.println("****************");
		
		vmax = cantidades[0];
		vmin = cantidades[0];	
		
		for (int i = 0; i < cantidades.length; i++) {
			acum += cantidades[i];
			if (cantidades[i] > vmax) vmax = cantidades[i];
			if (cantidades[i] < vmin) vmin = cantidades[i];
		}
		media = (float) acum/cantidades.length;
		System.out.println("El valor máximo es: " + vmax);
		System.out.println("El valor mínimo es: " + vmin);
		System.out.println("La media es: " + formato.format(media));
		
		sc.close();

	}
}