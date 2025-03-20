
package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

public class PSP08_ListaInteractiva {

    public static void main(String[] args) {
        // Datos del servidor FTP
        String servidorFTP = "ftp.rediris.es";
        String usuario = "anonymous";
        String password = "";

        FTPClient clienteFTP = new FTPClient();

        try {
            // Conectar al servidor
            clienteFTP.connect(servidorFTP);
            int codResp = clienteFTP.getReplyCode();
            if (!FTPReply.isPositiveCompletion(codResp)) {
                System.out.printf("ERROR: Conexión rechazada con código %d.\n", codResp);
                return;
            }

            // Configuración de la conexión FTP
            clienteFTP.enterLocalPassiveMode();
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // Autenticación
            if (clienteFTP.login(usuario, password)) {
                System.out.printf("INFO: Conexión establecida con %s.\n", servidorFTP);
            } else {
                System.out.println("ERROR: No se pudo autenticar.");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            String directorio;

            while (true) {
                // Mostrar contenido del directorio actual
                mostrarContenidoDirectorio(clienteFTP);

                // Pedir nuevo directorio al usuario
                System.out.print("\nIntroduce el nombre del directorio ('exit' para salir): ");
                directorio = scanner.nextLine();

                if (directorio.equalsIgnoreCase("exit")) {
                    break;
                }

                // Intentar cambiar de directorio
                if (clienteFTP.changeWorkingDirectory(directorio)) {
                    System.out.printf("INFO: Cambiado al directorio: %s\n", clienteFTP.printWorkingDirectory());
                } else {
                    System.out.println("ERROR: No se pudo acceder al directorio.");
                }
            }

            // Cerrar conexión
            scanner.close();
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("INFO: Conexión cerrada.");

        } catch (IOException e) {
            System.out.println("ERROR: Ocurrió un problema con el servidor FTP.");
            e.printStackTrace();
        }
    }

    // Método para mostrar el contenido del directorio actual
    private static void mostrarContenidoDirectorio(FTPClient clienteFTP) throws IOException {
        System.out.printf("\nINFO: Directorio actual: %s\n", clienteFTP.printWorkingDirectory());

        FTPFile[] archivos = clienteFTP.listFiles();
        if (archivos.length == 0) {
            System.out.println("Directorio vacío.");
        } else {
            System.out.println("Contenido del directorio:");
            for (FTPFile archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("[DIR] " + archivo.getName());
                } else {
                    System.out.println("      " + archivo.getName());
                }
            }
        }
    }
}
