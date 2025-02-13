//Mostrar nombre e IP del host

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PSP05_InfoLocalHost {

  public static void main(String[] args) {
    
    InetAddress dirLocalHost = null;
    try {
      dirLocalHost = InetAddress.getLocalHost();
      if(dirLocalHost != null) {
        System.out.printf("Nombre: %s, dirección IP: %s.\n",
                dirLocalHost.getHostName(), dirLocalHost.getHostAddress());
      } else {
        System.out.printf("No se pudo obtener dirección IP del propio host.");
      }
    } catch (UnknownHostException ex) {
      System.out.println("Excepción UnknownHostException al llamar a getLocalHost()");
    }

  }

}