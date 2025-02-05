//Cálculo de los valores de una ecuación de 2º grado
// Exemplos:
// a=1 b=-5 c=6  ==> X1=3, X2=2
// a=1 b=-14 c=49  ==> X1=7, X2=7
// a=2 b=-7 c=3  ==> X1=3, X2=1/2
// a=-1 b=7 c=-10  ==> X1=2, X2=5

package UD1_programacion_de_procesos.psp00_introduccion;

import java.util.Scanner;

public class PSP13_Ecuacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float a, b, c;
		
		System.out.println("Resolución de ecuaciones");
		System.out.println("========================");
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("Introduce coeficiente a: ");
		a = sc.nextFloat();
		System.out.print("Introduce coeficiente b: ");
		b = sc.nextFloat();
		System.out.print("Introduce coeficiente c: ");
		c = sc.nextFloat();
		
		if (a == 0 && b == 0 && c == 0) {
			System.out.println("La ecuación presenta infinitas soluciones");
		} else if (a == 0 && b == 0 && c != 0) {
			System.out.println("La ecuación no tiene solución");
		} else if (a != 0 && b != 0 && c == 0) {
			System.out.println("x1 = 0");
			System.out.println("x2 = " + -b/a);
		} else if (a == 0 && b != 0 && c != 0) {
			System.out.println("x1 = x2 = " + -c/b);
		} else {
			System.out.println("x1 = " + 
		(-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a));
			System.out.println("x2 = " + 
		(-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a));
		}

		sc.close();
	}

}