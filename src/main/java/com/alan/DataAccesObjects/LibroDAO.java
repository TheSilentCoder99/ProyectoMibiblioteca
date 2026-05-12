package com.alan.DataAccesObjects;

import com.alan.clases.conexionDB;
import com.alan.clases.Libro;
import com.alan.controladores.pantallaPrincipalController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA LIBRO
public class LibroDAO {


    //    MÉTOoDO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS LIBROS
    public List<Libro> getAllLibros() {

//        LA CONSULTA A EJECUTAR
        String consultaDevolverLibros = "SELECT * FROM libro ORDER BY title";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉToODO PORQUE CADA CONSULTA ES INDEPENDIENTE.
//        TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List<Libro> listaLibros = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {

//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverLibros);

//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverLibros);

//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO (UNA FILA) DEVUELVA NULL
            while (rs.next()) {
                Libro libro = new Libro(rs.getInt("id"), rs.getString("title"), rs.getInt("year_publicacion"), rs.getInt("pages"), rs.getString("description"), rs.getString("opinion"), rs.getInt("cover_id"));

                listaLibros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }

    public int insertarLibro(String titulo, int yearPublicacion, int paginas, String descripcion, String opinion) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO libro (title, year_publicacion, pages, description, opinion) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

//            ASIGNAS VALORES A LA FILA QUE VAS A INSERTAR
            consulta.setString(1, titulo);
            consulta.setInt(2, yearPublicacion);
            consulta.setInt(3, paginas);
            consulta.setString(4, descripcion);
            consulta.setString(5, opinion);
            consulta.execute();

//            ESTO DEVUELVE EL ID DEL LIBRO QUE SE ACABA DE INGRESAR, ES LO QUE USAS EN EL CONTROLADOR DE VENTANA ADD LIBRO
//            PARA ACTUALIZAR LA TABLA DE AUTOR Y LIBRO
            ResultSet rs = consulta.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // devuelve
            } else {
                return -1;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarLibro(int idLibro) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM libro WHERE id = ?"
            );

//            ASIGNAS VALORES DE LA FILA QUE VAS A BORRAR
            consulta.setInt(1, idLibro);

            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ActualizarLibro(int idLibro, String titulo, int paginas, int yearPublicacion, String description, String opinion) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("UPDATE libro SET title = ?, pages = ?, year_publicacion = ?, description = ?, opinion = ? WHERE id = ?"
            );

            // ASIGNAS VALORES DE LA FILA QUE VAS A ACTUALIZAR
            consulta.setString(1, titulo);
            consulta.setInt(2, paginas);
            consulta.setInt(3, yearPublicacion);
            consulta.setString(4, description);
            consulta.setString(5, opinion);
            consulta.setInt(6, idLibro);

            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> librosAnterioresSXII() {
        List<Libro> listaLibrosAnterioresSXII = new ArrayList<>();
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM libros_anteriores_SigloXXI"
            );

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Libro libroVista = new Libro();

                libroVista.setTitulo(rs.getString("title"));
                libroVista.setYearPublicacion(rs.getInt("year_publicacion"));
                libroVista.setPaginas(rs.getInt("pages"));
                libroVista.setDescripcion(rs.getString("description"));

                listaLibrosAnterioresSXII.add(libroVista);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaLibrosAnterioresSXII;
    }


    public List<Libro> librosPosterioresSXII() {
        List<Libro> listaLibrosPosterioresSXII = new ArrayList<>();
        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT * FROM libros_posteriores_SigloXXI"
            );

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
            e.printStackTrace();
        }
        return listaLibrosPosterioresSXII;
    }

    public List<Libro> librosPorGenero(String nombreGenero){
        List<Libro> listaLibrosPorGenero = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("SELECT libro.id, libro.title, libro.year_publicacion, libro.pages\n" +
                    "FROM libro\n" +
                    "INNER JOIN genero_libro ON libro.id = genero_libro.libro_id\n" +
                    "INNER JOIN genero  ON genero_libro.genere_id = genero.id\n" +
                    "WHERE genero.nombre = ?\n" +
                    "ORDER BY libro.title;"
            );

            consulta.setString(1,nombreGenero);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                Libro libroEnGenero = new Libro();

                libroEnGenero.setTitulo(rs.getString("title"));
                libroEnGenero.setYearPublicacion(rs.getInt("year_publicacion"));
                libroEnGenero.setPaginas(rs.getInt("pages"));

                listaLibrosPorGenero.add(libroEnGenero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibrosPorGenero;

    }
}
