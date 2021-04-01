package com.todoist.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;
    private static Logger logger;

    /**
     * This method is used to load the properties from config.properties file
     *
     * @return it returns Properties prop object
     */
    public static void init_prop() {
        String propertiesFileName = "config.properties";

        // To initiate the prop
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config/" + propertiesFileName)) {
            logger = LogManager.getLogger();
            properties = new Properties();
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.debug("File Not Found" + e.getMessage());
        } catch (IOException e) {
            logger.debug("IO exception" + e.getMessage());
        }

    }

    public static String readValueFromPropertyFile(String key) {
        if(properties == null) init_prop();
        return properties.getProperty(key).trim();
    }

    public static Properties getProperties() {
        return properties;
    }
}
