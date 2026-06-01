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

//
        cbPais.setOnKeyPressed(event -> {
            String teclaPulsada = event.getText();

            // IGNORAR TECLAS ESPECIALES QUE DEVUELVEN STRING VACÍO
            if (teclaPulsada == null || teclaPulsada.isEmpty()) return;

//            AL PULSAR UNA TECLA, SE COMPRUEBA SU COINCIDENCIA CON LA PRIMERA LETRA DE TODOS LOS PAÍSES.
            // SE RECORRE LA LISTA DE PAÍSES Y SE BUSCA EL PRIMER ELEMENTO QUE EMPIECE POR ESA LETRA.
            for (Pais pais : seleccionPaisObservable) {
                if (pais.toString().toLowerCase().startsWith(teclaPulsada.toLowerCase())) {
                    cbPais.getSelectionModel().select(pais);
                    break; // PARAMOS AL ENCONTRAR EL PRIMERO
                }
            }
        });
    }


    public void cerrarVentanaAutor() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputYearFallecimiento, inputYearNacimiento, inputNombre, inputApellido2, inputApellido1));

        if (inputNombre.getText().isEmpty() && inputApellido1.getText().isEmpty() &&
                inputYearNacimiento.getText().isEmpty()) {
            Stage stage = (Stage) inputNombre.getScene().getWindow();
            stage.close();
        } else {
            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
                }
            }
        }

    }


    //    MANEJO DE EXCEPCIONES
    public void guardarAutor() {
        Pais paisSeleccionado;
        try {
            String nombre = inputNombre.getText().trim();
            String apellido1 = inputApellido1.getText().trim();
            String apellido2 = inputApellido2.getText().trim();
            String yearNacimiento = inputYearNacimiento.getText().trim();
            String yearFallecimiento = inputYearFallecimiento.getText().trim();

            // VALIDACIONES SEPARADAS
            if (nombre.length() < 2 || !nombre.matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+")) {
                tipoAlerta.mostrarAlertaWarning("NOMBRE INCORRECTO", "EL NOMBRE DEBE TENER AL MENOS 2 LETRAS Y SOLO PUEDE CONTENER LETRAS.", "REVISAR CAMPO NOMBRE");
                return;
            }
            if (apellido1.length() < 2 || !apellido1.matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+")) {
                tipoAlerta.mostrarAlertaWarning("APELLIDO INCORRECTO", "EL PRIMER APELLIDO DEBE TENER AL MENOS 2 LETRAS Y SOLO PUEDE CONTENER LETRAS.", "REVISAR CAMPO APELLIDO");
                return;
            }
            if (yearNacimiento.isEmpty()) {
                tipoAlerta.mostrarAlertaWarning("AÑO VACÍO", "DEBES INGRESAR EL AÑO DE NACIMIENTO.", "RELLENAR CAMPO AÑO");
                return;
            }
            if (!yearNacimiento.matches("-?[0-9]+")) { // -? PERMITE AÑOS NEGATIVOS (a.C.)
                tipoAlerta.mostrarAlertaWarning("FORMATO INCORRECTO", "EL AÑO DE NACIMIENTO SOLO PUEDE CONTENER NÚMEROS.", "REVISAR CAMPO AÑO");
                return;
            }
            // PROBLEMA: FALTABA VALIDAR QUE SE SELECCIONÓ UN PAÍS
            if (cbPais.getSelectionModel().getSelectedItem() == null) {
                tipoAlerta.mostrarAlertaWarning("PAÍS NO SELECCIONADO", "DEBES SELECCIONAR UN PAÍS.", "SELECCIONAR PAÍS");
                return;
            } else {
                paisSeleccionado = cbPais.getSelectionModel().getSelectedItem();
            }

            int nacimientoParseado = Integer.parseInt(yearNacimiento);
            int fallecimientoParseado = 0;

            if (!yearFallecimiento.isEmpty()) {
                if (!yearFallecimiento.matches("-?[0-9]+")) {
                    tipoAlerta.mostrarAlertaWarning("FORMATO INCORRECTO", "EL AÑO DE FALLECIMIENTO SOLO PUEDE CONTENER NÚMEROS.", "REVISAR CAMPO AÑO");
                    return;
                }
                fallecimientoParseado = Integer.parseInt(yearFallecimiento);
            }

            String apellido2Final = apellido2.isEmpty() ? " " : apellido2;

            Alert resultadoIngresoAutor = tipoAlerta.mostrarAlertaConfirmacion("INGRESAR AUTOR", "VAS A INGRESAR EL SIGUIENTE AUTOR EN LA BBDD: " + nombre + " " + apellido1 + " ¿ESTÁS SEGURO DE CONTINUAR?", "INGRESAR NUEVO AUTOR");

//            haya o no haya conexión, mientras se pulse ok, saldrá un mensaje de que se ha insertado, siendo que puede ser mentira.
            if (resultadoIngresoAutor.getResult() == ButtonType.OK) {

                int idLibro = autordao.insertarAutor(nombre, apellido1, apellido2Final, paisSeleccionado.getNombrePais(), nacimientoParseado, fallecimientoParseado);

                if (idLibro != -1) {
                    tipoAlerta.mostrarAlertaInfo("AUTOR INSERTADO", "SE HA INSERTADO EL AUTOR: " + nombre + " " + apellido1, null); // FALTABA CONFIRMACIÓN DE ÉXITO
                } else {
                    tipoAlerta.mostrarAlertaError("ERROR AL GUARDAR", "NO SE HA PODIDO GUARDAR EL AUTOR. REVISAR CONEXIÓN A LA BASE DE DATOS.", "REVISAR CONEXIÓN");
                    return;
                }
            }

            // LIMPIAR CAMPOS
            List<Node> elementosVentana = new ArrayList<>(Arrays.asList(inputYearNacimiento, inputYearFallecimiento, inputApellido2, inputApellido1, inputNombre));
            for (Node elemento : elementosVentana) {
                if (elemento instanceof TextField tf) tf.clear();
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            tipoAlerta.mostrarAlertaError("HA OCURRIDO UN ERROR", "SE HA INGRESADO UN FORMATO ERRÓNEO EN ALGÚN CAMPO.", "REVISAR CAMPOS");
        }
    }
}

