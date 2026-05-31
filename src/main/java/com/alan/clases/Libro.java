package com.alan.clases;

public class Libro {

    private int id;
    private String titulo;
    private int yearPublicacion;
    private int paginas;
    private String descripcion;
    private String opinion;
    private int coverID;
    private String imagen;

    //    CONSTRUCTORES
    public Libro() {

    }

    public Libro(int id, String titulo, int yearPublicacion, int paginas, String descripcion, String opinion, int coverID, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.descripcion = descripcion;
        this.opinion = opinion;
        this.coverID = coverID;
        this.imagen = imagen;
    }

    public Libro(int id, String titulo, int yearPublicacion, int paginas, String descripcion, String opinion, int coverID) {
        this.id = id;
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.descripcion = descripcion;
        this.opinion = opinion;
        this.coverID = coverID;
    }

    public Libro(String titulo, int yearPublicacion, int paginas, String descripcion, String opinion, int coverID) {
        this.titulo = titulo;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.descripcion = descripcion;
        this.opinion = opinion;
        this.coverID = coverID;
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

    public int getCoverID() {
        return coverID;
    }

    public void setCoverID(int coverID) {
        this.coverID = coverID;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", yearPublicacion=" + yearPublicacion +
                ", paginas=" + paginas +
                ", descripcion='" + descripcion + '\'' +
                ", opinion='" + opinion + '\'' +
                '}';
    }

}
