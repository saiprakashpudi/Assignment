package com.caw.configuration;


import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

import static com.caw.configuration.Constant.LOG_CONFIG_FILE;

public class Config {

    private static Properties properties = new Properties();
    private static final Logger Log = Logger.getLogger(Config.class);

    //load properties
    public static String getProperty(String key) {
        try {
            InputStream stream = new FileInputStream(new File(LOG_CONFIG_FILE));
            properties.load(stream);
            stream.close();
        } catch (FileNotFoundException e) {
            Log.error("File Not Found: "+e.getMessage());
        } catch (IOException e) {
            Log.error("IO Exception: ", e);
        }
        return properties.getProperty(key);
    }
}
