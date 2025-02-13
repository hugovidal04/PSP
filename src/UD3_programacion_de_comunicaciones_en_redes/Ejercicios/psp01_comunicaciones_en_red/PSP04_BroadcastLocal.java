//Programa que muestra la dirección de broadcast para la red local
package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class PSP04_BroadcastLocal {

  public static void main(String[] args) {
    
    try {
      Enumeration<NetworkInterface> eIfs = NetworkInterface.getNetworkInterfaces();
      Iterator<NetworkInterface> iIfs = eIfs.asIterator();
      while (iIfs.hasNext()) {
        NetworkInterface nIf = iIfs.next();
        List<InterfaceAddress> lDir = nIf.getInterfaceAddresses();
        for (InterfaceAddress dirIf : lDir) {
          InetAddress dirIP = dirIf.getAddress();
          if (dirIP.isSiteLocalAddress()) { // Es privada
            System.out.printf("Interfaz %s: %s\n", nIf.getDisplayName(),
                    dirIf.getBroadcast().getHostAddress());
          }
        }
      }
    } catch (SocketException ex) {
      System.err.println("ERROR: Excepción SocketException");
      ex.printStackTrace();
    }
    
  }

}