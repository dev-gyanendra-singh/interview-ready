package LLD.Logger;

public class LoggerChainBuilder {

    public static Logger buildLoggerChain() {
        Logger errorLogger = new ErrorLogger();
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();

        errorLogger.setNext(debugLogger);
        debugLogger.setNext(infoLogger);

        return errorLogger; // Chain starts with highest severity
    }

    public static void main(String[] args) {
        Logger loggerChain = buildLoggerChain();

        loggerChain.log(LogLevel.INFO, "This is an info message.");
        loggerChain.log(LogLevel.DEBUG, "This is a debug message.");
        loggerChain.log(LogLevel.ERROR, "This is an error message.");
    }
}

