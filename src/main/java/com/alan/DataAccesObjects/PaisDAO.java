package com.alan.DataAccesObjects;

import com.alan.clases.Pais;
import com.alan.clases.conexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//ESTA CLASE SOLAMENTE SE VA A OCUPAR DE MOSTRAR LOS PAÍSES. ¿TIENE SENTIDO BORRAR O INSERTAR EN UNA TABLA QUE YA LOS CONTIENE TODOS? ES COMO UN CATÁLOGO.
public class PaisDAO {

    public List<Pais> getAllPaises() {
//        LA CONSULTA A EJECUTAR
        String consultaDevolverPaises = "SELECT * FROM pais ORDER BY nombre";

        List<Pais> listaPaises = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {

            PreparedStatement consulta = conn.prepareStatement(consultaDevolverPaises);

            ResultSet rs = consulta.executeQuery(consultaDevolverPaises);

            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("id"), rs.getString("nombre"), rs.getString("codigo_ISO"));
                listaPaises.add(pais);
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
        return listaPaises;
    }

    public Pais buscarPais(int idPais) {
        Pais paisBuscado = new Pais();
        String consultaDevolverPais = "SELECT * FROM pais WHERE id = ?";

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverPais);
            consulta.setInt(1, idPais);

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                paisBuscado.setId(rs.getInt("id"));
                paisBuscado.setNombrePais(rs.getString("nombre"));
                paisBuscado.setCodigo_ISO(rs.getString("codigo_ISO"));
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
        return paisBuscado;
    }

    public void borrarPais(int idPais) {
        try (Connection conn = conexionDB.getConnection()) {

            PreparedStatement consulta = conn.prepareStatement("DELETE FROM pais WHERE id = ?");
            consulta.setInt(1, idPais);
            consulta.executeUpdate();

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
    }


}
