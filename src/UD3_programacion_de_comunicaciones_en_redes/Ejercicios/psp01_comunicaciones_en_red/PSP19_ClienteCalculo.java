package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PSP19_ClienteCalculo {

    public static void main(String[] args) {
        final String HOST = "localhost"; // Dirección del servidor
        final int PUERTO = 12347; // Puerto en el que escucha el servidor

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor de cálculo en " + HOST + ":" + PUERTO);

            while (true) {
                // Introducir la operación
                System.out.print("Introduce la operación (+, -, *, / o vacío para salir): ");
                String operacion = teclado.readLine();
                if (operacion.isEmpty()) {
                    System.out.println("Saliendo del cliente...");
                    break;
                }

                // Introducir los operandos
                System.out.print("Introduce el primer valor: ");
                double valor1 = Double.parseDouble(teclado.readLine());
                System.out.print("Introduce el segundo valor: ");
                double valor2 = Double.parseDouble(teclado.readLine());

                // Enviar datos al servidor
                salida.println(operacion);
                salida.println(valor1);
                salida.println(valor2);

                // Recibir y mostrar el resultado
                String resultado = entrada.readLine();
                System.out.println(resultado);
            }

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}