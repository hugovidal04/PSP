package psp00_introduccion;

import java.io.IOException;

public class PSP29_ExcepcionPropagada {
	private int caracter;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PSP29_ExcepcionPropagada ejemplo = new PSP29_ExcepcionPropagada();
		
		try {
			ejemplo.leerCaracter();
			ejemplo.imprimirCaracter();
		} catch (IOException ex) {
			System.out.println("Se ha capturado una excepción....");
			System.out.println(ex.getMessage());
		}

	}
	
	public void leerCaracter() throws IOException {
		System.out.print("Introduce carácter a leer: ");
		caracter = System.in.read();
	}
	
	public void imprimirCaracter() {
		System.out.print("Imprimiendo carácter: ");
		System.out.println((char)caracter);
	}

}
