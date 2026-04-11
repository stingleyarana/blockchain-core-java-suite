package org.blockchain.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader(String configFile) {
        this.properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (Exception e) {
            loadDefaultConfig();
        }
    }

    private void loadDefaultConfig() {
        properties.setProperty("node.port", "6000");
        properties.setProperty("mining.difficulty", "4");
        properties.setProperty("block.time.target", "10000");
        properties.setProperty("api.port", "8080");
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }
}
