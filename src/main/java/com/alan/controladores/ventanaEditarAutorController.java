package com.alan.controladores;

import com.alan.DataAccesObjects.PaisDAO;
import com.alan.clases.Alertas;
import com.alan.clases.Autor;
import com.alan.clases.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alan.DataAccesObjects.AutorDAO;


public class ventanaEditarAutorController {

    int fechaActual = LocalDate.now().getYear();

    @FXML
    private TextField inputNombre;

    @FXML
    private TextField inputApellido1;

    @FXML
    private TextField inputApellido2;

    @FXML
    private TextField inputYearNacimiento;

    @FXML
    private ComboBox<Pais> cbPais;

    @FXML
    private TextField inputYearFallecimiento;

    private final AutorDAO autordao = new AutorDAO();

    private final PaisDAO paisdao = new PaisDAO();

    private Autor autorAEditar;

    ObservableList<Pais> seleccionPaisObservable = FXCollections.observableArrayList();

    public void initialize() {
        seleccionPaisObservable.addAll(paisdao.getAllPaises());
        cbPais.setItems(seleccionPaisObservable);
        cbPais.setOnKeyPressed(event -> {
            String teclaPulsada = event.getText();

            // IGNORAR TECLAS ESPECIALES QUE DEVUELVEN STRING VACÍO
            if (teclaPulsada == null || teclaPulsada.isEmpty()) return;

            // RECORRER LA LISTA Y BUSCAR EL PRIMER ELEMENTO QUE EMPIECE POR ESA LETRA
            for (Pais pais : seleccionPaisObservable) {
                if (pais.toString().toLowerCase().startsWith(teclaPulsada.toLowerCase())) {
                    cbPais.getSelectionModel().select(pais);
                    break; // PARAMOS AL ENCONTRAR EL PRIMERO
                }
            }
        });
    }

    //    CON ESTE SETTER, AL AUTOR DE ESTA CLASE SE LE PASAN LAS CARACTERÍSTICAS DEL AUTOR SELECCIONADO MEDIANTE LOS LISTENER DEL MÉTDO AbrirVentanaEditarElemento(). AUTOR DE ESTA CLASE ES UN SIMPLE RECEPTOR VACÍO DEL AUTOR IMPORTANTE, QUE ES EL QUE VIENE DEL CONTROLADOR DE LA PANTALLA PRINCIPAL
    public void setAutor(Autor autor) {
        this.autorAEditar = autor;
        // Rellena los campos con los datos del autor recibido
        inputNombre.setText(autor.getNombre());
        inputApellido1.setText(autor.getApellido1());
        inputApellido2.setText((autor.getApellido2()));

//        OBTENGO EL PAIS DEL AUTOR A TRAVÉS DEL MÉTDO BUSCAR PAÍS DEL DAO
        Pais paisAutor = paisdao.buscarPais(autor.getPais_id());

        cbPais.setValue(paisAutor);
        inputYearNacimiento.setText(String.valueOf(autor.getYearNacimiento()));
        inputYearFallecimiento.setText(String.valueOf(autor.getYearFallecimiento()));

//        DEFINIENDO PROMPT TEXTS POR SI EL USUARIO BORRA LOS DATOS Y NO RECUERDA QUÉ VALORES TENÍA EL AUTOR SELECCIONADO
        inputNombre.setPromptText(autor.getNombre());
        inputApellido1.setPromptText(autor.getApellido1());
        inputApellido2.setPromptText((autor.getApellido2()));
        inputYearNacimiento.setPromptText(String.valueOf(autor.getYearNacimiento()));
        inputYearFallecimiento.setPromptText(String.valueOf(autor.getYearFallecimiento()));
    }

    //    MANEJO DE EXCEPCIONES DEL MÉTTODO
    public void actualizarAutor() {
        try {
            String nombre = inputNombre.getText();
            String apellido1 = inputApellido1.getText();
            String apellido2 = inputApellido2.getText();
            String fallecimiento = inputYearFallecimiento.getText();
            Pais paisActual = cbPais.getSelectionModel().getSelectedItem();
            Alertas alertas = new Alertas();

            // VALIDACIONES ANTES DE PARSEAR
            if (nombre.isEmpty() || apellido1.isEmpty() || nombre.length() < 2) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL NOMBRE Y APELLIDO NO PUEDEN ESTAR VACÍOS.", "INGRESA VALORES VÁLIDOS");
                return; // <-- FALTABA EL RETURN, sin él el código seguía ejecutándose aunque hubiera error
            }

            if (inputYearNacimiento.getText().isEmpty()) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL CAMPO AÑO DE NACIMIENTO NO PUEDE ESTAR VACÍO.", "INGRESA UN AÑO VÁLIDO");
                return; // <-- MISMO PROBLEMA
            }

            if (paisActual == null) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "DEBES SELECCIONAR UN PAÍS.", "SELECCIONA UN PAÍS");
                return;
            }

            // PARSEO DESPUÉS DE VALIDAR, YA SABEMOS QUE NO ESTÁ VACÍO
            int nacimiento = Integer.parseInt(inputYearNacimiento.getText());

            if (nacimiento > fechaActual || Integer.parseInt(fallecimiento) > fechaActual) {
                alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL AÑO DE NACIMIENTO O FALLECIMIENTO NO PUEDEN SER MAYOR AL AÑO ACTUAL", "INGRESA UN AÑO VÁLIDO");
                return;
            }

            Alert confirmacionActualizacion = alertas.mostrarAlertaConfirmacion("ACTUALIZAR AUTOR.", "¿CONTINUAR CON LA ACTUALIZACIÓN?", "ACEPTAR PARA CONTINUAR.");

            if (confirmacionActualizacion.getResult() == ButtonType.OK) {
                autordao.ActualizarAutor(this.autorAEditar.getId(), nombre, apellido1, apellido2, nacimiento, fallecimiento, paisActual.getId());
                alertas.mostrarAlertaInfo("ACTUALIZACIÓN REALIZADA", "SE HA ACTUALIZADO EL AUTOR", null);
                Stage stage = (Stage) inputNombre.getScene().getWindow();
                stage.close();
            } else {
                alertas.mostrarAlertaInfo("ACTUALIZACIÓN NO REALIZADA.", "NO SE HA ACTUALIZADO EL AUTOR.", null);
            }

        } catch (NumberFormatException e) {
            Alertas alertas = new Alertas();
            alertas.mostrarAlertaError("ERROR DE FORMATO.", "EL AÑO DE NACIMIENTO Y FALLECIMIENTO DEBEN SER UN NÚMERO.", "INGRESA UN AÑO VÁLIDO.");
        }
    }

    public void cerrarVentanaEditarAutor() {

        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputYearFallecimiento, inputYearNacimiento, inputNombre, inputApellido2, inputApellido1));

        if (inputNombre.getText().isEmpty() && inputApellido1.getText().isEmpty() &&
                inputYearNacimiento.getText().isEmpty()) {
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


}
