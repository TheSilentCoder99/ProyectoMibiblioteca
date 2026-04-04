package com.alan.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class conexionDB {
    private static String url;
    private static String user;
    private static String password;
    private static boolean useSSL;

    // Bloque estático: se ejecuta UNA SOLA vez al cargar la clase. Es como un initialize(), CREAS ESTE BLOQUE PARA PREPRAR
//    TODA LA CONEXIÓN: EL USER, LA CONTRASEÑA, CONEXIÓN SEGURA...
    static {
        try (InputStream input = conexionDB.class
                .getClassLoader()
                .getResourceAsStream("database.properties")) {

            Properties props = new Properties();
            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            useSSL = Boolean.parseBoolean(props.getProperty("db.ssl", "false"));

            // Si no quieres SSL, modifica la URL
            if (!useSSL) {
                url = url + "?allowPublicKeyRetrieval=true&useSSL=false";
            }
        } catch (IOException e) {
            System.out.println("Error al cargar database.properties: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}