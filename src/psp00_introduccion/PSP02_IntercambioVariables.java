//Programa que lee por teclado los valores de dos variables enteras,
//muestra por pantalla los valores de ambas variables,
//intercambia los valores de ambas variables y
//finalmente muestra de nuevo los valores de las variables

package psp00_introduccion;

import java.util.Scanner;

public class PSP02_IntercambioVariables {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int var1, var2, aux;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca el valor de var1: ");
		var1 = sc.nextInt();
		System.out.print("Introduzca el valor de var2: ");
		var2 = sc.nextInt();
		
		System.out.println();
		
		System.out.println("El valor de var1 es: " + var1);
		System.out.println("El valor de var2 es: " + var2);
		
		//Intercambiamos valores
		System.out.println();
		System.out.println("Intercambiamos valores");
		aux = var1;
		var1 = var2;
		var2 = aux;
		System.out.println("**********************");
		System.out.println();
		
		System.out.println("Ahora el valor de var1 es: " + var1);
		System.out.println("Ahora el valor de var2 es: " + var2);
		
		sc.close();
	}

}
