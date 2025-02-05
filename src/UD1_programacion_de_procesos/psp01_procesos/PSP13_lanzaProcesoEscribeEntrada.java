package UD1_programacion_de_procesos.psp01_procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PSP13_lanzaProcesoEscribeEntrada {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("nslookup");
		pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
		
		try (InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
				BufferedReader br = new BufferedReader(isr)) {
			String linea;
			System.out.print("Introducir nombre de dominio: ");
			
			while ((linea = br.readLine()) != null && linea.length() != 0) {
				Process p = pb.start();
				
				try (OutputStream os = p.getOutputStream();
						OutputStreamWriter oswp = new OutputStreamWriter(os, "UTF-8")) {
					oswp.write(linea + "\n"); //Envía línea leída al proceso
				}
			
				try {
					p.waitFor();
				} catch (InterruptedException ex) {
				}
				System.out.print("Introducir nombre del dominio: ");
			}			
		} catch (IOException ex) {
			System.out.println("ERROR: de E/S");
	        ex.printStackTrace();
		}
	}
}
