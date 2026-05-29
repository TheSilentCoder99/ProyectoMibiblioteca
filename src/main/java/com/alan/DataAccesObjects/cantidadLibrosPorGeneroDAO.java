package com.alan.DataAccesObjects;

import com.alan.clases.clasesCompuestas.cantidadLibrosPorGenero;
import com.alan.clases.conexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cantidadLibrosPorGeneroDAO {

    public List<cantidadLibrosPorGenero> getCantidadLibrosPorGenero() {
        List<cantidadLibrosPorGenero> lista = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement(
//                    cantidadLibros_por_Genero es una vista ya creada directamente en la BD
                    "SELECT * FROM cantidadLibros_por_Genero"
            );

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                lista.add(new cantidadLibrosPorGenero(
                        rs.getString("genero"),
                        rs.getInt("Cantidad_de_Libros")
                ));
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
        return lista;
    }

}
