package com.alan.controladores;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.Autor;
import com.alan.clases.Genero;
import com.alan.clases.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class pantallaPrincipalController {

    LibroDAO librodao = new LibroDAO();
    AutorDAO autordao = new AutorDAO();
    GeneroDAO generodao = new GeneroDAO();

    @FXML
    private Button addAutor;

    @FXML
    private Button addGenero;

    @FXML
    private Button addLibro;

    @FXML
    private TextField inputBuscarLibro;

    @FXML
    private Button mostrarAllAutores;

    @FXML
    private Button mostrarAllGeneros;

    @FXML
    private Button mostrarAllLibros;

    @FXML
    private Label mostrarDescripcion;

    @FXML
    private TableView<Libro> mostrarLibros;
    @FXML
    private TableView<Autor> mostrarAutores;
    @FXML
    private TableView<Genero> mostrarGeneros;
    @FXML
    private TableColumn<Autor, String> colNombreAutor;
    @FXML
    private TableColumn<Autor, String> colApellidoAutor;
    @FXML
    private TableColumn<Autor, String> colApellido2Autor;
    @FXML
    private TableColumn<Autor, String> colNacimiento;
    @FXML
    private TableColumn<Autor, String> colFallecimiento;
    @FXML
    private TableColumn<Autor, String> colPaisAutor;

    @FXML
    private TableColumn<Genero, String> colNombreGenero;
    @FXML
    private TableColumn<Genero, String> colIDGenero;


    @FXML
    private TableColumn<Libro, Integer> colPaginas;
    @FXML
    private TableColumn<Libro, Integer> colPublicacion;
    @FXML
    private TableColumn<Libro, String> colTitulo;

    @FXML
    public void initialize() {

        //        COLUMNAS DE LA TABLAVIEW LIBROS
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

//        LA OBSERVABLE SE RELLENA CON UN ARRAYLIST, DESPUÉS METES LA OBSERVABLE EN EL TABLEVIEW
        ObservableList<Libro> listaLibrosObservable = FXCollections.observableArrayList();
        listaLibrosObservable.addAll(librodao.getAllLibros());
        mostrarLibros.setItems(listaLibrosObservable);

        mostrarLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            mostrarDescripcion.setText(newValue.getDescripcion());
        });

//        COLUMNAS DE LA TABLEVIEW AUTOR
        colNombreAutor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoAutor.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2Autor.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("yearNacimiento"));
        colFallecimiento.setCellValueFactory(new PropertyValueFactory<>("yearFallecimiento"));
        colPaisAutor.setCellValueFactory(new PropertyValueFactory<>("pais_id"));

//        OBSERVABLE DE AUTORES
        ObservableList<Autor> listaAutoresObservable = FXCollections.observableArrayList();
        listaAutoresObservable.addAll(autordao.getAllAutores());
        mostrarAutores.setItems(listaAutoresObservable);


        //        COLUMNAS DE LA TABLEVIEW GENERO
        colIDGenero.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreGenero.setCellValueFactory(new PropertyValueFactory<>("nombre"));

//        OBSERVABLE DE GENEROS
        ObservableList<Genero> listaGenerosObservable = FXCollections.observableArrayList();
        listaGenerosObservable.addAll(generodao.getAllGeneros());
        mostrarGeneros.setItems(listaGenerosObservable);


        mostrarLibros.setVisible(true);
        mostrarAutores.setVisible(false);
        mostrarGeneros.setVisible(false);
    }


    @FXML
    public void mostrarLibros() {
        //        SOLO MUESTRO LOS LIBROS

        mostrarLibros.setVisible(true);
        mostrarGeneros.setVisible(false);
        mostrarAutores.setVisible(false);
    }

    public void mostrarAutores() {
        mostrarLibros.setVisible(false);
        mostrarGeneros.setVisible(false);
//        SOLO MUESTRO LOS AUTORES
        mostrarAutores.setVisible(true);
    }

    public void mostrarGeneros(){
        mostrarLibros.setVisible(false);
        mostrarAutores.setVisible(false);
        //        SOLO MUESTRO LOS GENEROS
        mostrarGeneros.setVisible(true);

    }


}