package LLD.Logger;

public abstract class Logger {
    protected LogLevel level;
    protected Logger nextLogger;

    public Logger(LogLevel level) {
        this.level = level;
    }

    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(LogLevel requestLevel, String message) {
        if (requestLevel.getSeverity() >= level.getSeverity()) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.log(requestLevel, message);
        }
    }

    protected abstract void write(String message);
}

