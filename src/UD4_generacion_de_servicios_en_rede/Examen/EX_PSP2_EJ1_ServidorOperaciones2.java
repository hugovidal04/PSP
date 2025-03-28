package UD4_generacion_de_servicios_en_rede.Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

public class EX_PSP2_EJ1_ServidorOperaciones2 {

	//Formateamos las salidas de los decimales
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        final int PUERTO = 12347;
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de cálculo esperando conexiones en el puerto " + PUERTO);

            while (true) {
            	//Acepta la conexión con el cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                     PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) {

                    String inputLine;
                    while ((inputLine = entrada.readLine()) != null) {
                        if (inputLine.equalsIgnoreCase("exit")) {
                            System.out.println("El cliente ha terminado la conexión");
                            break;
                        }

                        //Nos aseguramos de que el usuario sepa cual es el formato
                        String[] partes = inputLine.split(",");
                        if (partes.length < 3) {
                            salida.println("Formato incorrecto. Usa: Operacion,num1,num2");
                            continue;
                        }
                        
                        //Nos evitamos problemas con mayúsculas y espacios
                        String operacion = partes[0].trim().toUpperCase();
                        double valor1, valor2;
                        try {
                            valor1 = Double.parseDouble(partes[1].trim());
                            valor2 = Double.parseDouble(partes[2].trim());
                        } catch (NumberFormatException e) {
                            salida.println("Error: Los valores deben ser números");
                            continue;
                        }

                        //Menú de opciones
                        String resultado;
                        switch (operacion) {
                            case "DOBLE":
                                resultado = doble(valor1, valor2);
                                break;
                            case "MAYOR":
                                resultado = mayor(valor1, valor2);
                                break;
                            case "ORDENADO":
                                resultado = ordenado(valor1, valor2);
                                break;
                            //Tenemos en cuenta que el usuario pueda escribir con tilde o no
                            case "BÁSICAS":
                            case "BASICAS":
                                resultado = basicas(valor1, valor2);
                                break;
                            //Tenemos en cuenta que el usuario pueda escribir con tilde o no    
                            case "MÚLTIPLO":
                            case "MULTIPLO":
                                resultado = multiplo(valor1, valor2);
                                break;
                            default:
                                resultado = "Operación no válida. Opciones: Doble, Mayor, Ordenado, Básicas, Múltiplo";
                        }

                        salida.println("Respuesta del servidor: " + resultado);
                    }
                } catch (IOException ex) {
                    System.err.println("Error procesando la solicitud del cliente: " + ex.getMessage());
                }

                clienteSocket.close();
                System.out.println("Conexión con el cliente cerrada");
            }
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    //Método para calcular el doble
    private static String doble(double valor1, double valor2) {
        return "Doble: " + df.format(valor1 * 2) + " y " + df.format(valor2 * 2);
    }

    //Método para calcular el dato mayor
    private static String mayor(double valor1, double valor2) {
        return "Mayor: " + df.format(Math.max(valor1, valor2));
    }

    //Método para ordenar los datos
    private static String ordenado(double valor1, double valor2) {
        return valor1 > valor2 ? "Ordenado: " + df.format(valor2) + ", " + df.format(valor1) : "Ordenado: " + df.format(valor2) + ", " + df.format(valor1);
    }

    //Método para las operaciones básicas
    private static String basicas(double valor1, double valor2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Suma: ").append(df.format(valor1 + valor2));
        sb.append(", Resta: ").append(df.format(valor1 - valor2));
        sb.append(", Multiplicación: ").append(df.format(valor1 * valor2));
        
        if (valor2 != 0) {
            sb.append(", División: ").append(df.format(valor1 / valor2));
        } else {
            sb.append(", División: No definida (división por cero)");
        }
        
        return sb.toString();
    }

    //Método para los múltiplos
    private static String multiplo(double valor1, double valor2) {
        if (valor1 == 0) {
            return "No se puede determinar si 0 es múltiplo de otro número";
        }
        return (valor2 % valor1 == 0) ? "Múltiplo: NO" : "Múltiplo: SI";
    }
}