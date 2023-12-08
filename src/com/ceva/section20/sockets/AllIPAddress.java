package com.ceva.section20.sockets;

import java.net.InetAddress;
import java.net.UnknownHostException;
/*
 * Listamos todas las direccion IP de la computadora
 */
public class AllIPAddress {
    public static void main(String[] args) {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            InetAddress allIps[] = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allIps != null) {
                System.out.println("List of IP addresses:");
                for (InetAddress addr : allIps) {
                    System.out.println("    " + addr);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println(e.getClass().getName() + " generated: " + e.getMessage());
        }
    }
}
