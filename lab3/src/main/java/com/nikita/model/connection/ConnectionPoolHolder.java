package com.nikita.model.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static final String PROPERTIES_FILE_NAME = "/db.properties";
    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            Path propertiesPath = null;

            try {
                propertiesPath = Paths.get(ConnectionPoolHolder.class.getResource(PROPERTIES_FILE_NAME).toURI());
            } catch (URISyntaxException err) {
                System.err.println(err);
            }

            try (FileReader reader = new FileReader(propertiesPath.toFile())) {
                Properties properties = new Properties();
                properties.load(reader);

                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl(properties.getProperty("url"));
                dataSource.setUsername(properties.getProperty("username"));
                dataSource.setPassword(properties.getProperty("password"));
                dataSource.setDriverClassName(properties.getProperty("driver.class.name"));

                ConnectionPoolHolder.dataSource = dataSource;
            } catch (Exception err) {
                System.err.println("XXX Error: DB connection failed");
                System.err.println(err);
                System.exit(-1);
            }
        }
        return dataSource;
    }
}
