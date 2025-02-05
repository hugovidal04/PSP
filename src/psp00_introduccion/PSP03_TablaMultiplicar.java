//Tabla de multiplicar de un valor leído por teclado
//entre dos valores ini y fin, también leídos por teclado
//El programa finaliza cuando introducimos un número menor o igual a cero

package psp00_introduccion;

import java.util.Scanner;

public class PSP03_TablaMultiplicar {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num, ini, fin;
		
		Scanner sc = new Scanner(System.in);
		
		do {
		
			System.out.print("Introduzca un número: ");
			num = sc.nextInt();
			
			if (num > 0) {
				System.out.print("Introduzca inicio: ");
				ini = sc.nextInt();
				
				System.out.print("Introduzca fin: ");
				fin = sc.nextInt();
				
				for (int i = ini; i <= fin; i++) {
					System.out.println(num + " * " + i + " = " + (num*i));
				}
			}
		} while (num > 0);
		
		System.out.print("Programa finalizado ");
		
		sc.close();
	}

}
