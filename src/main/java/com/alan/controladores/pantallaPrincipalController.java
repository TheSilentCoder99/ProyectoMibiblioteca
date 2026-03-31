package com.alan.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class pantallaPrincipalController {

    @FXML
    private Button addAutor;

    @FXML
    private Button addGenero;

    @FXML
    private Button addLibro;

    @FXML
    private TableColumn<?, ?> colAutor;

    @FXML
    private TableColumn<?, ?> colGenero;

    @FXML
    private TableColumn<?, ?> colPaginas;

    @FXML
    private TableColumn<?, ?> colPublicacion;

    @FXML
    private TableColumn<?, ?> colTitulo;

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
    private TableView<?> mostrarLibros;

}