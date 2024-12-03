package com.ceva.core1.ch06.serviceLoader;

/*
 * metodos que cada instancia del servicio deberia proveer
 */
public interface Cipher {
    byte[] encrypt(byte[] source, byte[] key);
    byte[] decrypt(byte[] source, byte[] key);
    int strength();
}
