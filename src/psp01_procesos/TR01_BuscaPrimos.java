package psp01_procesos;

public class TR01_BuscaPrimos {
	
	//Declaramos la función esPrimo para evaluar si un número es primo o no
		private static boolean esPrimo(int n) {
			int cont=2;
			boolean aux=true;
					
			while (aux && cont < n) {
				if (n % cont == 0) {
					aux = false;
					break;}
				cont++;
			}
			return aux;
		}
		
	
	public int contarPrimos(int n1, int n2) {
		int cont = 0;
		for (int i = n1; i <= n2; i++) {
			if (esPrimo(i)) cont++;
		}

		return cont;
	}
	
	

	public static void main(String[] args) {
		if (args.length < 2) {
            System.out.println("Error: Se necesitan dos parámetros de entrada.");
            return;
        }
		
		try {
			//Ejecución con parámetros
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);

            TR01_BuscaPrimos buscaPrimos = new TR01_BuscaPrimos();
            int resultado = buscaPrimos.contarPrimos(n1, n2);
            
            // Mostrar el resultado de la suma
            //System.out.println("La suma de " + n1 + " a " + n2 + " es: " + resultado);         //Sin definir el formato de salida
            System.out.printf("La cantidad de primos entre: % 3d y % 7d es: % 7d\n", n1, n2, resultado); //Definiendo el formato de salida

        } catch (NumberFormatException e) {
            System.out.println("Error: Los parámetros deben ser números enteros.");
        }

	}

}
