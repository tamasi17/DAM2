package log4Mats;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.print.Doc;
import java.io.*;
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
    private File log;
    private long maxSize=20;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");

    /**
     * Logger constructor will be called from getInstance() to respect Singleton.
     * @param config
     */
    private Logger(Document config) {
        Element e = config.getDocumentElement(); // xml root
        this.configLevel = LogLevel.valueOf(e.getAttribute("status"));
        e = config.getElementById("MaxFileSize");
        this.maxSize = Long.parseLong(e.getTextContent());
        e = config.getElementById("FilePath");
        String path = e.getTextContent();
        e = config.getElementById("FileName");
        String name = e.getTextContent();
        this.log = new File(path + name);
    }

    /**
     * Singleton method that returns an instance only if it does not exist previously.
     * It is called from LogManager's getLogger(File xml).
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
        String time = LocalDateTime.now().format(FORMATTER);
        System.out.println(time + "["+level+"]: " + source + " - " + message);
        File log = new File("logs\\errors.log");
        // if it doesnt exist mkdirs()
        if (log.length()>=maxSize){
            rotateLog(log);
        }
        // if (logFile has exceeded max size){ rotateLog();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log))){
            bw.write(time + " [" + level + "] " + source + ": " + message);
        } catch (IOException ioe){
            System.err.println("Error in I/O while logging");
        }
    }

    void rotateLog(File oldLog){
        String time = LocalDateTime.now().format(FORMATTER);
        String text="";
        File aux = new File("logs\\musicShop" + time +".log");

        // Reading old log
        try(BufferedReader br = new BufferedReader(new FileReader(oldLog))){
            String line;
            while ((line=br.readLine()) != null){
                text += br.readLine();
            }
        } catch (FileNotFoundException fnfe){
            System.err.println("File not found");
        } catch (IOException ioe){
            System.err.println("I/O error");
        }

        // Writing old log in a stored file

        // Compressing log?

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

