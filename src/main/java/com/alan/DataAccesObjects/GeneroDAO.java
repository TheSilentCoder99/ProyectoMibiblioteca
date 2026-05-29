package com.alan.DataAccesObjects;

import com.alan.clases.Genero;
import com.alan.clases.conexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA AUTOR
public class GeneroDAO {

    //    MÉTODO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS AUTORES.
    public List<Genero> getAllGeneros() {

        String consultaDevolverGeneros = "SELECT * FROM genero ORDER BY nombre";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉTODO PORQUE CADA CONSULTA ES INDEPENDIENTE. TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List<Genero> listaGeneros = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverGeneros);

//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverGeneros);

//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO DEVUELVA NULL
            while (rs.next()) {
                Genero genero = new Genero(rs.getInt("id"), rs.getString("nombre"));
                listaGeneros.add(genero);
            }

        }catch (SQLException e) {
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
        return listaGeneros;
    }

    public void insertarGenero(String nombre) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO genero (nombre) VALUES (?)"
            );

            consulta.setString(1, nombre);

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

    public void borrarGenero(int idGenero) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM genero WHERE id = ?"
            );

            consulta.setInt(1, idGenero);

            consulta.executeUpdate();

        }catch (SQLException e) {
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

    //    PARA EL MÉTODO QUE ACTUALIZA (O CREA) EL GÉNERO DEL LIBRO, DEBES TOMAR EL ID DEL LIBRO QUE ESTÁS CREANDO JUNTO CON EL ID DE LOS GÉNEROS SELECCIONADOS DE LA TABLA Y HACER EL INSERT EN BASE A ESOS PARÁMETROS. MUY SIMILAR A CÓMO SE HACE CON LOS AUTORES
    public void actualizarTablaGeneroLibro(int idLibro, List<Integer> idGenero) {
        for (int i = 0; i < idGenero.size(); i++) {

            try (Connection conn = conexionDB.getConnection()) {
                PreparedStatement consulta = conn.prepareStatement(
                        "INSERT INTO genero_libro VALUES (?, ?);"
                );
                consulta.setInt(1, idLibro);
                consulta.setInt(2, idGenero.get(i));
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


}
