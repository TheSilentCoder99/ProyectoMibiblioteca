package com.alan.clases;

import javafx.scene.control.Alert;

public class Alertas {

    public Alert mostrarAlertaInfo(String titulo, String contenido, String cabecera) {

        Alert alertaInformacion = new Alert(Alert.AlertType.INFORMATION);
        alertaInformacion.setHeight(300);
        alertaInformacion.setWidth(300);
        alertaInformacion.setTitle(titulo);
        alertaInformacion.setContentText(contenido);
        alertaInformacion.setHeaderText(cabecera);
        alertaInformacion.showAndWait();

        return alertaInformacion;
    }

    public Alert mostrarAlertaWarning(String titulo, String contenido, String cabecera) {
        Alert alertaWarning = new Alert(Alert.AlertType.WARNING);
        alertaWarning.setHeight(300);
        alertaWarning.setWidth(300);
        alertaWarning.setTitle(titulo);
        alertaWarning.setContentText(contenido);
        alertaWarning.setHeaderText(cabecera);
        alertaWarning.showAndWait();

        return alertaWarning;
    }

    public Alert mostrarAlertaConfirmacion(String titulo, String contenido, String cabecera) {
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setHeight(300);
        alertaConfirmacion.setWidth(300);
        alertaConfirmacion.setTitle(titulo);
        alertaConfirmacion.setContentText(contenido);
        alertaConfirmacion.setHeaderText(cabecera);
        alertaConfirmacion.showAndWait();

        return alertaConfirmacion;
    }

    public Alert mostrarAlertaError(String titulo, String contenido, String cabecera) {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setHeight(300);
        alertaError.setWidth(300);
        alertaError.setTitle(titulo);
        alertaError.setContentText(contenido);
        alertaError.setHeaderText(cabecera);
        alertaError.showAndWait();

        return alertaError;
    }

}
