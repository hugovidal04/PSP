//  //Muestra información sobre las interfaces de red disponibles en el sistema (con IP)

package UD3_programacion_de_comunicaciones_en_redes.Ejercicios.psp01_comunicaciones_en_red;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class PSP02_InfoInterfaz2 {

  public static void muestraInfoInterfaz(NetworkInterface interfaz) throws SocketException {
    // Nombre
    System.out.printf("Nombre: %s (%s), loopback: %s\n",
            interfaz.getDisplayName(), interfaz.getName(), interfaz.isLoopback() ? "sí" : "no");
    System.out.printf("Soporta multicast: %s\n",
            interfaz.supportsMulticast() ? "sí" : "no");
    System.out.printf("MTU: %d\n", interfaz.getMTU());
    System.out.println("---------------");
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
    System.out.println("");
    
    // Dir. IP
    Enumeration<InetAddress> enumDirIP = interfaz.getInetAddresses();
    Iterator<InetAddress> itDirIP = enumDirIP.asIterator();
    if (itDirIP.hasNext()) {
      System.out.println("Direcciones IP: ");
    }
    int iDirIP = 1;
    while (itDirIP.hasNext()) {
      InetAddress dirIP = (InetAddress) itDirIP.next();
      String ver = (dirIP instanceof Inet4Address) ? "IPv4" : (dirIP instanceof Inet6Address) ? "IPv6" : "";
      System.out.printf("[%d] (%s) %s", iDirIP++, ver, dirIP);
      System.out.print(", bytes: [");
      byte bytesDir[] = dirIP.getAddress();
      for (int iByte = 0; iByte < bytesDir.length; iByte++) {
        if (iByte > 0) {
          System.out.print(", ");
        }
        System.out.printf("%d", Byte.toUnsignedInt(bytesDir[iByte]));
      }
      System.out.printf("]");
      System.out.printf(", dirección local (isSiteLocalAddress()): %s\n",
              dirIP.isSiteLocalAddress() ? "sí" : "no"
      );
      System.out.printf("\n");
    }
    // Direcciones de interfaz de red
    List<InterfaceAddress> lsDirInterfaz = interfaz.getInterfaceAddresses();
    if (!lsDirInterfaz.isEmpty()) {
      System.out.println("Información de interfaz: ");
    }
    int iIf = 1;
    for (InterfaceAddress ifAdd : lsDirInterfaz) {
      System.out.printf("[%d] Dirección: %s, Longitud prefijo: %d, Dirección broadcast: %s\n",
              iIf++, ifAdd.getAddress(), ifAdd.getNetworkPrefixLength(), ifAdd.getBroadcast()
      );
    }

    System.out.printf("\n\n");
  }

  public static void main(String[] args) {

    try {
      Enumeration<NetworkInterface> enumInterfaces;
      enumInterfaces = NetworkInterface.getNetworkInterfaces();
      Iterator<NetworkInterface> itInterfaces = enumInterfaces.asIterator();
      while (itInterfaces.hasNext()) {
        NetworkInterface interfaz = (NetworkInterface) itInterfaces.next();
        muestraInfoInterfaz(interfaz);
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }

    /*
    Stream<NetworkInterface> streamInterfaces;
    try {
      streamInterfaces = NetworkInterface.networkInterfaces();
      Iterator itInterfaces = streamInterfaces.iterator();
      while (itInterfaces.hasNext()) {
        NetworkInterface interfaz = (NetworkInterface) itInterfaces.next();
        muestraInfoInterfaz(interfaz);
      }
    } catch (SocketException ex) {
      ex.printStackTrace();
    }
     */
  }

}
