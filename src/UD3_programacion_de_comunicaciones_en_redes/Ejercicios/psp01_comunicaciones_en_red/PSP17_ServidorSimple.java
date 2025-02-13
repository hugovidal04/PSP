package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.io.*;
import java.net.*;

public class PSP17_ServidorSimple {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12346)) {
            System.out.println("Servidor en espera de conexiones...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");
            
            // Comunicación con el cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String mensaje = in.readLine();
            System.out.println("Cliente dice: " + mensaje);
            out.println("Mensaje recibido: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
