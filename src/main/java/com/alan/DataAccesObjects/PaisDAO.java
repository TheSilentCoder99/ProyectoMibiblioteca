package com.alan.DataAccesObjects;
import com.alan.clases.Pais;
import com.alan.clases.conexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//ESTA CLASE SOLAMENTE SE VA A OCUPAR DE MOSTRAR LOS PAÍSES. NO TIENE SENTIDO BORRAR O INSERTAR EN UNA TABLA QUE YA LOS CONTIENE TODOS
public class PaisDAO {

    //    MÉTOoDO QUE ABRE LA CONEXION, EJECUTA LA QUERY Y ME TRAE TODOS LOS PAISES
    public List<Pais> getAllPaises() {
//        LA CONSULTA A EJECUTAR
        String consultaDevolverPaises = "SELECT * FROM pais ORDER BY nombre";

//        CREO EL ARRAYLIST, SE CREA UN ARRAY LIST NUEVO CADA VEZ QUE SE LLAMA AL MÉToODO PORQUE CADA CONSULTA ES INDEPENDIENTE.
//        TIENE SENTIDO QUE CADA UNA DEVUELVA UN ARRAYLIST DIFERENTE
        List <Pais> listaPaises = new ArrayList<>();

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {
//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverPaises);
//            El ResultSet SOLO SE GENERA CUANDO LA CONSULTA REALIZADA ES UN SELECT. TE DEVUELVE TODOS LOS VALORES QUE SE GUARDEN EN ESA TABLA PARA CADA FILA
            ResultSet rs = consulta.executeQuery(consultaDevolverPaises);
//            RECORRE LA TABLA Y OBTIENE VALORES HASTA QUE EL SIGUIENTE ESPACIO RECORRIDO (UNA FILA) DEVUELVA NULL
            while (rs.next()) {
                Pais pais = new Pais(rs.getInt("id"),rs.getString("nombre"),rs.getString("codigo_ISO"));
                listaPaises.add(pais);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPaises;
    }

    public Pais buscarPais(int idPais) {
        Pais paisBuscado = new Pais();
//        LA CONSULTA A EJECUTAR
        String consultaDevolverPais = "SELECT * FROM pais WHERE id = ?";

//        ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {
//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement(consultaDevolverPais);
            consulta.setInt(1,idPais);

            ResultSet rs = consulta.executeQuery();

            while (rs.next()) {
                paisBuscado.setId(rs.getInt("id"));
                paisBuscado.setNombrePais(rs.getString("nombre"));
                paisBuscado.setCodigo_ISO(rs.getString("codigo_ISO"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paisBuscado;
    }

    public void borrarPais(int idPais){
        // ABRO LA CONEXIÓN CON LA BD Y LE ENVÍO LA CONSULTA
        try (Connection conn = conexionDB.getConnection()) {
//            Usa PreparedStatement en vez de Statement...
            PreparedStatement consulta = conn.prepareStatement("DELETE FROM pais WHERE id = ?");
            consulta.setInt(1,idPais);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
