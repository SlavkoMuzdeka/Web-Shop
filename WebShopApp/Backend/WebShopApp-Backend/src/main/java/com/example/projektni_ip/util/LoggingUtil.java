package com.example.projektni_ip.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is used for logging exceptions
 */
public class LoggingUtil {

    /**
     * Writes the entire stacktrace exception into a single string
     * We use this because it is very convenient when viewing logs later
     *
     * @param e
     * @param log
     */
    public static void logException(Throwable e, Log log) {
        StringBuilder builder = new StringBuilder();
        builder.append(e);
        builder.append(System.lineSeparator());
        for (StackTraceElement element : e.getStackTrace()) {
            builder.append(element);
            builder.append(System.lineSeparator());
        }
        log.error(builder);
    }

    public static <T> void logException(Throwable e, Class<T> clazz) {
        logException(e, LogFactory.getLog(clazz));
    }
}
