package com.alan.controladores;

import com.alan.clases.Alertas;
import com.alan.clases.Autor;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField inputYearFallecimiento;

    private AutorDAO autordao = new AutorDAO();

    private Autor autorAEditar;

    public void setAutor(Autor autor) {
        this.autorAEditar = autor;
        // Rellena los campos con los datos del autor recibido
        inputNombre.setText(autor.getNombre());
        inputApellido1.setText(autor.getApellido1());
        inputApellido2.setText((autor.getApellido2()));
//        ¿CAMBIAR TAMBIÉN EL PAÍS?
        inputYearNacimiento.setText(String.valueOf(autor.getYearNacimiento()));
        inputYearFallecimiento.setText(String.valueOf(autor.getYearFallecimiento()));
    }

    public void actualizarAutor(){

        String nombre = inputNombre.getText(),apellido1 = inputApellido1.getText(),apellido2 = inputApellido2.getText();
        int nacimiento = Integer.parseInt(inputYearNacimiento.getText());
        int fallecimiento = Integer.parseInt(inputYearFallecimiento.getText());

//        CREAR UNA LISTA DE FIELDTEXT. COMPARAR SUS VALORES CON LOS VALORES DEL OBJETO. SI SON IGUALES, MENSAJE DE "NO HA HABIDO CAMBIOS" Y DETENER EJECUCIÓN. SINO, APLICAR CAMBIOS.

        List<TextField> listaValoresArellenar = new ArrayList<>(Arrays.asList(inputNombre,inputApellido1,inputApellido2,inputYearNacimiento,inputYearFallecimiento));

        for(TextField tf : listaValoresArellenar){
            if(tf.getText().isEmpty() || tf.getText().length() < 3){
                Alertas alerta = new Alertas();
                alerta.mostrarAlertaError("CAMPOS VACÍOS","LOS CAMPOS A MODIFICAR NO PUEDEN ESTAR VACÍOS","RELLENAR CAMPOS");
            }
        }

        autordao.ActualizarAutor(this.autorAEditar.getId(),nombre,apellido1,apellido2,nacimiento,fallecimiento);
    }

    public void cerrarVentanaEditarAutor(){

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
