package UD1_programacion_de_procesos.Ejercicios.psp01_procesos;

import java.io.File;

public class PSP15_LanzadorRedireccionado {
	
	public void lanzarSumador(Integer n1, Integer n2, String fichResultado) {
		final String path = "C:\\Users\\JOSEMI\\eclipse-workspace\\tmp\\";
		String clase = "psp01_procesos.PSP14_Sumador";
		ProcessBuilder pb;
		
		try {
			pb = new ProcessBuilder("java", "-cp", "bin", clase, n1.toString(), n2.toString());
			
			//Redireccionamos
			pb.redirectError(new File(path + "psp15_errores.txt"));
			pb.redirectOutput(new File(path + fichResultado));
			pb.start();
			
		} catch (Exception ex) {
			System.err.println("Se ha producido un ERROR....");
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PSP15_LanzadorRedireccionado l = new PSP15_LanzadorRedireccionado();
		
		l.lanzarSumador(1, 100, "psp15_result1.txt");
		l.lanzarSumador(2, 1000, "psp15_result2.txt");
		l.lanzarSumador(3, 10000, "psp15_result3.txt");

				
		System.out.println("Programa finalizado");
		System.out.println("Comprueba los resultados en los archivos correspondientes");
	}

}
