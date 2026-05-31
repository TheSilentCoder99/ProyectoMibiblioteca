package com.alan.DataAccesObjects;

import com.alan.clases.conexionDB;
import com.alan.clases.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA LIBRO
public class LibroDAO {


    //    MÉTODO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS LIBROS
    public List<Libro> getAllLibros() {

        String consultaDevolverLibros = "SELECT * FROM libro ORDER BY title";

        List<Libro> listaLibros = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {

            PreparedStatement consulta = conn.prepareStatement(consultaDevolverLibros);

            ResultSet rs = consulta.executeQuery(consultaDevolverLibros);

            while (rs.next()) {
                Libro libro = new Libro(rs.getInt("id"), rs.getString("title"), rs.getInt("year_publicacion"), rs.getInt("pages"), rs.getString("description"), rs.getString("opinion"), rs.getInt("cover_id"));

                listaLibros.add(libro);
            }

        }catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
        return listaLibros;
    }

    public int insertarLibro(String titulo, int yearPublicacion, int paginas, String descripcion, String opinion) {
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO libro (title, year_publicacion, pages, description, opinion) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            consulta.setString(1, titulo);
            consulta.setInt(2, yearPublicacion);
            consulta.setInt(3, paginas);
            consulta.setString(4, descripcion);
            consulta.setString(5, opinion);

//            Ejecuto la consulta de forma normal para insertar el libro
            consulta.executeUpdate();

//            Statement.RETURN_GENERATED_KEYS => ESTO DEVUELVE EL ID DEL LIBRO QUE SE ACABA DE INGRESAR. LO NECESITO EN EL CONTROLADOR DE VENTANA ADD LIBRO PARA ACTUALIZAR LA TABLA DE AUTOR Y LIBRO.
            ResultSet rs = consulta.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }

        } catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
        return -1;
    }

    public void borrarLibro(int idLibro) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM libro WHERE id = ?");

            consulta.setInt(1, idLibro);

            consulta.executeUpdate();

        } catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public boolean ActualizarLibro(int idLibro, String titulo, int paginas, int yearPublicacion, String description, String opinion) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("UPDATE libro SET title = ?, pages = ?, year_publicacion = ?, description = ?, opinion = ? WHERE id = ?");

            consulta.setString(1, titulo);
            consulta.setInt(2, paginas);
            consulta.setInt(3, yearPublicacion);
            consulta.setString(4, description);
            consulta.setString(5, opinion);
            consulta.setInt(6, idLibro);

            return consulta.executeUpdate() > 0;

        }catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }

        return false;
    }

//    VISTAS QUE SE MUESTRAN EN LA OPCIÓN DE VISTAS RESUMEN:

    public List<Libro> librosAnterioresSXII() {
        List<Libro> listaLibrosAnterioresSXII = new ArrayList<>();
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM libros_anteriores_SigloXXI");

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {

                Libro libroVista = new Libro();

                libroVista.setTitulo(rs.getString("title"));
                libroVista.setYearPublicacion(rs.getInt("year_publicacion"));
                libroVista.setPaginas(rs.getInt("pages"));
                libroVista.setDescripcion(rs.getString("description"));

                listaLibrosAnterioresSXII.add(libroVista);
            }

        }catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }

        return listaLibrosAnterioresSXII;
    }


    public List<Libro> librosPosterioresSXII() {
        List<Libro> listaLibrosPosterioresSXII = new ArrayList<>();
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM libros_posteriores_SigloXXI");

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Libro libroVista = new Libro();

                libroVista.setTitulo(rs.getString("title"));
                libroVista.setYearPublicacion(rs.getInt("year_publicacion"));
                libroVista.setPaginas(rs.getInt("pages"));
                libroVista.setDescripcion(rs.getString("description"));

                listaLibrosPosterioresSXII.add(libroVista);
            }

        } catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
        return listaLibrosPosterioresSXII;
    }


    public List<Libro> librosPorGenero(String nombreGenero) {
        List<Libro> listaLibrosPorGenero = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT libro.id, libro.title, libro.year_publicacion, libro.pages\n" + "FROM libro\n" + "INNER JOIN genero_libro ON libro.id = genero_libro.libro_id\n" + "INNER JOIN genero  ON genero_libro.genere_id = genero.id\n" + "WHERE genero.nombre = ?\n" + "ORDER BY libro.title;");

            consulta.setString(1, nombreGenero);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Libro libroEnGenero = new Libro();

                libroEnGenero.setTitulo(rs.getString("title"));
                libroEnGenero.setYearPublicacion(rs.getInt("year_publicacion"));
                libroEnGenero.setPaginas(rs.getInt("pages"));

                listaLibrosPorGenero.add(libroEnGenero);
            }

        } catch (SQLException e) {
            String estado = e.getSQLState();
            if (estado == null) {
                System.err.println("[LibroDAO] Error desconocido: " + e.getMessage());
            } else if (estado.startsWith("08")) {
                // 08xxx = errores de conexión (servidor caído, timeout, puerto incorrecto...)
                System.err.println("[LibroDAO] No se pudo conectar a la base de datos. Verifica que el servidor esté activo.");
            } else if (estado.startsWith("23")) {
                // 23xxx = violación de restricción (clave foránea, NOT NULL, duplicado...)
                System.err.println("[LibroDAO] Operación rechazada por la base de datos: " + e.getMessage());
            } else if (estado.startsWith("42")) {
                // 42xxx = tabla o columna no existe, error en la query
                System.err.println("[LibroDAO] Error en la consulta SQL: " + e.getMessage());
            } else {
                System.err.println("[LibroDAO] Error SQL (" + estado + "): " + e.getMessage());
            }
            e.printStackTrace();
        }
        return listaLibrosPorGenero;

    }
}
