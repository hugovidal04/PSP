//Cálculo de los factoriales de los primeros 20 números enteros

package UD1_programacion_de_procesos.psp00_introduccion;

public class PSP07_Factoriales {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long factorial = 1;
		
		System.out.println("CÁLCULO DE FACTORIALES");
		System.out.println("======================");
		
		//System.out.print("Introduzca un número: ");
		//num = sc.nextInt();
		for (int cont = 2; cont <= 21; cont ++) {
			factorial = 1;
			for (int i = 2; i <= cont; i++) {
				factorial *= i;
				//factorial = factorial * i;
			}

			System.out.println("El factorial de " + cont + " es: " + factorial);
}
	}

}