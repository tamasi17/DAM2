package log4Mats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que define un logger por nivel de prioridad de errores:
 * Trace, Debug, Info, Warn, Error. (de menor a mayor prioridad)
 * <p>
 * Los logs se guardan en .../logs/nombreTienda.log y cuando se llenan se rota fichero.
 */


public class Logger {

    // Singleton from refactoring.guru:
    // This field must be declared volatile so that double check lock would work correctly.
    private static volatile Logger instance;

    private Document configXml;
    private static LogLevel configLevel = LogLevel.TRACE; //initialize only for testing

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");

    private Logger(Document config) {
        Element e = config.getDocumentElement(); // xml root
        configLevel = LogLevel.valueOf(e.getAttribute("status"));
    }

    public static Logger getInstance(Document configXml) {
        // Double-checked locking (DCL). Exists to prevent race condition between
        // multiple threads that may attempt to get singleton instance at the same time,
        // creating separate instances as a result.

        Logger result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Logger.class) {
            if (instance == null) {
                instance = new Logger(configXml);
            }
            return instance;
        }
    }

    void log(LogLevel level, String source, String message) {
        String time = LocalDateTime.now().format(FORMATTER);
        System.out.println(time + "["+level+"]: " + source + " - " + message);
        // if (logFile has exceeded max size){ rotateLog();
        // Add to log file with pattern format here
    }


    public void trace(String source, String message) {
        System.out.println("Tracing...");
        if (LogLevel.TRACE.isLoggable(configLevel)) {
            log(LogLevel.TRACE, source, message);
            System.out.println("Trace logged: " + "traceID here");
        }
    }

    public void debug(String source, String message) {
        System.out.println("Debugging...");
        if (LogLevel.DEBUG.isLoggable(configLevel)) {
            log(LogLevel.DEBUG, source, message);
            System.out.println("Debug logged: " + "traceID here");
        }
    }

    public void info(String source, String message) {
        System.out.println("Retrieving info...");
        if (LogLevel.INFO.isLoggable(configLevel)) {
            log(LogLevel.INFO, source, message);
            System.out.println("Info logged: " + "ID here");
        }
    }

    public void warn(String source, String message) {
        System.out.println("Retrieving warnings...");
        if (LogLevel.WARN.isLoggable(configLevel)) {
            log(LogLevel.WARN, source, message);
            System.out.println("Warning logged: " + "ID here");
        }
    }

    public void error(String source, String message) {
        System.out.println("Retrieving info...");
        if (LogLevel.ERROR.isLoggable(configLevel)) {
            log(LogLevel.ERROR, source, message);
            System.out.println("Error logged: " + "ID here");
        }
    }

    public void fatal(String source, String message) {
        System.out.println("Program is crashing lol...");
        if (LogLevel.FATAL.isLoggable(configLevel)) {
            log(LogLevel.FATAL, source, message);
            System.out.println("Fatality logged: " + "ID here");
        }
    }

    public void getLogLevel() {
        System.out.println(configLevel);
    }

}

