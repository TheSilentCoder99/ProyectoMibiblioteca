package com.alan.DataAccesObjects;

import com.alan.clases.AutorLibro;
import com.alan.clases.conexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorLibroDAO {

    public List<AutorLibro> getLibrosPorAutor(int idAutor) {
        List<AutorLibro> librosDelAutor = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement(
                    "SELECT libro.title, libro.pages, libro.year_publicacion, autor.id, autor.nombre, autor.apellido1, pais.nombre\n" +
                            "FROM libro\n" +
                            "INNER JOIN autor_libro\n" +
                            "ON autor_libro.libro_id = libro.id\n" +
                            "INNER JOIN autor\n" +
                            "ON autor_libro.autor_id = autor.id\n" +
                            "INNER JOIN pais\n" +
                            "ON autor.pais_id = pais.id\n" +
                            "WHERE autor.id = ?\n" +
                            "ORDER BY autor.nombre, autor.apellido1 ASC;"
            );
            consulta.setInt(1, idAutor);
            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                librosDelAutor.add(new AutorLibro(rs.getInt("id"), rs.getString("title"), rs.getInt("pages"), rs.getInt("year_publicacion"),
                        rs.getString("nombre"), rs.getString("apellido1"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librosDelAutor;
    }

    public void actualizarTablaLibroAutor(int idLibro, List<Integer> idAutor) {
        for(int i = 0; i<idAutor.size();i++){

            try (Connection conn = conexionDB.getConnection()) {
                PreparedStatement consulta = conn.prepareStatement(
                        "INSERT INTO autor_libro VALUES (?, ?);"
                );
                consulta.setInt(1,idLibro);
                consulta.setInt(2,idAutor.get(i));
                consulta.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }


}
