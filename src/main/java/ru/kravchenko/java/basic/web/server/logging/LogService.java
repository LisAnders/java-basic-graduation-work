package ru.kravchenko.java.basic.web.server.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogService {
    private static final Logger logger = LogManager.getLogger(LogService.class.getName());

    public static void info(String info) {
        logger.info(info);
    }

    public static void error(String error) {
        logger.error(error);
    }

    public static void debug(String debug) {
        logger.debug(debug);
    }
}
