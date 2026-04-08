package com.alan.clases;

public class AutorLibro {
    private int idAutor;
    private String title;
    private String nombre;
    private String apellido1;
    private String paisNombre;
    private int yearPublicacion;
    private int paginas;

   public AutorLibro(int idAutor, String title, int paginas, int yearPublicacion,String nombre, String apellido1, String paisNombre) {
       this.title = title;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.paisNombre = paisNombre;
        this.yearPublicacion = yearPublicacion;
        this.paginas = paginas;
        this.idAutor = idAutor;
   }

    public AutorLibro() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPaisNombre() {
        return paisNombre;
    }

    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
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

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdAutor() {
       return this.idAutor;
    }
}
