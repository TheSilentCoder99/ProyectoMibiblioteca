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
            e.printStackTrace();
        }
        return lista;
    }

}
