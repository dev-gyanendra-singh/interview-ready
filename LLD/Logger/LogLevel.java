package LLD.Logger;

public enum LogLevel {
    INFO(1),
    DEBUG(2),
    ERROR(3);

    private final int severity;

    LogLevel(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }
}

