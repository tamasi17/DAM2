package redes.ejercicio1;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

public class Main1 {
    static void main() {

        Scanner sc = new Scanner(System.in);


        try {
            // Primero Enumeration de NetWorkInterfaces
            Enumeration<NetworkInterface> direcciones = NetworkInterface.getNetworkInterfaces();
            NetworkInterface interfazRed = null;

            List<NetworkInterface> listaDirecciones = Collections.list(direcciones);

            for (NetworkInterface ir : listaDirecciones) {
                System.out.println(ir.getName() + ": " + ir.getDisplayName());
            }

            String interfazBuscada = solicitarRed(sc);

            for (NetworkInterface ir : listaDirecciones) {
                if (ir.getName().equalsIgnoreCase(interfazBuscada)) {
                    interfazRed = ir;
                }
            }

            if (interfazRed == null) {
                throw new SocketException("No hay una interfaz con ese nombre");
            }

            System.out.println("Name and description: " + interfazRed.getName() + ": " + interfazRed.getDisplayName());
            System.out.println("Active: " + interfazRed.isUp());
            System.out.println("Loopback: " + interfazRed.isLoopback());
            System.out.println("\nAssociated IP Adresses:");
            for (InetAddress inet : Collections.list(interfazRed.getInetAddresses())) {
                String hostAddress = String.valueOf(inet.getHostAddress());
                String tipoIp = hostAddress.contains("192") ? "IPv4" : "IPv6";
                System.out.println("Host name: " + inet.getHostName());
                System.out.println(tipoIp + " - Host Address: " + inet.getHostAddress());
            }

            List<InterfaceAddress> interfaceAddresses = interfazRed.getInterfaceAddresses();
            for (InterfaceAddress iAddress : interfaceAddresses) {

                System.out.println("\nIP: " + iAddress.getAddress());
                System.out.println("Network Prefix Length: " + iAddress.getNetworkPrefixLength());
                if (iAddress.getBroadcast() != null) System.out.println("Broadcast: " + iAddress.getBroadcast());

            }


        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        sc.close();

    }

    private static String solicitarRed(Scanner sc) {
        System.out.println("Qué interfaz buscamos?");
        // ethernet_32769
        String interfazBuscada = sc.nextLine();
        return interfazBuscada;
    }
}
