package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PSP19_ServidorCalculo {

    public static void main(String[] args) {
        final int PUERTO = 12347; // Puerto en el que escucha el servidor

        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de cálculo esperando conexiones en el puerto " + PUERTO);

            while (true) {
                // Aceptar la conexión de un cliente
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

                // Procesar las solicitudes del cliente
                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                     PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                    String operacion;
                    while ((operacion = entrada.readLine()) != null) {
                        // Leer la operación a realizar
                        if (operacion.isEmpty()) {
                            System.out.println("El cliente ha terminado la conexión.");
                            break;
                        }

                        // Leer los dos operandos
                        double valor1 = Double.parseDouble(entrada.readLine());
                        double valor2 = Double.parseDouble(entrada.readLine());

                        // Calcular el resultado
                        double resultado = calcular(operacion, valor1, valor2);

                        // Enviar el resultado al cliente
                        salida.println("Resultado: " + resultado);
                        System.out.printf("Operación recibida: %s %.2f %.2f -> Resultado: %.2f\n", operacion, valor1, valor2, resultado);
                    }
                } catch (Exception e) {
                    System.err.println("Error procesando la solicitud del cliente: " + e.getMessage());
                }

                // Cerrar la conexión con el cliente
                clienteSocket.close();
                System.out.println("Conexión con el cliente cerrada.");
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Método para calcular la operación
    private static double calcular(String operacion, double valor1, double valor2) throws IllegalArgumentException {
        return switch (operacion) {
            case "+" -> valor1 + valor2;
            case "-" -> valor1 - valor2;
            case "*" -> valor1 * valor2;
            case "/" -> {
                if (valor2 == 0) throw new IllegalArgumentException("División entre cero no permitida");
                yield valor1 / valor2;
            }
            default -> throw new IllegalArgumentException("Operación no válida: " + operacion);
        };
    }
}
