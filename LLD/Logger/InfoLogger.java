package LLD.Logger;

public class InfoLogger extends Logger {
    public InfoLogger() {
        super(LogLevel.INFO);
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}
