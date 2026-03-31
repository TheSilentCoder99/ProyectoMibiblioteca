package com.alan.clases;
/**
 * CLASE GÉNERO creada a partir de la tabla género de la BD misLibros_db*/

public class genero {
    private int id;
    private String nombre;

//    CONSTRUCTORES
    genero(){

    }

    genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    genero(String nombre) {
        this.nombre = nombre;
    }

//    GETTER Y SETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
