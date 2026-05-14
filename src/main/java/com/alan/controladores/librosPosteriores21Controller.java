package com.alan.controladores;

import com.alan.DataAccesObjects.LibroDAO;
import com.alan.clases.Libro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class librosPosteriores21Controller {

    LibroDAO librodao = new LibroDAO();
    ObservableList<Libro> observableLibros = FXCollections.observableArrayList();

    @FXML
    TableView<Libro> listaLibros;
    @FXML
    private TableColumn<Libro, String> colTitulo;
    @FXML
    private TableColumn<Libro, Integer> colPaginas;
    @FXML
    private TableColumn<Libro, Integer> colPublicacion;

    @FXML
    private TextArea descripcionLibro;

    public void initialize() {

        observableLibros.addAll(librodao.librosPosterioresSXII());
        listaLibros.setItems(observableLibros);

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPublicacion.setCellValueFactory(new PropertyValueFactory<>("yearPublicacion"));
        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

        colTitulo.setPrefWidth(400);
        colPublicacion.setPrefWidth(100);
        colPaginas.setPrefWidth(100);

        descripcionLibro.setPrefWidth(580);
        descripcionLibro.setPrefHeight(300);

        listaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) return; // evita NullPointerException al deseleccionar

            if (newValue.getDescripcion() == null || newValue.getDescripcion().isBlank()) {
                descripcionLibro.setText("Descripción no añadida.".toUpperCase());
            } else {
                descripcionLibro.setText(newValue.getDescripcion());
            }
        });

    }


    public void cerrarVentana() {

        Stage stage = (Stage) listaLibros.getScene().getWindow();
        stage.close();
    }
}
