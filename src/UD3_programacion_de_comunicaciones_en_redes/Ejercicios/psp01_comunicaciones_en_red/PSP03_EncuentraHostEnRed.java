//Programa que permite determinar qué direcciones locales dentro de la red local son alcanzables
package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.io.IOException;


public class PSP03_EncuentraHostEnRed {

  private static final int T_ESPERA_ALCANZABLE = 200;  // Tiempo espera para determinar si host alcanzable.

  public static void main(String[] args) {
        
    try {
      byte[] bytesDirIP = new byte[]{(byte) 192, (byte) 168, (byte) 56, (byte) 0};
      
      System.out.printf("Probando rango de direcciones de %d.%d.%d.%d a %d.%d.%d.%d\n.",
              Byte.toUnsignedInt(bytesDirIP[0]), Byte.toUnsignedInt(bytesDirIP[1]), Byte.toUnsignedInt(bytesDirIP[2]), 1,
              Byte.toUnsignedInt(bytesDirIP[0]), Byte.toUnsignedInt(bytesDirIP[1]), Byte.toUnsignedInt(bytesDirIP[2]), Byte.toUnsignedInt((byte) 254));
      
      for (int b = 1; b < 255; b++) {
        bytesDirIP[3] = (byte) b;
        Inet4Address dirIP = (Inet4Address) InetAddress.getByAddress(bytesDirIP);
        System.out.println("> Probando host : " + dirIP.getHostAddress());
        if (dirIP.isReachable(T_ESPERA_ALCANZABLE)) {
          System.out.println("### Host alcanzable: " + dirIP.getHostAddress());
        }
      }
    } catch (UnknownHostException ex) {
      System.out.println("ERROR: host desconocido");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("ERROR: de E/S");
      ex.printStackTrace();
    }

  }

}
