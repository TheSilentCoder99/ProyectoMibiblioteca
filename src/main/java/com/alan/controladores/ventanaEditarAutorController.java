package com.alan.controladores;

import com.alan.DataAccesObjects.LibroDAO;
import com.alan.DataAccesObjects.PaisDAO;
import com.alan.clases.Alertas;
import com.alan.clases.Autor;
import com.alan.clases.Pais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alan.DataAccesObjects.AutorDAO;


public class ventanaEditarAutorController {

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

    private AutorDAO autordao = new AutorDAO();

    private PaisDAO paisdao = new PaisDAO();

    private Autor autorAEditar;

    ObservableList<Pais> seleccionPaisObservable = FXCollections.observableArrayList();

    //    CON ESTE SETTER, AL AUTOR DE ESTA CLASE SE LE PASAN LAS CARACTERÍSTICAS DEL AUTOR SELECCIONADO MEDIANTE EL LISTENER. EL AUTOR DE ESTA CLASE ES UN SIMPLE RECEPTOR VACÍO DEL AUTOR QUE IMPORTA, QUE ES EL QUE VIENE DEL CONTROLADOR DE LA PANTALLA PRINCIPAL
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

    public void setAutor(Autor autor) {
        this.autorAEditar = autor;
        // Rellena los campos con los datos del autor recibido
        inputNombre.setText(autor.getNombre());
        inputApellido1.setText(autor.getApellido1());
        inputApellido2.setText((autor.getApellido2()));
//        OBTENGO EL PAIS DEL AUTOR A TRAVÉS DEL MÉThODO BUSCAR PAÍS DEL DAO
        Pais paisAutor = paisdao.buscarPais(autor.getPais_id());
        cbPais.setValue(paisAutor);
        inputYearNacimiento.setText(String.valueOf(autor.getYearNacimiento()));
        inputYearFallecimiento.setText(String.valueOf(autor.getYearFallecimiento()));

//        DEFINIENDO PROMPTTEXTS POR SI EL USUARIO BORRA LOS VALORES Y NO RECUERDA QUÉ TENÍA POR DEFECTO EL AUTOR SELECCIONADO
        inputNombre.setPromptText(autor.getNombre());
        inputApellido1.setPromptText(autor.getApellido1());
        inputApellido2.setPromptText((autor.getApellido2()));
        inputYearNacimiento.setPromptText(String.valueOf(autor.getYearNacimiento()));
        inputYearFallecimiento.setPromptText(String.valueOf(autor.getYearFallecimiento()));
    }

    public void actualizarAutor() {

        String nombre = inputNombre.getText(), apellido1 = inputApellido1.getText(), apellido2 = inputApellido2.getText();
        int nacimiento = Integer.parseInt(inputYearNacimiento.getText());
        String fallecimiento = inputYearFallecimiento.getText();

        Pais paisActual = cbPais.getSelectionModel().getSelectedItem();

        if (inputYearNacimiento.getText().isEmpty() || inputYearNacimiento.getText().equalsIgnoreCase(" ")) {
            Alertas alertas = new Alertas();
            alertas.mostrarAlertaError("ERROR EN LA INFORMACIÓN", "EL CAMPO AÑO DE NACIMIENTO NO PUEDE ESTAR VACÍO.", "INGRESA UN AÑO VÁLIDO");
        }

        Alertas alertas = new Alertas();
        Alert confirmacionActualizacion = alertas.mostrarAlertaConfirmacion("ACTUALIZAR AUTOR", "¿CONTINUAR CON LA ACTUALIZACIÓN?.", "ACEPTAR PARA CONTINUAR.");

        if (confirmacionActualizacion.getResult() == ButtonType.OK) {
            autordao.ActualizarAutor(this.autorAEditar.getId(), nombre, apellido1, apellido2, nacimiento, fallecimiento, paisActual.getId());
            alertas.mostrarAlertaInfo("ACTUALIZACIÓN REALIZADA", "SE HA ACTUALIZADO EL AUTOR", null);
//            UNA VEZ ACEPTADA LA ACTUALIZACIÓN, CIERRO LA VENTANA PARA QUE EL USUARIO NO ESCRIBA UN VALOR SUELTO, PULSE ACTUALIZAR Y QUIZÁ NO SEPA QUE SIGUE ACTUALIZANDO AL OBJETO ANTERIORMENTE SELECCIONADO.
            Stage stage = (Stage) inputNombre.getScene().getWindow();
            stage.close();

        } else {
            alertas.mostrarAlertaInfo("ACTUALIZACIÓN NO REALIZADA", "NO SE HA ACTUALIZADO EL AUTOR", null);
        }
    }

    public void cerrarVentanaEditarAutor() {

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


}
