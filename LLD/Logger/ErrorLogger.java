package LLD.Logger;

public class ErrorLogger extends Logger {
    public ErrorLogger() {
        super(LogLevel.ERROR);
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}

