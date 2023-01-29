/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ceva.javaelcamino.sec14.lambda.ch03;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author PC
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    void process(BufferedReader b) throws IOException;
}
