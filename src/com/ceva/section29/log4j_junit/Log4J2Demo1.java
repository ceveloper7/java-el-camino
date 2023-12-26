package com.ceva.section29.log4j_junit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Log4J2Demo1 {
    private static final Logger log = LogManager.getLogger(Log4J2Demo1.class);
    public static void main(String[] args) {
        log.info("Hola mundo!");
    }
}
