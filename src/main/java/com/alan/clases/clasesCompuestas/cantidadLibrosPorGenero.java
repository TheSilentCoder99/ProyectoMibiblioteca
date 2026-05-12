package com.alan.clases.clasesCompuestas;

//Clase creada para poder devolver el resultado de la consulta que devuelve el número total de libros por género
public class cantidadLibrosPorGenero {

    private String genero;
    private int cantidadDeLibros;

    public cantidadLibrosPorGenero(String genero, int cantidadDeLibros) {
        this.genero = genero;
        this.cantidadDeLibros = cantidadDeLibros;
    }

    public String getGenero() {
        return genero;
    }

    public int getCantidadDeLibros() {
        return cantidadDeLibros;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setCantidadDeLibros(int cantidadDeLibros) {
        this.cantidadDeLibros = cantidadDeLibros;
    }
}