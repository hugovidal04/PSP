
package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTP;

public class PSP09_FTP_Get {

  public static void main(String[] args) {

	  //Datos por defecto para conectar a rediris
	  String servidorFTP = "ftp.rediris.es";
	  String ficheroEnServidor = "welcome.msg";
	  String usuario = "anonymous";
	  String password = "";
    
    
    FTPClient clienteFTP = new FTPClient();

    try {
      clienteFTP.connect(servidorFTP);
      int codResp = clienteFTP.getReplyCode();
      if (!FTPReply.isPositiveCompletion(codResp)) {
        System.out.printf("ERROR: Conexión rechazada con código de respuesta %d.\n", codResp);
        System.exit(2);
      }

      clienteFTP.enterLocalPassiveMode();
      clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

      if (usuario != null && password != null) {
        boolean loginOK = clienteFTP.login(usuario, password);
        if (loginOK) {
          System.out.printf("INFO: Login con usuario %s realizado.\n", usuario);
        } else {
          System.out.printf("ERROR: Login con usuario %s rechazado.\n", usuario);
          return;
        }
      }

      System.out.printf("INFO: Conexión establecida, mensaje de bienvenida del servidor:\n====\n%s====\n", clienteFTP.getReplyString());
      System.out.printf("INFO: Directorio actual en servidor: %s.\n", clienteFTP.printWorkingDirectory());

      String tamFichEnServidor = clienteFTP.getSize(ficheroEnServidor);
      if (tamFichEnServidor == null) {
        System.out.printf("ERROR: Fichero %s no existe en servidor.\n", ficheroEnServidor);
        return;
      }

      String nomFichLocal = ficheroEnServidor.substring(ficheroEnServidor.lastIndexOf('/') + 1);
      try (FileOutputStream fos = new FileOutputStream(nomFichLocal)) {
        clienteFTP.retrieveFile(ficheroEnServidor, fos);
      }

      System.out.printf("INFO: Se ha intentado copiar fichero %s a fichero local %s.\n", ficheroEnServidor, nomFichLocal);
      System.out.printf("INFO: Respuesta del servidor:\n====\n%s====\n", clienteFTP.getReplyString());

      codResp = clienteFTP.getReplyCode();
      if (FTPReply.isPositiveCompletion(codResp)) {
        System.out.println("INFO: Servidor informa de que se ha completado satisfactoriamente la acción.");
      } else {
        System.out.println("INFO: Servidor informa de que NO se ha completado satisfactoriamente la acción.");
      }

    } catch (IOException e) {
      System.out.println("ERROR: conectando al servidor");
      e.printStackTrace();
      return;
    } finally {
      if (clienteFTP != null) {
        try {
          clienteFTP.disconnect();
          System.out.println("INFO: conexión cerrada.");
        } catch (IOException e) {
          System.out.println("AVISO: no se pudo cerrar la conexión.");
        }
      }
    }
  }
}
