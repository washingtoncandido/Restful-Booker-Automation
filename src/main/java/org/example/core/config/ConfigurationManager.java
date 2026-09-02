package org.example.core.config;

public final class ConfigurationManager {

    private static final Environment ENVIRONMENT =
            Environment.valueOf(
                    System.getProperty("env", "prd").toUpperCase()
            );

    private static final Configuration CONFIGURATION =
            new Configuration(ENVIRONMENT);

    private ConfigurationManager() {
    }

    public static String get(String key) {
        return CONFIGURATION.get(key);
    }

    public static Environment getEnvironment() {
        return ENVIRONMENT;
    }
}