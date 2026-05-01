package com.alan.clases;

public class Pais {
    private int id;
    private String nombrePais;
    private String codigo_ISO;

//    CONSTRUCTORES
    public Pais(){

    }

    public Pais(int id, String nombre, String codigoISO) {
        this.id = id;
        this.nombrePais = nombre;
        this.codigo_ISO = codigoISO;
    }

    public Pais(String nombre, String codigoISO) {
        this.nombrePais = nombre;
        this.codigo_ISO = codigoISO;
    }

//    GETTER Y SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getCodigo_ISO() {
        return codigo_ISO;
    }

    public void setCodigo_ISO(String codigo_ISO) {
        this.codigo_ISO = codigo_ISO;
    }

    @Override
    public String toString(){
        return this.nombrePais + " " + this.codigo_ISO;
    }
}
