package com.alan.DataAccesObjects;
import com.alan.clases.Autor;
import com.alan.clases.conexionDB;
import com.alan.controladores.pantallaPrincipalController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA AUTOR
public class AutorDAO {

    //    MÉTOoDO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS AUTORES
    public List<Autor> getAllAutores() {
//        LA CONSULTA A EJECUTAR
        String consultaDevolverAutores = "SELECT\n" +
                "\tid,\n" +
                "\tnombre,\n" +
                "\tapellido1,\n" +
                "\tapellido2,\n" +
                "\tpais_id,\n" +
                "\tyear_nacimiento,\n" +
                "\tIFNULL(year_fallecimiento,'Vivo') AS fallecido\n" +
                "FROM\n" +
                "\tautor\n" +
                "ORDER BY\n" +
                "\tnombre,\n" +
                "\tapellido1;";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉToODO PORQUE CADA CONSULTA ES INDEPENDIENTE.
//        TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List <Autor> listaAutores = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {

//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverAutores);

//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverAutores);

//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO (UNA FILA) DEVUELVA NULL
            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido1"), rs.getString("apellido2"),rs.getInt("pais_id"),rs.getInt("year_nacimiento"),rs.getString("fallecido") );

                listaAutores.add(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAutores;
    }

    public void insertarAutor(String nombre,String apellido1,String apellido2, String paisNombre, int yearNacimiento, int yearMuerte) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO autor (nombre,apellido1,apellido2, pais_id ,year_nacimiento,year_fallecimiento) VALUES (?,?,?,(SELECT id FROM pais WHERE nombrePais = (?)),?,?)"
            );

//            ASIGNAS VALORES A LA FILA QUE VAS A INSERTAR
            consulta.setString(1, nombre);
            consulta.setString(2, apellido1);
            consulta.setString(3, apellido2);
            consulta.setString(4, paisNombre);
            consulta.setInt(5, yearNacimiento);
            consulta.setInt(6, yearMuerte);

            consulta.executeUpdate();;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarAutor(int idAutor) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM autor WHERE id = ?"
            );

//            ASIGNAS VALORES DE LA FILA QUE VAS A BORRAR
            consulta.setInt(1,idAutor);

            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ActualizarAutor(int idAutor, String nombre, String apellido1, String apellido2, int nacimiento, String muerte, int idPais) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("UPDATE autor SET nombre = ?, apellido1 = ?, apellido2 = ?, year_nacimiento = ?, year_fallecimiento = ?, pais_id = ? WHERE id = ?"
            );

            // ASIGNAS VALORES DE LA FILA QUE VAS A ACTUALIZAR
            consulta.setString(1,nombre);
            consulta.setString(2,apellido1);
            consulta.setString(3,apellido2);
            consulta.setInt(4,nacimiento);

            // Si muerte está vacío, guardamos NULL en la BD PARA QUE DESPÚES, AL SELECCIONARLO, SE APLIQUE EL IF NULL DE LOS SELECT Y EN LA TABLA SE MUESTRE "VIVO"
            if (muerte == null || muerte.isBlank() || muerte.matches("[a-zA-Z]+")) {
                consulta.setNull(5, java.sql.Types.INTEGER);
            } else {
                consulta.setInt(5, Integer.parseInt(muerte));
            }

            consulta.setInt(6, idPais);

            consulta.setInt(7, idAutor);
            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
