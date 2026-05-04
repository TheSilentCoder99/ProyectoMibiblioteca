package com.alan.controladores;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.clases.Alertas;
import com.alan.clases.Pais;
import com.alan.DataAccesObjects.PaisDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ventanaAddAutorController {

    AutorDAO autordao = new AutorDAO();
    Alertas tipoAlerta = new Alertas();
    PaisDAO pais = new PaisDAO();
    ObservableList<Pais> seleccionPaisObservable = FXCollections.observableArrayList();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Pais> cbPais;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField inputApellido1;

    @FXML
    private TextField inputApellido2;

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputYearFallecimiento;

    @FXML
    private TextField inputYearNacimiento;

    public void initialize() {
        seleccionPaisObservable.addAll(pais.getAllPaises());
        cbPais.setItems(seleccionPaisObservable);
//        IMPLEMENTAR BÚSQUEDA POR LETRA,
//        ES DECIR, QUE AL PULSAR UNA LETRA
//        SALGAN TODOS LOS AUTORES QUE EMPIECEN POR ESA LETRA
    }


    public void cerrarVentanaAutor() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputYearFallecimiento, inputYearNacimiento, inputNombre, inputApellido2, inputApellido1));

        if (inputNombre.getText().isEmpty() && inputApellido1.getText().isEmpty() &&
                inputYearNacimiento.getText().isEmpty()) {
//            OBTENER VENTANA EN LA QUE ESTAS
            Stage stage = (Stage) inputNombre.getScene().getWindow();
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

    public void guardarAutor() {
        try {
//            CREO QUE AQUÍ HAY MUCHAS CONVERSIONES INNECESARIAS, REVISAR
//            AHORA NO ESTOY SEGURO DE QUE LAS HAYA. REVISAR
//            TOMAR
            String nombre = inputNombre.getText();
            String apellido1 = inputApellido1.getText();
            String yearNacimiento = inputYearNacimiento.getText();
            String yearFallecimiento = inputYearFallecimiento.getText();
            String apellido2 = inputApellido2.getText();

//            COMPROBAR
            if (inputNombre.getText().length() < 2 || !inputNombre.getText().matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+") || !inputApellido1.getText().matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+") || inputApellido1.getText().length() < 2 || inputYearNacimiento.getText().isEmpty()) {

                tipoAlerta.mostrarAlertaWarning("FALTA NOMBRE, PRIMER APELLIDO, O FECHA DE NACIMIENTO.", "DEBES INGRESAR EL NOMBRE, PRIMER APELLIDO Y LA FECHA DE NACIMIENTO PARA CONTINUAR CON EL GUARDADO DEL AUTOR.", "RELLENAR CAMPOS OBLIGATORIOS.");
                return;
            }

//            PARSEAR
            int NacimientoParseada = Integer.parseInt(yearNacimiento);
            int FallecimientoParseada = 0;

            if (inputApellido2.getText().isEmpty()) {
                inputApellido2.setText(" ");
            }

            if (inputYearFallecimiento.getText().isEmpty()) {
                inputYearFallecimiento.setText(" ");
            } else {
                FallecimientoParseada = Integer.parseInt(yearFallecimiento);
            }

            Alert resultadoIngresoLibro = tipoAlerta.alertaConfirmacion("INGRESAR AUTOR.", "VAS A INGRESAR EL SIGUIENTE AUTOR EN LA BBDD: " + nombre + apellido1 + " ¿ESTAS SEGURO DE CONTINUAR?", "INGRESAR NUEVO AUTOR.");

            if (resultadoIngresoLibro.getResult() == ButtonType.OK) {
                autordao.insertarAutor(nombre, apellido1, apellido2, cbPais.getSelectionModel().getSelectedItem().toString(), NacimientoParseada, FallecimientoParseada);
            }

//        LIMPIO LOS CAMPOS TRAS AÑADIR UN AUTOR
            List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputYearNacimiento, inputYearFallecimiento, inputApellido2, inputApellido1, inputNombre));

            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
                }
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
            tipoAlerta.mostrarAlertaError("HA OCURRIDO UN ERROR.", "SE HA INGRESADO UN FORMATO ERRÓNEO EN ALGÚN CAMPO. DEBES RELLENAR CADA CAMPO CON EL FORMATO CORRECTO.", "REVISAR CAMPOS.");
        }
    }

}

