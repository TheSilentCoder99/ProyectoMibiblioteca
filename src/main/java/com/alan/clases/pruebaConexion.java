package com.alan.clases;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.PaisDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class pruebaConexion {
    public static void main(String[] args) {
        try (Connection conn = conexionDB.getConnection()) {
            System.out.println("✅ Conexión exitosa!");
            //        EJEMPLO DE USO
//            LibroDAO librodao = new LibroDAO();
//            AutorDAO autordao = new AutorDAO();
//            GeneroDAO generodao = new GeneroDAO();
            PaisDAO paisdao = new PaisDAO();

            List <Pais> listaPaises = paisdao.getAllPaises();


            for (Pais p : listaPaises) {
                System.out.println(p.getNombre() + " | " + p.getCodigoISO());
            }

            } catch(SQLException e){
                System.out.println("❌ Error: " + e.getMessage());
            }


        }
    }