//Clase básica que suma los valores comprendidos entre n1 y n2

package examen01;

public class EX_PSP1_Ej1_Sumador {
	private int sumar(int n1, int n2) {
		int resultado = 0;
		for (int i = n1; i <= n2; i++) {
			resultado += i;
			 try {
				 Thread.sleep(2); // Pausar 2 milisegundos (en desarrollo puede ponerse a cero para agilizar las pruebas)
			    } catch (InterruptedException e) {
			        System.err.println("La ejecución fue interrumpida: " + e.getMessage());
			    }
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

          EX_PSP1_Ej1_Sumador sumador = new EX_PSP1_Ej1_Sumador();
          int resultado = sumador.sumar(n1, n2);
          
          // Mostrar el resultado de la suma
          System.out.printf("\nLa suma desde: % 3d hasta % 10d es: % 15d", n1, n2, resultado);

      } catch (NumberFormatException e) {
          System.out.println("Error: Los parámetros deben ser números enteros.");
      }
	}
}