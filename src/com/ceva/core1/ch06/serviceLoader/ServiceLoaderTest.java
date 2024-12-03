package com.ceva.core1.ch06.serviceLoader;

import java.io.*;
import java.util.*;

public class ServiceLoaderTest {
    public static ServiceLoader<Cipher> cipherLoader = ServiceLoader.load(Cipher.class);

    public static Cipher getCipher(int minStrength)
    {
        for (Cipher cipher : cipherLoader)
            // Implicitly calls iterator
            if (cipher.strength() >= minStrength) return cipher;
        return null;
    }

    public static void main(String[] args)throws UnsupportedEncodingException {
        Cipher cipher = getCipher(1);
        String message = "Meet me at the toga party.";
        byte[] bytes = cipher.encrypt(message.getBytes(), new byte[] { 3 });
        var encrypted = new String(bytes);
        System.out.println(encrypted);
    }
}
