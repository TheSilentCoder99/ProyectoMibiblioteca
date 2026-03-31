package com.alan.clases;

public class pais {
    private int id;
    private String nombre;
    private String codigoISO;

//    CONSTRUCTORES
    pais(){

    }

    pais(int id, String nombre, String codigoISO) {
        this.id = id;
        this.nombre = nombre;
        this.codigoISO = codigoISO;
    }

    pais(String nombre, String codigoISO) {
        this.nombre = nombre;
        this.codigoISO = codigoISO;
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

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }
}
