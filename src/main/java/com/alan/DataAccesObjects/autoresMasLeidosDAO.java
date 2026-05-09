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


    public List<autorMasLeido> getAutoresMasLeidos(){

        List<autorMasLeido> listaAutoresMasLeidos = new ArrayList<>();

        try (Connection conn = conexionDB.getConnection()) {
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
            e.printStackTrace();
        }
        return listaAutoresMasLeidos;
    }
}
