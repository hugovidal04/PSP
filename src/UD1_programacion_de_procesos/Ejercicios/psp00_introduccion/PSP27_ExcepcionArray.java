package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP27_ExcepcionArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int MAX = 5;
		int [] valores;
		Scanner sc = new Scanner(System.in);
		int cont = 0;
		
		valores = new int[MAX];
		
		try {
			System.out.println("Leemos valores del Array");
			System.out.println("************************");
			while(true) {
				System.out.print("Introduce el valor de la posición " + cont + ": ");
		        valores[cont] = sc.nextInt();
		        cont++;
			}
		} catch (ArrayIndexOutOfBoundsException ex){
			System.out.println("");
			System.out.println("Error: " + ex.getMessage());
			System.out.println("Se ha producido una excepción al intentar capturar el valor " + cont + " del array");
		} finally {
			System.out.println("Hemos capturado el error y salimos de forma controlada");
			sc.close();
		}
	}

}
