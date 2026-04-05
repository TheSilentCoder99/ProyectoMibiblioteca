package com.alan.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.alan.clases.Alertas;
import com.alan.DataAccesObjects.LibroDAO;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ventanaAddLibroController {
    LibroDAO librodao = new LibroDAO();
    Alertas tipoAlerta = new Alertas();

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextArea inputDescripcion;

    @FXML
    private TextArea inputOpinion;

    @FXML
    private TextField inputPaginas;

    @FXML
    private TextField inputTitulo;

    @FXML
    private TextField inputYearPublicacion;

    @FXML
    private Label lblMensaje;


    public void cerrarVentana() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion));

        if (inputPaginas.getText().isEmpty() && inputYearPublicacion.getText().isEmpty() &&
                inputTitulo.getText().isEmpty()) {
//            OBTENER VENTANA EN LA QUE ESTAS
            Stage stage = (Stage) inputTitulo.getScene().getWindow();
            stage.close();

        } else {
            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
//                    AÑADIR QUE SE BORRE TAMBIÉN EL CONTENIDO DEL TEXTAREA
                }
            }
        }
    }


    public void guardarLibro() {

        try{

//            TOMAR
        String titulo = inputTitulo.getText();
        String yearPublicacion = inputYearPublicacion.getText();
        String paginas = inputPaginas.getText();

//            COMPROBAR
            if(titulo.length() < 2  || paginas.isEmpty() || yearPublicacion.isEmpty()) {
                tipoAlerta.mostrarAlertaWarning("FALTA TITULO, Nº DE PÁGINAS O FECHA DE PUBLICACIÓN","DEBES INGRESAR EL TITULO, Nº DE PÁGINAS Y LA FECHA DE PUBLICACIÓN PARA CONTINUAR CON EL GUARDADO DEL LIBRO","RELLENAR CAMPOS OBLIGATORIOS");
                return;
            }

//            PARSEAR
        int yearPublicacionParseado = Integer.parseInt(yearPublicacion);
        int paginasParseada = Integer.parseInt(paginas);

        String descripcion = inputDescripcion.getText();
        String opinion = inputOpinion.getText();

        if(descripcion.isEmpty()){
            descripcion = " ";}
        if(opinion.isEmpty()){
            opinion = " ";}

            Alert resultadoIngresoLibro = tipoAlerta.alertaConfirmacion("INGRESAR LIBRO","VAS A INGRESAR EL SIGUIENTE LIBRO EN LA BBDD: " + titulo + " ¿estás seguro de continuar?","INGRESAR NUEVO LIBRO");

        if(resultadoIngresoLibro.getResult() == ButtonType.OK){
            librodao.insertarLibro(titulo,yearPublicacionParseado,paginasParseada,descripcion,opinion);
        }

    }catch(NumberFormatException e){
        e.printStackTrace();
            tipoAlerta.mostrarAlertaWarning("HA OCURRIDO UN ERROR","SE HA INGRESADO UN FORMATO ERRÓNEO EN ALGÚN CAMPO. DEBES RELLENAR CADA CAMPO CON EL FORMATO CORRECTO","REVISAR CAMPOS");
    }
}
}

