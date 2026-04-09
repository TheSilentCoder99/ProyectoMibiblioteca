package com.alan.controladores;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.Autor;
import com.alan.DataAccesObjects.AutorLibroDAO;
import com.alan.clases.AutorLibro;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pantallaPrincipalController {

    LibroDAO librodao = new LibroDAO();
    AutorDAO autordao = new AutorDAO();
    GeneroDAO generodao = new GeneroDAO();
    AutorLibroDAO autorlibrodao = new AutorLibroDAO();

    @FXML
    private
    Button botonEliminar;

    @FXML
    private Button addAutor;

    @FXML
    private Button addGenero;

    @FXML
    private Button addLibro;

    @FXML
    private TextField inputBuscarLibro;

    @FXML
    private TextField inputBuscarAutor;

    @FXML
    private TextField inputBuscarGenero;

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
    private TableView<AutorLibro> mostrarLibroAutor;

    //    COLUMNAS AUTORLIBRO
    @FXML
    private TableColumn<AutorLibro, String> colTituloLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPaginaLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPublicacionLibroAutor;


    //    COLUMNAS TABLA AUTOR
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

    //COLUMNAS TABLA LIBRO
    @FXML
    private TableColumn<Libro, Integer> colPaginas;
    @FXML
    private TableColumn<Libro, Integer> colPublicacion;
    @FXML
    private TableColumn<Libro, String> colTitulo;

    FilteredList<Libro> librosFiltrados;
    FilteredList<Autor> autoresFiltrados;
    FilteredList<Genero> generosFiltrados;
    List<AutorLibro> resultadoConsulta;
    ObservableList<Genero> listaGenerosObservable = FXCollections.observableArrayList();
    ObservableList<Libro> listaLibrosObservable = FXCollections.observableArrayList();
    ObservableList<Autor> listaAutoresObservable = FXCollections.observableArrayList();



    @FXML
    public void initialize() {

        //        COLUMNAS DE LA TABLAVIEW LIBROS
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

//        LA OBSERVABLE SE RELLENA CON UN ARRAYLIST, DESPUÉS METES LA OBSERVABLE EN EL TABLEVIEW
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
        listaAutoresObservable.addAll(autordao.getAllAutores());
        autoresFiltrados = new FilteredList<>(listaAutoresObservable);
        mostrarAutores.setItems(autoresFiltrados);


//        LISTENER PARA EL BUSCADO POR NOMBRE O APELLIDO
        inputBuscarAutor.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarAutores(newValue);
        });

        //        COLUMNAS DE LA TABLEVIEW GENERO
        colNombreGenero.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIDGenero.setCellValueFactory(new PropertyValueFactory<>("id"));


//        OBSERVABLE DE GENEROS
        listaGenerosObservable.addAll(generodao.getAllGeneros());
        generosFiltrados = new FilteredList<>(listaGenerosObservable);
        mostrarGeneros.setItems(generosFiltrados);

//        LISTENER PARA BUSCAR EL GÉNERO
        inputBuscarGenero.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarGeneros(newValue);
        });

//        COLUMNAS DE MOSTRAR LOS LIBROS DE CADA AUTOR
        colTituloLibroAutor.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPaginaLibroAutor.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colPublicacionLibroAutor.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));

        //        DECLARO LA OBSERVABLE DE AUTORLIBRO
        ObservableList<AutorLibro> autorLibroObservable = FXCollections.observableArrayList();
        resultadoConsulta = new ArrayList<>();

//        LISTENER PARA TRAER LOS LIBROS DEL AUTOR QUE HAYA SIDO SELECCIONADO EN ESE MOMENTO
        mostrarAutores.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            autorLibroObservable.clear();
            resultadoConsulta = autorlibrodao.getLibrosPorAutor(newValue.getId());
            autorLibroObservable.addAll(resultadoConsulta);
            mostrarLibroAutor.setItems(autorLibroObservable);
        });


//ESTADO INICIAL DE LAS TABLEVIEW
        mostrarLibros.setVisible(true);
        mostrarAutores.setVisible(false);
        mostrarGeneros.setVisible(false);
        mostrarLibroAutor.setVisible(false);

    }

    //    MÉTODOS
//    MÉTOoDO PARA DEFINIR EL PREDICADO DE FILTRADO
    @FXML
    void filtrarLibros(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            librosFiltrados.setPredicate(l -> true);  // Muestra todos
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            librosFiltrados.setPredicate(l ->
                    l.getTitulo().toLowerCase().contains(busqueda)
            );
        }
    }

    void filtrarAutores(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            autoresFiltrados.setPredicate(a -> true);  // Muestra todos
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            autoresFiltrados.setPredicate(a ->
                    a.getNombre().toLowerCase().contains(busqueda) ||
                            a.getApellido1().toLowerCase().contains(busqueda)
            );
        }
    }

    void filtrarGeneros(String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.isEmpty()) {
            generosFiltrados.setPredicate(a -> true);
        } else {
            String busqueda = textoBusqueda.toLowerCase();
            generosFiltrados.setPredicate(a ->
                    a.getNombre().toLowerCase().contains(busqueda)
            );
        }
    }


    @FXML
    public void mostrarLibros() {
        mostrarGeneros.setVisible(false);
        mostrarAutores.setVisible(false);
        mostrarLibroAutor.setVisible(false);
        inputBuscarAutor.setVisible(false);
        inputBuscarGenero.setVisible(false);

        //        SOLO MUESTRO LOS LIBROS Y EL BUSCADOR DE LIBROS
        mostrarLibros.setVisible(true);
        inputBuscarLibro.setVisible(true);
        mostrarDescripcion.setVisible(true);

    }

    public void mostrarAutores() {
        mostrarLibros.setVisible(false);
        mostrarGeneros.setVisible(false);
        inputBuscarLibro.setVisible(false);
        inputBuscarGenero.setVisible(false);
        mostrarDescripcion.setVisible(false);

//        SOLO MUESTRO LOS AUTORES Y EL BUSCADOR DE AUTORES
        mostrarAutores.setVisible(true);
        inputBuscarAutor.setVisible(true);
        mostrarLibroAutor.setVisible(true);

    }

    public void mostrarGeneros() {
        mostrarLibros.setVisible(false);
        mostrarAutores.setVisible(false);
        mostrarLibroAutor.setVisible(false);
        inputBuscarAutor.setVisible(false);
        inputBuscarLibro.setVisible(false);
        mostrarDescripcion.setVisible(false);
        //        SOLO MUESTRO LOS GENEROS Y EL BUSCADOR DE GENEROS
        mostrarGeneros.setVisible(true);
        inputBuscarGenero.setVisible(true);

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

    public void eliminarElemento() {

            if (mostrarLibros.isVisible()) {
                Libro seleccionado = mostrarLibros.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    librodao.borrarLibro(seleccionado.getId());
                }
            }

            if (mostrarAutores.isVisible()) {
            Autor seleccionado = mostrarAutores.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                autordao.borrarAutor(seleccionado.getId());
            }
        }

        if (mostrarGeneros.isVisible()) {
            Genero seleccionado = mostrarGeneros.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                generodao.borrarGenero(seleccionado.getId());
            }
        }

        }
    }