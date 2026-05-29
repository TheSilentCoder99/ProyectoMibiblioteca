package com.alan.DataAccesObjects;

import com.alan.clases.clasesCompuestas.autorMasLeido;
import com.alan.clases.conexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class autoresMasLeidosDAO {


    public List<autorMasLeido> getAutoresMasLeidos() {

        List<autorMasLeido> listaAutoresMasLeidos = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
            // autores_mas_leidos es una vista ya creada directamente en la BD
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM autores_mas_leidos"
            );

            ResultSet rs = consulta.executeQuery();
            while (rs.next()) {

                autorMasLeido autorDeLaLista = new autorMasLeido();

                autorDeLaLista.setId(rs.getInt("id"));
                autorDeLaLista.setNombre(rs.getString("nombre"));
                autorDeLaLista.setApellido1(rs.getString("apellido1"));
                autorDeLaLista.setApellido2(rs.getString("apellido2"));
                autorDeLaLista.setLibros(rs.getInt("libros"));

                listaAutoresMasLeidos.add(autorDeLaLista);
            }

        } catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[AutorDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[AutorDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[AutorDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[AutorDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[AutorDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
        return listaAutoresMasLeidos;
    }
}
