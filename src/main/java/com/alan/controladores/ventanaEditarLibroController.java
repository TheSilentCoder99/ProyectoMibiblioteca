package com.alan.controladores;

import com.alan.DataAccesObjects.LibroDAO;
import com.alan.clases.Alertas;
import com.alan.clases.Libro;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ventanaEditarLibroController {

    private final LibroDAO librodao = new LibroDAO();

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

    //    MISMA UTILIDAD QUE SETAUTOR EN EDITAR AUTOR CONTROLLER
    public void setLibro(Libro libro) {

        this.libroAEditar = libro;

        // Rellena los campos con los datos del libro recibido
        inputTitulo.setText(libro.getTitulo());
        inputYearPublicacion.setText(String.valueOf(libro.getYearPublicacion()));
        inputPaginas.setText(String.valueOf(libro.getPaginas()));
        inputDescripcion.setText((libro.getDescripcion()));
        inputOpinion.setText(libro.getOpinion());

//        EN CASO DE QUE EL USUARIO PULSE INVOLUNTARIAMENTE CANCELAR Y BORRE TODOS LOS DATOS DEL OBJETO, PODRÁ SEGUIR VIENDO QUÉ HABÍA ANTES GRACIAS AL PROMPT TEXT
        inputTitulo.setPromptText(libro.getTitulo());
        inputYearPublicacion.setPromptText(String.valueOf(libro.getYearPublicacion()));
        inputPaginas.setPromptText(String.valueOf(libro.getPaginas()));
        inputDescripcion.setPromptText((libro.getDescripcion()));
        inputOpinion.setPromptText(libro.getOpinion());
    }


    //    MANEJO DE EXCEPCIONES
    public void actualizarLibro() {
        try {
            String titulo = inputTitulo.getText();
            String yearPublicacion = inputYearPublicacion.getText();
            String paginas = inputPaginas.getText();
            String descripcion = inputDescripcion.getText();
            String opinion = inputOpinion.getText();
            Alertas alertas = new Alertas();

            // VALIDACIONES ANTES DE PARSEAR
            if (titulo.isEmpty()) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN.", "EL TÍTULO NO PUEDE ESTAR VACÍO.", "INGRESA UN TÍTULO VÁLIDO.");
                return; // <-- FALTABA EL RETURN
            }

            if (paginas.isEmpty()) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL Nº DE PÁGINAS NO PUEDE ESTAR VACÍO.", "INGRESA UN VALOR VÁLIDO.");
                return;
            }

            if (yearPublicacion.isEmpty()) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL AÑO DE PUBLICACIÓN NO PUEDE ESTAR VACÍO.", "INGRESA UN AÑO VÁLIDO.");
                return;
            }

            // PARSEO DESPUÉS DE VALIDAR
            int paginasParseadas = Integer.parseInt(paginas);
            int yearPublicacionParseado = Integer.parseInt(yearPublicacion);

            Alert confirmacionActualizacion = alertas.mostrarAlertaConfirmacion("ACTUALIZAR LIBRO.", "¿CONTINUAR CON LA ACTUALIZACIÓN?", "ACEPTAR PARA CONTINUAR.");

            if (confirmacionActualizacion.getResult() == ButtonType.OK) {
                librodao.ActualizarLibro(this.libroAEditar.getId(), titulo, paginasParseadas, yearPublicacionParseado, descripcion, opinion);
                alertas.mostrarAlertaInfo("ACTUALIZACIÓN REALIZADA.", "SE HA ACTUALIZADO EL LIBRO.", null);
                Stage stage = (Stage) inputTitulo.getScene().getWindow();
                stage.close();
            } else {
                alertas.mostrarAlertaInfo("ACTUALIZACIÓN NO REALIZADA.", "NO SE HA ACTUALIZADO EL LIBRO.", null);
            }

        } catch (NumberFormatException e) {
            Alertas alertas = new Alertas();
            alertas.mostrarAlertaError("ERROR DE FORMATO.", "LAS PÁGINAS Y EL AÑO DE PUBLICACIÓN DEBEN SER NÚMEROS.", "INGRESA VALORES VÁLIDOS.");
        }
    }


    public void cerrarVentanaEditarLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputOpinion, inputDescripcion, inputPaginas, inputTitulo, inputYearPublicacion));

        if (inputTitulo.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputPaginas.getText().isEmpty() && inputDescripcion.getText().isEmpty() && inputOpinion.getText().isEmpty()) {
            Stage stage = (Stage) inputTitulo.getScene().getWindow();
            stage.close();
        } else {
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
