package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.*;
import java.net.*;

public class PSP17_ClienteSimple {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12346)) {
            System.out.println("Conectado al servidor.");
            
            // Comunicación con el servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            out.println("¡Hola, servidor!");
            String respuesta = in.readLine();
            System.out.println("Servidor dice: " + respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
