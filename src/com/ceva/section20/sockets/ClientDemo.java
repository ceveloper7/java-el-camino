package com.ceva.section20.sockets;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        // nos conectamos al server
        Socket socket = new Socket("127.0.0.1", 2000);

        InputStream in = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String inputLine;
        while ((inputLine = br.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
