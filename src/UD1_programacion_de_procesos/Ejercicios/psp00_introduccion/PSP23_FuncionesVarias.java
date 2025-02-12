//Funciones variadas de utilidad múltiple

package UD1_programacion_de_procesos.Ejercicios.psp00_introduccion;

public class PSP23_FuncionesVarias {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		formatearTexto();
		
	}

	private static void formatearTexto() {
		System.out.printf("\nNúmero: %010d - TextoDereita: %15s - TextoIzquierda: %-15s - Decimal: %11.3f", 123, "Hola", "Hola", 3.14);
		System.out.printf("\nNúmero: %-10d - TextoDereita: %15s - TextoIzquierda: %-15s - Decimal: %11.3f", 1, "Prueba", "Prueba", 1234.1454);
		System.out.printf("\nNúmero: % 10d - TextoDereita: %15s - TextoIzquierda: %-15s - Decimal: %11.3f", 1234567, "Otra cosa", "Otra cosa", 35.4);
		System.out.printf("\nNúmero: % 10d - TextoDereita: %15s - TextoIzquierda: %-15s - Decimal: %11.3f", 1234, "Otra cosa más", "Otra cosa más", 35.41545);
		System.out.printf("\nNúmero: % 10d - TextoDereita: %15s - TextoIzquierda: %-15s - Decimal: %11.3f", 12, "Adios", "Adios", 35.0001);
	}
	
}