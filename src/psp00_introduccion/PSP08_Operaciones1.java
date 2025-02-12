//Programa que lee dos valores por teclado
//y realiza las operaciones básicas entre ellos

package psp00_introduccion;

import java.util.Scanner;

public class PSP08_Operaciones1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("OPERACIONES BÁSICAS I");
		System.out.println("*********************");
		
		float num1, num2;
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce primer número: ");
		num1 = sc.nextFloat();
		System.out.print("Introduce segundo número: ");
		num2 = sc.nextFloat();
		
		System.out.println("La suma de los números es: " + (num1 + num2));
		System.out.println("La resta de los números es: " + (num1 - num2));
		System.out.println("La multiplicación de los números es: " + (num1 * num2));
		if (num2 != 0) {
			System.out.println("La división de los números es: " + (num1 / num2));
		} else {
			System.out.println("No es posible realizar la división");
		}
		
		sc.close();
	}

}