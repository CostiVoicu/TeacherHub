package com.example.teacherhub.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    static {
        try (InputStream input = DBConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find db.properties file.");
            }
            Properties properties = new Properties();
            properties.load(input);

            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
