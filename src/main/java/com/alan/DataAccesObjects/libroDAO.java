package com.alan.DataAccesObjects;

import com.alan.clases.conexionDB;
import com.alan.clases.libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA LIBRO
public class libroDAO {

//    MÉTOoDO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS LIBROS
    public List<libro> getAllLibros() {

//        LA CONSULTA A EJECUTAR
        String consultaDevolverLibros = "SELECT * FROM libro";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉToODO PORQUE CADA CONSULTA ES INDEPENDIENTE.
//        TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List<libro> listaLibros = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {

//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverLibros);

//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverLibros);

//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO (UNA FILA) DEVUELVA NULL
            while (rs.next()) {
                libro libro = new libro(rs.getInt("id"), rs.getString("title"), rs.getInt("year_publicacion"), rs.getInt("pages"),rs.getString("description"), rs.getString("opinion"));

                listaLibros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }

    public void insertarLibro(){
        try(Connection conn = conexionDB.getConnection()){
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO libro VALUES (?,?,?,?,?,?)");

//            ASIGNAS VALORES A LA FILA QUE VAS A INSERTAR
            consulta.setInt(1, 1);



    }catch (SQLException e){
        e.printStackTrace();}
    }



}
