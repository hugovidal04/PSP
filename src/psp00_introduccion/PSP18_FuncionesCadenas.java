//Programa que realiza diversas operaciones con cadenas

package psp00_introduccion;

import java.util.Scanner;

public class PSP18_FuncionesCadenas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		short op;
		String cad1="", cad2;
		char letra;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Inversa");
		System.out.println("2. Palíndromo");
		System.out.println("3. Intercalar");
		System.out.println("4. Contar vocales");
		System.out.println("5. Contar letra");
		System.out.println();
		System.out.println("0. Salir");
		
		do {
			System.out.println();
			System.out.print("Introduzca opción del menú: ");
			op = sc.nextShort();
			sc.nextLine(); //Vaciamos buffer ("\n")
			
			switch (op) {
			case 1:
				System.out.println("Inversa");
				System.out.print("Introduzca Cadena: ");
				cad1 = sc.nextLine();
				System.out.println("La cadena inversa es: " + cadenaInversa(cad1));
				break;
			case 2:
				System.out.println("Palíndromo");
				System.out.print("Introduzca Cadena: ");
				cad1 = sc.nextLine();
				if (palindromo(cad1))
					System.out.println("La cadena ES un palíndromo");
				else
					System.out.println("La cadena NO ES un palíndromo");
				break;
			case 3:
				System.out.println("Intercalar");
				System.out.print("Introduzca Cadena1: ");
				cad1 = sc.nextLine();
				System.out.print("Introduzca Cadena2: ");
				cad2 = sc.nextLine();
				System.out.println(intercalar(cad1, cad2));
				break;
			case 4:
				System.out.println("Contar vocales");
				System.out.print("Introduzca Cadena: ");
				cad1 = sc.nextLine();
				System.out.println("La cadena tiene " + contarVocales(cad1) + " vocales");
				break;
			case 5:
				System.out.println("Contar letra");
				System.out.print("Introduzca Cadena: ");
				cad1 = sc.nextLine();				
				System.out.print("Introduzca Letra: ");
				letra = sc.next().charAt(0);
				System.out.print("Tener en cuenta mayúsculas (true/false): ");
				boolean tipo = sc.nextBoolean();
				System.out.println("La letra " + letra + " aparece " + contarLetra(cad1, letra, tipo) + " veces en la cadena");
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
	
	
	
//Función cadenaInversa
	private static String cadenaInversa(String cad) {
		String inversa = "";
		for (int i = cad.length()-1; i>=0; i--) {
			inversa += cad.charAt(i);
		}
		return inversa;
	}
	
//Función Intercalar
	private static String intercalar(String cad1, String cad2) {
		String cadAux = "";
		
		if (cad1.length() > cad2.length()) {
			return ("Cadena1 mayor que Cadena2");
		} else if (cad1.length() < cad2.length()) {
			return ("Cadena2 mayor que Cadena1");
		} else {
			for (int i = 0; i < cad1.length(); i++) {
				cadAux = cadAux + cad1.charAt(i) + cad2.charAt(i);
			}
		}
		return cadAux;
	}	
		
//Función Palíndromo
	private static boolean palindromo (String cad) {
		for (int i = 0; i < cad.length() / 2; i++) {
			if (cad.charAt(i) != cad.charAt(cad.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	
	
	// Función contarVocales()
	private static int contarVocales (String cad) {
		int cont=0;
		cad = cad.toLowerCase();
		for (int i = 0; i < cad.length(); i++) {
			if (cad.charAt(i) == 'a' || cad.charAt(i) == 'e'|| cad.charAt(i) == 'i'|| cad.charAt(i) == 'o'|| cad.charAt(i) == 'u')
				cont++;
			}
		return cont;
	}
	
	
	// Función contarLetra()
	private static int contarLetra (String cad, char letra, boolean tipo) {
		int cont=0;
		if (!tipo) {
			cad = cad.toLowerCase();
			letra = Character.toLowerCase(letra);
		}
		for (int i = 0; i < cad.length(); i++) {
			if (cad.charAt(i) == letra) {
				cont++;;
			}
		}
		return cont;
	}
	
}