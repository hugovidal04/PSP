//Programa que realiza la suma de dos números mediante una función

package UD1_programacion_de_procesos.psp00_introduccion;

import java.util.Scanner;

public class PSP15_FuncionSuma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a, b, suma;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduzca un número: ");
		a = sc.nextInt();
		System.out.print("Introduzca otro número: ");
		b = sc.nextInt();
		
		suma = sumar(a,b);
		
		System.out.println("La suma es: " + suma);
		
		sc.close();
	}
	
	
	//Declaramos una función que recibe como parámetros dos números enteros
	//y devuelve como resultado la suma de ambos 
	 private static int sumar(int x, int y)
	    {
	        return x + y;
	    }

}