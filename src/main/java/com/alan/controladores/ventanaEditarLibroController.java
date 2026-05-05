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

    public void setLibro(Libro libro) {

        this.libroAEditar = libro;

        // Rellena los campos con los datos del libro recibido
        inputTitulo.setText(libro.getTitulo());
        inputYearPublicacion.setText(String.valueOf(libro.getYearPublicacion()));
        inputPaginas.setText(String.valueOf(libro.getPaginas()));
        inputDescripcion.setText((libro.getDescripcion()));
        inputOpinion.setText(libro.getOpinion());

//        EN CASO DE QUE EL USUARIO PULSE CANCELAR Y BORRE TODOS LOS DATOS DEL OBJETO, PODRÁ SEGUIR VIENDO QUÉ HABÍA ANTES GRACIAS AL PROMPT TEXT
        inputTitulo.setPromptText(libro.getTitulo());
        inputYearPublicacion.setPromptText(String.valueOf(libro.getYearPublicacion()));
        inputPaginas.setPromptText(String.valueOf(libro.getPaginas()));
        inputDescripcion.setPromptText((libro.getDescripcion()));
        inputOpinion.setPromptText(libro.getOpinion());
    }

    public void actualizarLibro() {

        String titulo = inputTitulo.getText(), yearPublicacion = inputYearPublicacion.getText(), paginas = inputPaginas.getText(), descripcion = inputDescripcion.getText(), opinion = inputOpinion.getText();

        if ((titulo.isEmpty() || titulo.equalsIgnoreCase(" ")) || (paginas.isEmpty() || paginas.equalsIgnoreCase(" "))) {
            Alertas alertas = new Alertas();
            alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "LOS CAMPOS TITULO Y Nº DE PÁGINAS NO PUEDEN ESTAR VACÍOS.", "INGRESA VALORES VÁLIDOS");
        }

        Alertas alertas = new Alertas();
        Alert confirmacionActualizacion = alertas.mostrarAlertaConfirmacion("ACTUALIZAR LIBRO", "¿CONTINUAR CON LA ACTUALIZACIÓN?.", "ACEPTAR PARA CONTINUAR.");

        if (confirmacionActualizacion.getResult() == ButtonType.OK) {
            librodao.ActualizarLibro(this.libroAEditar.getId(), titulo, Integer.parseInt(paginas), Integer.parseInt(yearPublicacion), descripcion, opinion);
            alertas.mostrarAlertaInfo("ACTUALIZACIÓN REALIZADA", "SE HA ACTUALIZADO EL AUTOR", null);
//            UNA VEZ ACEPTADA LA ACTUALIZACIÓN, CIERRO LA VENTANA PARA QUE EL USUARIO NO ESCRIBA UN VALOR SUELTO, PULSE ACTUALIZAR Y QUIZÁ NO SEPA QUE SIGUE ACTUALIZANDO AL OBJETO ANTERIORMENTE SELECCIONADO.
            Stage stage = (Stage) inputTitulo.getScene().getWindow();
            stage.close();
        } else {
            alertas.mostrarAlertaInfo("ACTUALIZACIÓN NO REALIZADA", "NO SE HA ACTUALIZADO EL LIBRO", null);
        }

    }

    public void cerrarVentanaEditarLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputOpinion, inputDescripcion, inputPaginas, inputTitulo, inputYearPublicacion));

        if (inputTitulo.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputPaginas.getText().isEmpty() && inputDescripcion.getText().isEmpty() && inputOpinion.getText().isEmpty()) {
//            OBTENER VENTANA EN LA QUE ESTAS
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
