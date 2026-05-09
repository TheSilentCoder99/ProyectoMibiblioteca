package com.alan.controladores;

import com.alan.DataAccesObjects.AutorLibroDAO;
import com.alan.DataAccesObjects.autoresMasLeidosDAO;
import com.alan.clases.AutorLibro;
import com.alan.clases.clasesCompuestas.autorMasLeido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class autoresMasLeidosController {

    autoresMasLeidosDAO autoresmasleidodao = new autoresMasLeidosDAO();

    AutorLibroDAO autorlibrodao = new AutorLibroDAO();

    ObservableList<autorMasLeido> observableLibros = FXCollections.observableArrayList();
    @FXML
    TableView<autorMasLeido> tablaAutoresMasLeidos;

    //    TABLA + COLUMNAS AUTORLIBRO

    @FXML
    private TableView<AutorLibro> mostrarLibroAutor;
    @FXML
    private TableColumn<AutorLibro, String> colTituloLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPaginaLibroAutor;
    @FXML
    private TableColumn<AutorLibro, Integer> colPublicacionLibroAutor;

    // COLUMNAS AUTOR MÁS LEÍDO
    @FXML
    private TableColumn<autorMasLeido, Integer> colid;
    @FXML
    private TableColumn<autorMasLeido, String> colNombre;
    @FXML
    private TableColumn<autorMasLeido, String> colApellido1;
    @FXML
    private TableColumn<autorMasLeido, String> colApellido2;
    @FXML
    private TableColumn<autorMasLeido, Integer> colCantidadLibros;

    ObservableList<autorMasLeido> autoresMasLeidosObservable = FXCollections.observableArrayList();

    public void initialize(){

        //VALORES DE LAS COLUMNAS QUE MUESTRAN NOMBRE Y NÚMERO DE LIBROS DE CADA AUTOR
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        colApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        colCantidadLibros.setCellValueFactory(new PropertyValueFactory<>("libros"));

//        MEDIDAS DE LAS COLUMNAS DE LA TABLA
        colNombre.setPrefWidth(250);
        colApellido1.setPrefWidth(200);
        colApellido2.setPrefWidth(200);
        colCantidadLibros.setPrefWidth(100);

//        RELLENO DE LA TABLA
        autoresMasLeidosObservable.addAll(autoresmasleidodao.getAutoresMasLeidos());
        tablaAutoresMasLeidos.setItems(autoresMasLeidosObservable);


//      CREANDO LISTENER DE UNA TABLA DEPENDIENDO DE OTRA
        ObservableList<AutorLibro> observableLibros = FXCollections.observableArrayList();

        mostrarLibroAutor.setItems(observableLibros); // la vinculas una vez aquí

// El listener reacciona cada vez que el usuario selecciona una fila
        tablaAutoresMasLeidos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                observableLibros.clear();
                observableLibros.addAll(autorlibrodao.getLibrosPorAutor(newValue.getId()));
            }
        });

        //        COLUMNAS DE MOSTRAR LOS LIBROS DE CADA AUTOR
        colTituloLibroAutor.setCellValueFactory(new PropertyValueFactory<>("title"));
        colPaginaLibroAutor.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colPublicacionLibroAutor.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));

        //        MEDIDAS DE LAS COLUMNAS DE LA TABLA LIBROXAUTOR
        colTituloLibroAutor.setPrefWidth(250);
        colPaginaLibroAutor.setPrefWidth(90);
        colPublicacionLibroAutor.setPrefWidth(250);
    }

    public void cerrarVentana(){

        Stage estaVentana = (Stage) tablaAutoresMasLeidos.getScene().getWindow();

        estaVentana.close();
    }
}


