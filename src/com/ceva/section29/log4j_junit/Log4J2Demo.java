package com.ceva.section29.log4j_junit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author rcosio
 */
public class Log4J2Demo {
    private static final Logger log = LogManager.getLogger(Log4J2Demo.class);

    public static void main(String[] args) {
        log.trace("Trace message!");
        log.debug("Debug message");
        log.info("Info message");
        log.info("Warning message");
        log.error("error message");
        log.fatal("fatal message");
    }
}
