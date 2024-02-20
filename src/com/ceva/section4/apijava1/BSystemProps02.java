package com.ceva.section4.apijava1;

import java.util.Properties;

public class BSystemProps02 {
    public static void main(String[] args) {
        Properties props = System.getProperties();
        for(String prop : props.stringPropertyNames()){
            System.out.println(prop + " = " + System.getProperty(prop));
        }
    }
}
