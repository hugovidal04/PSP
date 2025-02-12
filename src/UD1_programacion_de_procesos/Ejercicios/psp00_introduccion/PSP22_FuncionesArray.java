//Gestión de arrays con menú simple

package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

import java.util.Scanner;

public class PSP22_FuncionesArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		final int MAX = 10;				//Nº de elementos del array
		int v_Min = 1;					//Valor mínimo de los elementos del array
		int v_Max = 100;				//Valor máximo de los elementos del array
		int[] vector = new int[MAX];
		int valAux =0;					//Valor auxiliar utilizado en diversas opciones del menú
				
		short op;

		do {
			mostrarMenu();
			
			System.out.print("\nIntroduzca opción del menú [0-9]: ");
			op = sc.nextShort();

			switch (op) {
			case 1:
				System.out.println("PARAMETRIZAR");
				System.out.println("------------");
				System.out.print("Introduzca valor mínimo: ");
				v_Min = sc.nextInt();
				System.out.print("Introduzca valor máximo: ");
				v_Max = sc.nextInt();
				break;

			case 2:
				System.out.println("RELLENAR ARRAY");
				System.out.println("--------------");
				rellenarVector(vector, v_Min, v_Max);
				System.out.println("Vector rellenado correctamente");
				break;

			case 3:
				System.out.println("MOSTRAR VALORES");
				System.out.println("---------------");
				mostrarValores(vector);
				break;

			case 4:
				System.out.println("INSERTAR VALOR");
				System.out.println("--------------");
				
				int pos;
				
				do {
					System.out.print("Posición a insertar (0-" + MAX + "): ");
					pos = sc.nextInt();
				} while (pos < 1 || pos > MAX);
				System.out.print("Valor a insertar: ");
				valAux = sc.nextInt();
				
				insertarValor(vector, pos, valAux);
				break;

			case 5:
				System.out.println("ELIMINAR RANGO");
				System.out.println("--------------");
				
				int ini, fin;

				System.out.print("Posición inicial: ");
				ini = sc.nextInt();
				System.out.print("Posición final: ");
				fin = sc.nextInt();
				
				eliminarRango(vector, ini, fin);
				break;

			case 6:
				System.out.println("BUSCAR VALOR");
				System.out.println("------------");
				
				System.out.print("Valor a buscar: ");
				valAux = sc.nextInt();
				
				int resul = buscarValor(vector, valAux);
				if (resul < 0)
					System.out.println("El valor indicado no se encuentra en el array");
				else
					System.out.println("El valor indicado está en la posición: " + ++resul);
				break;

			case 7:
				System.out.println("OCURRENCIAS");
				System.out.println("-----------");
				
				System.out.print("Valor a buscar: ");
				valAux = sc.nextInt();
				
				System.out.println("El valor introducido aparece " + ocurrencias(vector, valAux) + " veces");	
				break;

			case 8:
				System.out.println("ORDENAR");
				System.out.println("-------");
				
				ordenarVector(vector);
				mostrarValores(vector);
				break;

			case 9:
				System.out.println("INVERTIR");
				System.out.println("--------");
				
				invertirVector(vector);
				mostrarValores(vector);
				break;
				
			case 0:
				System.out.println("Fin de programa");
				break;
				
			default:
				System.out.println("La opción elegida no es válida");
				break;
			}

		} while (op != 0);

		sc.close();

	}
	
	//*******************************************************************************
	//Función mostrarMenu
	private static void mostrarMenu() {
		System.out.println();
		System.out.println("********************************");
		System.out.println("        MENU          ");
		System.out.println("        ====");
		System.out.println("1. Parametrizar");
		System.out.println("2. Rellenar vector");
		System.out.println("3. Mostrar Valores");
		System.out.println("4. Insertar valor");
		System.out.println("5. Eliminar rango");
		System.out.println("6. Buscar valor");
		System.out.println("7. Ocurrencias");
		System.out.println("8. Ordenar");
		System.out.println("9. Invertir");
		System.out.println();
		System.out.println("0. Salir");
		System.out.println("********************************");
	}

	//*******************************************************************************
	//Función rellenarVector
	private static void rellenarVector(int[] v, int min, int max) {
		for (int i = 0; i < v.length; i++) {
			v[i] = (int) (Math.random() * (max - min + 1) + min);
		}
	}
	
	//*******************************************************************************
	//Función mostrarValores
	private static void mostrarValores(int[] v) {
		System.out.print("[");
		
		//Modo convencional
//		for (int i = 0; i < v.length; i++) {
//			System.out.print(" " + v[i] + " ");
//		}
		
		//Modo simplificado
		for(int elto : v) System.out.print(" " + elto + " ");
		
		System.out.println("]");
	}

	//*******************************************************************************
	//Función insertarValor
	private static void insertarValor(int[] v, int pos, int val) {
		for (int i = v.length - 1; i >= pos; i--) {
			v[i] = v[i - 1];
		}
		v[pos - 1] = val;
	}
	
	//*******************************************************************************	
	//Función eliminarRango
	private static void eliminarRango(int[] v, int ini, int fin) {
		for (int i = ini - 1; i < fin; i++) v[i] = 0;
	}
	
	//*******************************************************************************
	//Función buscarValor
	private static int buscarValor(int[] v, int val) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] == val) return i;
		}
		return -1;
	}
	
	//*******************************************************************************
	//Función ocurrencias
	private static int ocurrencias(int[] v, int val) {
		int cont = 0;
		for (int i = 0; i < v.length; i++) {
			if (v[i] == val) cont++;
		}
		return cont;
	}
	
	//*******************************************************************************
	//Función ordenarVector
	private static void ordenarVector(int[] v) {
		int aux;
		for (int i = 0; i < v.length; i++) {
			for (int j = i + 1; j < v.length; j++) {
				if (v[i] > v[j]) {
					aux = v[i];
					v[i] = v[j];
					v[j] = aux;
				}
			}
		}
	}
	
	//*******************************************************************************
	//Función invertirVector
	private static void invertirVector(int[] v) {
		int aux;
		for (int i = 0; i < v.length / 2; i++) {
			aux = v[i];
			v[i] = v[v.length - 1 - i];
			v[v.length - 1 - i] = aux;
		}
	}

}
