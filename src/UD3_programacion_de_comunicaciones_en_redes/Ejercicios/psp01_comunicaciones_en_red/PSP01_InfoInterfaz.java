 //Muestra información sobre las interfaces de red disponibles en el sistema

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;

public class PSP01_InfoInterfaz {

  public static void muestraInfoInterfaz(NetworkInterface interfaz) throws SocketException {
    // Nombre
    System.out.printf("Nombre: %s (%s), loopback: %s\n",
            interfaz.getDisplayName(), interfaz.getName(), interfaz.isLoopback() ? "sí" : "no");
    System.out.printf("Soporta multicast: %s\n",
            interfaz.supportsMulticast() ? "sí" : "no");
    System.out.printf("MTU: %d\n", interfaz.getMTU());
    // Dir. MAC
    byte[] dirMac = interfaz.getHardwareAddress();
    System.out.printf("Dir. MAC: ", interfaz.getDisplayName());
    boolean primerByte = true;
    if (dirMac != null) {
      for (int i = 0; i < dirMac.length; i++) {
        if (!primerByte) {
          System.out.print(":");
        }
        System.out.printf("%02x", dirMac[i]);
        primerByte = false;
      }
    } else {
      System.out.println("(N/A)");
    }
    System.out.printf("\n");
  }

  public static void main(String[] args) {

    try {
      Enumeration<NetworkInterface> enumInterfaces;
      enumInterfaces = NetworkInterface.getNetworkInterfaces();
      Iterator<NetworkInterface> itInterfaces = enumInterfaces.asIterator();
      int i = 1;
      while (itInterfaces.hasNext()) {
        NetworkInterface interfaz = (NetworkInterface) itInterfaces.next();
        System.out.printf("---- [%d] ----\n", i++);
        muestraInfoInterfaz(interfaz);
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }
  }

}
