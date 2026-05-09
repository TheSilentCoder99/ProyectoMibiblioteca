package com.alan.clases.clasesCompuestas;

public class autorMasLeido {

    private int id;
    private String nombre,apellido1,apellido2;
    private int libros;

    public autorMasLeido(){

    }

    public autorMasLeido(String nombre, String apellido1, String apellido2, int libros){
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.libros = libros;
    }

    public autorMasLeido(int id, String nombre, String apellido1, String apellido2, int libros){
       super();
       this.id = id;
    }

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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getLibros() {
        return libros;
    }

    public void setLibros(int libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "autoresMasLeidos{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", libros=" + libros +
                '}';
    }
}
