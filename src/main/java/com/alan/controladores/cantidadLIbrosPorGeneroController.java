package com.alan.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class cantidadLIbrosPorGeneroController {

    @FXML
    TableView listaLibrosPorGenero;

    public void initialize(){


    }

    public void cerrarVentana(){

            Stage estaVentana = (Stage) listaLibrosPorGenero.getScene().getWindow();

            estaVentana.close();
        }
    }
