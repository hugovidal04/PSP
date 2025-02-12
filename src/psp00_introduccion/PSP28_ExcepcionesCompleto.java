package psp00_introduccion;

import java.util.Scanner;

public class PSP28_ExcepcionesCompleto {

	
	public static int numerador = 10;
	public static Integer denominador = null;
	public static float division;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Nos preparamos para hacer la división");
		System.out.println("*************************************");
		
		System.out.print("Introduce el numerador: ");
        numerador = sc.nextInt();
        
        System.out.print("Introduce el denominador: ");
        denominador = sc.nextInt();
        
		try {
			division = numerador / denominador;
		} catch (ArithmeticException ex){
			division = 0; //Si hay una excepción de tipo aritmética, asumo que la división tiene un valor 0
			System.out.println("\nError: " + ex.getMessage());
			//ex.printStackTrace();
		} catch (NullPointerException ex){
			division = 1; //Si hay una excepción de tipo null, asumo que la división tiene un valor 1
			System.out.println("\nError: " + ex.getMessage());
			
		} finally {
			System.out.println("División: " + division);
			System.out.println("Finalización del proceso");
			System.out.println("************************");
		}
		
		sc.close();

	}

}
