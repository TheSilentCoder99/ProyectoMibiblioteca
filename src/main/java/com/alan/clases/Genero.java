package com.alan.clases;
/**
 * CLASE GÉNERO creada a partir de la tabla género de la BD misLibros_db*/

public class Genero {
    private int id;
    private String nombre;

//    CONSTRUCTORES
    public Genero(){

    }

    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Genero(String nombre) {
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

    @Override
    public String toString() {
        return this.nombre;
    }
}
