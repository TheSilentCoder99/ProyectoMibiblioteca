package com.alan.clases;

/**
 * CLASE LIBROo creada a partir de la tabla clase de la BD misLibros_db*/

public class Libro {

    private int id;
    private String titulo;
    private int yearPublicacion;
    private int paginas;
    private String descripcion;
    private String opinion;

//    CONSTRUCTORES
    public Libro(){

    }

    public Libro(int id, String titulo, int yearPublicacion, int paginas, String descripcion, String opinion){
        this.id = id;
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.descripcion = descripcion;
        this.opinion = opinion;
    }

    public Libro(String titulo, int yearPublicacion, int paginas, String descripcion, String opinion){
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.descripcion = descripcion;
        this.opinion = opinion;
    }

//    GETTER Y SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getYearPublicacion() {
        return yearPublicacion;
    }

    public void setYearPublicacion(int yearPublicacion) {
        this.yearPublicacion = yearPublicacion;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

}
