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


    public void guardarGenero() {

        String nombreGenero = inputNombre.getText();

        if (nombreGenero.length()<3 || !nombreGenero.matches("[a-záéíóúüñA-ZÁÉÍÓÚÜÑ ]+")) {
            tipoAlerta.mostrarAlertaWarning("RELLENAR CAMPO","DEBES RELLENAR UN NOMBRE VÁLIDO PARA EL GÉNERO LITERARIO","GÉNERO LITERARIO INCORRECTO");
            return;
        }

            Alert resultadoIngresoLibro = tipoAlerta.alertaConfirmacion("INGRESAR GÉNERO","VAS A INGRESAR EL SIGUIENTE GÉNERO EN LA BBDD: " + nombreGenero + " ¿ESTÁS SEGURO DE CONTINUAR?","INGRESAR NUEVO GÉNERO");

            if(resultadoIngresoLibro.getResult() == ButtonType.OK){
                generodao.insertarGenero(nombreGenero);
            }

            inputNombre.clear();
    }

    public void cerrarVentanaGenero() {
        if (inputNombre.getText().isEmpty()) {
//            OBTENER VENTANA EN LA QUE ESTAS Y CERRARLA
            Stage stage = (Stage) inputNombre.getScene().getWindow();
            stage.close();
        } else {
            inputNombre.clear();
        }
    }
}
