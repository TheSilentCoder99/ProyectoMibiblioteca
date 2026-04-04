package com.alan.clases;

/**
 * CLASE AUTOR creada a partir de la tabla autor de la BD misLibros_db*/

public class Autor {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int pais_id;
    private int yearNacimiento;
    private int yearFallecimiento;

    Autor(){

    }

    // Constructor para NUEVOS autores (sin ID porque se generan en la BD al hacer insert into)
    public Autor(String nombre, String apellido1, String apellido2,
                 int pais_id, int yearNacimiento, int yearFallecimiento) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.pais_id = pais_id;
        this.yearNacimiento = yearNacimiento;
        this.yearFallecimiento = yearFallecimiento;
    }

//    CONSTRUCTOR PARA AUTORES EXISTENTES (con ID, viene de la BD). Se asignan a los objetos a la hora de cargar los datos desde la BD
    public Autor(int id, String nombre, String apellido1, String apellido2, int pais_id, int yearNacimiento, int yearFallecimiento){
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.pais_id = pais_id;
        this.yearNacimiento = yearNacimiento;
        this.yearFallecimiento = yearFallecimiento;
    }

    /**
     * GETTERS Y SETTERS*/
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

    public int getPais_id() {
        return pais_id;
    }

    public void setPais_id(int pais_id) {
        this.pais_id = pais_id;
    }

    public int getYearNacimiento() {
        return yearNacimiento;
    }

    public void setYearNacimiento(int yearNacimiento) {
        this.yearNacimiento = yearNacimiento;
    }

    public int getYearFallecimiento() {
        return yearFallecimiento;
    }

    public void setYearFallecimiento(int yearFallecimiento) {
        this.yearFallecimiento = yearFallecimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
