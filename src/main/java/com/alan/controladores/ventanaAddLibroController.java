package com.alan.controladores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alan.DataAccesObjects.AutorDAO;
import com.alan.DataAccesObjects.AutorLibroDAO;
import com.alan.clases.Alertas;
import com.alan.DataAccesObjects.LibroDAO;
import com.alan.clases.Autor;
import com.alan.DataAccesObjects.AutorDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox comboBoxAutores;

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

    ObservableList<Autor> autoresObservable = FXCollections.observableArrayList();
    AutorDAO autordao = new AutorDAO();
    AutorLibroDAO autorlibrodao = new AutorLibroDAO();
    Autor seleccionado;

    public void initialize(){

        autoresObservable.addAll(autordao.getAllAutores());

        comboBoxAutores.setItems(autoresObservable);

//        IMPLEMENTAR BÚSQUEDA POR LETRA, ES DECIR, QUE AL PULSAR UNA LETRA SALGAN TODOS LOS AUTORES QUE EMPIECEN POR ESA LETRA

        }

    public void cerrarVentanaLibro() {
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion,inputDescripcion,inputOpinion));

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
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
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

        int idLibro = 0;

        if(resultadoIngresoLibro.getResult() == ButtonType.OK){
            idLibro = librodao.insertarLibro(titulo,yearPublicacionParseado,paginasParseada,descripcion,opinion);
        }

//        SECCIÓN PARA ACTUALIZAR TABLA LIBRO AUTOR AL REALIZAR EL GUARDADO DEL LIBRO
            seleccionado = (Autor) comboBoxAutores.getValue();
            autorlibrodao.actualizarTablaLibroAutor(idLibro, seleccionado.getId());

//        LIMPIO LOS CAMPOS TRAS AÑADIR UN LIBRO
        List<Node> ElementosVentana = new ArrayList<>(Arrays.asList(inputTitulo, inputDescripcion, inputYearPublicacion, inputPaginas, inputOpinion,inputDescripcion,inputOpinion));

            for (Node Elemento : ElementosVentana) {
                if (Elemento instanceof TextField tf) {
                    tf.clear();
//
                } else if (Elemento instanceof TextArea ta) {
                    ta.clear();
                }
            }

    }catch(NumberFormatException e){
        e.printStackTrace();
            tipoAlerta.mostrarAlertaError("HA OCURRIDO UN ERROR","SE HA INGRESADO UN FORMATO ERRÓNEO EN ALGÚN CAMPO. DEBES RELLENAR CADA CAMPO CON EL FORMATO CORRECTO","REVISAR CAMPOS");
    }
}
}

