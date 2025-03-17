/*
 * Servidor recibe dos strings cualquiera y un string con los posibles valores: Alfa, Mezclar, Mayus .
 * El servidor recibe estos parámetros y devuelve la siguiente respuesta en función del tercer parámetro:
 *   Menor-> Imprime las cadenas en orden alfabético. Por ejemplo: si recibe en los dos primeros parámetros bbb y aaa, la salida por consola será: aaa y a continuación bbb
 *   Mezclar->Muestra por consola un string formado por las letras de las dos cadenas suministradas inicialmente. Por ejemplo: si recibe en los dos primeros parámetros bbbcc y aaa, la salida por consola será bababacc
 *   Mayus-> Devuelve las dos cadenas en mayúsculas.  Por ejemplo: si recibe en los dos primeros parámetros aaa y bbb, la salida por consola será: AAA y a continuación BBB
 */

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PSP20_ServidorCadenas {

    public static void main(String[] args) {
        final int PUERTO = 12345; // Puerto del servidor

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de cadenas en ejecución en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                    // Leer la petición del cliente
                    String peticion = entrada.readLine();
                    System.out.println("Petición recibida: " + peticion);

                    // Separar los parámetros (formato: "cadena1,cadena2,operacion")
                    String[] datos = peticion.split(",");
                    if (datos.length != 3) {
                        salida.println("Error: Formato incorrecto. Use: cadena1,cadena2,operacion");
                        continue;
                    }

                    String cadena1 = datos[0].trim();
                    String cadena2 = datos[1].trim();
                    String operacion = datos[2].trim().toLowerCase();

                    // Procesar la operación y enviar el resultado al cliente
                    String resultado = procesarOperacion(cadena1, cadena2, operacion);
                    salida.println(resultado);
                    System.out.println("Operación: " + operacion + " | Entrada: " + cadena1 + ", " + cadena2 + " | Resultado: " + resultado);

                } catch (Exception e) {
                    System.err.println("Error procesando solicitud del cliente: " + e.getMessage());
                }
                cliente.close();
                System.out.println("Conexión con el cliente cerrada.");
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // Método que realiza la operación solicitada
    private static String procesarOperacion(String cadena1, String cadena2, String operacion) {
        return switch (operacion) {
            case "alfa" -> ordenarAlfabeticamente(cadena1, cadena2);
            case "mezclar" -> mezclarCadenas(cadena1, cadena2);
            case "mayus" -> cadena1.toUpperCase() + "\n" + cadena2.toUpperCase();
            default -> "Operación no válida: " + operacion;
        };
    }

    // Ordena las cadenas en orden alfabético
    private static String ordenarAlfabeticamente(String c1, String c2) {
        return (c1.compareTo(c2) < 0) ? (c1 + "\n" + c2) : (c2 + "\n" + c1);
    }

    // Mezcla las cadenas intercalando caracteres
    private static String mezclarCadenas(String c1, String c2) {
        StringBuilder mezcla = new StringBuilder();
        int len1 = c1.length(), len2 = c2.length();
        int maxLen = Math.max(len1, len2);

        for (int i = 0; i < maxLen; i++) {
            if (i < len1) mezcla.append(c1.charAt(i));
            if (i < len2) mezcla.append(c2.charAt(i));
        }
        return mezcla.toString();
    }
}
