package com.bl.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final ApplicationConfig INSTANCE = new ApplicationConfig();
    private final Properties properties = new Properties();

    private ApplicationConfig() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            } else {
                System.err.println("application.properties file not found in classpath. Using defaults.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load application configuration", e);
        }
    }

    public static ApplicationConfig getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public int getIntProperty(String key, int defaultValue) {
        String val = properties.getProperty(key);
        return val != null ? Integer.parseInt(val) : defaultValue;
    }

    public long getLongProperty(String key, long defaultValue) {
        String val = properties.getProperty(key);
        return val != null ? Long.parseLong(val) : defaultValue;
    }
}