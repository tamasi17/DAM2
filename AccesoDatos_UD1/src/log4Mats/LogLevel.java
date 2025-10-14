package log4Mats;

public enum LogLevel {

    // Enums por prioridades:
    TRACE(0),
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5);

    private int priority;

    LogLevel(int priority){
        this.priority=priority;
    }

    public int getPriority() {
        return priority;
    }

    // Confirma si el mensaje se tiene que guardar en los logs o no
    public boolean isLoggable(LogLevel configLevel){
        return this.priority >= configLevel.priority;
    }

    public static LogLevel fromString(String level) {
        return LogLevel.valueOf(level.toUpperCase());
    }
}
