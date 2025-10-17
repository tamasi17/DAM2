package log4Mats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private static LogLevel configLevel;  // configLevel = LogLevel.TRACE; initialize only for testing
    private String logPath;
    private File log;
    private long maxSize;

    // Date/time format needs to be Windows-friendly.
    // Program runs really fast, had to add milliseconds to the log naming.
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS");


    /**
     * Logger constructor will be called from getInstance() to respect Singleton.
     *
     * @param config
     */
    private Logger(Document config) {
        Element e = config.getDocumentElement(); // xml root
        this.configLevel = LogLevel.valueOf(e.getAttribute("status"));
        // getElementsByName() devuelve NodeList, item() devuelve Node, cast a Element para poder usarlo.
        e = (Element) config.getElementsByTagName("MaxFileSize").item(0);
        this.maxSize = Long.parseLong(e.getTextContent());
        e = (Element) config.getElementsByTagName("FilePath").item(0);
        this.logPath = e.getTextContent();
        e = (Element) config.getElementsByTagName("FileName").item(0);
        this.logPath += e.getTextContent();
        this.log = new File(this.logPath);
    }

    /**
     * Singleton method that returns an instance only if it does not exist previously.
     * It is called from LogManager's getLogger(File xml).
     *
     * @param configXml
     * @return
     */
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

        confirmLogExists();
        rotateLogIfNeeded(log);

        String time = LocalDateTime.now().format(FORMATTER);
        System.out.println(time + "[" + level + "]: " + source + " - " + message); // to check

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))) {
            bw.write(time + " [" + level + "] " + source + ": " + message);
        } catch (IOException ioe) {
            System.err.println("Error in I/O while logging");
        }
    }

    void rotateLogIfNeeded(File oldLog) {

        long size = log.length();
        if (size<maxSize) return;

        String time = LocalDateTime.now().format(FORMATTER);
        String newLogName = log.getName().replace(".log","_"+time+".log");

        System.out.println("\no--------------------o\n" +
                "Log has reached maximum size.\n" +
                "Creating a new log: " + newLogName);

        try{

        // Files asks for a Path
        Path source = log.toPath();
        // move( Path source, Path target, options...)
        // resolveSibling creates a new path in the same directory but with a new name
        Files.move(source,source.resolveSibling(newLogName));

        Files.createFile(source); // new and empty file, returns Path

        } catch (IOException ioe){
            System.err.println("Log file was not rotated correctly");
        }

        // ---------------   Compressing log? --> check GZIPOutputStream;

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

    private void confirmLogExists() {
        if (!this.log.exists()) {
            try {
                File parent = log.getParentFile();
                // if log has a parent directory but the directory does not exist
                if (parent != null && !parent.exists()) {
                    if (!parent.mkdirs()) {
                        throw new IOException("Failed to create directories: " + parent.getAbsolutePath());
                    }
                }
                if (this.log.createNewFile()) {
                    System.out.println("Log file created: " + log.getAbsolutePath());
                } else {
                    System.out.println("Log file exists. Writing on: " + log.getAbsolutePath());
                }
            } catch (IOException ioe) {
                System.err.println("Log file was not created: " + ioe.getLocalizedMessage());
            }
        }
    }

}

