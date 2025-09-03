package LLD.DesignPatterns.Creational.Sigleton;


public class AppConfig {
    private static volatile AppConfig instance;
    private String environment;

    private AppConfig() {
        // Simulate loading config
        this.environment = "PRODUCTION";
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }

    public String getEnvironment() {
        return environment;
    }
}

