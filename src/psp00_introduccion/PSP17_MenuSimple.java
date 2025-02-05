//Programa que muestra un menú simple

package psp00_introduccion;

import java.util.Scanner;

public class PSP17_MenuSimple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		short op;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Primera opción");
		System.out.println("2. Segunda opción");
		System.out.println("3. Tercera opción");
		System.out.println("4. Cuarta opción");
		System.out.println();
		System.out.println("0. Salir");
		System.out.println("---------------------");
		System.out.println();
		
		do {
			System.out.print("Introduzca opción del menú [0-4]: ");
			op = sc.nextShort();
			
			switch (op) {
			case 1:
				System.out.println("Has elegido la primera opción");
				break;
			case 2:
				System.out.println("Has elegido la segunda opción");
				break;
			case 3:
				System.out.println("Has elegido la tercera opción");
				break;
			case 4:
				System.out.println("Has elegido la cuarta opción");
				break;
			case 5:
				
				break;
			case 0:
				System.out.println("Fin de programa");
				break;
			default:
				System.out.println("La opción elegida no es válida");
				break;
			}
		} while (op!=0);
		
		sc.close();
		
	}

}