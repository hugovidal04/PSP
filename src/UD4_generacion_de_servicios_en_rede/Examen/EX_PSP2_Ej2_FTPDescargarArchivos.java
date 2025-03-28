package UD4_generacion_de_servicios_en_rede.Examen;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class EX_PSP2_Ej2_FTPDescargarArchivos {

    public static void main(String[] args) {
        
        // Datos por defecto para conectar a gnu
        String servidorFTP = "ftp.gnu.org";
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
            String entradaUsuario;

            while (true) {
                mostrarContenidoDirectorio(clienteFTP);
                
                // Pedir entrada al usuario
                System.out.print("\nIntroduce o nome dun ficheiro ou directorio ('exit' para saír, '..' para ir atrás): ");
                entradaUsuario = scanner.nextLine();

                if (entradaUsuario.equalsIgnoreCase("exit")) {
                    break;
                }

                // Manejar directorio padre
                /*if (entradaUsuario.equals("..")) {
                    clienteFTP.changeToParentDirectory();
                    continue;
                }*/

                //Verifica si es un directorio
                if (esDirectorio(clienteFTP, entradaUsuario)) {
                    //Cambia al directorio
                    if (clienteFTP.changeWorkingDirectory(entradaUsuario)) {
                        System.out.println("Entrando en el directorio: " + entradaUsuario);
                    } else {
                        System.out.println("ERROR: No se pudo acceder al directorio.");
                    }
                } else {
                    //Intenta descargar el archivo
                    try (FileOutputStream fos = new FileOutputStream(entradaUsuario)) {
                        if (clienteFTP.retrieveFile(entradaUsuario, fos)) {
                            System.out.println("Archivo '" + entradaUsuario + "' descargado con éxito");
                        } else {
                            System.out.println("ERROR: No se pudo descargar el archivo.");
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR: Problema al crear el archivo local: " + e.getMessage());
                    }
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

    // Método para verificar si una entrada es un directorio
    private static boolean esDirectorio(FTPClient clienteFTP, String nombre) throws IOException {
        // Guardar el directorio actual
        String directorioActual = clienteFTP.printWorkingDirectory();
        
        // Intentar cambiar al directorio
        boolean esDir = clienteFTP.changeWorkingDirectory(nombre);
        
        // Volver al directorio original
        if (esDir) {
            clienteFTP.changeWorkingDirectory(directorioActual);
        }
        return esDir;
    }

    // Mostrar el contenido del directorio actual
    private static void mostrarContenidoDirectorio(FTPClient clienteFTP) throws IOException {
        System.out.printf("\nINFO: Directorio actual: %s\n", clienteFTP.printWorkingDirectory());

        FTPFile[] archivos = clienteFTP.listFiles();
        if (archivos.length == 0) {
            System.out.println("Directorio vacío.");
        } else {
            System.out.println("Contenido del directorio:");
            for (FTPFile archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("[D] " + archivo.getName());
                } else {
                    System.out.println("      " + archivo.getName());
                }
            }
        }
    }
}