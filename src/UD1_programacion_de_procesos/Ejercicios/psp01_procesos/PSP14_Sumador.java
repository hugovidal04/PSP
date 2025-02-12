//Clase básica que suma los valores comprendidos entre n1 y n2

package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

public class PSP14_Sumador {
	public int sumar(int n1, int n2) {
		int resultado = 0;
		for (int i = n1; i <= n2; i++) {
			resultado += i;
		}

		return resultado;
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
            System.out.println("Error: Se necesitan dos parámetros de entrada.");
            return;
        }
		
		try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);

            PSP14_Sumador sumador = new PSP14_Sumador();
            int resultado = sumador.sumar(n1, n2);
            
            // Mostrar el resultado de la suma
            //System.out.println("La suma de " + n1 + " a " + n2 + " es: " + resultado);         //Sin definir el formato de salida
            System.out.printf("\nLa suma desde: % 3d hasta % 10d es: % 15d", n1, n2, resultado); //Definiendo el formato de salida

        } catch (NumberFormatException e) {
            System.out.println("Error: Los parámetros deben ser números enteros.");
        }
	}
}
