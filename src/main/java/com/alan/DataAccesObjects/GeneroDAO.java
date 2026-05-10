package com.alan.DataAccesObjects;
import com.alan.clases.Genero;
import com.alan.clases.conexionDB;
import com.alan.controladores.pantallaPrincipalController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CLASE QUE SE OCUPA DE HACER CONSULTAS EN LA BD RELACIONADAS CON LA TABLA AUTOR
public class GeneroDAO {

    //    MÉTOoDO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS AUTORES
    public List<Genero> getAllGeneros() {
//        LA CONSULTA A EJECUTAR
        String consultaDevolverGeneros = "SELECT * FROM genero ORDER BY nombre";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉToODO PORQUE CADA CONSULTA ES INDEPENDIENTE.
//        TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List <Genero> listaGeneros = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {

//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverGeneros);

//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverGeneros);

//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO (UNA FILA) DEVUELVA NULL
            while (rs.next()) {
                Genero autor = new Genero(rs.getInt("id"),rs.getString("nombre"));
                listaGeneros.add(autor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaGeneros;
    }

    public void insertarGenero(String nombre) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("INSERT INTO genero (nombre) VALUES (?)"
            );

//            ASIGNAS VALORES A LA FILA QUE VAS A INSERTAR
            consulta.setString(1, nombre);

            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarGenero(int idGenero) {

        try (Connection conn = conexionDB.getConnection()) {
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM genero WHERE id = ?"
            );

//            ASIGNAS VALORES DE LA FILA QUE VAS A BORRAR
            consulta.setInt(1,idGenero);

            consulta.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    PARA EL MÉTHODO QUE ACTUALIZA (O CREA) EL GÉNERO DEL LIBRO, DEBES TOMAR EL ID DEL LIBRO QUE ESTÁS CREANDO JUNTO CON EL ID DE LOS GÉNEROS SELECCIONADOS DE LA TABLA Y HACER EL INSERT EN BASE A ESOS PARÁMETROS. MUY SIMILAR A CÓMO SE HACE CON LOS AUTORES
    public void actualizarTablaGeneroLibro(int idLibro, List<Integer> idGenero) {
        for(int i = 0; i<idGenero.size();i++){

            try (Connection conn = conexionDB.getConnection()) {
                PreparedStatement consulta = conn.prepareStatement(
                        "INSERT INTO genero_libro VALUES (?, ?);"
                );
                consulta.setInt(1,idLibro);
                consulta.setInt(2,idGenero.get(i));
                consulta.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }




}
