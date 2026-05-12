package com.alan.controladores;

import com.alan.DataAccesObjects.GeneroDAO;
import com.alan.clases.Alertas;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ventanaAddGeneroController {
    Alertas tipoAlerta = new Alertas();
    GeneroDAO generodao = new GeneroDAO();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField inputNombre;


    //    MANEJO DE EXCEPCIONES
    public void guardarGenero() {
        try {
            String nombreGenero = inputNombre.getText().trim(); // trim() ELIMINA ESPACIOS AL INICIO Y AL FINAL

            if (nombreGenero.isEmpty()) {
                tipoAlerta.mostrarAlertaWarning("CAMPO VACÍO", "DEBES INGRESAR UN NOMBRE PARA EL GÉNERO.", "RELLENAR CAMPO");
                return;
            }
            if (nombreGenero.length() < 3) {
                tipoAlerta.mostrarAlertaWarning("NOMBRE DEMASIADO CORTO", "EL NOMBRE DEL GÉNERO DEBE TENER AL MENOS 3 CARACTERES.", "RELLENAR CAMPO");
                return;
            }
            if (!nombreGenero.matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+")) {
                tipoAlerta.mostrarAlertaWarning("FORMATO INCORRECTO", "EL NOMBRE DEL GÉNERO SOLO PUEDE CONTENER LETRAS.", "REVISAR CAMPO");
                return;
            }

            Alert resultadoIngresoGenero = tipoAlerta.mostrarAlertaConfirmacion("INGRESAR GÉNERO", "VAS A INGRESAR EL SIGUIENTE GÉNERO EN LA BBDD: " + nombreGenero.toUpperCase() + " ¿ESTÁS SEGURO DE CONTINUAR?", "INGRESAR NUEVO GÉNERO");

            if (resultadoIngresoGenero.getResult() == ButtonType.OK) {
                generodao.insertarGenero(nombreGenero);
                tipoAlerta.mostrarAlertaInfo("GÉNERO INSERTADO", "SE HA INSERTADO EL GÉNERO: " + nombreGenero.toUpperCase(), null); // FALTABA CONFIRMACIÓN DE ÉXITO
            }

            inputNombre.clear();

        } catch (Exception e) {
            e.printStackTrace();
            tipoAlerta.mostrarAlertaError("HA OCURRIDO UN ERROR", "NO SE HA PODIDO INSERTAR EL GÉNERO.", "REVISAR CONEXIÓN");
        }
    }

    public void cerrarVentanaGenero() {
        if (inputNombre.getText().isEmpty()) {
            Stage stage = (Stage) inputNombre.getScene().getWindow();
            stage.close();
        } else {
            inputNombre.clear();
        }
    }
}
