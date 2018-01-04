package com.gabrielspassos.poc.infra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropretiesReader {
    InputStream inputStream;
    String port = "";

    public String getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value and print it out
            port = prop.getProperty("server.port");
        } catch (Exception e) {

        } finally {
            inputStream.close();
        }

        return port;
    }
}
