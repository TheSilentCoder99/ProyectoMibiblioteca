package com.alan.clases;

import com.alan.DataAccesObjects.libroDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class pruebaConexion {
    public static void main(String[] args) {
        try (Connection conn = conexionDB.getConnection()) {
            System.out.println("✅ Conexión exitosa!");
            //        EJEMPLO DE USO
            libroDAO librodao = new libroDAO();
            List<libro> listaLibros = librodao.getAllLibros();

            for (libro libro : listaLibros) {
                System.out.println(libro.getTitulo());
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }


    }
}