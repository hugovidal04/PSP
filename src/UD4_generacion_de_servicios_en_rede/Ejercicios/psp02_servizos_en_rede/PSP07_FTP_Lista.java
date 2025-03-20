//Conectar a servidor FTP u y proporcionar lista de archivos y ficheros disponibles

package UD4_generacion_de_servicios_en_rede.Ejercicios.psp02_servizos_en_rede;

import java.io.IOException;	
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTP;

public class PSP07_FTP_Lista {

  public static void main(String[] args) {
  
	  //Datos por defecto para conectar a rediris
	  String servidorFTP = "ftp.rediris.es";
	  String usuario = "anonymous";
	  String password = "";

	// Creación de la instancia del cliente FTP 
    FTPClient clienteFTP = new FTPClient();

    try {
      clienteFTP.connect(servidorFTP);					//Se conecta al servidor FTP
      int codResp = clienteFTP.getReplyCode();			//Obtiene el código de respuesta
      if (!FTPReply.isPositiveCompletion(codResp)) {
        System.out.printf("ERROR: Conexión rechazada con código de respuesta %d.\n", codResp);
        System.exit(2);
      }

      //Establecemos conexión en modo pasivo
      clienteFTP.enterLocalPassiveMode();				//conexión a través de firewalls y NAT
      
      //Establecemos tipo de transferencia como archivos binarios
      clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

      
      //Autentificamos usuario
      if (usuario != null && password != null) {
        boolean loginOK = clienteFTP.login(usuario, password);
        if (loginOK) {
          System.out.printf("INFO: Login con usuario %s realizado.\n", usuario);
        } else {
          System.out.printf("ERROR: Login con usuario %s rechazado.\n", usuario);
          return;
        }
      }
  
      //Se muestra el mensaje de bienvenida del servidor y el directorio actual
      System.out.printf("INFO: Conexión establecida, mensaje de bienvenida del servidor:\n====\n%s====\n", clienteFTP.getReplyString());
      System.out.printf("INFO: Directorio actual en servidor: %s.\n", clienteFTP.printWorkingDirectory());

      //Se obtiene la lista de archivos y directorios del servidor
      FTPFile[] fichServ = clienteFTP.listFiles();
      
      //Recorremos y mostramos archivos del servidor
      for (FTPFile f: fichServ) {
    	 String infoAdicFich = "";
    	 if(f.getType() == FTPFile.DIRECTORY_TYPE) {
    		 infoAdicFich = "/";
    	 }
    	 else if (f.getType() == FTPFile.SYMBOLIC_LINK_TYPE) {
    		 infoAdicFich = " -> " + f.getLink();
    	 }
    	 System.out.printf("%S%S\n", f.getName(), infoAdicFich);
      }

    } catch (IOException e) {
      System.out.println("ERROR: conectando al servidor");
      e.printStackTrace();
      return;
    } finally { //Gestionamos el cierre de conexión
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