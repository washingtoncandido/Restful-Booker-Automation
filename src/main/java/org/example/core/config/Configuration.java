package org.example.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private final Properties properties;

    public Configuration(Environment environment) {
        this.properties = load(environment);
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    private Properties load(Environment environment) {

        String fileName = "application-"
                + environment.name().toLowerCase()
                + ".properties";

        Properties properties = new Properties();

        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                throw new RuntimeException(
                        "Arquivo de configuração não encontrado: " + fileName
                );
            }

            properties.load(input);

            return properties;

        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao carregar o arquivo: " + fileName,
                    e
            );
        }
    }
}