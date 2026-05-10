package com.alan.controladores;

import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.cantidadLibrosPorGeneroDAO;
import com.alan.clases.Libro;
import com.alan.clases.clasesCompuestas.cantidadLibrosPorGenero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class cantidadLIbrosPorGeneroController {

    @FXML
    TableView<cantidadLibrosPorGenero> numLibrosPorGenero;
    @FXML
    TableColumn<String, cantidadLibrosPorGenero> colGenero;
    @FXML
    TableColumn<Integer, cantidadLibrosPorGenero> colCantidad;

    cantidadLibrosPorGeneroDAO cantidadgenerodao = new cantidadLibrosPorGeneroDAO();

    ObservableList<cantidadLibrosPorGenero> observableLibrosPorGenero = FXCollections.observableArrayList();

//    TABLA PARA MOSTRAR TITULOS SEGÚN EL GÉNERO PULSADO
    @FXML
    TableView <Libro> tablaTitulosPorGenero;
    @FXML
    TableColumn<Integer, Libro> colTitulo;
    @FXML
    TableColumn<String, Libro> colPages;
    @FXML
    TableColumn<Integer, Libro> colPublicacion;

    LibroDAO librodao = new LibroDAO();

    ObservableList<Libro> observableLibros = FXCollections.observableArrayList();

    public void initialize(){
        observableLibrosPorGenero.addAll(cantidadgenerodao.getCantidadLibrosPorGenero());

        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colGenero.setPrefWidth(300);

        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDeLibros"));
        colCantidad.setPrefWidth(100);

        numLibrosPorGenero.setItems(observableLibrosPorGenero);

//        RELLENANDO TABLA QUE MOSTRARÁ TITULOS SEGÚN EL GÉNERO SELECCIONADO

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colTitulo.setPrefWidth(300);

        colPages.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        colPages.setPrefWidth(100);

        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPublicacion.setPrefWidth(100);

        numLibrosPorGenero.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, newValue) ->{
                    observableLibros.clear();
                    observableLibros.addAll(librodao.librosPorGenero(newValue.getGenero()));
                    tablaTitulosPorGenero.setItems(observableLibros);
        });
    }

    public void cerrarVentana(){

            Stage estaVentana = (Stage) numLibrosPorGenero.getScene().getWindow();

            estaVentana.close();
        }
    }
