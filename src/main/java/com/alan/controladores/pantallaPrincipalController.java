package com.alan.controladores;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.Autor;
import com.alan.clases.Genero;
import com.alan.clases.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    FilteredList<Libro> librosFiltrados;

    @FXML
    public void initialize() {

        //        COLUMNAS DE LA TABLAVIEW LIBROS
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

//        LA OBSERVABLE SE RELLENA CON UN ARRAYLIST, DESPUÉS METES LA OBSERVABLE EN EL TABLEVIEW
        ObservableList<Libro> listaLibrosObservable = FXCollections.observableArrayList();
        listaLibrosObservable.addAll(librodao.getAllLibros());
        librosFiltrados = new FilteredList<>(listaLibrosObservable);
        mostrarLibros.setItems(librosFiltrados);

//        LISTENER PARA EL BUSCADO POR TITULO
        inputBuscarLibro.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarLibros(newValue);
        });

//        LISTENER PARA LA DESCRIPCIÓN
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

//ESTADO INICIAL DE LAS TABLEVIEW
        mostrarLibros.setVisible(true);
        mostrarAutores.setVisible(false);
        mostrarGeneros.setVisible(false);


//        CREANDO LA FILTEREDLIST

    }

//    MÉTOoDO PARA DEFINIR EL PREDICADO DE FILTRADO
    @FXML
    void filtrarLibros(String textoBusqueda) {  // ← Recibe el texto como parámetro
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            librosFiltrados.setPredicate(l -> true);  // Muestra todos
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            librosFiltrados.setPredicate(l ->
                    l.getTitulo().toLowerCase().contains(busqueda)
            );
        }
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

    public void mostrarGeneros() {
        mostrarLibros.setVisible(false);
        mostrarAutores.setVisible(false);
        //        SOLO MUESTRO LOS GENEROS
        mostrarGeneros.setVisible(true);

    }

    public void cambiarVentanas(Event e) throws IOException {
        Button botonVentana = (Button) e.getSource();
        String rutaVentana = "";
        Stage primaryStage = new Stage();

        switch (botonVentana.getId()) {
            case "addLibro":
                rutaVentana = "/ventanaAddLibro.fxml";
                primaryStage.setTitle("AÑADIR LIBRO");
                break;
            case "addAutor":
                rutaVentana = "/ventanaAddAutor.fxml";
                primaryStage.setTitle("AÑADIR AUTOR");
                break;
            case "addGenero":
                rutaVentana = "/ventanaAddGenero.fxml";
                primaryStage.setTitle("AÑADIR GÉNERO");
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaVentana));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

}