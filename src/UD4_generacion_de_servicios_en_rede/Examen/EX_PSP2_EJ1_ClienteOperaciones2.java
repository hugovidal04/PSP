package UD4_generacion_de_servicios_en_rede.Examen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EX_PSP2_EJ1_ClienteOperaciones2 {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 12347;

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            while (true) {
                System.out.println("\nOperaciones disponibles: Doble, Mayor, Ordenado, Básicas, Múltiplo");
                System.out.print("Introduce la operación y dos números separados por coma (ejemplo: Doble,4,7) o 'exit' para salir: ");
                
                String input = teclado.readLine().trim();
                
                //Salida con la palabra exit
                if (input.equalsIgnoreCase("exit")) {
                    salida.println("exit");
                    System.out.println("Saliendo del cliente...");
                    break;
                }

                salida.println(input);
                
                String respuesta = entrada.readLine();
                System.out.println(respuesta);
            }

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}