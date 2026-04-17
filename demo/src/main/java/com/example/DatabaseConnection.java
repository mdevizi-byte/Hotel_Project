package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/DB_hotel";
    private static final String PLACEHOLDER_USER = "sez";
    private static final String PLACEHOLDER_PASSWORD = "def";

    private static final Properties DB_PROPS = loadProperties();

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.out.println("Impossibile leggere db.properties, uso variabili ambiente/default.");
        }

        return properties;
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return "";
    }

    public static Connection getConnection() throws SQLException {
        String url = firstNonBlank(System.getenv("HOTEL_DB_URL"), DB_PROPS.getProperty("db.url"), DEFAULT_URL);
        String user = firstNonBlank(System.getenv("HOTEL_DB_USER"), DB_PROPS.getProperty("db.user"), PLACEHOLDER_USER);
        String password = firstNonBlank(System.getenv("HOTEL_DB_PASSWORD"), DB_PROPS.getProperty("db.password"),
                PLACEHOLDER_PASSWORD);

        if (PLACEHOLDER_USER.equals(user) || PLACEHOLDER_PASSWORD.equals(password)) {
            throw new SQLException(
                    "Credenziali DB non configurate. Imposta HOTEL_DB_USER/HOTEL_DB_PASSWORD oppure aggiorna src/main/resources/db.properties.");
        }

        return DriverManager.getConnection(url, user, password);
    }
}