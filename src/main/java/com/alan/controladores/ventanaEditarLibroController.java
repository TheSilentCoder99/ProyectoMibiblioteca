package com.alan.controladores;

import com.alan.clases.Autor;
import com.alan.clases.Libro;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ventanaEditarLibroController {

    @FXML
    private TextField inputTitulo;

    @FXML
    private TextField inputYearPublicacion;

    @FXML
    private TextField inputPaginas;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private TextArea inputOpinion;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCancelar;

    private Libro libroAEditar;

    public void initialize(){


    }


    public void actualizarLibro() {


    }


    public void setLibro(Libro libro) {
//        this.libroAEditar = libro;
//        // Rellena los campos con los datos del autor recibido
//        inputNombre.setText(autor.getNombre());
//        inputApellido1.setText(autor.getApellido1());
//        inputApellido2.setText((autor.getApellido2()));
//        inputYearNacimiento.setText(String.valueOf(autor.getYearNacimiento()));
    }


    public void cerrarVentanaEditarLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputOpinion,inputDescripcion,inputPaginas,inputTitulo,inputYearPublicacion));

        if (inputTitulo.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputPaginas.getText().isEmpty() && inputDescripcion.getText().isEmpty() && inputOpinion.getText().isEmpty()){
//            OBTENER VENTANA EN LA QUE ESTAS
            Stage stage = (Stage)inputTitulo.getScene().getWindow();
            stage.close();
        } else{
            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
//
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
                }
            }
        }
    }
}
