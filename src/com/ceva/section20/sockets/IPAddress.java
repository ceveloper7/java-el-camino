package com.ceva.section20.sockets;

import java.net.InetAddress;
public class IPAddress {
    public static void main(String[] args) throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("this name: " + inetAddress.getHostName());
        System.out.println("this IP:   " + inetAddress.getHostAddress());
    }
}
