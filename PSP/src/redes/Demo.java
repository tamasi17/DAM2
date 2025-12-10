package redes;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringJoiner;

public class Demo {

    static void main() {

        // Obtener interfaces


        try {
            Enumeration<NetworkInterface> interfacesEncontradas =
                    NetworkInterface.getNetworkInterfaces();

            NetworkInterface myNetworkInterface = null;

            for (NetworkInterface interfaz : Collections.list(interfacesEncontradas)) {

                System.out.println(interfaz.getName());

                if (interfaz.getName().equalsIgnoreCase("ethernet_32769")){
                    myNetworkInterface = interfaz;
                    break;
                }
            }


            System.out.println("Nombre: " + myNetworkInterface.getName());
            System.out.println("Mas info: " + myNetworkInterface.getDisplayName());
            System.out.println("Esta activa?: " + myNetworkInterface.isUp());
            System.out.println("Es virtual?: " + myNetworkInterface.isVirtual());
            System.out.println("MTU: " + myNetworkInterface.getMTU());

            byte[] mac = myNetworkInterface.getHardwareAddress();

            /*
             StringBuilder sirve para construir cadenas sin reventar el pool de String
             String hola1 = "hola";   // crea la referencia de memoria ref1
             String hola2 = "hola";   // busca en el pool si ya existe esa cadena, y porque existe, asigna ref1
             hola1 == hola2 --> true, misma ref
             StringBuilder hace que no se generen nuevas refs de memoria, en vez de ir haciendo hola+loQueSea.

             */

            StringJoiner direccionMacFormateada = new StringJoiner("-");
            for (byte b : mac) {
                int valorDecimal = b & 0xFF;
                String hex = String.format("%02x", valorDecimal);
                direccionMacFormateada.add(hex);
            }

            System.out.println("Direccion MAC: " + direccionMacFormateada);


            Enumeration<InetAddress> direccionesIp = myNetworkInterface.getInetAddresses();

            InetAddress direccionIp = direccionesIp.nextElement();
            System.out.println("Direccion IP: "+ direccionIp.getHostAddress());
            direccionIp = direccionesIp.nextElement();
            System.out.println("Direccion IP: "+ direccionIp.getHostAddress());
            System.out.println("HostName: "+ direccionIp.getHostName());


        } catch (SocketException se) {
            System.err.println("Error al recibir informacion de la NetworkInterface "+ se.getLocalizedMessage());
        }




        // nos quedamos con una que sea loopback


    }

}
